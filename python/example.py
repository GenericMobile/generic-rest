#!/usr/bin/python
import urllib
import urllib2
import base64
import json

json_data = json.dumps({
  'From': 'TEST',
  'To': ['+15551234'],
  'Text': 'Test message'
})

req = urllib2.Request(
  url="https://api.genericmobile.se/SmsGateway/api/v1/Message",
  data=json_data,
  headers={
    'Authorization': 'Basic ' + base64.b64encode('USERNAME:PASSWORD'),
    'Content-Type': 'application/json'
  }
)
f = urllib2.urlopen(req)
print f.read()