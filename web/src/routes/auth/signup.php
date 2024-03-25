<?php
   include("C:/xampp/htdocs/TimeCode/web/src/net/xml.php");
   include("C:/xampp/htdocs/TimeCode/web/src/func.php");

   $content = req("C:/xampp/htdocs/TimeCode/web/src/net/xsd/signup.xsd");
   $xml = new SimpleXMLElement('<response/>');

   if(!$content){
      $xml->addChild('state', 'error/xml');
   }else{
      $name = $content->name;
      $surname = $content->surname;
      $email = $content->email;
      $password = $content->password;
      $psw = password_hash($password, PASSWORD_BCRYPT);

      $id_user = signup($name, $surname, $email, $psw);
      if(isset($id_user)){
         $xml->addChild('state', 'success/signup');
         $xml->addChild('id_user', $id_user);
      }else{
         $xml->addChild('state', 'error/signup');
      }
   }

   header("Content-Type: application/xml; charset=utf-8");
   echo $xml->asXML();
?>
