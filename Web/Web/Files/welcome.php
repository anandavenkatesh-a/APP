<?php
session_start();
include 'config.php';

// Check if user is authenticated
if (!isset($_SESSION['username'])) {
    header("Location: index.html");
    exit();
}

// Retrieve user's private files from the database
$username = $_SESSION['username'];
$sql = "SELECT * FROM files WHERE username='$username' AND mode='private'";
$result = $conn->query($sql);
$privateFiles = array();
if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $privateFiles[] = $row;
    }
}

// Retrieve public files from the database
$sql = "SELECT * FROM files WHERE mode='public'";
$result = $conn->query($sql);
$publicFiles = array();
if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $publicFiles[] = $row;
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome</title>
</head>
<body>
    <h2>Welcome, <?php echo $_SESSION['username']; ?>!</h2>
    
    <!-- Display private files -->
    <h3>Private Files:</h3>
    <ul>
        <?php foreach ($privateFiles as $file) : ?>
            <li>
                <?php echo $file['name']; ?> 
                <a href="<?php echo $file['name']; ?>" download>Download</a>
            </li>
        <?php endforeach; ?>
    </ul>
    
    <!-- Display public files -->
    <h3>Public Files:</h3>
    <ul>
        <?php foreach ($publicFiles as $file) : ?>
            <li>
                <?php echo $file['name']; ?> 
                <a href="<?php echo $file['name']; ?>" download>Download</a>
            </li>
        <?php endforeach; ?>
    </ul>
    
    <!-- Upload form -->
    <h3>Upload File:</h3>
    <form action="upload.php" method="post" enctype="multipart/form-data">
        <label for="file">Select File:</label>
        <input type="file" name="file" id="file" required><br><br>
        <label for="mode">Mode:</label>
        <select name="mode" id="mode">
            <option value="public">Public</option>
            <option value="private">Private</option>
        </select><br><br>
        <button type="submit" name="submit">Upload</button>
    </form>

    <a href="logout.php">Logout</a>
</body>
</html>
