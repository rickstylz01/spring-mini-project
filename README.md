# spring-mini-project

## Library Management System (Class Project)

This is a class project for a Library Management System, created as part of a course or class assignment. The project is designed to demonstrate my understanding of software development concepts, particularly in the context of building a web application using Spring Boot.

## Project Overview

The Library Management System is a web-based application that allows users to manage their personal book collections. It provides core functionalities such as user registration, authentication, and book management.

![Library Management System Diagram](https://github.com/rickstylz01/spring-mini-project/assets/27748809/9428aa1c-3c28-43cc-8e95-bb62af30908d)


| Endpoint                     | Description                               |
|------------------------------|-------------------------------------------|
| POST /auth/users/register/    | Create a new user account                  |
| POST /auth/users/login/       | Log in and authenticate a user             |
| POST /api/books/              | Create a new book                          |
| GET /api/books/               | Retrieve a list of all books               |
| GET /api/books/{bookId}       | Retrieve a specific book by its ID         |
| PUT /api/books/{bookId}       | Update the details of a specific book      |
| DELETE /api/books/{bookId}    | Delete a specific book by its ID           |




## User Stories
### User Registration and Authentication

1. As a new user, I want to register for an account using a unique email address and password so that I can access the library system.

2. As a registered user, I want to securely log in with my email and password to access my personal book collection.

3. As a registered user, I want the system to remember my login status so that I don't have to log in every time I visit the website.

4. As a registered user, I want the option to log out of my account to ensure the security of my data.

### Book Management

5. As a user, I want to add a new book to my collection by providing details such as title, author, price, and genres.

6. As a user, I want to view a list of all the books in my collection, including their details.

7. As a user, I want to view the details of a specific book in my collection, including its title, author, price, and genres.

8. As a user, I want to edit the details of a book in my collection, such as updating the title, author, or price.

9. As a user, I want to delete a book from my collection if I no longer own it or want to track it.

### Error Handling

10. As a user, I want to receive a clear error message if I enter incorrect login credentials during the authentication process.

11. As a user, I want to be notified if I attempt to register with an email address that is already in use.

12. As a user, I want to see informative error messages if there are issues with adding, updating, or deleting books in my collection.

### Security

13. As a user, I want my personal data and book collection to be secure and accessible only through proper authentication.

14. As a user, I want the system to prevent unauthorized access to my account and book collection.

15. As a user, I want to be automatically logged out after a period of inactivity to ensure the security of my account.

### Additional Features (Optional)

16. As a user, I want the option to categorize my books into genres for better organization.

17. As a user, I want to search for specific books in my collection based on criteria such as title, author, or genre.

18. As a user, I want to see statistics or visualizations of my book collection, such as the total number of books or the distribution of genres.

19. As a user, I want to receive recommendations for new books to add to my collection based on my existing books or reading preferences.

20. As a user, I want to mark books as read or unread to keep track of my reading progress.



## Features

- **User Registration and Authentication:** Users can register with unique email addresses and securely log in using JWT (JSON Web Token) authentication.

- **Book Management:** Users can perform CRUD (Create, Read, Update, Delete) operations on their books. Each book can have attributes such as title, author, price, and genre.

- **User-specific Data:** All book data is associated with the logged-in user, ensuring data privacy and separation.

- **Custom Exceptions:** The application handles information conflicts and not-found scenarios with custom exception handling and appropriate HTTP status codes.

## General Approach

The aim of this project was to put all of the skills that I recently learned about Java, OOP, and Spring into practice. 
I began by creating an **ERD** diagram to clearly see the relationships that each entity would have with each other. 
Once I completed this diagram I created an MVC and some user stories to get a layout/blueprint of goals to work towards.
Once I had that established I started by setting up my project dependencies using the [Spring Initializer](https://start.spring.io/).
After that, I set up my dev profile and properties to be able to work with my H2 database. 

My next move was to create my class models. I created and connected my models based on the relationships I came up with in my ERD diagram.   
Afterward, I moved on to creating the business logic for the user first to be able to register and then log in. To accomplish this I needed to establish some authentication/authorization and password encryption.
I began with password encryption and utilized the PasswordEncoder provided by the Spring security package.  

From there I moved on to securing endpoints and only allowing the `/auth/users/login` and `/auth/user/register` to be freely accessible. I used these endpoints to test my business logic and controller for a user to be able to register and log in.
I created user-seeded _dummy_ data to avoid the need of having to always register a user to test my endpoints.

Then I recreated these steps to create a book.


## Getting Started

To get started with this class project, follow these steps:

1. Clone the project repository provided by your instructor or download the project files if available.

2. Open the project in your preferred Integrated Development Environment (IDE) with Spring Boot support.

3. Configure the `application.properties` or `application.yml` file with your database connection details. You may also need to set up other application properties as specified in the project requirements.

4. Build and run the application using Maven or your IDE's build tools.

5. Access the application at `http://localhost:9096` in your web browser for testing and demonstration.

## Project Structure

The project follows a typical Spring Boot project structure with packages for models, repositories, services, controllers, and security components. You may have additional packages or classes based on your project's specific requirements.

## Dependencies

- Spring Boot
- Spring Security
- Spring Data JPA
- H2 Database Engine
- JSON Web Tokens (JWT) for authentication

## Usage

1. Users can register for an account using their unique email addresses.

2. After registration, users can log in securely using their credentials.

3. Once logged in, users can perform book management operations, including adding, editing, deleting, and viewing book details.

## Credits

- Created as a class project by Rick Maya for Unit 2: Spring Boot at General Assembly/Interapt.
