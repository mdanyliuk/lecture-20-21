run docker-compose.yml to run consul and mongo <br>
open consul ui at http://localhost:8500 <br>
go to `key/value` tab <br>
create `config` folder <br>
go to `config` folder and crate `library` folder <br>
go to `library` folder and create `mongoUri` key with value `mongodb://localhost:27001/library` <br>
then run application from `library` and `reader` directories <br>
<br>
### Endpoints<br>
use following credentials for basic authentication:<br>
username: admin<br>
password: admin<br>
--<br>
username: user<br>
password: user<br>
GET `http://localhost:8090/api/users` get list of all users (for admin)<br>
POST `http://localhost:8090/api/users` create new user (for admin) <br>
example of correct request body: <br>
`{
"username":"john",
"password":"pass"
}` <br>
GET `http://localhost:8090/api/books/all` get list of all books (for admin) <br>
POST `http://localhost:8090/api/books` add new book (for admin) <br>
example of correct request body: <br>
`{
"author":"author",
"name":"name",
"content":"content"
}` <br>
POST `http://localhost:8090/api/books/bind` add book to user (for admin) <br>
example of correct request body: <br>
`{
"userId": "6428616f634504312586f277",
"bookId": "6429c5ef81e878403c1dde51"
}` <br>
GET `http://localhost:8090/api/books` list of user's books (for user) <br>
GET `http://localhost:8090/api/books/{id}` content of book (for user) <br>
<br>
### Reader app<br>
http://localhost:8080/ <br>

