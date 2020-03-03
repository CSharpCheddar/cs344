<?php
  // login to db
  $db = new PDO("mysql:dbname=partymaps;
    host=localhost","ubuntu", "Hydrozoan000");
  // get input
  $username = $db->quote($_GET['username']);
  // send message
  $sql = "UPDATE Messaging SET wasRead=TRUE
          WHERE rid=$username AND wasRead IS FALSE";
  $db->query($sql);
?>
