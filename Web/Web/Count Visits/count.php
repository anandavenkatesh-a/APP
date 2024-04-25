<!-- count.php -->
<?php
$visits_file = "visits.txt";
$visits = 0;

if (file_exists($visits_file)) {
    $visits = (int)file_get_contents($visits_file);
}

$visits++;

file_put_contents($visits_file, $visits);

return $visits;
?>
