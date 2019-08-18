# Test API

## USER

### GET all users

curl -k https://localhost:8089/user
[{"email":"sarah.connor@gmail.com","fullName":"Sarah Connor","firstName":"Sarah","lastName":"Connor","password":"nope","phone":null,"profession":null,"membership":null,"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-17T05:25:22.399+0000","updatedDate":"2019-08-17T05:25:22.399+0000","isStaff":true,"profilePicture":"http://localhost:8080/profile_picture.png"},{"email":"david.bowie@gmail.com","fullName":"David Bowie","firstName":"David","lastName":"Bowie","password":"yeah","phone":null,"profession":null,"membership":null,"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-17T05:25:22.399+0000","updatedDate":"2019-08-17T05:25:22.399+0000","isStaff":true,"profilePicture":"http://localhost:8080/profile_picture.png"},{"email":"john.doe@gmail.com","fullName":"John Doe","firstName":"John","lastName":"Doe","password":"toto","phone":"080-1111-2222","profession":{"label":"Accountant"},"membership":{"label":"Basic Plan","price":3.99,"tax":8.2},"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-17T05:25:22.400+0000","updatedDate":"2019-08-17T05:25:22.400+0000","isStaff":false,"profilePicture":"http://localhost:8080/profile_picture.png"},{"email":"jane.doe@gmail.com","fullName":"Jane Doe","firstName":"Jane","lastName":"Doe","password":"tototo","phone":"091-3333-4444","profession":{"label":"Clerk Accountant"},"membership":{"label":"Premium Plan","price":15.99,"tax":10.45},"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-17T05:25:22.400+0000","updatedDate":"2019-08-17T05:25:22.400+0000","isStaff":false,"profilePicture":"http://localhost:8080/profile_picture.png"},{"email":"alex@email.com","fullName":"Alex K","firstName":"Alex","lastName":"K","password":"nope","phone":null,"profession":null,"membership":null,"isNewMessageNotified":null,"isNewServiceAdvertised":null,"subscribed":null,"createdDate":"2019-08-17T05:33:43.059+0000","updatedDate":"2019-08-17T05:33:43.059+0000","isStaff":null,"profilePicture":null}]%

### POST Create a user / Sign up

curl -k https://localhost:8089/user -H "Content-Type: application/json" -X POST -d '{"email":"alex@email.com","fullName":"Alex K","firstName":"Alex","lastName":"K","password":"nope"}' | jq
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   448    0   350  100    98   3932   1101 --:--:-- --:--:-- --:--:--  5033
{
  "email": "alex@email.com",
  "fullName": "Alex K",
  "firstName": "Alex",
  "lastName": "K",
  "password": "nope",
  "phone": null,
  "profession": null,
  "membership": null,
  "isNewMessageNotified": null,
  "isNewServiceAdvertised": null,
  "subscribed": null,
  "createdDate": "2019-08-17T05:33:43.059+0000",
  "updatedDate": "2019-08-17T05:33:43.059+0000",
  "isStaff": null,
  "profilePicture": null
}

### PUT Update a password / Forgot password

curl -k https://localhost:8089/user -H "Content-Type: application/json" -X PUT -d '{"email":"alex@email.com","fullName":"Alex K","firstName":"Alex","lastName":"K","password":"double nope"}' | jq
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   436    0   331  100   105  10677   3387 --:--:-- --:--:-- --:--:-- 14064
{
  "email": "alex@email.com",
  "fullName": "Alex K",
  "firstName": "Alex",
  "lastName": "K",
  "password": "double nope",
  "phone": null,
  "profession": null,
  "membership": null,
  "isNewMessageNotified": null,
  "isNewServiceAdvertised": null,
  "subscribed": null,
  "createdDate": null,
  "updatedDate": "2019-08-17T05:36:43.081+0000",
  "isStaff": null,
  "profilePicture": null
}


## Ticket

### GET Ticket by ID

curl -k https://localhost:8089/ticket/3 -H "Content-Type: application/json"
{"id":3,"title":"I do not have anymore a problem","owner":{"email":"john.doe@gmail.com","fullName":"John Doe","firstName":"John","lastName":"Doe","password":"toto","phone":"080-1111-2222","profession":{"label":"Accountant"},"membership":{"label":"Basic Plan","price":3.99,"tax":8.2},"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-17T07:15:57.349+0000","updatedDate":"2019-08-17T07:15:57.349+0000","isStaff":false,"profilePicture":"http://localhost:8080/profile_picture.png"},"operator":{"email":"sarah.connor@gmail.com","fullName":"Sarah Connor","firstName":"Sarah","lastName":"Connor","password":"nope","phone":null,"profession":null,"membership":null,"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-17T07:15:57.348+0000","updatedDate":"2019-08-17T07:15:57.348+0000","isStaff":true,"profilePicture":"http://localhost:8080/profile_picture.png"},"category":{"label":"Guide"},"status":"CLOSED","createdDate":"2019-08-17T07:15:57.350+0000"}%

### GET Ticket by owner

curl -k "https://localhost:8089/ticket?owner=john.doe@gmail.com" -H "Content-Type: application/json" -X GET
[{"id":1,"title":"Houston, I got a problem","owner":{"email":"john.doe@gmail.com","fullName":"John Doe","firstName":"John","lastName":"Doe","password":"toto","passwordLength":null,"phone":"080-1111-2222","profession":{"label":"Accountant"},"membership":{"label":"Basic Plan","price":3.99,"tax":8.2},"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-17T11:53:36.719+0000","updatedDate":"2019-08-17T11:53:36.719+0000","isStaff":false,"profilePicture":"http://localhost:8080/profile_picture.png"},"operator":{"email":"sarah.connor@gmail.com","fullName":"Sarah Connor","firstName":"Sarah","lastName":"Connor","password":"nope","passwordLength":null,"phone":null,"profession":null,"membership":null,"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-17T11:53:36.718+0000","updatedDate":"2019-08-17T11:53:36.718+0000","isStaff":true,"profilePicture":"http://localhost:8080/profile_picture.png"},"category":{"label":"Tutorial"},"status":"OPEN","createdDate":"2019-08-17T11:53:36.720+0000"},{"id":2,"title":"How to make a pizza","owner":{"email":"john.doe@gmail.com","fullName":"John Doe","firstName":"John","lastName":"Doe","password":"toto","passwordLength":null,"phone":"080-1111-2222","profession":{"label":"Accountant"},"membership":{"label":"Basic Plan","price":3.99,"tax":8.2},"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-17T11:53:36.719+0000","updatedDate":"2019-08-17T11:53:36.719+0000","isStaff":false,"profilePicture":"http://localhost:8080/profile_picture.png"},"operator":{"email":"david.bowie@gmail.com","fullName":"David Bowie","firstName":"David","lastName":"Bowie","password":"yeah","passwordLength":null,"phone":null,"profession":null,"membership":null,"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-17T11:53:36.718+0000","updatedDate":"2019-08-17T11:53:36.718+0000","isStaff":true,"profilePicture":"http://localhost:8080/profile_picture.png"},"category":{"label":"Guide"},"status":"CLOSED","createdDate":"2019-08-17T11:53:36.720+0000"},{"id":3,"title":"I do not have anymore a problem","owner":{"email":"john.doe@gmail.com","fullName":"John Doe","firstName":"John","lastName":"Doe","password":"toto","passwordLength":null,"phone":"080-1111-2222","profession":{"label":"Accountant"},"membership":{"label":"Basic Plan","price":3.99,"tax":8.2},"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-17T11:53:36.719+0000","updatedDate":"2019-08-17T11:53:36.719+0000","isStaff":false,"profilePicture":"http://localhost:8080/profile_picture.png"},"operator":{"email":"sarah.connor@gmail.com","fullName":"Sarah Connor","firstName":"Sarah","lastName":"Connor","password":"nope","passwordLength":null,"phone":null,"profession":null,"membership":null,"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-17T11:53:36.718+0000","updatedDate":"2019-08-17T11:53:36.718+0000","isStaff":true,"profilePicture":"http://localhost:8080/profile_picture.png"},"category":{"label":"Guide"},"status":"CLOSED","createdDate":"2019-08-17T11:53:36.720+0000"}]%

### POST a new ticket
curl -k https://localhost:8089/ticket -H "Content-Type: application/json" -X POST -d '{"title": "Just a test", "owner": "john.doe@gmail.com", "category": "Guide", "content": "I cannot figure out how to do that"}'
{"id":9,"author":{"email":"john.doe@gmail.com","fullName":"John Doe","firstName":"John","lastName":"Doe","password":"toto","passwordLength":null,"phone":"080-1111-2222","profession":{"label":"Accountant"},"membership":{"label":"Basic Plan","price":3.99,"tax":8.2},"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-17T23:33:29.284+0000","updatedDate":"2019-08-17T23:33:29.284+0000","isStaff":false,"profilePicture":"http://localhost:8080/profile_picture.png"},"content":"I cannot figure out how to do that","ticket":{"id":6,"title":"Just a test","owner":{"email":"john.doe@gmail.com","fullName":"John Doe","firstName":"John","lastName":"Doe","password":"toto","passwordLength":null,"phone":"080-1111-2222","profession":{"label":"Accountant"},"membership":{"label":"Basic Plan","price":3.99,"tax":8.2},"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-17T23:33:29.284+0000","updatedDate":"2019-08-17T23:33:29.284+0000","isStaff":false,"profilePicture":"http://localhost:8080/profile_picture.png"},"operator":null,"category":{"label":"Guide"},"status":"OPEN","createdDate":null},"createdDate":"2019-08-17T23:34:38.667+0000","attachedFile":null}%

### PATCH Update the status of a ticket

The status should be switched everytime a PATCH request is done

 curl -k https://localhost:8089/ticket/1 -H "Content-Type: application/json" -X PATCH
{"id":1,"title":"Houston, I got a problem","owner":{"email":"john.doe@gmail.com","fullName":"John Doe","firstName":"John","lastName":"Doe","password":"toto","passwordLength":null,"phone":"080-1111-2222","profession":{"label":"Accountant"},"membership":{"label":"Basic Plan","price":3.99,"tax":8.2},"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-17T08:00:30.679+0000","updatedDate":"2019-08-17T08:00:30.679+0000","isStaff":false,"profilePicture":"http://localhost:8080/profile_picture.png"},"operator":{"email":"sarah.connor@gmail.com","fullName":"Sarah Connor","firstName":"Sarah","lastName":"Connor","password":"nope","passwordLength":null,"phone":null,"profession":null,"membership":null,"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-17T08:00:30.678+0000","updatedDate":"2019-08-17T08:00:30.678+0000","isStaff":true,"profilePicture":"http://localhost:8080/profile_picture.png"},"category":{"label":"Tutorial"},"status":"CLOSED","createdDate":"2019-08-17T08:00:30.680+0000"}%


## Message

### GET Messages by ticket ID

curl -k https://localhost:8089/message\?ticketId\=1 -H "Content-Type: application/json" -X GET
[{"id":1,"author":{"email":"john.doe@gmail.com","fullName":"John Doe","firstName":"John","lastName":"Doe","password":"toto","passwordLength":null,"phone":"080-1111-2222","profession":{"label":"Accountant"},"membership":{"label":"Basic Plan","price":3.99,"tax":8.2},"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-17T08:00:30.679+0000","updatedDate":"2019-08-17T08:00:30.679+0000","isStaff":false,"profilePicture":"http://localhost:8080/profile_picture.png"},"content":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","ticket":{"id":1,"title":"Houston, I got a problem","owner":{"email":"john.doe@gmail.com","fullName":"John Doe","firstName":"John","lastName":"Doe","password":"toto","passwordLength":null,"phone":"080-1111-2222","profession":{"label":"Accountant"},"membership":{"label":"Basic Plan","price":3.99,"tax":8.2},"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-17T08:00:30.679+0000","updatedDate":"2019-08-17T08:00:30.679+0000","isStaff":false,"profilePicture":"http://localhost:8080/profile_picture.png"},"operator":{"email":"sarah.connor@gmail.com","fullName":"Sarah Connor","firstName":"Sarah","lastName":"Connor","password":"nope","passwordLength":null,"phone":null,"profession":null,"membership":null,"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-17T08:00:30.678+0000","updatedDate":"2019-08-17T08:00:30.678+0000","isStaff":true,"profilePicture":"http://localhost:8080/profile_picture.png"},"category":{"label":"Tutorial"},"status":"OPEN","createdDate":"2019-08-17T08:00:30.680+0000"},"createdDate":"2019-08-17T08:00:30.681+0000","attachedFile":null}]

### POST Message

 curl -k https://localhost:8089/message -H "Content-Type: application/json" -X POST -d '{"user": {"email":"john.doe@gmail.com","fullName":"John Doe","firstName":"John","lastName":"Doe","password":"toto","passwordLength":null,"phone":"080-1111-2222","profession":{"label":"Accountant"},"membership":{"label":"Basic Plan","price":3.99,"tax":8.2},"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-17T08:00:30.679+0000","updatedDate":"2019-08-17T08:00:30.679+0000","isStaff":false,"profilePicture":"http://localhost:8080/profile_picture.png"}, "content": "Hello world!!!"}'
{"id":13,"author":null,"content":"Hello world!!!","ticket":{"id":1,"title":"Houston, I got a problem","owner":{"email":"john.doe@gmail.com","fullName":"John Doe","firstName":"John","lastName":"Doe","password":"toto","passwordLength":null,"phone":"080-1111-2222","profession":{"label":"Accountant"},"membership":{"label":"Basic Plan","price":3.99,"tax":8.2},"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-17T11:53:36.719+0000","updatedDate":"2019-08-17T11:53:36.719+0000","isStaff":false,"profilePicture":"http://localhost:8080/profile_picture.png"},"operator":{"email":"sarah.connor@gmail.com","fullName":"Sarah Connor","firstName":"Sarah","lastName":"Connor","password":"nope","passwordLength":null,"phone":null,"profession":null,"membership":null,"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-17T11:53:36.718+0000","updatedDate":"2019-08-17T11:53:36.718+0000","isStaff":true,"profilePicture":"http://localhost:8080/profile_picture.png"},"category":{"label":"Tutorial"},"status":"OPEN","createdDate":"2019-08-17T11:53:36.720+0000"},"createdDate":"2019-08-17T22:45:06.253+0000","attachedFile":null}%

## Profession

### GET Professions
curl -k https://localhost:8089/profession -H "Content-Type: application/json" -X GET
[{"label":"Accountant"},{"label":"Clerk Accountant"}]%

## Password Reminder

### POST Generate code

curl -k https://localhost:8089/reminder -H "Content-Type: application/json" -X POST -d '{"email": "john.doe@gmail.com"}'
generated%

### PUT Verify code

2 possible reasons to fail:
 curl -k https://localhost:8089/reminder -H "Content-Type: application/json" -X PUT -d '{"email": "john.doe@gmail.com", "code": "16"}'
Received code not found in cache.%

 curl -k https://localhost:8089/reminder -H "Content-Type: application/json" -X PUT -d '{"email": "john.doe@gmail.com", "code": "1699"}'
Code expired or wrong.%

If successful:
curl -k https://localhost:8089/reminder -H "Content-Type: application/json" -X PUT -d '{"email": "john.doe@gmail.com", "code": "1699"}'
OK.%

## Payment

### GET All payment methods registered for all users

 curl -k https://localhost:8089/payment-method -H "Content-Type: application/json" -X GET
[{"id":1,"card":{"issuer":"MasterCard"},"cardNumber":"1111-1111-1111-1111","cardholderName":"John Doe","cvv":111,"expirationDate":"2018/11","address":"1st street of Washington","city":"Seattle","stateProvince":"Washington","postalCode":"98105","country":"USA","payerEmail":"john.doe@gmail.com"},{"id":2,"card":{"issuer":"Visa"},"cardNumber":"2222-2222-2222-2222","cardholderName":"Jane Doe","cvv":222,"expirationDate":"2010/12","address":"Shimotakaido 8-21-5","city":"Tokyo","stateProvince":"112-2103","postalCode":"Suginami","country":"Tokyo","payerEmail":"jane.doe@gmail.com"}]%


### GET All payment methods registered by user

curl -k "https://localhost:8089/payment-method?email=john.doe@gmail.com" -H "Content-Type: application/json" -X GET
{"id":1,"card":{"issuer":"MasterCard"},"cardNumber":"1111-1111-1111-1111","cardholderName":"John Doe","cvv":111,"expirationDate":"2018/11","address":"1st street of Washington","city":"Seattle","stateProvince":"Washington","postalCode":"98105","country":"USA","payer":{"email":"john.doe@gmail.com","fullName":"John Doe","firstName":"John","lastName":"Doe","password":"toto","passwordLength":null,"phone":"080-1111-2222","profession":{"label":"Accountant"},"membership":{"label":"Basic Plan","price":3.99,"tax":8.2},"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-18T06:24:58.196+0000","updatedDate":"2019-08-18T06:24:58.196+0000","isStaff":false,"profilePicture":"http://localhost:8080/profile_picture.png"}}%


### POST Add a new payment method linked to a user
curl -k https://localhost:8089/payment-method -H "Content-Type: application/json" -X POST -d '{"id":1,"card":{"issuer":"MasterCard"},"cardNumber":"2222-3333-4444-5556","cardholderName":"John Doe","cvv":111,"expirationDate":"2018/11","address":"1st street of Washington","city":"Seattle","stateProvince":"Washington","postalCode":"98105","country":"USA","payer":{"email":"john.doe@gmail.com","fullName":"John Doe","firstName":"John","lastName":"Doe","password":"toto","passwordLength":null,"phone":"080-1111-2222","profession":{"label":"Accountant"},"membership":{"label":"Basic Plan","price":3.99,"tax":8.2},"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-18T06:24:58.196+0000","updatedDate":"2019-08-18T06:24:58.196+0000","isStaff":false,"profilePicture":"http://localhost:8080/profile_picture.png"}}'

{"id":1,"card":{"issuer":"MasterCard"},"cardNumber":"2222-3333-4444-5556","cardholderName":"John Doe","cvv":111,"expirationDate":"2018/11","address":"1st street of Washington","city":"Seattle","stateProvince":"Washington","postalCode":"98105","country":"USA","payer":{"email":"john.doe@gmail.com","fullName":"John Doe","firstName":"John","lastName":"Doe","password":"toto","passwordLength":null,"phone":"080-1111-2222","profession":{"label":"Accountant"},"membership":{"label":"Basic Plan","price":3.99,"tax":8.2},"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-18T06:36:23.560+0000","updatedDate":"2019-08-18T06:36:23.560+0000","isStaff":false,"profilePicture":"http://localhost:8080/profile_picture.png"}}%


## Subscription plan

### GET All subscription plans

curl -k https://localhost:8089/subscription-plan -H "Content-Type: application/json" -X GET

[{"label":"Basic Plan","price":3.99,"tax":8.2},{"label":"Standard Plan","price":10.99,"tax":9.43},{"label":"Premium Plan","price":15.99,"tax":10.45}]%


## Order

### GET All orders

curl -k https://localhost:8089/order -H "Content-Type: application/json" -X GET

[{"id":"RF120941","deliveryEstimate":"2018-12-04T15:00:00.000+0000","createdDate":"2019-08-18T06:36:23.567+0000","transactorEmail":"john.doe@gmail.com","plan":{"label":"Basic Plan","price":3.99,"tax":8.2}},{"id":"RF151093","deliveryEstimate":"2018-12-14T15:00:00.000+0000","createdDate":"2019-08-18T06:36:23.568+0000","transactorEmail":"jane.doe@gmail.com","plan":{"label":"Standard Plan","price":10.99,"tax":9.43}}]%

### GET An order by email

curl -k "https://localhost:8089/order?email=john.doe@gmail.com" -H "Content-Type: application/json" -X GET

[{"id":"RF120941","deliveryEstimate":"2018-12-04T15:00:00.000+0000","createdDate":"2019-08-18T06:36:23.567+0000","transactorEmail":"john.doe@gmail.com","plan":{"label":"Basic Plan","price":3.99,"tax":8.2}},{"id":"RF151093","deliveryEstimate":"2018-12-14T15:00:00.000+0000","createdDate":"2019-08-18T06:36:23.568+0000","transactorEmail":"jane.doe@gmail.com","plan":{"label":"Standard Plan","price":10.99,"tax":9.43}}]%


### GET Order by ID
curl -k "https://localhost:8089/order/RF120941" -H "Content-Type: application/json" -X GET

{"id":"RF120941","deliveryEstimate":"2018-12-04T15:00:00.000+0000","createdDate":"2019-08-18T06:48:47.111+0000","transactorEmail":"john.doe@gmail.com","plan":{"label":"Basic Plan","price":3.99,"tax":8.2}}%


### POST Create new order

curl -k https://localhost:8089/order -H "Content-Type: application/json" -X POST -d '{"label": "Basic Plan", "orderer": "john.doe@gmail.com"}'

{"id":"REF001946","deliveryEstimate":"2019-08-21T08:19:50.824+0000","createdDate":"2019-08-18T08:19:50.826+0000","transactor":{"email":"john.doe@gmail.com","fullName":"John Doe","firstName":"John","lastName":"Doe","password":"toto","passwordLength":null,"phone":"080-1111-2222","profession":{"label":"Accountant"},"membership":{"label":"Basic Plan","price":3.99,"tax":8.2},"isNewMessageNotified":true,"isNewServiceAdvertised":true,"subscribed":true,"createdDate":"2019-08-18T08:19:31.810+0000","updatedDate":"2019-08-18T08:19:31.810+0000","isStaff":false,"profilePicture":"http://localhost:8080/profile_picture.png"},"plan":{"label":"Basic Plan","price":3.99,"tax":8.2}}%
