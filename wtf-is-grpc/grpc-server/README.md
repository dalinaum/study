```sh
go install google.golang.org/grpc/cmd/protoc-gen-go-grpc@latest
go install google.golang.org/protobuf/cmd/protoc-gen-go-grpc@latest
```

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

```
$ mongosh
use admin
db.adminCommand(
   {
      shutdown: 1,
      comment: "Convert to cluster"
   }
)```

```sh
$ mongod --port 27017 --dbpath /opt/homebrew/var/mongodb --replSet rs0 --bind_ip localhost
```

Re-run mongo service.

```sh
$ mongosh
rs.initiate()
```

```sh
mongoimport --jsonArray --collection users --type json --file users-data.json
```