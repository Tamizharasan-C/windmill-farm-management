# windmill-farm-management


## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

mvn install

java -jar target\windmill-farm-management-0.0.1-SNAPSHOT.jar

1. POST http://localhost:8080/windmill/register

	{
		"id":"1234567890123456",
		"name":"ABC windmill company",
		"address": "Chennai",
		"lat" : 123.45,
		"lon":456.78
	}
	
2. POST http://localhost:8080/windmill/energy
	{
	"windmillId":"1234567890123456",
	"electricityGenerated":18
	}
	
3. GET http://localhost:8080/windmill/report/1234567890123457 


## Additional Docs
![Code Coverage Report](https://github.com/Tamizharasan-C/windmill-farm-management/blob/master/Code%20Coverage.png)
