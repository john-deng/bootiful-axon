# demo-complaint
demo-complaint is a demo application of CQRS & Event Sourcing design pattern based on Spring boot and Axon Framework

# Getting started

## Run the demo
```bash
mvn spring-boot:run
```

## Test it
```bash

# command
http --timeout=36000 :8080/complaint 'company=Apple' 'description=The MacBook Pro is too expensive!'

HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Date: Tue, 22 Aug 2017 10:56:00 GMT
Transfer-Encoding: chunked

{
    "message": "成功",
    "returnCode": 1000
}

# query

http :8080/find-all

HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Date: Tue, 22 Aug 2017 10:58:40 GMT
Transfer-Encoding: chunked

[
    {
        "company": "Apple",
        "complaintId": "2a903df0cb3f432698b3954f4bd8b69d",
        "description": "The MacBook Pro is too expensive!"
    }
]

```