<?php
  // login to db
  $db = new PDO("mysql:dbname=partymaps;
    host=localhost","ubuntu", "Hydrozoan000");
  // get input
  $sender= $db->quote($_GET['sender']);
  $receiver = $db->quote($_GET['receiver']);
  $message = $db->quote($_GET['message']);
  // send message
  $check = "SELECT * FROM Credentials WHERE username=$receiver";
  $query = $db->query($check)->
    fetchAll(PDO::FETCH_ASSOC);
  if (count($query) > 0) {
    $sql = "INSERT INTO Messaging VALUES
            (uuid(), $sender, $receiver, $message, DEFAULT, DEFAULT)";
    $db->query($sql);
    echo "Message sent";
  } else {
    echo "User does not exist";
  }

?>
