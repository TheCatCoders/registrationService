# Registation Service

This is a java service that allows a user to register to an application.

This is a maven project so just import in your favorite IDE and it should work.

Should run in a multitude of application server.  Has been tested with:
* Wildfly 10.1
* Openshift Container Platform ( OCP ) using S2I

The service can be tested using Postman using a JSON object that look like:

{
  "emailAdr": "postman@test.com",
  "password": "ppppp",
  "age": "1955-05-05",
  "gender": "Male",
  "username": " Postman"
}

#Here how to [contribute](CONTRIBUTING.md)
