# Registation Service

API that allows a user to register to an application.

This is a maven project so just import in your favorite IDE and it should work.

#Here how to [contribute](CONTRIBUTING.md)

Should run in a multitude of application server.  Has been tested with:
* Wildfly 10.1 +
* Openshift Container Platform ( OCP ) using S2I

---

The REST API consists of the following methods:

Method  |  URL  |  Action
--------|-------|--------------
POST | /register | Register a client to an application input: JSON

---
##### The service can be tested using Postman using a JSON object using this information

example of a JSON


raw  JSON

The service can be tested using Postman using a JSON object that look like:

{
  "emailAdr": "postman@test.com",
  "password": "password",
  "age": "1955-05-05",
  "gender": "Male",
  "username": " Postman"
}
