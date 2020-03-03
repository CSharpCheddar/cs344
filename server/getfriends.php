<?php
  // login to db
  $db = new PDO("mysql:dbname=partymaps;
    host=localhost","ubuntu", "Hydrozoan000");
  $user = $db->quote($_GET['user']);
  $check = "SELECT * FROM Friends WHERE user1=$user";
  $query = $db->query($check)->
    fetchAll(PDO::FETCH_ASSOC);
  $check2 = "SELECT * FROM Friends WHERE user2=$user";
  $query2 = $db->query($check2)->
    fetchAll(PDO::FETCH_ASSOC);
  $printMe = [];
  foreach($query as $row) {
    $printMe[] = $row;
  }
  foreach($query2 as $row) {
    $printMe[] = $row;
  }
  echo json_encode($printMe);
?>
