# Rainy Hills
    This project is part of a test for a software engineer position. 
    There is a PDF file (RainyHills.pdf) that explains the challenge.

## Architecture
    The project is a back-end server that provides a REST and JSF interface 
    for expose the services.
    
* Stack: 
    * WildFly Swarm 2017.11.0
    * Java 8
    * JSF / PrimeFaces
    * Junit / Mockito / JaCoCo
    
    
## Test Structure
## Unit Test & Coverage
	It was defined the threshold in 90% of coverage, using JaCoCo plugin (currently the coverage is 100%)
### How to Generate Cover Report
    mvn clean test jacoco:report
    firefox target/site/jacoco/index.html

## How to Run
### Requirements
* Java 8 - JDK
* Maven

### Execution Commands
    mvn package wildfly-swarm:run 
    	
	In the end of execution, access the link http://localhost:8080/faces/index.xhtml 
	
	The package will be available for deploy in target/VolumeSystem.war
	
	Also, the package is deployed on Heroku and can be accessed 
	using the link http://sysvolume.herokuapp.com/faces/index.xhtml

	For the rest service, it could be 
	accessed by the URL 
	http://sysvolume.herokuapp.com/rest/volume?columns=6,5,6 or using localhost with the same url.