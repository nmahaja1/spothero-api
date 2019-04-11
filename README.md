# Spothero RESTful API

Sample API to calculate Parking Price based on input criteria. This application uses the following technology stack:
1. Java
2. Spring Boot
3. Spring Actuator(in-built health check and metrics)
4. H2 In-memory DB
5. Swagger 2 
6. Docker 

Pre-requisites:

1. Gradle must be installed. Please refer to https://gradle.org/install/ for instructions pertaining to your operating system
2. JDK 8. Please refer to https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html for instructions pertaining to your operating system

**Disclaimer** : This code has not been tested in higher JDK versions.

# Instruction to run the code:

1. Open terminal. Clone the repository by typing the following command

```git clone https://github.com/nmahaja1/spothero-api.git```

2. Jump into the newly cloned repository by using cd command on terminal

**Note**: The app can be run using gradle or in a docker container. Please find below individual instructions for each case.

# Instructions to run using Gradle:

1. In order to run the app using gradle, run the following command:

```./gradlew build && java -jar build/libs/spothero-api-0.1.0.jar```

2. Wait until the terminal shows `Started Application in <<x>> seconds`

3. Open Chrome Browser. The **Swagger** link to the app is `http://localhost:8080/swagger-ui.html`

4. Create rate. Click `rate-controller`. Click `POST /rate Create Rate`. Click `Try it out`. 

Enter Input json as below and click `Execute` button:

```
[
   {
      "days":"mon,tues,thurs",
      "times":"0900-2100",
      "tz":"America/Chicago",
      "price":1500
   },
   {
      "days":"fri,sat,sun",
      "times":"0900-2100",
      "tz":"America/Chicago",
      "price":2000
   },
   {
      "days":"wed",
      "times":"0600-1800",
      "tz":"America/Chicago",
      "price":1750
   },
   {
      "days":"mon,wed,sat",
      "times":"0100-0500",
      "tz":"America/Chicago",
      "price":1000
   },
   {
      "days":"sun,tues",
      "times":"0100-0700",
      "tz":"America/Chicago",
      "price":925
   }
]
```

5. Calculate price (based on rates above and given business logic).

Click `price-controller`. Click `POST /price`. Click `Try it out`. 

Enter Input json as below and click `Execute` button:

```
{
	"startDateTime" : "2019-04-01T01:00:00-05:00",
	"endDateTime" : "2019-04-01T10:30:00-05:00"
}
```

6. Validate API based on different scenarios.

7. To view in-built *Metrics*, open URL `http://localhost:8080/actuator`. 


# Instructions to run the app in its own Docker container.


1. Before building the Docker image make sure Docker is running on your machine by executing the below command

```docker ps```

If you get a error message, it indicates that Docker is not set up correctly on your machine. Please set up Docker again.

2. Build docker image with Gradle with the following command

```./gradlew build docker```

3. Run the docker image created using the following command

```docker run -p 8081:8080 -t spotherodocker/spothero-api```

4. Open Chrome Browser. The *Swagger* link to the app is `http://localhost:8081/swagger-ui.html`. Follow the steps to use the app as mentioned in the Gradle section.


# References

1. https://medium.com/@shrikarvk/creating-a-docker-container-for-spring-boot-app-d5ff1050c14f [Docker For Spring Boot Application]
2. https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api [Swagger for Spring Boot Application]
3. http://www.springboottutorial.com/spring-boot-and-h2-in-memory-database [H2 In-memory DB for Spring Boot Application]
  