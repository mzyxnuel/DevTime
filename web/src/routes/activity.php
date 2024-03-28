<?php
   include("../../../.php");
   include(import() . "/web/src/net/xml.php");
   include(import() . "/web/src/func.php");

   $xml = new SimpleXMLElement('<response/>');

   // request method post
   if($_SERVER['REQUEST_METHOD'] == 'POST'){
      $content = req(import() . "/web/src/net/xsd/activity.xsd");

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
      }
   }

   // request method get
   if($_SERVER['REQUEST_METHOD'] == 'GET'){
      $api_key = $_GET['api_key'];
      $project = $_GET['project'];

      $result = get_info($api_key, $project);

      $xml->addChild('api_key', $api_key);
      $xml->addChild('project', $project);
   }

   // xml response
   if($result){
      $xml->addChild('state', 'success/activity');
   }else{
      $xml->addChild('state','error/activity');
   }

   header("Content-Type: application/xml; charset=utf-8");
   echo $xml->asXML();
?>
