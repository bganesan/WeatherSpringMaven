WeatherSpringMaven
==================

The spring and maven based web application to display the weather data based on zipcode in front end.

I have used JSON data as the dataformat from web service.

Reasons for using JSON:
* JSON is simple and lean compared to XML, hence processing of the JSON is faster than XML which improves the response time if the application.
* RESTful API's have to be faster, reliable and easy to handle the data. JSON is better than XML in these areas.
* XML enforces schema for parsing and will be used in places where data needs to be highly structured. JSON can be used without schema sharing between server and client.
* JSON parsing is faster compared to XML parsing.

Test case execution:
* Open command line or terminal.
* Navigate to the project directory.
* Execute 'mvn test' to execute tests.
*
Web application setup requirement:

Prerequesite:
  * Maven 3
  * Apache Tomcat 7
  
Steps for setup:
  * Copy the settings.xml into .m2 folder of your machine.
  * Copy the tomcat-users.xml to the <TOMCAT_HOME>/conf folder.
  * Checkout the code from the repository

Steps to run the web application:
  * Open command line or terminal.
  * Navigate to the <TOMCAT_HOME>/bin folder.
  * Execute 'startup.bat'
  
  * Open command line or terminal.
  * Navigate to the project directory.
  * Execute 'mvn clean install tomcat7:deploy' command.
  * Open a browser and hit 'http://localhost:8080/WeatherSpringMaven/weather' URL.
  * Enter zipcode and view the weather data.
  
Steps to undeploy the web application:
  * Open command line or terminal.
  * Navigate to project directory.
  * Execute 'mvn tomcat7:undeploy' command.
