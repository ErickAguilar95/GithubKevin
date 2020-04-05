<?php
trait A
{
    public function a(){}
}
trait B
{
    use A;
}
trait C
{
    use A;
}
class D
{
    use B, C;
}
?>