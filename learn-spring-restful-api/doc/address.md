# ADDRESS API SPEC

## Create Address
endpoint POST /api/contacts/{idContact}/addresses

Request Header : 
- X-API-TOKEN : Token (Mandatory)

Request Body :
```json
{
  "street": "Jalan apa",
  "city": "Kota",
  "province": "Provinsi",
  "country": "Negara",
  "postalCode": "123123"
}
```
Response Body (Success) :
```json
{
  "data": {
    "street": "Jalan apa",
    "city": "Kota",
    "province": "Provinsi",
    "country": "Negara",
    "postalCode": "123123"
  }
}
```

Response Body (Failed) :
```json
{
  "errors" : "Contact is not found"
}
```

## Update Address
endpoint PUT /api/contacts/{idContact}/addresses/{idAddress}

Request Header :
- X-API-TOKEN : Token (Mandatory)

- Request Body :
```json
{
  "street": "Jalan apa",
  "city": "Kota",
  "province": "Provinsi",
  "country": "Negara",
  "postalCode": "123123"
}
```
Response Body (Success) :
```json
{
  "data": {
    "id": "random String",
    "street": "Jalan apa",
    "city": "Kota",
    "province": "Provinsi",
    "country": "Negara",
    "postalCode": "123123"
  }
}
```

Response Body (Failed) :
```json
{
  "errors" : "Address is not found"
}
```

## Get Address
endpoint GET /api/contacts/{idContact}/addresses/{idAddress}

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

## Remove Contact
- endpoint DELETE /api/contacts/{idContact}/addresses/{idAddress}

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

## List Address
- endpoint GET /api/contacts/{idContact}/addresses

Request Header:
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data": [
    {
      "street": "Jalan apa",
      "city": "Kota",
      "province": "Provinsi",
      "country": "Negara",
      "postalCode": "123123"
    }
  ]
}
```

Response Body (Failed) :
```json
{
  "errors" : "Contact is not found"
}
```