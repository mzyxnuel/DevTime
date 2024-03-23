<?php
   include("C:/xampp/htdocs/TimeCode/web/src/net/xml.php");
   include("C:/xampp/htdocs/TimeCode/web/src/func.php");

   $user = "root";
   $password = "";
   $db = "timecode";
   $host = "localhost";
   $port = 3306;
   $conn = new PDO("mysql:host=$host; dbname=$db; port=$port", $user, $password);

   $content = req("C:/xampp/htdocs/TimeCode/web/src/net/xsd/signup.xsd");
   $name = $content->name;
   $surname = $content->surname;
   $email = $content->email;
   $password = $content->password;
   $psw = password_hash($password, PASSWORD_BCRYPT);

   try{
      $xml = new SimpleXMLElement('<response/>');
      $xml->addChild('request', 'signup');

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

      $conn->query("INSERT INTO users VALUES(NULL, $name, $surname, $email, $psw)");

      if(isset($id_user)){
         $xml->addChild('success', 'false');
      }else{
         $xml->addChild('success', 'false');
      }

      header("Content-Type: application/xml; charset=utf-8");
      echo $xml->asXML();
   }catch(Exception $e){
      die("users error");
   }

?>
