<?php
   include("C:/xampp/htdocs/TimeCode/web/src/net/xml.php");
   include("C:/xampp/htdocs/TimeCode/web/src/func.php");

   $content = req("C:/xampp/htdocs/TimeCode/web/src/net/xsd/activity.xsd");
   $xml = new SimpleXMLElement('<response/>');

   if(!$content){
      $xml->addChild('state', 'error/xml');
   }else{
      $api_key = $content->api_key;
      $start_time = $content->start_time;
      $end_time = $content->end_time;
      $project_name = $content->project_name;
      $os = $content->os;
      $files_container = $content->files_container;

      $result = insert($api_key, $start_time, $end_time,$project_name, $os, $files_container);
      if($result){
         $xml->addChild('state', 'success/activity');
      }else{
         $xml->addChild('state','error/activity');
      }
   }

   header("Content-Type: application/xml; charset=utf-8");
   echo $xml->asXML();
?>
