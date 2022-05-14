# mock

## POST Without headers

trp: content-type = null, 
axis2: messageType = application/xml, 
axis2: contentType = 

## POST with Content-Type and without body

trp: content-type = application/json, 
axis2: messageType = application/json, 
axis2: contentType = application/json

>> "Content-Type: application/json[\r][\n]"
<< "activityid: dcb7aff7-98f1-4cb1-a7a0-7aaf7b3a3700[\r][\n]"
<< "Content-Type: application/json; charset=UTF-8[\r][\n]"

## POST with Content-Type and body.

trp: content-type = application/json, 
axis2: messageType = application/json, 
axis2: contentType = application/json, 
Payload: {"hello":"world"}

>> "Content-Type: application/json[\r][\n]"
>> "{"hello":"world"}"
<< "activityid: 963decae-1065-4642-9ba5-bc4cd17ec363[\r][\n]"
<< "Content-Type: application/json; charset=UTF-8[\r][\n]"
<< "{"hello":"world"}[\r][\n]"

# messageTypeXML

## POST Without headers

trp: content-type = null, 
axis2: messageType = application/xml, 
axis2: contentType = 

<< "Content-Type: application/xml[\r][\n]"

## POST with Content-Type and without body

trp: content-type = application/json, 
axis2: messageType = application/json, 
axis2: contentType = application/json

>> "Content-Type: application/json[\r][\n]"
<< "activityid: dcb7aff7-98f1-4cb1-a7a0-7aaf7b3a3700[\r][\n]"
<< "Content-Type: application/xml; charset=UTF-8[\r][\n]"

## POST with Content-Type and body.

trp: content-type = application/json, 
axis2: messageType = application/json, 
axis2: contentType = application/json, 
Payload: {"hello":"world"}

>> "Content-Type: application/json[\r][\n]"
>> "{"hello":"world"}"
<< "activityid: 24d2959f-e924-4809-ba7c-145c41ff4d51[\r][\n]"
<< "Content-Type: application/xml; charset=UTF-8[\r][\n]"
<< "<jsonObject><hello>world</hello></jsonObject>[\r][\n]"

# ContentTypeXML

## POST Without headers

trp: content-type = null, 
axis2: messageType = application/xml, 
axis2: contentType = 

<< "Content-Type: application/xml[\r][\n]"

## POST with Content-Type and without body

trp: content-type = application/json, 
axis2: messageType = application/json, 
axis2: contentType = application/json

>> "Content-Type: application/json[\r][\n]"
<< "activityid: dcb7aff7-98f1-4cb1-a7a0-7aaf7b3a3700[\r][\n]"
<< "Content-Type: application/xml; charset=UTF-8[\r][\n]"

## POST with Content-Type and body.

trp: content-type = application/json, 
axis2: messageType = application/json, 
axis2: contentType = application/json, 
Payload: {"hello":"world"}

>> "Content-Type: application/json[\r][\n]"
>> "{"hello":"world"}"
<< "activityid: 24d2959f-e924-4809-ba7c-145c41ff4d51[\r][\n]"
<< "Content-Type: application/xml; charset=UTF-8[\r][\n]"
<< "<jsonObject><hello>world</hello></jsonObject>[\r][\n]"