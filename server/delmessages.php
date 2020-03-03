<?php
  // login to db
  $db = new PDO("mysql:dbname=partymaps;
    host=localhost","ubuntu", "Hydrozoan000");
  // get input
  $user1 = $db->quote($_GET['user1']);
  $user2 = $db->quote($_GET['user2']);
  // send message
  $check = "SELECT * FROM Messaging WHERE sid=$user1 AND rid=$user2";
  $query = $db->query($check)->
    fetchAll(PDO::FETCH_ASSOC);
  $check2 = "SELECT * FROM Messaging WHERE sid=$user2 AND rid=$user1";
  $query2 = $db->query($check2)->
    fetchAll(PDO::FETCH_ASSOC);
  if (count($query) > 0) {
    $sql = "DELETE FROM Messaging WHERE sid=$user1 AND rid=$user2";
    $db->query($sql);
  }
  if (count($query2) > 0) {
    $sql = "DELETE FROM Messaging WHERE sid=$user2 AND rid=$user1";
    $db->query($sql);
  }
  if (count($query) == 0 && count($query2) == 0) {
    echo "No messages deleted";
  } else {
    echo "Messages deleted";
  }

?>
