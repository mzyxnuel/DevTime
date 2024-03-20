<?php
include("C:/xampp/htdocs/TimeCode/web/net/xml.php");

$content = req("C:/xampp/htdocs/TimeCode/web/net/xsd/login.xsd");

//esempio
$email = $content->email;
echo $email;

//TODO: risposta in xml
