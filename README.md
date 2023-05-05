# E-station Backend API
This is the backend for the E-station project.

## Api Documentation
The link of the api is : https://estation-api.herokuapp.com/api/

## Auth
### The following routes do not require authentication:
```

POST: /api/auth

{
    "email": "mail@mail.com",
    "password": "Password123+"
}
```
You'll get a response like the following:
```
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE2ODI2MTc5NzYsImV4cCI6MTY4MjYxODg3Nn0.zSiqzNKo1EfpVZtNoVWElj34iQ0eZ_E6Wy1wnPGnPPg",
    "msg": "authentication_success",
    "user": {
        "id_user": 0,
        "nom": "Admin",
        "prenom": "Admin",
        "email": "mail@mail.com",
        "matricule": "D7777",
        "profile": {
            "id_profile": 0,
            "nom": "ADMIN",
            "description": "Administrator profile"
        }
    },
    "refreshToken": "f8e716f9-a262-4878-96ff-080f06e028bb"
}
```
You need to save the refreshToken because it will be needed to refresh the access token when it expires.
The default expiration time for the access token is 15 minutes. 
The expiration time for the refresh token is 3 days.

#### Refresh token
```
POST: /api/auth/refresh

{
    "refreshToken": "f8e716f9-a262-4878-96ff-080f06e028bb"
}
```
You'll get the following response:
```
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE2ODI2MTkwODEsImV4cCI6MTY4MjYxOTk4MX0.OGy_-3s1eVzjVxnbYrvoF-xZ5zMtlckwtiGwS1rV7ss",
    "refreshToken": "f8e716f9-a262-4878-96ff-080f06e028bb"
}
```
The token is the new access token that you need to save and use for the next requests.
The refresh token is the same as the one you sent in the request.
When the refreshToken expires you'll have to authenticate again.


## All the following routes require the Bearer token:
## User
```
GET:     /api/user (Get all users)
         /api/user/{id} (Get user by id)
         /api/user/getUser (Get user by token)
POST:    /api/user/
         /api/user/setStation (Requires admin or manager role)  
PUT:     /api/user/{id} 
DELETE:  /api/user/{id} (Requires admin role)

An example of user body:
{
        "id_user": 1,
        "nom": "Oussama",
        "prenom": "Berhili",
        "email": "oussama@gmail.com",
        "matricule": "D47559",
        "profile": {
            "id_profile": 1,
            "nom": "USER",
            "description": "User profile"
        },
        "stations": [
            {
                "station": {
                    "id": 1,
                    "nom_station": "Station 1",
                    "adresse": "Description 1"
                },
                "date_debut": "Fri May 05 19:33:44 WEST 2023"
            }
        ]
    }
    
    
    
    
An example of setStation body:
{
    "stationUserKey":{
        "id_station": 1,
        "id_user": 1    
    },
    "station":  {
        "id": 1,
        "nom_station": "Station 1",
        "adresse": "Description 1"
    },
    "user":  {
        "id_user": 1,
        "nom": "Yasser",
        "prenom": "yass",
        "email": "mail@mail.cpm",
        "matricule": "dd88",
        "profile": {
            "id_profile": 1,
            "nom": "USER",
            "description": "User profile"
        }
    }
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
    "id": "1",
    }
    ]
}

```


## Responses & status codes
```
Each error response will have a body with a message and a status code.

Example of error response:
{
    "msg": "The user with id 1 does not exist"
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