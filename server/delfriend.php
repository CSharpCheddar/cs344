<?php
  // login to db
  $db = new PDO("mysql:dbname=partymaps;
    host=localhost","ubuntu", "Hydrozoan000");
  // get friends
  $user1 = $db->quote($_GET['user1']);
  $user2 = $db->quote($_GET['user2']);
  // find friendship in table
  $check = "SELECT * FROM Friends WHERE user1=$user1 AND user2=$user2";
  $query = $db->query($check)->
    fetchAll(PDO::FETCH_ASSOC);
  $check2 = "SELECT * FROM Friends WHERE user1=$user2 AND user2=$user1";
  $query2 = $db->query($check2)->
    fetchAll(PDO::FETCH_ASSOC);
  // if it exists, delete it, else doesn't exist
  if (count($query) > 0) {
    $delete = "DELETE FROM Friends WHERE user1=$user1 AND user2=$user2";
    $del = $db->query($delete);
    echo "Friendship deleted";
  } else if (count($query2) > 0) {
    $delete2 = "DELETE FROM Friends WHERE user1=$user2 AND user2=$user1";
    $del2 = $db->query($delete2);
    echo "Friendship deleted";
  } else {
    echo "Friendship doesn't exist";
  }
?>
