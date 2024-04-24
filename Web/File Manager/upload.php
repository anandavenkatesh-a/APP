<?php
   $servername = "localhost";
   $user = "root";
   $password = "";
   $db = "file_manager";
   
   $conn = mysqli_connect($servername,$user,$password,$db);

   $file = $_FILES['fileToUpload']['name'];
   echo $file.'\n';
   $folder = "uploadedFiles/";

   $file_name = rand(100,100000).$file;
   echo "stored as : ".$file_name;

   move_uploaded_file($_FILES['fileToUpload']['tmp_name'], $folder.$file_name);
   $file_entry = "INSERT INTO file VALUES('".$file."','".$file_name."')";
   mysqli_query($conn,$file_entry);
?>