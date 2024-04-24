<html>
<body>
  <?php
     echo "Name : ".$_POST["name"] ?> <br>
  <?php
     echo "password : ".$_POST["password"]    
  ?><br>
  
  <?php
    $servername = "localhost";
    $username = "root";
    $password = "";
    $db = "userAuth";

    // Create connection
    $conn = mysqli_connect($servername, $username, $password,$db);
  ?>

  <?php
     $sql_create_table = "CREATE TABLE IF NOT EXISTS user(
                          username varchar(25) primary key,
                          password varchar(25))";
     mysqli_query($conn,$sql_create_table); 
  ?>
  
  <?php
    $sql_entry = "INSERT INTO user VALUES ('".$_POST["name"]."','".$_POST["password"]."')";
    //echo $sql_entry; 
    mysqli_query($conn,$sql_entry); 
  ?>
</body>
</html>