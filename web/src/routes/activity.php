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
      $api_key = isset($_GET['api_key']) ? $_GET['api_key'] : null;
      $project = isset($_GET['project']) ? $_GET['project'] : null;
      $id_project = isset($project) ? check_project($project) : null;

      $xml->addChild('state', check_request($api_key, $project));
      if($xml->state == "success/get_info") {
         $xml->addChild('name', $project ? $project : $api_key);
         $xml->addChild('time', get_time($api_key, $id_project));
         $xml->addChild('incremental_percentage', incremental_percentage($api_key, $id_project));

         $project_names_container = $xml->addChild('project_names_container');
         foreach(get_projects($api_key) as $project_name) {
            $project_container = $project_names_container->addChild('project_container');
            $project_container->addChild('project_name', $project_name);
         }

         $dates_container = $xml->addChild("dates_container"); // dates
         // for($i = 0; $i<31; $i++){
         //    $date_container = $dates_container->addChild('date_container');
         //    $date_container->addChild('date', '');
         //    $project_container = $date_container->addChild('project_container');
         //    $project_container->addChild('time', '');
         //    $project_container->addChild('name', '');
         // }

         $oss_container = $xml->addChild('oss_container');
         foreach(percentage_oss($api_key, $id_project) as $os_name => $percentage){
            $os_container = $oss_container->addChild('os_container');
            $os_container->addChild('os_name', $os_name);
            $os_container->addChild('percentage', $percentage);
         }

         $languages_container = $xml->addChild('languages_container');
         foreach(percentage_languages($api_key, $id_project) as $language_name => $percentage){
            $language_container = $languages_container->addChild('language_container');
            $language_container->addChild('language_name', $language_name);
            $language_container->addChild('percentage', $percentage);
         }
      }
   }

   header("Content-Type: application/xml; charset=utf-8");
   echo $xml->asXML();
?>
