# spring-boot-scala

[![Build Status](https://travis-ci.org/wonwoo/spring-boot-scala.svg?branch=master)](https://travis-ci.org/wonwoo/spring-boot-scala)

### spring-boot-scala rest api server


#### maven 
```
git clone https://github.com/wonwoo/spring-boot-scala.git
cd spring-boot-scala
mvn spring-boot:run
```

#### docker
```
docker pull wonwoo/spring-boot-scala
docker run -p 8080:8080 -t wonwoo/spring-boot-scala

```


#### test
- account
```
curl http://localhost:8080/accounts
curl http://localhost:8080/account/1
curl -X POST -H "Content-Type: application/json" -d '{"name":"scala","password":"scalapw"}' http://localhost:8080/account
curl -X PATCH -H "Content-Type: application/json" -d '{"name":"spring"}' http://localhost:8080/account/3
curl -X DELETE http://localhost:8080/account/3
curl http://localhost:8080/accounts
```
- post
```
curl http://localhost:8080/posts
curl http://localhost:8080/post/1
curl -X POST -H "Content-Type: application/json" -d '{"title":"spring and scala","content":"wow! scala"}' http://localhost:8080/post
curl -X PATCH -H "Content-Type: application/json" -d '{"title":"scala","content":"let`s go scala"}' http://localhost:8080/post/3
curl -X DELETE http://localhost:8080/post/3
curl http://localhost:8080/posts
```



