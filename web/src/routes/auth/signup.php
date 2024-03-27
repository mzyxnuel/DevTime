<?php
   include("../../../../.php");
   include(import() . "/web/src/net/xml.php");
   include(import() . "/web/src/func.php");

   $content = req(import() . "/web/src/net/xsd/signup.xsd");
   $xml = new SimpleXMLElement('<response/>');

   if(!$content){
      $xml->addChild('state', 'error/xml');
   }else{
      $name = $content->name;
      $surname = $content->surname;
      $email = $content->email;
      $password = $content->password;
      $psw = password_hash($password, PASSWORD_BCRYPT);

      $api_key = signup($name, $surname, $email, $psw);
      if(isset($api_key)){
         $xml->addChild('state', 'success/signup');
         $xml->addChild('api_key', $api_key);
      }else{
         $xml->addChild('state', 'error/signup');
      }
   }

   header("Content-Type: application/xml; charset=utf-8");
   echo $xml->asXML();
?>
