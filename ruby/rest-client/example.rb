#!/usr/bin/ruby
require 'uri'
require 'rest-client'

uri = URI::HTTPS.build(
  userinfo: 'USERNAME:PASSWORD',
  host: 'api.genericmobile.se',
  path: '/SmsGateway/api/v1/Message'
)

RestClient.post(
  uri.to_s,
  {
    'From' => 'TEST',
    'To'   => ['+15551234'],
    'Text' => 'Test message'
  }
)