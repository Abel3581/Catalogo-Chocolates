# Catalogo-Chocolates
Back-End con java, Spring boot para una tienda de ventas de chocolates.<br>
Version: En desarrollo.<br>
PROXIMATE UML...


### PROJECT SETUP

- Postman
- Maven
- JDK 11
- MySQL
- Spring boot
- jpa

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
| 1  |  ROLE_ADMIN  |  ADMIN  |
| 2  |  ROLE_USER   |  USER   | 


Information to develop:<br>
The client wants an api rest to sell their products, they are asking for a login and registration, so that the buyer can 
select their products and these are saved in the cart once purchased, the buyer is sent an email with the receipt, they can pay 
in cash and for the paid market, the products must be displayed in 10 elements, there are categories, slides, security with spring security.
When a customer makes a purchase, a notification should appear advising the admin that a purchase has been made.