# CurrencyRateApp

How to run the app under linux

-----------------------------------------------------------
First you need to have jdk 11 installed.
-----------------------------------------------------------
Set your MySQL DataBase username and password at 
"src/main/resources/application.properties".
-----------------------------------------------------------
Set your fixer.io access key at 
"src/main/java/app/currencyrate/services/DataModelServiceImpl.java".
-----------------------------------------------------------

Spring Initializr uses maven wrapper so type this:

$ ./mvnw clean spring-boot:run

-----------------------------------------------------------

Alternatively using your installed maven version type this:

$ mvn clean spring-boot:run

-----------------------------------------------------------

When the app starts, we can immediately interrogate it.

$ curl -v localhost:8000/

-----------------------------------------------------------
