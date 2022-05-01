# Catalogo-Chocolates
## Back-End with java, Spring boot for a chocolate shop.<br>
### Version: Under development.<br>
#### NEXT UML...


### PROJECT SETUP

- Postman
- Maven
- JDK 11
- MySQL
- Spring boot
- jpa

## Autores :star_struck:

- Abel Acevedo- *Desarrollador Backend, Tester, Documentación* - :alien:[Abel3581](https://github.com/Abel3581)


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
The client wants a rest of api to sell his products, he is requesting a login and registration, so that the buyer can
select your products and these are saved in the cart once purchased, an email is sent to the buyer with the receipt, you can pay
in cash and by paid market, the products must be paginated by 10 elements, there are categories, slides, security with springboot.
When a customer makes a purchase, a notification should appear informing the admin that a purchase has been made.
To manage images, AWS can be used to save and consume them.
<br>
For the Front End it would be ideal to use React with bootstrap.
<br>
Any java and react developer will be welcome to participate.
