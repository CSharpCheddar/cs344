<?php
  // login to db
  $db = new PDO("mysql:dbname=partymaps;
    host=localhost","ubuntu", "Hydrozoan000");
  // get new friends
  $user1 = $db->quote($_GET['user1']);
  $user2 = $db->quote($_GET['user2']);
  // make sure friendship doesn't already exist
  $check = "SELECT * FROM Friends WHERE user1=$user1 AND user2=$user2";
  $query = $db->query($check)->
    fetchAll(PDO::FETCH_ASSOC);
  $check2 = "SELECT * FROM Friends WHERE user1=$user2 AND user2=$user1";
  $query2 = $db->query($check2)->
    fetchAll(PDO::FETCH_ASSOC);
  $check3 = "SELECT * FROM Credentials WHERE username=$user2";
  $query3 = $db->query($check3)->
    fetchAll(PDO::FETCH_ASSOC);
  if (count($query) == 0 && count($query2) == 0) {
    // store new friendship
    if (count($query3) > 0) {
      $sql = "INSERT INTO Friends
              VALUES (uuid(), $user1, $user2)";
      $db->query($sql);
      echo "Friends";
    } else {
      echo "User doesn't exist";
    }
  } else {
    echo "Already friends";
  }
?>
