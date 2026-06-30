# USER API SPEC

## Register User
- endpoint POST /api/users

Request Body :
```json
{
  "username": "jiwon",
  "password": "rahasia",
  "name": "Jiwon Jiwon"
}
```
Response Body (Success) :
```json
{
  "data": "OK"
}
```

Response Body (Failed) :
```json
{
  "errors" : "Username must not blank, ??"
}
```

## Login User
- endpoint POST /api/auth/login

Request Body :
```json
{
  "username": "Jiwon",
  "password": "rahasia"
}
```
Response Body (Success) :
```json
{
  "data": {
    "token": "",
    "expiredAt": 123123123 // miliseconds
  }
}
```

Response Body (Failed) :
```json
{
  "errors" : "Username or password wrong"
}
```

## Get User
- endpoint GET /api/users/current

Request Header:
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data": {
    "username": "jiwon",
    "name": "Jiwon Jiwon"
  }
}
```

Response Body (Failed) :
```json
{
  "errors" : "Unauthorized"
}
```

## Update User
- endpoint PATCH /api/users/current

Request Header:
- X-API-TOKEN : Token (Mandatory)

Request Body :
```json
{
  "name": "Jiwon Jiwon", // put if only want to update name
  "password": "new password" // put if only want to update password
}
```

Response Body (Success) :
```json
{
  "data": {
    "username": "jiwon",
    "name": "Jiwon Jiwon"
  }
}
```

Response Body (Failed) :
```json
{
  "errors" : "Unauthorized"
}
```

## Logout User
- endpoint DELETE /api/auth/logout

Request Header:
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data": "OK"
}
```
