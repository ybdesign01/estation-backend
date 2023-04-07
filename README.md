# E-station Backend API
This is a Spring Boot based REST API

```
https://estation-api.herokuapp.com/api/
Routes without auth:
POST: /api/auth (Get your token)
These following routes require the Bearer token:
GET: /api/user (Get all users)
     /api/user/{id} (Get user by id)
     /api/user/getUser (Get user by token)
```
