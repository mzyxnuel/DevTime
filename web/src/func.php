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

    // return [id_user: user exists - null: user doesnt exist]
    function check_user($email){
        $conn = db();
        try{
            $query = $conn->prepare("SELECT id_user FROM users WHERE email = :email");
            $query->bindParam(':email', $email);
            $query->execute();
            $result = $query->fetch(PDO::FETCH_ASSOC);
            return $result ? $result['id_user'] : null;
        }catch(Exception $e){
            die("check_user");
        }
    }

    // return [true: psw is correct - false: psw isnt correct]
    function check_psw($id_user, $password){
        $conn = db();
        try{
            $query = $conn->prepare("SELECT psw FROM users WHERE id_user = :id_user");
            $query->bindParam(':id_user', $id_user);
            $query->execute();
            $result = $query->fetch(PDO::FETCH_ASSOC);
            return $result && password_verify($password, $result['psw']) ? true : false;
        }catch(Exception $e){
            die("check_psw");
        }
    }

    // return [id_user: user exists - null: user doesnt exist]
    function login($email, $password){
        $id_user = check_user($email);
        if(isset($id_user)){
            if(!check_psw($id_user, $password))
                return null;
        }
        return $id_user;
    }

    // return [id_user: new user - null: user already registered]
    function signup($name, $surname, $email, $psw){
        $conn = db();
        $id_user = check_user($email);

        if(!isset($id_user)){
            try{
                $query = $conn->prepare("INSERT INTO users VALUES(NULL, :name, :surname, :email, :psw, NOW())");
                $query->bindParam(':name', $name);
                $query->bindParam(':surname', $surname);
                $query->bindParam(':email', $email);
                $query->bindParam(':psw', $psw);
                $query->execute();

                return $conn->lastInsertId();
            }catch(Exception $e){
                die("signup");
            }
        }
        return null;
    }

    // return [ ]
    function insert($id_user, $start, $end, $pr_name, $os, $files_container){
        // date and time
        $date = date("d-m-Y", $start);
        $unix_time = $end - $start;
        $h = floor($unix_time / 3600);
        $m = floor(($unix_time % 3600) / 60);
        $s = $unix_time % 60;
        $time = sprintf('%02d:%02d:%02d', $h, $m, $s);

        // os
        $id_os = check_os($os);

        // project
        $id_project = check_project($pr_name);
        if(!isset($id_project))
            $id_project = project($pr_name);
        user_project($id_user, $id_project);

        // activity
        $id_activity = check_activity($date);
        if(isset($id_activity))
            update_activity($id_activity, $time);
        else
            $id_activity = activity($id_user, $id_project, $id_os, $date, $time);

        // languages
        $modify_rows_ext = modify_rows_ext($id_project, $files_container);
        activity_languages($id_activity, $modify_rows_ext);
        project_languages($id_project, $modify_rows_ext);
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
            die("check_project");
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
            die("project");
        }
    }

    // return [ ]
    function user_project($id_user, $id_project){
        $conn = db();
        try{
            $query = $conn->prepare("INSERT IGNORE INTO users_projects VALUES(:id_user, :id_project)");
            $query->bindParam(':id_user', $id_user);
            $query->bindParam(':id_project', $id_project);
            $query->execute();
        }catch(Exception $e){
            die("user_project");
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
            die("check_os");
        }
    }

    // return [id_activity: activity already exists - null: activity doesnt exist]
    function check_activity($date){
        $conn = db();
        try{
            $query = $conn->prepare("SELECT id_activity FROM activities WHERE date = :date");
            $query->bindParam(':date', $date);
            $query->execute();
            $result = $query->fetch(PDO::FETCH_ASSOC);
            return $result ? $result['id_activity'] : null;
        }catch(Exception $e){
            die("check_activity");
        }
    }

    // return [ ]
    function update_activity($id_activity, $time){
        $conn = db();
        try{
            $query = $conn->prepare("UPDATE activities SET time = time + :time WHERE id_activity = :id_activity");
            $query->bindParam(':id_activity', $id_activity);
            $query->bindParam(':time', $time);
            $query->execute();
        }catch(Exception $e){
            die("activity");
        }
    }

    // return [id_activity]
    function activity($id_user, $id_project, $id_os, $date, $time){
        $conn = db();
        try{
            $query = $conn->prepare("INSERT INTO projects VALUES(NULL, :date, :time, :id_user, :id_project, :id_os)");
            $query->bindParam(':date', $date);
            $query->bindParam(':time', $time);
            $query->bindParam(':id_user', $id_user);
            $query->bindParam(':id_project', $id_project);
            $query->bindParam(':id_os', $id_os);
            $query->execute();
            return $conn->lastInsertId();
        }catch(Exception $e){
            die("activity");
        }
    }

    // return [rows per extension associative array after new activity]
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

    // return [rows per extension associative array before new activity]
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
            die("old_rows_ext");
        }
    }

    // return [modify rows per extension associative array]
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
    function activity_languages($id_activity, $modify_rows_ext){
        try{
            $conn = db();
            foreach ($modify_rows_ext as $ext => $modify_rows) {
                $query = $conn->prepare("INSERT INTO activities_languages VALUES (:id_project, :ext, :modify_rows)
                                         ON DUPLICATE KEY UPDATE modify_rows = modify_rows + :modify_rows");
                $query->bindParam(':id_project', $id_project);
                $query->bindParam(':ext', $ext);
                $query->bindParam(':modify_rows', $modify_rows);
                $query->execute();
            }
            $query = $conn->prepare("DELETE FROM activities_languages WHERE modify_rows = 0");
            $query->execute();
        }catch(Exception $e){
            die("activity_languages");
        }
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
            die("project_languages");
        }
    }
?>
