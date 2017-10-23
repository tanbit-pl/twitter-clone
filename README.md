## Description

Built a simple social networking application, similar to Twitter, and
exposed it through a web API. The application should support the scenarios
below.

## Scenarios

- Posting - user should be able to post a 140 character message.
- Wall - user should be able to see a list of the messages they've posted, in reverse
chronological order.
- Following - user should be able to follow another user. Following doesn't have to be
reciprocal: Alice can follow Bob without Bob having to follow Alice.
- Timeline - user should be able to see a list of the messages posted by all the people
they follow, in reverse chronological order.

## Requirements
- Java 8
- Maven
- curl (test only)

## Running

**Build, test and run application**

*In project root folder*

`mvn spring-boot:run`

**Open URL**

http://localhost:8080/api/v1/users

#### Integration test

[HsbcTwitterCloneApplicationTests.java](src/test/pl.tanbit.hsbctwitterclone/HsbcTwitterCloneApplicationTests.java).

## WEB API

Tweet new message (It will create new user automatically):
```
POST ../api/v1/users/{userId}/tweet
```

Wall, get user tweets:
```
GET ../api/v1/users/{userId}/wall
```

User follow another:
```
PUT ../api/v1/{userId}/follow/{followUserId}
```

Timeline from user - all messages posted by other peoples that the user is following
```
GET ../api/v1/{userId}/{userId}/timeline
```

### Examples

Tweet new post:
```
 curl -X POST -H "Content-Type: text/plain" --data "Test tweet one" http://localhost:8080/api/v1/users/adam/tweet
```

### Wall

```
curl -X POST -H "Content-Type: text/plain" --data "Test tweet one" http://localhost:8080/api/v1/users/adam/tweet

curl -X POST -H "Content-Type: text/plain" --data "Test tweet two" http://localhost:8080/api/v1/users/adam/tweet

curl -X GET  http://localhost:8080/api/v1/users/adam/wall
```

### Following

```
curl -X PUT http://localhost:8080/api/v1/users/peter/follow/adam
```

### Timeline

```
curl -X POST -H "Content-Type: text/plain" --data "Test tweet one" http://localhost:8080/api/v1/users/adam/tweet

curl -X POST -H "Content-Type: text/plain" --data "Test tweet two" http://localhost:8080/api/v1/users/adam/tweet

curl -X PUT http://localhost:8080/api/v1/users/peter/follow/adam

curl -X POST -H "Content-Type: text/plain" --data "Test tweet three" http://localhost:8080/api/v1/users/paul/tweet

curl -X PUT http://localhost:8080/api/v1/users/peter/follow/paul

curl -X GET http://localhost:8080/api/v1/users/peter/timeline
```
