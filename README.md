# E-station Backend API
This is the backend for the E-station project.
Web App link: https://e-station-frontend-host.vercel.app/

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
         /api/user/getAffectationsMontant/{id} | Status: 404 - msg: user_not_found, 404: no_pompes_assigned_to_user, 200 transactions_already_submitted
         /api/user/getAllByStation/{id} | Status: 404 - msg: station_not_found, no_users_found / 200
         
GET:     /api/user/getPompes/{id} | Status: 404 - msg: user_not_found, no_pompes_assigned_to_user, 200
***** This route gets the pompes of a user to which he's set to today
Response:
[
    {
        "idPompeUser": 2,
        "pompe": {
            "id_pompe": 1,
            "nom_pompe": "pompe test"
        },
        "user": {
            "id_user": 7,
            "nom": "tesnom",
            "prenom": "testprenom",
            "email": "testemail@gmail.com",
            "matricule": "bfvfkvf",
            "profile": {
                "id_profile": 1,
                "nom": "USER",
                "description": "User profile"
            }
        },
        "dateDebut": "2023-06-01T03:02:00",
        "dateFin": "2023-06-03T03:02:00",
        "releve": true
    }
]
```
```
Body of /api/user/getAffectationsMontant:
{
    "email": "email@gmail.com"
}

Response of /api/user/getAffectationsMontant:
[
    {
        "pompeUser": {
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
            "dateDebut": "2023-05-31T21:35:03",
            "dateFin": "2023-05-31T23:59:00"
        },
        "montant": 22154.5
    }
]
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
    "profile": "USER"
}
```
```
POST:     /api/user/setStation | Status: 404: station_not_found, user_not_found / 201 msg: station_user_created
Body:
{
    "idStation": 1,
    "idUser":1
    "date_debut": "2023-05-23T18:30:00" ---- *Optional default is current date
    "date_fin": "2023-05-23T18:30:00" ----- *Optional
}

Response:
{
    "msg": "station_user_created",
    "stationUser": {
        "idStationUser": 1,
        "station": {
            "id": 1,
            "nom_station": "Station 1",
            "adresse": "Description 1"
        },
        "user": {
            "id_user": 17,
            "nom": "Yasser",
            "prenom": "yass",
            "email": "adminededdddd@gmail.com",
            "matricule": "dd88",
            "profile": {
                "id_profile": 1,
                "nom": "USER",
                "description": "User profile"
            }
        },
        "date_debut": "2023-06-01T13:33:23.1884593"
    }
}
```
```
POST:     /api/user/setPompe | Status: 404 - msg: user_not_found, pompe_not_found / 400 - msg: pompe_unavailable_during_time_range, pompe_user_exists, user_already_has_pompes, invalid_date, manager_cannot_have_pompe, admin_cannot_have_pompe / 201 msg: pompe_user_created
Body:
{
"idPompe": 2,
"idUser":1,
"dateDebut": "2023-05-23T18:30:00", ** Date follows the ISO 8601 format
"dateFin": "2023-05-24T08:30:00" ** Date follows the ISO 8601 format
}



Response: 
{
    "pompeUser": {
        "idPompeUser": 4,
        "pompe": {
            "id_pompe": 1,
            "nom_pompe": "pompe test"
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
        "dateDebut": "2023-06-10T23:59:00",
        "dateFin": "2023-06-20T15:30:00",
        "releve": false
    },
    "msg": "pompe_user_created"
}
```
```
POST:    /api/user/updateStation (Requires admin or manager role) | Status: 400 - msg: station_user_not_found, 200
Body:
{
    "idStation": 1,
    "idUser": 1    
    "date_debut": "2023-05-31T10:30:00" ---- *Optional default is current date
    "date_fin": "2023-05-31T10:30:00" ----- *Optional
}
Response:
{
    "msg": "station_user_created",
    "stationUser": {
        "idStationUser": 1,
        "station": {
            "id": 1,
            "nom_station": "Station 1",
            "adresse": "Description 1"
        },
        "user": {
            "id_user": 17,
            "nom": "Yasser",
            "prenom": "yass",
            "email": "adminededdddd@gmail.com",
            "matricule": "dd88",
            "profile": {
                "id_profile": 1,
                "nom": "USER",
                "description": "User profile"
            }
        },
        "date_debut": "2023-06-01T13:33:23.1884593"
    }
}

When a user is set to another station the old one's date_fin is set to current date.
```
```
PUT:     /api/user/{id} | Status: 400 - msg: user_not_updated/ user_not_found / profile_not_found, 200
Body:
{
    "nom": "Yasser",
    "prenom": "yass",
    "email": "adminededdddd@gmail.com",
    "matricule": "dd88",
    "password": "Yasser123+",
    "profile": "USER"
}
```
```
DELETE:  /api/user/{id} (Requires admin role) | Status: 404 - msg: user_not_found, 200 msg: user_deleted
```

## Service
```
GET:     /api/services (Get all services) | Status: 200, 404 - msg: no_services_found
         /api/services/{id} (Get service by id) | Status: 200, 404 - msg: service_not_found
         /api/services/getByStation/{id} (Get services by station id) | Status: 200, 404 - msg: no_service_found
POST:    /api/services/ | Status: 400 - msg: service_not_added, service_already_exists, 201 - msg: service_added
         /api/services/addToStation/{id} (Requires admin role) | Status: 400 - msg: service_not_added, 201 - msg: service_added
PUT:     /api/services/{id} | Status: 400 - msg: service_not_updated, 200 - msg: service_updated
DELETE:  /api/services/{id} (Requires admin role) | Status: 400 - msg: service_not_deleted, 200 - msg: service_deleted

An example of Service body: (POST, PUT)
{
    "nom_service": "Service 1",
    "description": "Description 1"
    "station": {  ************Optional could also be null and set later on or use addToStation endpoint
        "id": "1",
    }
}

POST: /api/services/addToStation/{id}

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
    "nom_station": "Station 1",
    "adresse": "Description 1",
    "services":[  ***Optional if the service is not yet created
    {
    "id": "1",
    }
    ]
}

Response of getInfo:
{
    "carburant": [
        {
            "nomCarburant": "GASOIL 50",
            "prixCarburant": 13.45,
            "percentChange": "14.47%",
            "increase": true
        },
        {
            "nomCarburant": "ESSENCE 100",
            "prixCarburant": 12.15,
            "percentChange": "-16.38%",
            "increase": false
        }
    ],
    "chiffreToday": 0.0,
    "citerneJaugage": [
        {
            "nomCiterne": "CITERNE 1",
            "jaugage": 10000.0,
            "percentageLevel": "100.0",
            "nomProduit": "GASOIL 50"
        },
        {
            "nomCiterne": "CITERNE 2",
            "jaugage": 10000.0,
            "percentageLevel": "100.0",
            "nomProduit": "ESSENCE 100"
        }
    ]
}

```
## Pompe
```
GET:     /api/pompe (Get all pompes) | Status: 200, 404 - msg: no_pompes_found
         /api/pompe/{id} (Get pompe by id) | Status: 200, 404 - msg: pompe_not_found
POST:    /api/pompe/ | Status: 400 - msg: pompe_not_added, 201 - msg: pompe_added
         /api/pompe/setCiterne | Status: 404 - msg: pompe_not_found, citerne_not_found / 400: citerne_pompe_not_added / 200 - msg: citerne_added
PUT:     /api/pompe/{id} | Status: 400 - msg: pompe_not_updated / 404: pompe_not_found / 200 - msg: pompe_updated
DELETE:  /api/pompe/{id} | Status: 400 - msg: pompe_not_deleted, 200 - msg: pompe_deleted

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
    
    
-- Set citerne to pompe /api/pompe/setCiterne   
Body:
{
    "idCiterne": 1,
    "idPompe": 1
}
```
## Citerne
```
GET:     /api/citerne (Get all citernes) | Status: 200, 404 - msg: no_citernes_found
         /api/citerne/{id} (Get citerne by id) | Status: 200, 400 - msg: citerne_not_found
POST:    /api/citerne/ | Status: 400 - msg: produit_does_not_exist, produit_not_in_station, 201 - msg: citerne_added
PUT:     /api/citerne/{id} | Status: 400 - msg: citerne_not_updated, 200 - msg: citerne_updated
DELETE:  /api/citerne/{id} | Status: 400 - msg: citerne_not_deleted, 200 - msg: citerne_deleted

An example of citerne body: (POST, PUT)
{
    "nom_citerne": "CITERNE 2",
    "capaciteMaximale": 10000,
    "capaciteActuelle": 1000, ***Optional default is capaciteMaximale (Use it only if you want to set a specific value on update)
    "produit": {
        "id_produit": 1 *** Product should belong to a service that belongs to a station
    },
    "station":{
        "id": 1
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
    "unite": "Litre"
}
```

## Produit
```
GET:     /api/produit (Get all produits) | Status: 200, 404 - msg: no_produits_found
         /api/produit/{id} (Get produit by id) | Status: 200, 404 - msg: produit_not_found
POST:    /api/produit/ | Status: 400 - msg: produit_not_added, 201 - msg: produit_added
PUT:     /api/produit/{id} | Status: 400 - msg: produit_not_updated, 200 - msg: produit_updated
DELETE:  /api/produit/{id} | Status: 400 - msg: produit_not_deleted, 200 - msg: produit_deleted

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
         /api/releve/getByUser/{id} (Get releve by user id) | Status: 200, 404 - msg: user_not_found, no_releves_found
POST:    /api/releve/ | Status: 400 - msg: releve_not_added, pompe_already_in, pompe_already_out / 201 - msg: releve_added
PUT:     /api/releve/{id} (TO DO)
DELETE:  /api/releve/{id} (TO DO)

An example of releve body: (POST, PUT) ***** ! IMPORTANT ! --- It will only be submitted if the current time corresponds to the time of PompeUser *****
{
    "compteur": 1555,
    "pompeUser":{
        "idPompeUser": 3 ***** Get from /api/user/getPompes/{id} *****
    }
}

An example of GET: /api/releve/getByPompeUser/{id} response:
{
"releve": true
}

```
## Responses & status codes
```
Each error response will have a body with a message and a status code.

Example of error response:
{
    "msg": "user_not_found"
}

Messages are:

user_not_found
password_not_match
authentication_success
no_users_found
no_station_assigned_to_user
no_pompes_assigned_to_user
pompe_not_found
pompe_not_updated
pompe_not_added
citerne_not_found
citerne_not_added
citerne_not_deleted
citerne_deleted
citerne_pompe_not_added
pompe_user_not_updated
admin_cannot_have_pompe
manager_cannot_have_pompe
invalid_date
invalid_body
expired_token
refreshtoken_expired
user_already_has_pompes
pompe_unavailable_during_time_range
station_user_not_updated
station_user_not_added
station_user_created
station_user_not_found
station_not_found
no_citerne_found
no_pompe_found
no_users_found
no_station_found
no_service_found
service_already_exists
service_not_added
service_not_found
service_not_updated
service_does_not_exist
produit_not_added
produit_deleted
produit_not_deleted
produit_not_found
produit_not_updated
no_produit_found
no_releves_found
releve_not_found
pompe_user_not_found
date_not_in_range (date de releve)
releve_not_added
releve_not_updated
no_releves_found
refresh_token_expired
no_refresh_token_found
station_user_created
email_already_used
user_not_added
profile_not_found
user_not_updated
user_cannot_delete_himself
type_not_added
type_not_found
type_not_updated
type_does_not_exist
no_type_found
transactions_already_submitted


```
