### Running this application ###

To run this application from the command line with Maven:

`mvn spring-boot:run`

Or in an IDE, just run the `Application` class

Word search is executed by calling API: http://localhost:8080/search/${input}

### Assumptions ###
Input word length is limited to 7 characters for issues of scale.  Excessive length will be truncated.
If there is a whitespace, only characters to the left of the space will be accounted for in the search.
All input characters will be downcased.