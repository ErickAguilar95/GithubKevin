<?php
function test($a) {
    fwrite($a, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ');
    fseek($a, 0);
    fwrite($a, '1234567890');
    fseek($a, 0);
    var_dump(fread($a, 1024));
    fclose($a);
}

$a = fopen('php://temp', 'a+');
test($a);

echo '<br>';

echo '-------------------' . PHP_EOL . '<br>';

$a = fopen(tempnam(sys_get_temp_dir(), "hello"), 'a+');
test($a);

?>