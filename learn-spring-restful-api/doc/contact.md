# CONTACT API SPEC

## Create Contact
endpoint POST /api/contacts

Request Header : 
- X-API-TOKEN : Token (Mandatory)

- Request Body :
```json
{
  "firstName": "Jiwon",
  "lastName": "Jiwon",
  "email": "jiwon@gmail.com",
  "phone": "081254079880"
}
```
Response Body (Success) :
```json
{
  "data": {
    "id" : "random string",
    "firstName": "Jiwon",
    "lastName": "Jiwon",
    "email": "jiwon@gmail.com",
    "phone": "081254079880"
  }
}
```

Response Body (Failed) :
```json
{
  "errors" : "Email format invalid, phone format invalid, ..."
}
```

## Update Contact
endpoint PUT /api/contacts/{idContact}

Request Header :
- X-API-TOKEN : Token (Mandatory)

Request Body :
```json
{
  "firstName": "Jiwon",
  "lastName": "Jiwon",
  "email": "jiwon@gmail.com",
  "phone": "081254079880"
}
```

Response Body (Success) :
```json
{
  "data": {
    "id": "random string",
    "firstName": "Jiwon",
    "lastName": "Jiwon",
    "email": "jiwon@gmail.com",
    "phone": "081254079880"
  }
}
```

Response Body (Failed) :
```json
{
  "errors" : "Email format invalid, phone format invalid, ..."
}
```

## Get Contact
endpoint GET /api/contacts/{idContact}

Request Header:
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data": {
    "id": "random string",
    "firstName": "Jiwon",
    "lastName": "Jiwon",
    "email": "jiwon@gmail.com",
    "phone": "081254079880"
  }
}
```

Response Body (Failed, 404) :
```json
{
  "errors" : "Contact is not found"
}
```

## Search Contact
endpoint GET /api/contacts

Query Param :
- name = String, contact first name or last name, using like query, optional
- phone = String, contact phone, using like query, optional
- email = String, contact email, using like query, optional
- page = Integer, start from zero
- size = Integer, default 10

Request Header:
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data": [
    {
      "id": "random string",
      "firstName": "Jiwon",
      "lastName": "Jiwon",
      "email": "jiwon@gmail.com",
      "phone": "081254079880"
    }
  ],
  "paging": {
    "currentPage": 0,
    "totalPage": 10,
    "size": 10
  }
}
```

Response Body (Failed, 404) :
```json
{
  "errors" : "Contact is not found"
}
```

## Remove Contact
- endpoint DELETE /api/contacts/{idContact}

Request Header:
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data": "OK"
}
```

Response Body (Failed) :
```json
{
  "errors" : "Unauthorized"
}
```