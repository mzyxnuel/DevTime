<?php
   include("C:/xampp/htdocs/TimeCode/web/src/net/xml.php"); //TODO se riesci a trovare una soluzione usando gli import relativi

   $user = "root";
   $password = "";
   $db = "timecode";
   $host = "localhost";
   $port = 3306;
   $conn = new PDO("mysql:host=$host; dbname=$db; port=$port", $user, $password);

   $content = req("C:/xampp/htdocs/TimeCode/web/src/net/xsd/login.xsd");
   $email = $content->email;
   $password = $content->password;

   try{
      $xml = new SimpleXMLElement('<response/>');
      $xml->addChild('request', 'login');

      $id_user = null;
      $users = $conn->query("SELECT * FROM users");

      foreach($users as $row){
         if($row['email'] == $email && password_verify($password, $row['psw']))
            $id_user = $row['id_user'];
      }

      if(isset($id_user)){
         $xml->addChild('success', 'true');
         $xml->addChild('id_user', $id_user);
      }else{
         $xml->addChild('success', 'false');
      }

      header("Content-Type: application/xml; charset=utf-8");
      echo $xml->asXML();
   }catch(Exception $e){
      die("users error");
   }

?>
