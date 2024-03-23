<?php
   include("C:/xampp/htdocs/TimeCode/web/src/net/xml.php");
   include("C:/xampp/htdocs/TimeCode/web/src/func.php");

   $content = req("C:/xampp/htdocs/TimeCode/web/src/net/xsd/signup.xsd");
   $name = $content->name;
   $surname = $content->surname;
   $email = $content->email;
   $password = $content->password;
   $psw = password_hash($password, PASSWORD_BCRYPT);

   try{
      $xml = new SimpleXMLElement('<response/>');

      $id_user = signup($name, $surname, $email, $psw);
      if(isset($id_user)){
         $xml->addChild('state', 'signup/success');
         $xml->addChild('id_user', $id_user);
      }else{
         $xml->addChild('state', 'signup/error');
      }

      header("Content-Type: application/xml; charset=utf-8");
      echo $xml->asXML();
   }catch(Exception $e){
      die("signup error");
   }

?>
