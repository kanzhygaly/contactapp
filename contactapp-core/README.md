# contactapp-core
Backend side for **contactapp** built on Angular 7

## API
 - Get all contacts
 > GET http://localhost:8080/contacts
 - Search contacts by name
 > GET http://localhost:8080/contacts?search=name:{name} 
 - Get contact by id
 > GET http://localhost:8080/contacts/{id} 
 - Create contact
 > POST http://localhost:8080/contacts
 - Update contact
 > PUT http://localhost:8080/contacts/{id}
 - Delete contact
 > DELETE http://localhost:8080/contacts/{id}