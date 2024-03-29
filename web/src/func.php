<?php
    // database connection
    function db() {
        $user = "root";
        $password = "";
        $db = "timecode";
        $host = "localhost";
        $port = 3306;

        return new PDO("mysql:host=$host; dbname=$db; port=$port", $user, $password);
    }

    // return [api_key: user exists - null: user doesnt exist]
    function check_user($email){
        $conn = db();
        try{
            $query = $conn->prepare("SELECT api_key FROM users WHERE email = :email");
            $query->bindParam(':email', $email);
            $query->execute();
            $result = $query->fetch(PDO::FETCH_ASSOC);
            return $result ? $result['api_key'] : null;
        }catch(Exception $e){
            die("check_user: " . $e->getMessage());
        }
    }

    // return [true: psw is correct - false: psw isnt correct]
    function check_psw($api_key, $password){
        $conn = db();
        try{
            $query = $conn->prepare("SELECT psw FROM users WHERE api_key = :api_key");
            $query->bindParam(':api_key', $api_key);
            $query->execute();
            $result = $query->fetch(PDO::FETCH_ASSOC);
            return $result && password_verify($password, $result['psw']) ? true : false;
        }catch(Exception $e){
            die("check_psw: " . $e->getMessage());
        }
    }

    // return [generated api key]
    function api_key($length) {
        $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
        $random = '';
        for ($i = 0; $i < $length; $i++) {
            $random .= $characters[rand(0, strlen($characters) - 1)];
        }
        return $random;
    }

    // return [api_key: user exists - null: user doesnt exist]
    function login($email, $password){
        $api_key = check_user($email);
        if(isset($api_key)){
            if(!check_psw($api_key, $password))
                return null;
        }
        return $api_key;
    }

    // return [api_key: new user - null: user already registered]
    function signup($name, $surname, $email, $psw){
        $conn = db();
        $api_key = check_user($email);
        if(!isset($api_key)){
            $api_key = api_key(20);
            try{
                $query = $conn->prepare("INSERT INTO users VALUES(:api_key, :name, :surname, :email, :psw, NOW())");
                $query->bindParam(':api_key', $api_key);
                $query->bindParam(':name', $name);
                $query->bindParam(':surname', $surname);
                $query->bindParam(':email', $email);
                $query->bindParam(':psw', $psw);
                $query->execute();
                return $api_key;
            }catch(Exception $e){
                die("signup: " . $e->getMessage());
            }
        }
        return null;
    }

    // return [ ]
    function insert($api_key, $start, $end, $pr_name, $os, $files_container){
        $temp = $start;
        $date = date("Y-m-d", $temp = time());
        $time = $end - $start;
        $id_os = check_os($os);
        $id_project = check_project($pr_name);
        if(!isset($id_project))
            $id_project = project($pr_name);
        $id_activity = activity($api_key, $id_project, $id_os, $date, $time);
        $modify_rows_ext = modify_rows_ext($id_project, $files_container);

        user_project($api_key, $id_project);
        project_languages($id_project, $modify_rows_ext);
        activity_languages($id_activity, $modify_rows_ext);
    }

    // return [id_project: project exists - null: project doesnt exist]
    function check_project($pr_name){
        $conn = db();
        try{
            $query = $conn->prepare("SELECT id_project FROM projects WHERE name = :name");
            $query->bindParam(':name', $pr_name);
            $query->execute();
            $result = $query->fetch(PDO::FETCH_ASSOC);
            return $result ? $result['id_project'] : null;
        }catch(Exception $e){
            die("check_project: " . $e->getMessage());
        }
    }

    // return [id_project]
    function project($name){
        $conn = db();
        try{
            $query = $conn->prepare("INSERT INTO projects VALUES(NULL, :name)");
            $query->bindParam(':name', $name);
            $query->execute();
            return $conn->lastInsertId();
        }catch(Exception $e){
            die("project: " . $e->getMessage());
        }
    }

    // return [ ]
    function user_project($api_key, $id_project){
        $conn = db();
        try{
            $query = $conn->prepare("INSERT IGNORE INTO users_projects VALUES(:api_key, :id_project)");
            $query->bindParam(':api_key', $api_key);
            $query->bindParam(':id_project', $id_project);
            $query->execute();
        }catch(Exception $e){
            die("user_project: " . $e->getMessage());
        }
    }

    // return [id_os: os known - null: os doesnt known]
    function check_os($name){
        $conn = db();
        try{
            $query = $conn->prepare("SELECT id_os FROM oss WHERE name = :name");
            $query->bindParam(':name', $name);
            $query->execute();
            $result = $query->fetch(PDO::FETCH_ASSOC);
            return $result ? $result['id_os'] : null;
        }catch(Exception $e){
            die("check_os: " . $e->getMessage());
        }
    }

    // return [id_activity]
    function activity($api_key, $id_project, $id_os, $date, $time){
        $conn = db();
        try{
            $query = $conn->prepare("INSERT INTO activities VALUES(NULL, :date, :time, :api_key, :id_project, :id_os)");
            $query->bindParam(':date', $date);
            $query->bindParam(':time', $time);
            $query->bindParam(':api_key', $api_key);
            $query->bindParam(':id_project', $id_project);
            $query->bindParam(':id_os', $id_os);
            $query->execute();
            return $conn->lastInsertId();
        }catch(Exception $e){
            die("activity: " . $e->getMessage());
        }
    }

    // return [rows per extension after new activity - associative array]
    function new_rows_ext($files_container){
        $rows_ext = array();
        foreach ($files_container->file_container as $file) {
            $ext = pathinfo($file->file_name, PATHINFO_EXTENSION);
            $num_rows = $file->rows_count;
            if(array_key_exists($ext, $rows_ext)) {
                $rows_ext[$ext] += $num_rows;
            }else {
                $rows_ext[$ext] = $num_rows;
            }
        }
        return $rows_ext;
    }

    // return [rows per extension before new activity - associative array]
    function old_rows_ext($id_project){
        $conn = db();
        try{
            $query = $conn->prepare("SELECT ext, num_rows FROM projects_languages WHERE id_project = :id_project");
            $query->bindParam(':id_project', $id_project);
            $query->execute();
            $rows_ext = array();
            while ($row = $query->fetch(PDO::FETCH_ASSOC)) {
                $rows_ext[$row['ext']] = $row['num_rows'];
            }
            return $rows_ext;
        } catch(Exception $e){
            die("old_rows_ext: " . $e->getMessage());
        }
    }

    // return [modify rows per extension - associative array]
    function modify_rows_ext($id_project, $files_container){
        $new_rows_ext = new_rows_ext($files_container);
        $old_rows_ext = old_rows_ext($id_project);

        $ext_rows = array_merge(array_keys($new_rows_ext), array_keys($old_rows_ext));
        $modify_rows = array();

        foreach ($ext_rows as $ext) {
            $new_rows = isset($new_rows_ext[$ext]) ? $new_rows_ext[$ext] : 0;
            $old_rows = isset($old_rows_ext[$ext]) ? $old_rows_ext[$ext] : 0;
            $modified_rows[$ext] = $new_rows - $old_rows;
        }
        return $modified_rows;
    }

    // return [ ]
    function project_languages($id_project, $modify_rows_ext){
        try {
            $conn = db();
            foreach ($modify_rows_ext as $ext => $modify_rows) {
                $query = $conn->prepare("INSERT INTO projects_languages VALUES (:id_project, :ext, :modify_rows)
                                         ON DUPLICATE KEY UPDATE num_rows = num_rows + :modify_rows");
                $query->bindParam(':id_project', $id_project);
                $query->bindParam(':ext', $ext);
                $query->bindParam(':modify_rows', $modify_rows);
                $query->execute();
            }
            $query = $conn->prepare("DELETE FROM projects_languages WHERE num_rows = 0");
            $query->execute();
        } catch(Exception $e) {
            die("project_languages: " . $e->getMessage());
        }
    }

    // return [ ]
    function activity_languages($id_activity, $modify_rows_ext){
        try{
            $conn = db(); //TODO controlla l'estensione se è presente in ext, altrimenti ignora il file, (magari anche il numero di righe)
            foreach ($modify_rows_ext as $ext => $modify_rows) {
                if($modify_rows != 0){
                    $query = $conn->prepare("INSERT INTO activities_languages VALUES (:id_activity, :ext, :modify_rows)");
                    $query->bindParam(':id_activity', $id_activity);
                    $query->bindParam(':ext', $ext);
                    $query->bindParam(':modify_rows', $modify_rows);
                    $query->execute();
                }
            }
        }catch(Exception $e){
            die("activity_languages " . $e->getMessage());
        }
    }

    // return [true: api key is correct - false: api key isnt correct]
    function check_api_key($api_key){
        $conn = db();
        try{
            $query = $conn->prepare("SELECT * FROM users WHERE api_key = :api_key");
            $query->bindParam(':api_key', $api_key);
            $query->execute();
            $result = $query->fetch(PDO::FETCH_ASSOC);
            return $result !== false;
        }catch(Exception $e){
            die("check_api_key: " . $e->getMessage());
        }
    }

    // return [true: api key and project match - false: api key and project doesnt match]
    function check_user_project($api_key, $id_project){
        $conn = db();
        try{
            $query = $conn->prepare("SELECT * FROM users_projects WHERE api_key = :api_key AND id_project = :id_project");
            $query->bindParam(':api_key', $api_key);
            $query->bindParam('id_project', $id_project);
            $query->execute();
            $result = $query->fetch(PDO::FETCH_ASSOC);
            return $result !== false;
        }catch(Exception $e){
            die("check_user_project: " . $e->getMessage());
        }
    }

    // return [state of the request]
    function check_request($api_key, $project){
        if(check_api_key($api_key)){
            if(isset($project)){
                $id_project = check_project($project);
                if(isset($id_project)){
                    if(check_user_project($api_key, $id_project)){
                        return "success/get_info";
                    }else{
                        return "error/access_denied";
                    }
                }else{
                    return "error/invalid_project_name";
                }
            }else{
                return "success/get_info";
            }
        }else{
            return "error/invalid_api_key";
        }
    }

    // return [project names associated with api key]
    function get_projects($api_key){
        $conn = db();
        try {
            $query = $conn->prepare("SELECT PR.name
                                    FROM users_projects AS UP
                                    INNER JOIN projects AS PR USING(id_project)
                                    WHERE UP.api_key = :api_key");
            $query->bindParam(':api_key', $api_key);
            $query->execute();
            $result = $query->fetchAll(PDO::FETCH_COLUMN);
            return $result;
        } catch(PDOException $e) {
            die("get_projects: " . $e->getMessage());
        }
    }

    // return [time taken]
    function get_time($api_key, $id_project){
        $conn = db();
        try{
            $query_content = "SELECT SUM(time) AS sum_time FROM activities WHERE api_key = :api_key";
            if(isset($id_project)) $query_content .= " AND id_project = :id_project";
            $query = $conn->prepare($query_content);
            $query->bindParam(':api_key', $api_key);
            if(isset($id_project)) $query->bindParam(':id_project', $id_project);
            $query->execute();
            $result = $query->fetch(PDO::FETCH_ASSOC);
            return $result ? $result['sum_time'] : null;
        } catch(Exception $e){
            die("get_time: " . $e->getMessage());
        }
    }

    function incremental_percentage($api_key, $id_project){
        // non ho voglia :)
    }

    // return [ TODO ]
    function percentage_languages($api_key, $id_project){
        $conn = db();
        try{
            $query = $conn->prepare("");
            $query->bindParam(':id_project', $id_project);
            $query->execute();
            $percentage_ext = array();
            while ($row = $query->fetch(PDO::FETCH_ASSOC)) {
                $percentage_ext[$row['ext']] = $row['percentage'];
            }
            return $percentage_ext;
        } catch(Exception $e){
            die("info_project: " . $e->getMessage());
        }
    }

    // return [ TODO ]
    function info_project_os($project){
        $conn = db();
        try{
            $query = $conn->prepare("");
            $query->bindParam(':id_project', $id_project);
            $query->execute();
            $result = $query->fetchAll(PDO::FETCH_ASSOC);
            return $result;
        } catch(Exception $e){
            die("info_project: " . $e->getMessage());
        }
    }
?>
