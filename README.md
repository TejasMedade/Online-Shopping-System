# Online-Shopping-System


![Logo](https://nimbus-screenshots.s3.amazonaws.com/s/91e455fba18e9a844446391993ef3a15.png)


## WEB SERVICE : REST A.P.I.

The Shopper's Club API is a robust and reliable RESTful solution for online e-commerce platforms. It allows customers to browse and purchase products with a seamless and user-friendly interface. The API is designed to provide a wide range of functionalities for managing products, orders, and customer information.

The API offers powerful features for browsing products, including the ability to save items to a cart for future purchase. Additionally, the API supports CRUD operations for managing products and orders, making it easy for customers to manage their shopping experience.

Security is a top priority for the Shopper's Club API, and it includes user and admin validation and authentication to ensure secure transactions. Additionally, the API includes simplified exception handling to make it easy for developers to understand and resolve errors.

The primary goal of the Shopper's Club API is to provide customers with a seamless shopping experience. With this API, customers can easily find and purchase products without any hassle, making it an ideal solution for e-commerce platforms looking to improve their customer experience.


## Tech Stack

- JAVA
- SPRING
- SPRINGBOOT
- HIBERNATE
- MAVEN
- J.D.B.C
- MYSQL
- POSTMAN

## Dependencies

- SPRING DATA JPA
- SPRING BOOT DEVTOOLS
- SPRING WEB
- HIBERNATE
- MYSQL DRIVER
- VALIDATION
- LOMBOK
- SWAGGER UI



## Features

 - **Authentication and Authorization:** Implementing user and admin validation and authentication for secure transactions, using JWT token-based authentication.

- **CRUD operations:** Enabling CRUD operations for managing products and orders, including adding, updating, viewing, and deleting products and customer - accounts.
- **Product browsing and cart management:** Allowing customers to browse products and save them to a cart for future purchase, as well as updating and deleting products from the cart.
- **Order management:** Enabling customers to place, view, and cancel orders with filtering options, and for administrators to view all orders and customers.
- **Database management:** Providing functionality for administrators to manage the database, including adding, updating, and deleting products and administrators.
- **Exception handling:** Implementing simplified exception handling for easy understanding of errors.
- **Search and Filtering:** Search and filter functionality for orders, customers and products for ease of use.
- **Pagination:** Pagination feature allows to navigate through large amount of data easily
- **Data Transfer Objects:** Custom Request and Response Data Transfer Objects for all HTTP Requests to improve the overall performance of the API.
- **User-Friendly Experience:** The primary objective of the API is to provide a simple and user-friendly experience for customers and administrators.


## Administrator Functionalities
    
   - Login & Logout
   - View all Products
   - View all Orders of an User
   -  View all Customers
   -  Add New Products into Database
   -  Remove Products from Database
   -  Update Products in Database 
   -  Delete Admin from Database

## Customer Functionalities
    
  - Signup 
   -  Login & Logout
   -  Update all Personal Details and Address 
   -  View all Products with filtering options
   -  Add Products to Cart
   -  Update Products Quantity in Cart 
   -  Delete Products from Cart
   -  Empty Cart
   -  Add Order
   -  Cancel Order
   -  View Orders with filtering options
   -  Delete Customer Account
    




## Setting & Installation 

Install the Spring Tools Suite 
```bash
https://spring.io/tools
```

Install MySQL Community Server

```bash
https://dev.mysql.com/downloads/mysql/
```

Clone the Repository

```bash
git clone https://github.com/TejasMedade/Online-Shopping-System
```

Open MySQL Server
```bash
Create a New Database in SQL: "Online_Shopping_System" 
```

Create a Admin For Your Database

```bash
INSERT INTO ADMIN VALUES('1001','admin_email','admin_first_name','admin_last_name','admin_mobile_number','admin_password');
```

Note for Admin & User
```bash
Admin Id : Min=1000, Max=1010 ; User Id : Enter Your Registered Mobile Number For Login, User Validation and Authentication. 
```




## Run Locally


Go to the Project Directory

```bas
Open the Online_Shopping_System Folder with S.T.S
```

Go to **src/main/resources > application.properties** & change your username and password (MySQL server username & password)

```bash
spring.datasource.username="username"

spring.datasource.password="password"
```

To change the **Server Port**

```bash
server.port=8088
```

Go to **com.masai package > Online_Shopping_System.java**

```bash
Run as Spring Boot App !
```
Open the following URL for Swagger-UI 
```bash
http://localhost:8088/swagger-ui/
```

## ER Diagram


![Online Shopping Application(1)](https://user-images.githubusercontent.com/105907169/202779904-80050caf-b679-4b67-9afe-da67296fcb59.png)




## Demo





## URL
```bash
http://localhost:8088
```
## Admin Login Logout API Reference
![image](https://user-images.githubusercontent.com/105907169/202855307-82cc63ca-7183-4785-8b30-31794d6dc7cb.png)

## Customer Login Logout API Reference
![image](https://user-images.githubusercontent.com/105907169/202855339-e5f872f6-e120-42cb-9fe8-432fb7ed9018.png)

## Admin API Reference

![Screenshot 2022-11-25 at 12-54-24 Swagger UI](https://user-images.githubusercontent.com/105907169/203924387-d053fa23-1a77-4c03-b33d-45d3007360d1.png)

## Customer API Reference
![image](https://user-images.githubusercontent.com/105907169/202855233-53a77b6f-dd18-4a74-b615-5aeb16f15da3.png)

## Product API Reference
![image](https://user-images.githubusercontent.com/105907169/202855428-2f93dc36-0e11-41b4-b592-5138eebd3e0b.png)

## Address API Reference
![image](https://user-images.githubusercontent.com/105907169/202855062-1588b3a0-caa6-46b5-accd-6759e86f2e98.png)

## Cart API Reference
![image](https://user-images.githubusercontent.com/105907169/202855032-4b7c5510-b6c3-44e0-b496-e8d52d571165.png)

## Order API Reference
![image](https://user-images.githubusercontent.com/105907169/202855388-da648cc9-bf27-42a8-bb2f-9e19f67f63b4.png)



## Contributions

Contributions are always **Welcome** !

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **Greatly Appreciated**.

If you have any ideas on how to improve this resume, please feel free to fork the repository and submit a pull request. Your contributions, no matter how big or small, are greatly appreciated and will help to make this repository even better.

In addition to contributing to the repository, you can also connect with me for further development and collaboration on this API. Together, we can continue to improve and evolve the API to meet the needs of the community.

We encourage you to give the repository a star and we thank you for your interest in this project. 

Your support is greatly appreciated.

## 🔗 Contact Me
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://tejasmedade.github.io/)

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/TejasMedade)

[![twitter](https://img.shields.io/badge/twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/TejasMedade)


## Authors

- [Tejas Vilas Medade](https://github.com/tejasmedade)

- [Sumedh Ambhore](https://github.com/sumedh9561)

- Utkarsh Keshari

- Rajesh Biswas

- Abidesh Kumar

## Acknowledgements


- [Anand Singh Yadav](https://github.com/anandsinghyadav)

- [Masai School](https://www.masaischool.com/)
 
