# M1-JEE-Project - Interns Supervision Solution (I2S)
## Informations
Group composed of : 
- Mathieu Cantagrel
- Théo Delettre
- Vincent Dubois
- Vincent Mouillon
- Karsten Roy
- Victor Tang  

Group : SE2
## Description
JEE Web Application that help any teacher at EFREI Paris supervise easily the interns assigned to him/her.

## Technologies/framework used

Built with
- JAVA
- CSS
- JS
- JEE Plateform

IDE 
- IntelliJ IDEA  

Database : 
- PostgreSQL


## Features
This project enables any teacher to supervise the internships of his student.
He can :
- login with his Efrei email address and his password
- see, on the homepage, all the internship assigned to him
- filter this internship by years, name of the student and keywords.
- update the information of the internship on the homepage
- see the details of the internship on the mission details page
- update the information of the internship on the mission details page
- logout

## Code Example

## Installation
### Database 

#### Setup database with PostgreSQL

For the first time, you need to follow every step. After that, you'll only need to run scripts from [Database setup](#database-setup).

#### User setup

You need to create a user (`adm`) to interact with the database.  We assume that you know how to run script to PostgreSQL engine through the shell or the IDE.

Run the script `User Setup.sql` while being connected to Postgres database.

#### Database setup

The creation of the database is separated in two scripts, because they will not be executed in the same database.

#### Database in itself

Run the script `DB create.sql` while being connected to Postgres database.

#### DDL

Run the script `JEE db.sql` while being connected to st2eedb database.

#### Populate

Run the script `Populate.sql` while being connected to st2eedb database.

#### Connect to the database with Java

The login, password and url connection to the database are stored inside the db.properties file.   
Those strings are used in the DataServices classes.

## Tests

## How to use?
### Login
You first need to login in the login page :
![login page](./Screenshot/LoginPage.JPG)

If you make a mistake in your credentials or if you let field empty, an error message will be displayed.  
![Error empty fields](./Screenshot/ErrorEmptyFields.JPG)  

![Error bad credentials](./Screenshot/ErrorCredentials.JPG)

### Homepage
Once you are logged in, you have access to some actions on the home page.  
![Homepage](./Screenshot/Homepage.JPG)
First, you have an overview of all the interns that are assigned to you in the table.  
You can modify every information and validate your change by clicking on the "modify" button at the beginning of the line.  
![Table](./Screenshot/Internship.JPG)
Also, you can see more details about an internship by clicking on the "details" button on each line. This will redirect you to the mission details page.  
You can filter the internships by the year, keywords or name of the intern and validate your choice by clicking on the "Search button".  
![Filter](./Screenshot/Filter.JPG)  
Last, you can logout of your account by clicking on the logout button placed in the top right corner, just right next to your name. You will be redirect to the login page.  

### Details
On the mission details page, you will be able to modify other information.
Company :
- Name of the company
- Intern supervisor inside the company
- Begin date
- End date
- Address of the company   
 ![Company](./Screenshot/Company.JPG)   
 
 Student :
 - Group
 - First name
 - Last name
 - Email
 - URL of the linkedin profile   
![Student](./Screenshot/Student.JPG)   

Internship :
- Report's title
- Description
- Your comments
- The student comments  
![Internship](./Screenshot/InternshipDetails.JPG)   

Skills and keywords : 
- Add keywords
- Add skills  
![Skills and keywords](./Screenshot/Skills_keywords.JPG)   

You must click on the "modify" or "add" button of each section you have modified to validate your changes.
A message will be displayed each time you modify something to keep you inform about the changes. 
![Error database](./Screenshot/Error_db.JPG)  

![Success database](./Screenshot/Success_db.JPG)  
   


