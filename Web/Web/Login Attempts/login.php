<?php
session_start();
include 'config.php';

if(isset($_POST['username']) && isset($_POST['password'])) {
    $username = $_POST['username'];
    $password = $_POST['password'];

    $sql = "SELECT id, username, password, successful_login_attempts, unsuccessful_login_attempts FROM users WHERE username='$username'";
    $result = $conn->query($sql);

    if ($result->num_rows == 1) {
        $row = $result->fetch_assoc();
        if(password_verify($password, $row['password'])) {
            $_SESSION['username'] = $username;

            // Increment successful login attempts
            $successfulAttempts = $row['successful_login_attempts'] + 1;
            $_SESSION['success'] = $successfulAttempts;
            $_SESSION['failure'] = $row['unsuccessful_login_attempts'];
            $updateSql = "UPDATE users SET successful_login_attempts=$successfulAttempts WHERE username='$username'";
            $conn->query($updateSql);

            header('Location: welcome.php');
            exit();
        } else {
            // Increment unsuccessful login attempts
            $unsuccessfulAttempts = $row['unsuccessful_login_attempts'] + 1;
            $_SESSION['failure'] = $unsuccessfulAttempts;
            $_SESSION['success'] = $row['successful_login_attempts'];
            $updateSql = "UPDATE users SET unsuccessful_login_attempts=$unsuccessfulAttempts WHERE username='$username'";
            $conn->query($updateSql);

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
