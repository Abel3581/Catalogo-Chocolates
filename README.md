# Catalogo-Chocolates
Back-End con java, Spring boot para una tienda de ventas de chocolates.<br>
Version: En desarrollo.


### PROJECT SETUP

- Postman
- Maven
- JDK 11
- MySQL

To run the project execute:

`mvn spring-boot:run`


### USERS SEED

| email              | role  |  
|--------------------|-------|
| ADMIN{id}@test.com | admin | 
| USER{id}@test.com  | user  | 

By default, 10 users with admin role and 10 users with user role will be created where the {id} in
the email is a number from 0 to 10 per role. All the users have "tienda1234" as password.

### ROLES SEED

| id |  description |  name   |
|----|--------------|---------| 
| 1  |  ROLE_USER   |  USER   |
| 2  |  ROLE_ADMIN  |  ADMIN  | 
