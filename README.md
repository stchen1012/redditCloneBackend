# redditClone Backend

The redditClone backend has been created to allow for users to login, sign up, create/delete posts and comments. 

## Table of Contents
* [Technologies Used](#technologies-used)
* [Dependencies and Installation](#dependencies-and-installation)
* [User Stories](#user-stories)
* [Project Deliverables and Timeline](#project-deliverables-and-timeline)
* [General Approach](#general-approach)
* [Design Decisions](#design-decisions)
* [Coverage](#coverage)


## Technologies Used
- Java - utilized Eclipse as the IDE
- Git / GitHub - to host our code for version control and a shared working repository
- Pivotal Tracker - to write user stories and keep track of the technical requirements

## Dependencies and Installation
- Maven
- Tomcat
	- Apache Tomcat, created by the Apache Software Foundation, is an open-source HTTP server and a container for Java Servlets and Java Server Pages (JSPs), technologies that allow us to create dynamic webpages in Java. Tomcat provides a server for us to run them.
	- Download Tomcat, making sure to get the latest version. Unzip it and move it to the folder of your choice.
	- **Adding Tomcat to Eclipse**
		- Let's navigate back to Eclipse and connect Tomcat so that we can use it to run our Eclipse projects.
		- If the "Servers" tab is not already added to the bottom section of Eclipse:
		- Go to Eclipse > Window > Show View > Servers.
		- Click on the link provided to create a new server.
		- Select Tomcat v9.0 under Apache.
		- Select the folder where you moved Apache Tomcat.
		- Keep clicking "Next" until you get to "Finish."

Refer to the dependencies in the pom.xml file for the following:
- Spring
- Hibernate
- Postgres
- JUnit
- Mockito

## User Stories
Link:   https://www.pivotaltracker.com/n/projects/2407513

## Project Deliverables and Timeline
**10/21**
- User stories
- ERD
- Add dependencies

**10/22**
- Sign up functionality
- Login functionality
- Profile functionality
- Add security

**10/23**
- Post functionality (GET/POST/DELETE)
- Comment functionality (GET/POST/DELETE)

**10/24**
- Integrate backend with frontend application
- Testing

**10/25**
- Testing

## General Approach

The general approach taken was to work collaboratively to ensure that all project deliverables would be met. We began with the planning phase where we developed a list of endpoints that would need to be created and discussed the best way to structure the relationship between the USER, POST and COMMENT objects and created a deliverables timeline. After generally discussing user stories and utilizing pivotal tracker to allocate and prioritize work, we focused on building the backend within the first few days so that we would have ample time to integrate, create unit tests and troubleshoot any unforeseen errors for the remainder of the project.

A few of the challenges that we encountered and how we resolved those issues:

- Issues relating to the relational mapping of our tables - initially we were setting bidirectional relationships, where the child would be added back to the parent table since we were unaware the Hibernate would automatically handle this for us. This led to many errors where the system would believe that there were duplicate values. 

- Challenges with CORS when integrating the front end with the back end. This was resolved by adding a CorsConfigurationSource to the SecurityConfig file.

We spent a significant amount of time focused on testing - both writing tests and checking the endpoints to make sure that the functionality was maintained.

## Design Decisions

**API Table**

![api](imgs/api_table.PNG)


**ERD / Association Choices**

![erd](imgs/erd.PNG)

- **Post to User (Many-to-One Unidirectional)** - A post is dependent on a user to exist and a call to a post will require a username on load. A user on the other hand can exist independently from a post and a call to a user does not necessarily require a list of posts. Therefore, a unidirectional approach was taken where a post will have a reference to a user.


- **Post to Comment (Bidirectional) -**  A comment is dependent on a post to exist and our front-end requires all comments to be loaded when a post is called. Therefore a bidirectional approach where both sides have a reference to each other.


- **Comment to User (Many-to-One Unidirectional) -** A comment is dependent on a user to exist and a call to a comment will require a username on load. A user on the other hand can exist independently from a comment and a call to a user does not necessarily require a list of comments. Therefore, a unidirectional approach was taken where a comment will have a reference to a user.


- **User to User Profile (One-to-One Unidirectional) -** A user-profile may only be accessed through a user. Therefore, a one-to-one unidirectional approach was taken where a user will have a reference to a user-profile.


**Error Handling**
- **Incorrect Login Details** - Exception handling around login was created. If a user submits an incorrect username, password, or both. Exception is handled and a 401 status is passed along with a JSON object containing a message stating "Incorrect username or password". Login exception is handled through the exception handler. See below for a sample handler.


- **Deleting Post/Comment** - Exception handling around deleting post/comments not made by the user was created. A postId/commentId is passed along the URL to delete a post/comment. Therefore, a check is made internally to determine if the currently logged in user (obtained through the JWT token) agrees with the user attached to the post/comment id. If the logged in user is different from the original poster, a DeleteException is thrown and the post is not deleted. See below for a sample handler.

```java
public class UserController {
...
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleExcption(IncorrectLoginException err){
		ErrorResponse error = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), err.getMessage());
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}
...
}
```

## Coverage

![erd](imgs/coverage.PNG)

We achieved >80% coverage on all areas except the config package.


