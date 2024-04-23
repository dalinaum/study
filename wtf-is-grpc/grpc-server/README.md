```sh
go install google.golang.org/grpc/cmd/protoc-gen-go-grpc@latest
go install google.golang.org/protobuf/cmd/protoc-gen-go-grpc@latest
```

```
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