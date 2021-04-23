<?php
    $url = "http://169.254.169.254/latest/meta-data/instance-id";
    $instance_id = file_get_contents($url);
    echo " <h2> <font color='blue'> Instance ID: <b>" . $instance_id . "</b><br/></font> </h2> ";
?>
