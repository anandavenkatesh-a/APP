<?php
session_start();
include 'config.php';

if(isset($_POST['username']) && isset($_POST['password'])) {
    $username = $_POST['username'];
    $password = $_POST['password'];

    $sql = "SELECT id, username, password FROM users WHERE username='$username'";
    $result = $conn->query($sql);

    if ($result->num_rows == 1) {
        $row = $result->fetch_assoc();
        if(password_verify($password, $row['password'])) {
            $_SESSION['username'] = $username;
            header('Location: welcome.php');
            exit();
        } else {
            header('Location: index.html');
            exit();
        }
    } else {
        header('Location: index.html');
        exit();
    }
} else {
    header('Location: index.html');
    exit();
}

$conn->close();
?>
