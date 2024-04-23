package auth

import (
	"github.com/dalinaum/grpc-server/db/model"
	pb "github.com/dalinaum/grpc-server/pb"
)

func ConvertUserObjectToUser(model *model.UserModel) *pb.User {
	return &pb.User{
		Username: model.Username,
		Name:     model.Name,
		Id:       int32(model.ID.Timestamp().Day()),
	}
}
