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
    echo "Username taken";
  } else {
    // store new credentials
    $sql = "INSERT INTO Credentials
            VALUES ($user, $pass)";
    // if credentials were stored, good, else, bad
    $db->query($sql);
    echo "Registered";
  }
?>
