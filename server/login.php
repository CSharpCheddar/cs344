<?php
  // login to db
  $db = new PDO("mysql:dbname=partymaps;
    host=localhost","ubuntu", "Hydrozoan000");
  //get credentials
  $user = $db->quote($_GET['user']);
  $pass = $db->quote($_GET['pass']);
  // check credentials
  $sql = "SELECT * FROM Credentials WHERE
          username=$user AND password=$pass";
  $result = $db->query($sql)->
    fetchAll(PDO::FETCH_ASSOC);
  // if credentials are good, login, else register
  if (count($result) == 1) {
    echo "Logged in";
  } else {
    echo "Invalid credentials";
  }
?>
