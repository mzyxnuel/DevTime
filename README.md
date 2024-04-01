# TimeCode

## Project Description
TimeCode aims to enhance the efficiency and productivity of software developers by providing detailed analysis of time spent during software development. By offering a comprehensive overview of time spent on specific projects, programming languages used, and activities performed during development sessions, TimeCode provides detailed analysis to help developers better understand their work habits and adopt more efficient practices.

<img src="https://i.ibb.co/7NFcHzD/Time-Code-01-04-2024-20-20-32.png" />
<img src="https://i.ibb.co/RQtjWNP/Time-Code-01-04-2024-20-21-23.png" />
<img src="https://i.ibb.co/3sSwpZf/Windows-Power-Shell-01-04-2024-20-22-03.png" />

### Detailed Time Analysis:
Through the TimeCode interface, developers can view detailed analysis of time spent on specific projects, programming languages used, and activities performed during development sessions. This enables developers to identify areas where they could improve efficiency and optimize time spent on work.

In summary, the TimeCode project aims to provide software developers with a comprehensive tool for time tracking and analysis of work habits, helping them improve their productivity and overall performance.

## System Architecture
### Overview:
The system architecture follows the MVC (Model-View-Controller) pattern, where the client-side manages the logic of the web application. This organizational structure divides the code into three main components:
- **Model:** Represents the application data and associated business logic.
- **View:** Responsible for presenting data to users in an understandable and interactive format.
- **Controller:** Coordinates user interactions, handling requests from the user interface, manipulating models, and choosing views to display.

### Key Components:
- **IDE Monitor:** Monitors IDE for data saving, implements runnable to be used via thread.
- **XML to Java Object Transformation Class:** Transforms XML to Java objects and vice versa.
- **Class for Sending XML Data to API:** Handles sending XML data to the API.

### Interactions between Components:
- JavaFX uses the @FXML annotation on Java attributes to identify the corresponding graphical component by attribute name.

<img src="https://i.ibb.co/PmRPvt7/code.png" />

### Architecture Diagram:
<img src="https://i.ibb.co/m61F82M/Untitled-Diagram-1.png" />

## Web Service Description
The web service is designed to record and, when necessary, send information related to project development to which the user has access. It is developed with a REST architecture, using XML for data exchange (the client sends XML only in case of POST requests). The web service also employs specific XSD for data validation received.

### Features:
- **Authentication:** Allows users to register with the database and access their personal data through POST requests to login.php and signup.php pages, respectively.
- **Activity Registration:** Allows users to send project-related data they have worked on through a POST request to the activity.php page.
- **Data Analysis and Sending:** Provides users with a report on the progress of their activities through a GET request to the activity.php page.

## Project Requirements
### Functional Requirements:
- **Detailed Time Analysis:** TimeCode must provide developers with detailed analysis of time spent on specific projects, programming languages used, and activities performed during development sessions.
### Non-Functional Requirements:
- **Security:** Information collected and analyzed by TimeCode must be protected to ensure developers' data privacy and security.
- **Scalability:** The system must be designed to support a growing number of users and data without compromising its functionality or performance.
- **Usability:** Must offer an intuitive and easy-to-use user interface, allowing developers to easily access analyses and manage their data.
- **Reliability:** Must ensure the accuracy and precision of the provided analyses, minimizing human error and inaccuracies in collected data.
- **Integration:** The system must be able to easily integrate with other software development tools and platforms, allowing developers to effectively use TimeCode within their existing workflow.

## Implementation
### Programming Languages, Frameworks, and Libraries Used:
#### Client:
- Java (version 21.0.2)
- Maven
- JavaFX
- JAXB
- xjc
- AtlantaFX
- dotenv-java

#### Server:
- PHP
- PDO
- MySQL

### Description of the Main Development Phases

#### Design and Database Design:
We start with the logical design of the database, where each table and relationship is carefully thought out to ensure a solid and scalable architecture. This step is fundamental to ensure that our system can efficiently manage data without compromising performance. Subsequently, we proceed with the physical design, where every detail is optimized to maximize database efficiency.

#### Creation of XSD Schemas and XJC Classes:
We use JAXB to create XSD schemas that clearly and precisely define the structure of XML data. This process allows us to map XML schemas to Java classes, making XML data accessible and manipulable as Java objects.

#### Creation of EditorMonitor Class and Creation of View (Application FXML):
We develop a Java class that serves as an IDE monitor for our application. For the creation of views, we adopt FXML to declaratively define the user interface, separating the application's logic from its presentation for greater clarity and maintainability.

#### Creation of Client-Side and Server-Side Authentication:
We implement robust authentication mechanisms on both the client and server sides, ensuring the security of our applications.

#### Creation of API and SQL Queries:
We develop a REST API to expose our application's services, using PDO to create SQL queries that interact effectively with the database.

#### Creation of API Connection through Java Controllers.

## Testing and Validation
### Testing Strategy:
- Routes testing: Hoppscotch
- SQL Queries testing: Database Client JDBC
- Graphic Interface testing: Sample SQL code available [here](https://github.com/mzyxnuel/TimeCode/blob/master/.tests/sample-test.sql).

## Deployment and Project Management
Git/GitHub provided an integrated platform supporting project automation, monitoring, and management, significantly contributing to the project's success throughout its software development lifecycle.

## Conclusions
In conclusion, adding an extension for Visual Studio or creating a monitor for other IDEs is just the beginning. Future improvements could include more advanced code analysis, optimized project management, and integrated cloud tools. These developments will make the development experience even smoother and more powerful, simplifying developers' work and improving the quality of the produced code.

## Appendices
### Useful References or Links:
- [AtlantaFX](https://mkpaz.github.io/atlantafx/)
- [Hoppscotch](https://hoppscotch.com/)
- [JAXB Dependency](https://mvnrepository.com/artifact/jakarta.xml.bind/jakarta.xml.bind-api)
- [dotenv-java](https://github.com/cdimascio/dotenv-java)
