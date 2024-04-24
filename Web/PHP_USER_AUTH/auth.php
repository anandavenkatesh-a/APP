
<?php
    $servername = "localhost";
    $username = "root";
    $password = "";
    $db = "userAuth";

    // Create connection
    $conn = mysqli_connect($servername, $username, $password,$db);
?>
  
<?php
    $sql = "select * from user where username = '".$_POST["username"]."' and password = '".$_POST["password"]."'";
    //echo $sql;
    $res = mysqli_query($conn,$sql);
    if($res -> num_rows > 0){
        session_start();
        $_SESSION["username"] = $_POST["username"];
        header("Location: http://localhost/user_Auth/welcome.php");
        exit;
    } 
    else{
        echo "unsuccesful logged in";    
    }
?>