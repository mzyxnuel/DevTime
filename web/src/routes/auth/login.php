<?php
   include("C:/xampp/htdocs/TimeCode/web/src/net/xml.php");
   include("C:/xampp/htdocs/TimeCode/web/src/func.php");

   $content = req("C:/xampp/htdocs/TimeCode/web/src/net/xsd/login.xsd");
   $xml = new SimpleXMLElement('<response/>');

   if(!$content){
      $xml->addChild('state', 'error/xml');
   }else{
      $email = $content->email;
      $password = $content->password;

      $api_key = login($email, $password);
      if(isset($api_key)){
         $xml->addChild('state', 'success/login');
         $xml->addChild('api_key', $api_key);
      }else{
         $xml->addChild('state', 'error/login');
      }
   }

   header("Content-Type: application/xml; charset=utf-8");
   echo $xml->asXML();
?>
