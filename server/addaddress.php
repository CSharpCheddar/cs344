<?php
  // login to db
  $db = new PDO("mysql:dbname=partymaps;
    host=localhost","ubuntu", "Hydrozoan000");
  // get hoster info
  $hoster = $db->quote($_GET['hoster']);
  $handle = $db->quote($_GET['handle']);
  $address = $db->quote($_GET['address']);
  $address = str_replace("zzzz", " ", $address);
  // make sure hoster isn't already hosting
  $check = "SELECT * FROM Addresses WHERE hoster=$hoster";
  $query = $db->query($check)->
    fetchAll(PDO::FETCH_ASSOC);
  if (count($query) == 0) {
    // store new address if not already hosting
    $sql = "INSERT INTO Addresses
            VALUES ($hoster, $handle, $address)";
    $db->query($sql);
    echo "Address added";
  } else {
    echo "Already hosting";
  }
?>
