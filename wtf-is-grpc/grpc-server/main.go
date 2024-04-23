package main

import (
	"context"
	"fmt"
	"log"
	"net"

	"github.com/dalinaum/grpc-server/db"
	"github.com/dalinaum/grpc-server/gapi"

	"github.com/dalinaum/grpc-server/pb"
	"github.com/dalinaum/grpc-server/utils"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
	"google.golang.org/grpc"
	"google.golang.org/grpc/reflection"
)

func main() {
	config, err := utils.LoadConfiguration(".")
	if err != nil {
		log.Fatal("Failed to load configuration", err)
	}
	database, err := ConnectDatabase(config)
	if err != nil {
		log.Fatal(err)
	}
	conn := db.MongoCollections{
		Users: database.Collection("users"),
	}
	// Start the gRPC server
	runGrpcServer(config, conn)
}

// Connect to database
func ConnectDatabase(config utils.ViperConfig) (*mongo.Database, error) {
	clientOptions := options.Client().ApplyURI(config.DBSource)
	fmt.Printf("DBSource: %s\n", config.DBSource)
	fmt.Printf("config: %v\n", config)
	ctx := context.TODO()
	client, err := mongo.Connect(ctx, clientOptions)
	if err != nil {
		return nil, fmt.Errorf("error connecting to MongoDB: %v", err)
	}
	database := client.Database(config.DBNAME)
	return database, nil
}

// start runGrpcService
func runGrpcServer(config utils.ViperConfig, collection db.MongoCollections) {
	server, err := gapi.NewServer(config, collection)

	if err != nil {
		log.Fatal("Error creating server", err)
	}

	grpcServer := grpc.NewServer(
		grpc.UnaryInterceptor(server.AuthInterceptor),
	)
	pb.RegisterGrpcServerServiceServer(grpcServer, server)

	reflection.Register(grpcServer)

	listener, err := net.Listen("tcp", config.RPCSERVERADDRESS)
	if err != nil {
		log.Fatal("Error creating server", err)
	}

	log.Printf("gRPC server listening on %s", config.RPCSERVERADDRESS)
	err = grpcServer.Serve(listener)
	if err != nil {
		log.Fatal("Error serving gRPC server", err)
	}
}
