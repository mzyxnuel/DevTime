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
    function insert($id_user, $start, $end, $pr_name, $os, $files_name){
        $id_project = check_pr($pr_name);
        if(!isset($id_project))
            $id_project = project($pr_name);
        user_project($id_user, $id_project);

        $date = date("d-m-Y", $start);
        $unix_time = $end - $start;
        $h = floor($unix_time / 3600);
        $m = floor(($unix_time % 3600) / 60);
        $s = $unix_time % 60;
        $time = sprintf('%02d:%02d:%02d', $h, $m, $s);

        $id_os = check_os($os);

        activity($id_user, $id_project, $id_os, $date, $time);
        activity_language();
    }

    // return [id_project: project exists - null: project doesnt exist]
    function check_pr($pr_name){
        $conn = db();
        try{
            $query = $conn->prepare("SELECT id_project FROM projects WHERE name = :name");
            $query->bindParam(':name', $pr_name);
            $query->execute();
            $result = $query->fetch(PDO::FETCH_ASSOC);
            return $result ? $result['id_project'] : null;
        }catch(Exception $e){
            die("check_pr");
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

    // return [ ]
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
        }catch(Exception $e){
            die("activity");
        }
    }

    // return [ ]
    function activities_languages($id_activity, $files_name){

    }

    function get_ext($file_name){

    }
?>
