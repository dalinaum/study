package gapi

import (
	"fmt"

	"github.com/dalinaum/grpc-server/db"
	"github.com/dalinaum/grpc-server/pb"
	"github.com/dalinaum/grpc-server/token"
	"github.com/dalinaum/grpc-server/utils"
)

type Server struct {
	pb.GrpcServerServiceServer
	config        utils.ViperConfig
	dbCollections db.MongoCollections
	tokenMaker    token.Maker
}

func NewServer(config utils.ViperConfig, dbCollection db.MongoCollections) (*Server, error) {
	tokenMaker, err := token.NewJwtMaker(config.TokenStructureKey)
	if err != nil {
		return nil, fmt.Errorf("cannot create token maker: %w", err)
	}

	server := &Server{
		config:        config,
		dbCollections: dbCollection,
		tokenMaker:    tokenMaker,
	}

	return server, nil
}
