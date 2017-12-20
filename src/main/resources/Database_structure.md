# Database structure

### Users

```
  "user_id = 3186":{
    "login" : "michael",
    "password" : "defA45EabcF16780BC9D23",
    "email" : "michael@email.com",
    "phone" : "79990000000",
    "status" : "admin | active | banned"
  },
  "user_id = 3946":{
    "login" : "leonid",
    "password" : "9D216fA4C35Eab780BdecF",
    "email" : "leonid@email.com",
    "phone" : "79631111111",
    "status" : "admin | active | banned"
  }
```

### Goods
```
  "good_id = 6287" : {
    "name" : "Mercedes-Benz S-Class 2017",
    "amount" : "12",
    "description" : "Series of luxury flagship vehicles produced by the German automaker Mercedes-Benz"
  }
```
### Cart
`
  "cart_id = 26461" : {
  
    "user_id" : "3186", //changes from Taras
    
    "goods_id" : "6287", //changes from Taras
    
    "amount" : "1",
    
    "reserve_time" : "what format?"
    
  }
```
