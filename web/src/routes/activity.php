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

         insert($api_key, $start_time, $end_time, $project_name, $os, $files_container);

         $xml->addChild('state', 'success/activity');
      }
   }

   // request method get
   if($_SERVER['REQUEST_METHOD'] == 'GET'){
      $api_key = $_GET['api_key'];
      $project = isset($_GET['project']) ? $_GET['project'] : null;

      $xml->addChild('state', check_request($api_key, $project));
      if($xml->state == "success/get_info") {
         $xml->addChild('name', $project ? $project : $api_key);
         $xml->addChild('time', '');
         $xml->addChild('incremental_percentage', '');
         $project_names_container = $xml->addChild('project_names_container');
         foreach(get_projects($api_key) as $project_name) {
            $project_names_container->addChild('project_name', $project_name);
         }
      }
   }

   header("Content-Type: application/xml; charset=utf-8");
   echo $xml->asXML();
?>
