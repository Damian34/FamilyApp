Family SPRING BOOT REST Api
====================

Apliction is using libraries:
Spring Web
Spring Data JPA
Flyway Migration <-- to create schemas and tabels
PostgreSQL Driver
Thymeleaf <-- only to call custom error page
Junit5

(In project i left some comments. i knew that there shouldn't but i wanted to describe some things there so i left them:)

data are to connect to the PostgreSQL database and you can look in to them with pgAdmin 
(db is 20mb and is stand on https://api.elephantsql.com)
host name: dumbo.db.elephantsql.com
password:  PHm0jpvwUdwMtElhSwmHCA85Ucl9G6l8
username:  xvwxelvu

Tables description can be found in file below:
V1__Create_Tables.sql

Application you can run in IDE (i was using Netbeans 12.5)
or using docker:

docker need to be running and inside project folder use commands below:

docker build -t family-app-docker .
docker run -p 8080:8080 family-app-docker

PS.
(i was tring to crete more dockerfile's than one and like in taks but,
i skiped this point becouse i found that it is posible to create some kinda of docker-compose.yml file to run multiple 
multiple cointeiners ,but i couldn't figure out how create multiple cointeiners(mean this FamilyApp, FamilyMemberApp)
that would be separate and be created from the same app, i tried to find how i could skip some classes form .jar but 
i couldn't. so i left it with 1 cointeiner to run entity app.)

App have 2 controllers:
FamilyAppController
FamilyMemberAppController

To run methods PUT,POST,DELETE u can use Postman app, u will need only to 
select kind of methods ,type uri of sevice adn fill request body with json in my app

FamilyMemberAppController have Endpoints:

- createFamilyMember(is PUT method) that can be called eg by http://localhost:8080/createFamilyMember
in request body should be Json discribing FamilyMember 
eg: {"familyId":1,"givenName":"Mateusz","familyName":"Kowalski","age":24}

- searchFamilyMember(is GET method) that can be called eg by http://localhost:8080/searchFamilyMember?id=1
where id is familyID, as response will be given Json with list of FamilyMember's  

- deleteFamilyMembers(is Delete method) that can be called eg by http://localhost:8080/deleteFamilyMembers?id=1
this service is created for test to clear added records during testing
by given Family id the service is deleting appropriate records of FamilyMember's from DB


FamilyAppController have Endpoints:

- createFamily(is POST method) that can be called eg by http://localhost:8080/createFamily
in request body should be Json eg like below

{"Family":{"familyName":"Kowalski","nrOfInfants":1,"nrOfChildren":1,"nrOfAdults":1},
"FamilyMember":[{"givenName":"Kamil","familyName":"Kowalski","age":3},
{"givenName":"Olivier","familyName":"Kowalski","age":12},
{"givenName":"Mateusz","familyName":"Kowalski","age":24}]}

the service will recrete from Json object Family and list of FamilyMember
Family will be saved in this service and will send FamilyMember's to save using endponit http://localhost:8080/createFamilyMember

- getFamily(is GET method) that can be called eg by http://localhost:8080/getFamily?id=1
Family will be loaded my service, but to get FamilyMember's will be called http://localhost:8080/searchFamilyMember?id=1
that will retun list of FamilyMember and at the end data will be composed into 1 JSon that will be respond

- deleteFamily(is Delete method) that can be called eg by http://localhost:8080/deleteFamily?id=1
this service is created for test to clear added records during testing
by given Family id the service is deleting Family from DB and sending request for http://localhost:8080/deleteFamilyMembers?id=1
to delete FamilyMember's ,there is no respond from this method





