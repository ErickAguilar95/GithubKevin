<?php

$x = simplexml_load_string('<x>2.5</x>');
var_dump($x*1);
echo '<br>';

var_dump($x*1.0);
echo '<br>';

$y = '2.5';
var_dump($y*1);
echo '<br>';
var_dump((string)$x*1);
echo '<br>';

?>