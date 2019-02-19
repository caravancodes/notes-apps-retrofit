<?php
header("Content-type:application/json");

require_once('connect.php');

$sql = "SELECT * FROM note";
$query = $conn->query($sql);

$response = array();
while( $row=$query->fetch_assoc() ){
     
    array_push($response,
    array(
        'id'     => $row['id'],
        'title'  => $row['title'],
        'note'   => $row['note'],
        'color'  => $row['color'],
        'date'   => $row['date']
        )
    );
}

echo json_encode($response);




?>