<?php
   function req($xsd) {
      error_reporting(0);

      $request = file_get_contents("php://input");

      $xml = new DOMDocument();
      $xml->loadXML($request);

      try {
         if($xml->schemaValidate($xsd))
            return simplexml_load_string($request);
      } catch (Exception $e) {
         return false;
      }
   }
?>
