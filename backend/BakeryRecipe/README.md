## Feature

### Register

url: [localhost:8080/api/authentication/register]()

json:

    {
    "name": "user2",
    "email": "user@gmail.com",
    "dob": "2001-10-17",
    "username": "user2",
    "password": "123456",
    "avatar": null,
    "roles": ["USER"]
    }

### Login

url: [localhost:8080/api/authentication/login]()

json:

    {
    "username": "user2",
    "password": "123456"
    }   