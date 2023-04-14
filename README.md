# E-station Backend API
This is the backend for the E-station project.

## Api Documentation
The link of the api is : https://estation-api.herokuapp.com/api/

## Auth
```
Routes without auth:
POST: /api/auth (Get your token)

{
    "email": "mail@mail.com",
    "password": "Password123+"
}
```

### All the following routes require the Bearer token:
## User
```
GET:     /api/user (Get all users)
         /api/user/{id} (Get user by id)
         /api/user/getUser (Get user by token)
POST:    /api/user/
PUT:     /api/user/{id} 
DELETE:  /api/user/{id} (Requires admin role)

An example of user body:
{
    "nom": "Yasser",
    "prenom": "yass",
    "email": "mail@mail.com",
    "matricule": "dd88",
    "password": "Password123+",
    "profile": "USER"
}
```
## Service
```
GET:     /api/services (Get all services)
         /api/services/{id} (Get service by id)
POST:    /api/services/
PUT:     /api/services/{id}
DELETE:  /api/services/{id} (Requires admin role)

An example of Service body:
{
    "nom_service": "Service 1",
    "description": "Description 1"
}

```
## Station
```
GET:     /api/station (Get all stations)
         /api/station/{id} (Get station by id)
POST:    /api/station/
PUT:     /api/station/{id}
DELETE:  /api/station/{id} (Requires admin role)

An example of station body:
{
    "nom_station": "Service 1",
    "adresse": "Description 1",
    "services":[
    {
    "id_service": "1",
    "nom_service": "NOM",
    "description":"DESCRIPTION"
    }
    ]
}

```


## Responses & status codes
```
Each error response will have a body with a message and a status code.

Example of error response:
{
    "message": "The user with id 1 does not exist"
}

The possible status codes are:

200: OK - The request has succeeded
201: Created - The request has been fulfilled and resulted in a new resource being created
400: Bad Request - The server cannot or will not process the request due to an apparent client error
401: Unauthorized - The request has not been applied because it lacks valid authentication credentials for the target resource
403: Forbidden - The request was a legal request, but the server is refusing to respond to it
404: Not Found - The requested resource could not be found but may be available in the future
405: Method Not Allowed - A request method is not supported for the requested resource
500: Internal Server Error - The server encountered an unexpected condition that prevented it from fulfilling the request

```