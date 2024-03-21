<?php
function db() {
    return new PDO("mysql:host=localhost;dbname=timecode", "root", "");
}

?>
