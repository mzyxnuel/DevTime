<?php
include("C:/xampp/htdocs/TimeCode/web/src/net/xml.php"); //TODO se riesci a trovare una soluzione usando gli import relativi

$content = req("C:/xampp/htdocs/TimeCode/web/src/net/xsd/login.xsd");

$email = $content->email;

//esempio risposta
$xml = "<email>" . $email . "</email>";
res($xml);
