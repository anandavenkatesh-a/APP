<?php
session_start();
include 'config.php';

// Check if user is authenticated
if (!isset($_SESSION['username'])) {
    header("Location: index.html");
    exit();
}

if (isset($_FILES['file']) && !empty($_FILES['file']['name'])) {
    $username = $_SESSION['username'];
    $fileName = $_FILES['file']['name'];
    $fileTmpName = $_FILES['file']['tmp_name'];
    $mode = $_POST['mode'];

    // Move uploaded file to the uploads directory
    $targetDir = "uploads/";
    $targetFilePath = $targetDir . $fileName;
    move_uploaded_file($fileTmpName, $targetFilePath);

    // Insert file details into the database
    $sql = "INSERT INTO files (name, username, mode) VALUES ('$fileName', '$username', '$mode')";
    if ($conn->query($sql) === TRUE) {
        header('Location: welcome.php');
        exit();
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }
} else {
    echo "Please select a file to upload.";
}

$conn->close();
?>
