<?php

$username = 'USERNAME';
$password = 'PASSWORD';

# Generate JSON string from our associative array
$json_data = json_encode(
  array(
    'From' => 'TEST',
    'To'   => array('+15551234'),
    'Text' => 'Test message'
  )
);

# Setup context with basic authentication headers
$context = stream_context_create(
  array(
    'http' => array(
      'method'  => 'POST',
      'header'  =>
        'Authorization: Basic ' . base64_encode("$username:$password") . "\r\n" .
        "Content-type: application/json\r\n",
      'content' => $json_data
    )
  )
);
# perform the actual request with our context
$result = file_get_contents(
  'https://api.genericmobile.se/SmsGateway/api/v1/Message',
  false,
  $context
);
?>