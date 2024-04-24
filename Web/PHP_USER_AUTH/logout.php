<?php
   session_unset();
   // destroy the session
   session_destroy(); 
   
   header("Location: http://localhost/user_Auth/index.php");
?>