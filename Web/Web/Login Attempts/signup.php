<?php
include 'config.php';

if(isset($_POST['new_username']) && isset($_POST['new_password'])) {
    $newUsername = $_POST['new_username'];
    $newPassword = $_POST['new_password'];

    // Hash the password before storing it in the database
    $hashedPassword = password_hash($newPassword, PASSWORD_DEFAULT);

    $sql = "INSERT INTO users (username, password) VALUES ('$newUsername', '$hashedPassword')";

    if ($conn->query($sql) === TRUE) {
        echo "Signup successful!";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }
} else {
    echo "Please provide a username and password!";
}

$conn->close();
?>
