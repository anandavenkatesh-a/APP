<?php
   $servername = "localhost";
   $user = "root";
   $password = "";
   $db = "file_manager";
   $folder = "uploadedFiles/";
   
   $conn = mysqli_connect($servername,$user,$password,$db);
   $create_table = "CREATE TABLE IF NOT EXISTS file(
                    title varchar(25) PRIMARY KEY,
                    path varchar(500))";
   mysqli_query($conn,$create_table);
?>
<html>
<body>
   <form action="upload.php" method="post" enctype="multipart/form-data">
     <h3>Select file to upload:</h3>
     <input type="file" name="fileToUpload" id="fileToUpload"><br>
     <input type="submit" value="Upload Image" name="submit"><br>
   </form>
   <hr>
   <table>
      <thead>
         <tr>
            <th>file name</th>
            <th>download</th>
         </tr>
      </thead>
      <tbody>
         <?php
             $query = "select * from file";
             $files = mysqli_query($conn,$query);
             while($row = mysqli_fetch_array($files)){
                 $file_name = $row['title'];
                 $file_path = $folder.$row['path'];
                 echo "<tr>";
                 echo "<td>$file_name</td>";
                 echo "<td><a href = '$file_path' target='_blank'> View </a></td>";
                 echo "</tr>";
             }
         ?>
      </tbody>  
   </table>
</body>
</html>