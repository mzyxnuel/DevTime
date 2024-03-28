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
      $project = $_GET['project'];

      if(check_api_key($api_key)){ // verifico se l'api key esiste
         if(isset($project)){ // verifico se la richiesta riguarda uno specifico progetto
            $id_project = check_project($project);
            if(isset($id_project)){ // verifico se il progetto esiste
               if(check_user_project($api_key, $id_project)){
                  $ext_percentage = info_project_ext($project);
                  echo print_r($ext_percentage);
                  // $os_percentage = info_project_os($project);
               }else{
                  $xml->addChild('state', 'error/access_denied'); // l'utente non ha l'accesso al progetto
               }
            }else{
               $xml->addChild('state', 'error/invalid_project_name'); // il nome del progetto è sbagliato
            }
         }else{

         }
      }else{
         $xml->addChild('state', 'error/invalid_api_key'); // l'api key è sbagliata
      }
   }

   header("Content-Type: application/xml; charset=utf-8");
   echo $xml->asXML();
?>
