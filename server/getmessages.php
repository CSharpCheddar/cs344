<?php
  // login to db
  $db = new PDO("mysql:dbname=partymaps;
    host=localhost","ubuntu", "Hydrozoan000");
  $user = $db->quote($_GET['user']);
  $check = "SELECT * FROM Messaging WHERE rid=$user OR sid=$user";
  $query = $db->query($check)->
    fetchAll(PDO::FETCH_ASSOC);
  $printMe = [];
  foreach($query as $row) {
    $printMe[] = $row;
  }
  echo json_encode($printMe);
?>
