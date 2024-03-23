<?php
   include("C:/xampp/htdocs/TimeCode/web/src/net/xml.php"); //TODO se riesci a trovare una soluzione usando gli import relativi

   function createXML($id_user){
      $filePath = 'login_response.xml'
      $dom = new DOMDocument('1.0', 'utf-8');

      $root = $dom->createElement('user');
      $id = $dom->createElement('id_user', $id_user);

      $root->appendChild($id);
      $dom->appendChild($root);
      $dom->save($filePath);
   }

   $user = "root";
   $password = "";
   $db = "timecode";
   $host = "localhost";
   $port = 3306;
   $conn = new PDO("mysql:host=$host; dbname=$db; port=$port", $user, $password);

   $content = req("C:\Users\MicheleTosato\Desktop\SCUOLA\QUINTA\TimeCode\web\src\net\xsd\login.xsd");
   // $content = req("C:/xampp/htdocs/TimeCode/web/src/net/xsd/login.xsd");
   $email = $content->email;
   $password = $content->password;
   $psw = password_hash($password, PASSWORD_BCRYPT);

   echo "email: $email<br>";
   echo "password: $password<br>";
   echo "psw: $psw<br>";

   //response
   $xmlResponse = new DOMDocument();
   $user = $xmlResponse->createElementNS()

   try{
      $res = $conn->query("SELECT * FROM users");



      $xmlResponse->addChild()
   }catch(EXception $e){
      $
   }

   $xml = "<email>" . $email . "</email>";
   res($xml);



?>
