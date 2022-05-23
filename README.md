# SpringBoot Cake Manager Micro Service 

The service is a simple SpringBoot microservice that runs in the default Tomcat server.
The service allows users to see a list of cakes and to create new cakes.
The service supports both a REST endpoint and a 'single page app' web page.

## Building the service

The easiest way is to use the maven wrapper in the project root directory to build a docker image.

    ./mvnw clean install

This compiles the code, runs the tests and builds a jar file in target/cakemgr-DEVELOP-SNAPSHOT.jar.
It also builds a docker image called 'cake-manager'.

## Running the service

The service can be run in a docker container:

    docker run -p 8282:8282 cake-manager

Alternatively the service can be run directly

    java -jar cakemgr-DEVELOP-SNAPSHOT.jar

By default, at startup the service loads a list of cakes from a resource file.
This is because the cake list as the original data now has several broken image links and so is no longer a good source of data for a demonstration.


## Accessing the service

### REST endpoint

The service implements a REST endpoint at /cakes which can be accessed from PostMan or similar REST client with a GET request:

    http://localhost:8282/cakes

A new cake can be added by calling the same endpoint with a POST request and providing JSON data in the request body. Example:

    {
        "title": "Lemon Cheesecake",
        "desc": "A zesty lemon treat!",
        "image": "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"
    }

This returns a JSON object which includes the id of the new cake that was created in the in-memory database. 

### Web page

The web page is implemented as a 'single page application'. 
The page template index.html is downloaded and on load the javascript calls the REST /cakes endpoint to get the list of cakes as JSON which is then styled.  

A web UI can be accessed to show the list of cakes at:

    http://localhost:8282

By clicking the + button at the bottom of the screen a 'New Cake' form is shown. 
When the user clicks the 'Add New Cake' button the input data is validated and the REST /cakes endpoint is called to add the new cake to the database.
The new cake is shown to the user at the bottom of the list.

### Github and CI

The project is hosted in Github at:

    https://github.com/gregsewell/springboot-cake-manager

Two Github Actions have been added to support a simple Continuous Integration (CI) workflow.
The specifications of these actions can be seen in the project source .github/workflows folder:

The file maven-feature.yml compiles the code and runs the unit tests when a commit is made to any 'feature' branch (one not called 'main' or 'release*').

The file maven-main.yml runs on a merge to the main or a releae branch and additionally builds the application jar file and stores it in the GitHub packages area for the project.




