<?php

$severname = "localhost";
$user = "root";
$pass = "";
$db = "notes";
$conn = new mysqli("$severname","$user","$pass","$db");
if (!$conn) {
    echo 'Error';
}


?>