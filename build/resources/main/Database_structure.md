# Database structure

### Users

```
  "id = 3186":{
    "login" : "michael",
    "passhash" : "defA45EabcF16780BC9D23",
    "email" : "michael@email.com",
    "phone" : "79990000000",
    "status" : "admin | active | banned"
  },
  "id = 3946":{
    "login" : "leonid",
    "passhash" : "9D216fA4C35Eab780BdecF",
    "email" : "leonid@email.com",
    "phone" : "79631111111",
    "status" : "admin | active | banned"
  }
```

### Goods
```
  "id = 6287" : {
    "name" : "Mercedes-Benz S-Class 2017",
    "amount" : "12",
    "description" : "Series of luxury flagship vehicles produced by the German automaker Mercedes-Benz"
  }
```
### Cart
```
  "id = 26461" : {
    "login" : "michael",
    "good_id" : "6287",
    "amount" : "1",
    "reserve_time" : "what format?"
  }
```