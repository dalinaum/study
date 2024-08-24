```sh
go install google.golang.org/grpc/cmd/protoc-gen-go-grpc@latest
go install google.golang.org/protobuf/cmd/protoc-gen-go-grpc@latest
```

고 언어 서버를 실행하는 방법.

```sh
go run .
```

```
evans --host localhost --port 9090 -r repl

$ show package
+-------------------------+
|         PACKAGE         |
+-------------------------+
| grpc.reflection.v1      |
| grpc.reflection.v1alpha |
| pb                      |
+-------------------------+

$ package pb
$ show service
+-------------------+---------+----------------------+-----------------------+
|      SERVICE      |   RPC   |     REQUEST TYPE     |     RESPONSE TYPE     |
+-------------------+---------+----------------------+-----------------------+
| GrpcServerService | SignUp  | SignupRequestMessage | SignupResponseMessage |
| GrpcServerService | Login   | LoginRequestMessage  | LoginResponseMessage  |
| GrpcServerService | GetUser | EmptyRequestMessage  | GetUserResponse       |
+-------------------+---------+----------------------+-----------------------+

$ service GrpcServerService
$ show service
```

```
$ package pb
$ service GrpcServerService
$ call GetNotifications
```

```
$ package pb
$ service GrpcServerService
$ call SignUp
```

클러스터로 변경.

```sh
$ mongosh
use admin
db.adminCommand(
   {
      shutdown: 1,
      comment: "Convert to cluster"
   }
)
```

복제 세트로 시작.

```sh
$ mongod --port 27017 --dbpath /opt/homebrew/var/mongodb --replSet rs0 --bind_ip localhost
```

몽고 서비스를 다시 시작.

```sh
$ mongosh
rs.initiate()
```

유저 데이터를 가져오기 위해서 아래의 커맨드를 입력한다.

```sh
mongoimport --jsonArray --collection users --type json --file users-data.json
```