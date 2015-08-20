# POSTRESPONSE

This is a simple GetResponse wrapper api

## Maven
This project is built with [Maven](http://maven.apache.org). Be
sure to check the pom.xml for the dependencies if you're not using
maven.

## Use
The use is simple like:

```java
GetResponse getResponse = new GetResponse();
List<Company>customs = new ArrayList<Company>();
customs.add(new Company(nomeDaEntidade));
Contact contact = new Contact("Paul", "xx@domain.com", customs, new Campaign("VYh36"));
getResponse.addContact(contact);
```