This is a microservice designed to model a school system. It supports CRUD (Create, Read, Update, Delete) operations for three main entities: **Students**, **Teachers**, and **Subjects**. These entities have various relationships between them (One-to-Many, Many-to-Many), which can also be updated via API requests. 

The application is built with **Spring Boot** and follows best practices such as the use of **DTOs** (Data Transfer Objects) and **mappers** to ensure clean and maintainable code.

## Technologies Used

- **Spring Boot** for building RESTful web services
- **Java**
- **Maven** for dependency management
- **Git** for version control
- **DTOs** for data abstraction
- **Lombok** for reducing boilerplate code

## API Endpoints

Below are the available API endpoints for each entity, including the specific actions you can perform on **Students**, **Teachers**, and **Subjects**.

### Student Controller (Base path: `/api/v1/student`)

- **GET** `/api/v1/student`:  
  Retrieves a list of all students.
  
- **POST** `/api/v1/student`:  
  Adds a new student. Expects a `StudentDTO` object in the request body.
  
- **DELETE** `/api/v1/student/{studentId}`:  
  Deletes a student by their ID.
  
- **PUT** `/api/v1/student/{studentId}`:  
  Updates an existing student. Expects a `StudentDTO` object in the request body.

  

### Teacher Controller (Base path: `/api/v1/teacher`)

- **GET** `/api/v1/teacher`:  
  Retrieves a list of all teachers.
  
- **POST** `/api/v1/teacher`:  
  Adds a new teacher. Expects a `TeacherDTO` object in the request body.
  
- **DELETE** `/api/v1/teacher/{teacherId}`:  
  Deletes a teacher by their ID.
  
- **PUT** `/api/v1/teacher/{teacherId}`:  
  Updates an existing teacher. Expects a `TeacherDTO` object in the request body.



### Subject Controller (Base path: `/api/v1/subject`)

- **GET** `/api/v1/subject`:  
  Retrieves a list of all subjects.
  
- **POST** `/api/v1/subject`:  
  Adds a new subject. Expects a `SubjectDTO` object in the request body.
  
- **DELETE** `/api/v1/subject/{subjectId}`:  
  Deletes a subject by its ID.
  
- **PUT** `/api/v1/subject/{subjectId}`:  
  Updates an existing subject. Expects a `SubjectDTO` object in the request body.

- **PUT** `/api/v1/subject/{subjectId}/student/{studentId}`:  
  Enrolls a student in a subject.
  
- **PUT** `/api/v1/subject/{subjectId}/remove_student/{studentId}`:  
  Removes a student from an enrolled subject.
  
- **PUT** `/api/v1/subject/{subjectId}/teacher/{teacherId}`:  
  Assigns a teacher to a subject.
  
- **PUT** `/api/v1/subject/{subjectId}/remove_teacher`:  
  Removes the assigned teacher from a subject.


## Error Handling

All errors are logged and responses are returned with appropriate HTTP status codes. Each response includes the following structure:
- **Data**: The returned data or error message
- **HTTP Status**: The result of the API call (e.g., `200 OK`, `400 Bad Request`)
- **Timestamp**: The time at which the API was called
