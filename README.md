# Post system
API for creating, deleting and receiving posts, which will be developed in the future.   
   
**Create post**
```http request
POST http://localhost:8080/api/post
{
    "title": "POST TITLE",
    "description": "POST DESCRIPTION", 
    "author": "POST AUTHOR"
}
```
**Show all posts**
```http request
GET http://localhost:8080/api/post
```
**Delete post**
```http request
DELETE http://localhost:8080/api/post?title=POST TITLE
```
   
Thank you