<?php
include("C:/xampp/htdocs/TimeCode/web/net/xml.php"); //TODO se riesci a trovare una soluzione usando gli import relativi

$content = req("C:/xampp/htdocs/TimeCode/web/net/xsd/login.xsd");

//esempio
$email = $content->email;
echo $email;

//TODO: risposta in xml
