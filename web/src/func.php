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

    // return [id_user: email exists - null: email doesnt exist]
    function check_user($email){
        $conn = db();
        try{
            $res = $conn->prepare("SELECT id_user FROM users WHERE email = :email");
            $res->bindParam(':email', $email);
            $res->execute();
            $result = $res->fetch(PDO::FETCH_ASSOC);
            return $result ? $result['id_user'] : null;
        }catch(Exception $e){
            die("check_user");
        }
    }

    // return [true: psw is correct - false: psw isnt correct]
    function check_psw($id_user, $password){
        $conn = db();
        try{
            $stmt = $conn->prepare("SELECT psw FROM users WHERE id_user = :id_user");
            $stmt->bindParam(':id_user', $id_user);
            $stmt->execute();
            $result = $stmt->fetch(PDO::FETCH_ASSOC);
            if($result && password_verify($password, $result['psw'])) {
                return true;
            }
        }catch(Exception $e){
            die("check_psw");
        }
        return false;
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

    function check_pr($pr_name){
        $conn = db();
        try{
            $res = $conn->query("SELECT * FROM projects WHERE name = '$pr_name'");
        }catch(Exception $e){
            die("check_pr");
        }
        if(mysqli_num_rows($res) > 0){
            $row = $res->fetch_assoc();
            return $row['id_project'];
        }
        return null;
    }

    function insert($id_user, $unix_start, $unix_end, $pr_name, $files_name, $os){
        $id_project = check_pr($pr_name);
        if($id_project == -1)
            $id_project = project($pr_name);
        $date = date("d-m-Y", $unix_start);
        $start = date("Y-m-d H:i:s", $unix_start);
        $end = date("Y-m-d H:i:s", $unix_end);

        activity($id_user, $id_project, $date, $start, $end);
        activity_language();
    }

    // insert a new project in the database
    function project($pr_name){
        $conn = db();
        try{
            $conn->query("INSERT INTO projects VALUES(NULL, '$pr_name')");
            return $conn->insert_id;
        }catch(Exception $e){ die("project"); }
    }

    // insert a new activity in the database
    function activity($id_user, $id_project, $date, $start, $end){
        $conn = db();
        try{
            $conn->quesry("INSERT INTO activities VALUES(NULL, $date, TIMEDIFF($end, $start), $id_user, $id_project)");
        }catch(Exception $e){ die("activity"); }
    }

    function activity_language($id_activity, $files_name){

    }
?>
