<?php
  // login to db
  $db = new PDO("mysql:dbname=partymaps;
    host=localhost","ubuntu", "Hydrozoan000");
  // get new credentials
  $user = $db->quote($_GET['user']);
  $pass = $db->quote($_GET['pass']);
  // make sure username doesn't already exist
  $check = "SELECT * FROM Credentials WHERE username=$user";
  $query = $db->query($check)->
    fetchAll(PDO::FETCH_ASSOC);
  if (count($query) > 0) {
    $delete = "DELETE FROM Credentials WHERE username=$user";
    $del = $db->query($delete);
    echo "Account deleted";
  } else {
    echo "Account doesn't exist";
  }
?>
