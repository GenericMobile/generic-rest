#!/bin/bash
curl -v \
  --basic -u 'USERNAME:PASSWORD' \
  --data-urlencode 'From=TEST' --data-urlencode 'To[]=+15551234' --data-urlencode 'Text=Test message' \
  https://api.genericmobile.se/SmsGateway/api/v1/Message