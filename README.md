# to-do jsp
***
This is a simple application made for educational and demonstration purposes.

## Architecture
1. DAO - Data access layer
2. Controllers - Presentation layer

## Technologies
- Java 11
- Apache Maven
- Apache TomCat
- Apache Log4j2
- MySQL
- JDBC
- Http Servlet
- JSTL
- JSP
- HTML, CSS, XML, Bootstrap

## How to run a project
*WARNING* Installed TomCat and MySQL is compulsory for this project.
1. Add a tomcat local configuration in IntelliJ (Tomcat server - local, deployment - war 
exploded, application context: /)
2. Run SQL script located in ```src\main\resources\init_db.sql``` to set up a database for this project.
3. Configure ```src\main\java\todo\util\ConnectionUtil.java``` with your ```URL```, ```USERNAME```, ```PASSWORD``` and ```JDBC_DRIVER```.
4. Import Maven dependency from pom.xml.
