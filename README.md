# redditClone Backend

The redditClone backend has been created to allow for users to login, sign up, create/delete posts and comments. 

## Table of Contents
* [Dependencies and Installation](#dependencies-and-installation)
* [Project Deliverables and Timeline](#project-deliverables-and-timeline)
* [General Approach](#general-approach)
* [User Stories](#user-stories)
* [ERD](#erd)

## Dependencies and Installation
- Maven
- TomCat
- Spring
- Hibernate
- Postgres
- Junit
- Mockito

## Project Deliverables and Timeline
10/21
- User stories
- ERD
- Add dependencies

10/22
- Sign up functionality
- Login functionality
- Profile functionality
- Add security

10/23
- Post functionality (GET/POST/DELETE)
- Comment functionality (GET/POST/DELETE)

10/24
- Integrate backend with frontend application
- Testing

10/25
- Testing


## General Approach

The general approach taken was to work collaboratively to ensure that all project deliverables would be met. We began with the planning phase where we developed a list of endpoints that would need to be created and discussed the best way to structure the relationship between the USER, POST and COMMENT objects and created a deliverables timeline. We focused on building the backend within the first few days so that we would have ample time to integrate, create unit tests and troubleshoot any unforeseen errors.

A few of the challenges that we encountered and how we resolved those issues:

- Issues relating to the relational mapping of our tables - initially we were setting bidirectional relationships, where the child would be added back to the parent table since we were unaware the Hibernate would automatically handle this for us. This led to many errors where the system would believe that there were duplicate values. 

- Challenges with CORS when integrating the front end with the back end. This was resolved by adding a CorsConfigurationSource to the SecurityConfig file.

- [Testing]

We spent a significant amount of time focused on testing - both writing tests and checking the endpoints to make sure that the functionality was maintained.

## User Stories
Link:   https://www.pivotaltracker.com/n/projects/2407513

## ERD
Link:   https://www.lucidchart.com/documents/edit/42f801db-75bf-4909-b0cf-0330d492fe62/0_0?shared=true&docId=42f801db-75bf-4909-b0cf-0330d492fe62


