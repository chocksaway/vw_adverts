# login service for vw_adverts

Spring Boot has been used to implement a test driven development approach to authenticating a user.

A User pojo has a username, and password attribute.

An h2 (in memory) JPA repository is used to save users.

The key requirement is to implement authenticate functionality, validating credentials.

Unit tests (ControllerTests) test the authenticate endpoint with happy and sad paths.

For convenience, a UserDataLoader loads three users:

    + "miles", "password"
    + "john", "password"
    + "james", "password"

These are loaded using a CommandLineRunner, for convenience.

The following unit tests have been implemented:

authenticate Endpoint:

    testAuthenticateWithNewUser

    testAuthenticateWithNonExistingUser

    testAuthenticateWithWrongPassword

    testAuthenticateNullUser

user endpoint:

    testAddNewValidUserAlreadyExists

    testAddNewValidUserDoesNotExist

Custom exceptions have been implemented.  These are UserNotFoundException, UserAlreadyExistsException, and UserUnauthorisedException.

#### To run the unit tests

Use "mvn clean compile test":
