<?php
  // log in to db
  $db = new PDO("mysql:dbname=partymaps;
    host=localhost","ubuntu", "Hydrozoan000");
  // Get hoster info
  $hoster = $db->quote($_GET['hoster']);
  // Check if hosting
  $check = "SELECT * FROM Addresses WHERE hoster=$hoster";
  $query = $db->query($check)->
    fetchAll(PDO::FETCH_ASSOC);
  // If hosting, del address, else error
  if (count($query) > 0) {
    $delete = "DELETE FROM Addresses WHERE hoster=$hoster";
    $del = $db->query($delete);
    echo "Address deleted";
  } else {
    echo "Address doesn't exist";
  }
?>
