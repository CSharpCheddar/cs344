<?php
  // login to db
  $db = new PDO("mysql:dbname=partymaps;
    host=localhost","ubuntu", "Hydrozoan000");
  $check = "SELECT * FROM Addresses";
  $query = $db->query($check)->
    fetchAll(PDO::FETCH_ASSOC);
  $printMe = [];
  foreach($query as $row) {
    $printMe[] = $row;
  }
  echo json_encode($printMe);
?>
