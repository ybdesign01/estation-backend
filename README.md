# E-station Backend API
This is the backend for the E-station project.

## Api Documentation
The link of the api is : https://estation-api.herokuapp.com/api/

## Auth
### The following routes do not require authentication:
```
POST: /api/auth  | Status: 400 - msg: user_not_found, msg: password_not_match, msg: expired_token / Status: 200 

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
POST: /api/auth/refresh | Status: 400 - msg: refresh_token_invalid / Status: 200

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

```
POST: /api/auth/logout | Status: 400 - msg: refresh_token_invalid / Status: 200

{
    "refreshToken": "f8e716f9-a262-4878-96ff-080f06e028bb"
}
```
You'll get the following response: 
```
{
    "msg": "logout_success"
}
```


## All the following routes require the Bearer token:
## User
```
GET:     /api/user  | Status: 404 - msg: no_users_found, 200
         /api/user/{id} | Status: 404 - msg: user_not_found, 200
         /api/user/getUser (By token) | Status: 404 - msg: user_not_found, 200
         /api/user/getCurrentStation/{id} | Status: 404 - msg: user_not_found, no_station_assigned_to_user, 200
         
GET:     /api/user/getPompes/{id} | Status: 404 - msg: user_not_found, no_pompes_assigned_to_user, 200
***** This route gets the pompes of a user to which he's set to today
Response:
{
        "idPompeUser": 3,
        "pompe": {
            "id_pompe": 1,
            "nom_pompe": "POMPE1"
        },
        "user": {
            "id_user": 1,
            "nom": "Oussama",
            "prenom": "Berhili",
            "email": "oussama@gmail.com",
            "matricule": "D47559",
            "profile": {
                "id_profile": 1,
                "nom": "USER",
                "description": "User profile"
            }
        },
        "dateDebut": "2023-05-23T18:30:00",
        "dateFin": "2023-05-24T08:30:00"
    }
}
```
```
POST:    /api/user/  | Status: 404 - msg: user_exists, 200
Body: 
{
    "nom": "Yasser",
    "prenom": "yasser",
    "email": "admin@gmail.com",
    "matricule": "dd88",
    "password": "Yasser123+",
    "profile": {
            "id_profile": 1,
            "nom": "USER",
            "description": "User profile"
        }
}
```
```
POST:     /api/user/setStation | Status: 400 - msg: station_user_exists / 404: station_not_found, user_not_found / 201 msg: station_user_created
Body:
{
    "stationUserKey":{
        "id_station": 1,
        "id_user": 1    
    },
    "date_debut": "Fri May 05 19:33:44 WEST 2023" ---- *Optional default is current date
    "date_fin": "Fri May 05 19:33:44 WEST 2023" ----- *Optional
}

Response:
{
    "msg": "station_user_created",
    "stationUser": {
        "stationUserKey": {
            "id_station": 1,
            "id_user": 1
        },
        "station": {
            "id": 1,
            "nom_station": "Station 1",
            "adresse": "Description 1"
        },
        "user": {
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
            "pompes": [
                {
                    "pompe": {
                        "id_pompe": 1,
                        "nom_pompe": "POMPE1"
                    },
                    "dateDebut": "2023-05-23T18:30:00",
                    "dateFin": "2023-05-24T08:30:00"
                },
                {
                    "pompe": {
                        "id_pompe": 1,
                        "nom_pompe": "POMPE1"
                    },
                    "dateDebut": "2023-05-31T18:30:00",
                    "dateFin": "2023-05-31T20:30:00"
                },
                {
                    "pompe": {
                        "id_pompe": 1,
                        "nom_pompe": "POMPE1"
                    },
                    "dateDebut": "2023-05-31T10:30:00",
                    "dateFin": "2023-05-31T11:30:00"
                },
                {
                    "pompe": {
                        "id_pompe": 2,
                        "nom_pompe": "POMPE1"
                    },
                    "dateDebut": "2023-05-28T18:30:00",
                    "dateFin": "2023-05-29T08:30:00"
                }
            ]
        },
        "date_debut": "Tue May 23 23:23:08 WEST 2023"
    }
}
```
```
POST:     /api/user/setPompe | Status: 404 - msg: user_not_found, pompe_not_found / 400 - msg: pompe_unavailable_during_time_range, pompe_user_exists, user_already_has_pompes, invalid_date / 201 msg: pompe_user_created
Body:
{
"idPompe": 2,
"idUser":1,
"dateDebut": "2023-05-23T18:30:00", ** Date follows the ISO 8601 format
"dateFin": "2023-05-24T08:30:00" ** Date follows the ISO 8601 format
}

Response: 
{
    "msg": "pompe_user_created",
    "pompeUser": {
        "idPompeUser": 7,
        "pompe": {
            "id_pompe": 1,
            "nom_pompe": "POMPE1"
        },
        "user": {
            "id_user": 1,
            "nom": "Oussama",
            "prenom": "Berhili",
            "email": "oussama@gmail.com",
            "matricule": "D47559",
            "profile": {
                "id_profile": 1,
                "nom": "USER",
                "description": "User profile"
            }
        },
        "dateDebut": "2023-05-25T18:30:00", ** Date follows the ISO 8601 format
        "dateFin": "2023-05-26T08:30:00" ** Date follows the ISO 8601 format
    }
}
```
```
POST:    /api/user/updateStation (Requires admin or manager role) | Status: 400 - msg: station_user_not_found, 200
Body:
{
    "stationUserKey":{
        "id_station": 1,
        "id_user": 1    
    },
    "date_debut": "Fri May 05 19:33:44 WEST 2023" ---- *Optional default is current date
    "date_fin": "Fri May 05 19:33:44 WEST 2023" ----- *Optional
}
```
```
PUT:     /api/user/{id} | Status: 400 - msg: user_not_updated/ user_not_found / profile_not_found, 200
Body:
{
    "nom": "Yasser",
    "prenom": "yass",
    "email": "mail@mail.cpm",
    "matricule": "dd88",
    "password": "Yasser123+",
    "profile": {
            "id_profile": 1,
            "nom": "USER",
            "description": "User profile"
        }
}
```
```
DELETE:  /api/user/{id} (Requires admin role) | Status: 404 - msg: user_not_found, 200 msg: user_deleted
```

## Service
```
GET:     /api/services (Get all services) | Status: 200, 404 - msg: no_services_found
         /api/services/{id} (Get service by id) | Status: 200, 404 - msg: service_not_found
POST:    /api/services/ | Status: 400 - msg: service_not_added, 201 - msg: service_added
PUT:     /api/services/{id} | Status: 400 - msg: service_not_updated, 200 - msg: service_updated
DELETE:  /api/services/{id} (Requires admin role) | Status: 400 - msg: service_not_deleted, 200 - msg: service_deleted

An example of Service body: (POST, PUT)
{
    "nom_service": "Service 1",
    "description": "Description 1"
}

```
## Station
```
GET:     /api/station (Get all stations) | Status: 200, 404 - msg: no_stations_found
         /api/station/{id} (Get station by id) | Status: 200, 404 - msg: station_not_found
POST:    /api/station/ | Status: 400 - msg: station_not_added, 201 - msg: station_added
PUT:     /api/station/{id} | Status: 400 - msg: station_not_updated, 200 - msg: station_updated
DELETE:  /api/station/{id} (Requires admin role) | Status: 400 - msg: station_not_deleted, 200 - msg: station_deleted

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
## Pompe
```
GET:     /api/pompe (Get all pompes) | Status: 200, 404 - msg: no_pompes_found
         /api/pompe/{id} (Get pompe by id) | Status: 200, 404 - msg: pompe_not_found
POST:    /api/pompe/ | Status: 400 - msg: pompe_not_added, 201 - msg: pompe_added
PUT:     /api/pompe/{id} (TO DO)
DELETE:  /api/pompe/{id} (TO DO)

An example of pompe body: (POST, PUT)
{
    "nom_pompe": "Pompe 1",
}
Response:
{
        "id_pompe": 1,
        "nom_pompe": "POMPE1",
        "citernes": [], *Optional
        "users": [ *Optional
            {
               {
                "idPompeUser": 1,
                "pompe": {
                    "id_pompe": 1,
                    "nom_pompe": "POMPE1"
                },
                "user": {
                    "id_user": 1,
                    "nom": "Oussama",
                    "prenom": "Berhili",
                    "email": "oussama@gmail.com",
                    "matricule": "D47559",
                    "profile": {
                        "id_profile": 1,
                        "nom": "USER",
                        "description": "User profile"
                    }
                },
                "dateDebut": "2023-05-31T10:30:00",
                "dateFin": "2023-05-31T11:30:00"
            },
            }
        ]
    }
```
## Citerne
```
GET:     /api/citerne (Get all citernes) | Status: 200, 404 - msg: no_citernes_found
         /api/citerne/{id} (Get citerne by id) | Status: 200, 400 - msg: citerne_not_found
POST:    /api/citerne/ | Status: 400 - msg: produit_does_not_exist, 201 - msg: citerne_added
         /api/citerne/setPompes/{id} | Status: 400 - msg: citerne_not_found, 200
PUT:     /api/citerne/{id} (TO DO)
DELETE:  /api/citerne/{id} (TO DO)

An example of citerne body: (POST, PUT)
{
    "nom_citerne": "CITERNE1",
    "capacite": "1000L",
    "produit": {
        "id_produit": 1
    }
}

```
## TypeProduit
```
GET:     /api/produit/getTypes (Get all typeProduits) | Status: 200, 404 - msg: no_types_found
POST:    /api/produit/addType (Requires admin role) | Status: 400 - msg: type_not_added, 201 - msg: type_added

An example of typeProduit body: (POST)
{
    "nom_type": "Type 1",
}
```

## Produit
```
GET:     /api/produit (Get all produits) | Status: 200, 404 - msg: no_produits_found
         /api/produit/{id} (Get produit by id) | Status: 200, 404 - msg: produit_not_found
POST:    /api/produit/ | Status: 400 - msg: produit_not_added, 201 - msg: produit_added
PUT:     /api/produit/{id} (TO DO)
DELETE:  /api/produit/{id} (TO DO)

An example of produit body: (POST, PUT)
{
    "nom_produit": "produit 2",
    "prix_achat": 100,
    "prix_vente": 115,
    "service": {
        "id": 1
    },
    "type": {
        "id_type": 1
    }
}

```
## Releve
```
GET:     /api/releve (Get all releves) | Status: 200, 404 - msg: no_releves_found
         /api/releve/{id} (Get releve by id) | Status: 200, 404 - msg: releve_not_found
         /api/releve/getByPompeUser/{id} (Get releve by pompeUser id) | Status: 200, 404 - msg: pompe_user_not_found
POST:    /api/releve/ | Status: 400 - msg: releve_not_added, 201 - msg: releve_added
PUT:     /api/releve/{id} (TO DO)
DELETE:  /api/releve/{id} (TO DO)

An example of releve body: (POST, PUT) ***** ! IMPORTANT ! --- It will only be submitted if the current time corresponds to the time of PompeUser *****
{
    "compteur": 1555,
    "type_releve": "RELEVE_ENTREE",
    "pompeUser":{
        "idPompeUser": 3 ***** Get from /api/user/getPompes/{id} *****
    }
}

An example of GET: /api/releve/getByPompeUser/{id} response:
{
"releve_entree": true,
"releve_sortie": false
}

```
## Responses & status codes
```
Each error response will have a body with a message and a status code.

Example of error response:
{
    "msg": "user_not_found"
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