#!/usr/bin/ruby
require 'json'
require 'base64'
require 'uri'
require 'net/http'

uri = URI::HTTPS.build(
  userinfo: 'USERNAME:PASSWORD',
  host: 'api.genericmobile.se',
  path: '/SmsGateway/api/v1/Message'
)
Net::HTTP.start(uri.host, uri.port, use_ssl: true) do |http|
  http.request_post(
    uri.path,
    JSON.generate(
      'From' => 'TEST',
      'To'   => ['+15551234'],
      'Text' => 'Test message'
    ),
    {
      'Authorization' => "Basic #{Base64.strict_encode64(uri.userinfo)}",
      'Content-Type'  => 'application/json',
    } 
  )
end