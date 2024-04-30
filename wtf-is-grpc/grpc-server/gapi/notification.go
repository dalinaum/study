package gapi

import (
	"fmt"

	"github.com/dalinaum/grpc-server/pb"
	"github.com/dalinaum/grpc-server/services"
)

// GetNotifications is a gRPC service method that streams notifications to the client.
func (server *Server) GetNotifications(req *pb.EmptyRequestMessage, stream pb.GrpcServerService_GetNotificationsServer) error {
	fmt.Println("Notification service started")

	// Create a channel for receiving notifications
	notificationCh := make(chan *pb.NotificationMessage)

	// Start the backgoround service to listen for new user additions
	go services.NotificationNewUser(server.dbCollections.Users, notificationCh)

	fmt.Println("Notification service created")

	// Continuosly listen for events
	for {
		select {
		case <-stream.Context().Done():
			// Client disconnected, close the channel and exit
			close(notificationCh)
			return nil
		case notification, ok := <-notificationCh:
			if !ok {
				// The channel has been closed, exit the loop
				return nil
			}
			// Send the received notification to the client
			if err := stream.Send(&pb.NotificationMessage{
				Title:       notification.Title,
				Id:          int32(notification.Id),
				Description: notification.Description,
			}); err != nil {
				return err
			}
		}
	}
}
