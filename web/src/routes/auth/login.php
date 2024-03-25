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

      $id_user = login($email, $password);
      if(isset($id_user)){
         $xml->addChild('state', 'success/login');
         $xml->addChild('id_user', $id_user);
      }else{
         $xml->addChild('state', 'error/login');
      }
   }

   header("Content-Type: application/xml; charset=utf-8");
   echo $xml->asXML();
?>
