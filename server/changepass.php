<?php
  // login to db
  $db = new PDO("mysql:dbname=partymaps;
    host=localhost","ubuntu", "Hydrozoan000");
  //get credentials
  $user = $db->quote($_GET['user']);
  $pass = $db->quote($_GET['pass']);
  $newpass = $db->quote($_GET['newpass']);
  // check credentials
  $sql = "SELECT * FROM Credentials WHERE
          username=$user AND password=$pass";
  $result = $db->query($sql)->
    fetchAll(PDO::FETCH_ASSOC);
  // if credentials are good, login, else register
  if (count($result) == 1) {
    $sql2 = "UPDATE Credentials SET password=$newpass
             WHERE username=$user";
    $result2 = $db->query($sql2);
    echo "Password changed";
  } else {
    echo "Invalid credentials";
  }
?>
