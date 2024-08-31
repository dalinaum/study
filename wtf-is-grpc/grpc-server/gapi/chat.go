package gapi

import (
	"context"
	"fmt"
	"io"
	"log"
	"time"

	pb "github.com/dalinaum/grpc-server/pb"
	"github.com/dalinaum/grpc-server/services"
	"github.com/dalinaum/grpc-server/token"
	"google.golang.org/grpc/codes"
	"google.golang.org/grpc/status"
	"google.golang.org/protobuf/types/known/timestamppb"
)

// SendMessage handles sending messages.
func (server *Server) SendMessage(stream pb.GrpcServerService_SendMessageServer) error {
	// Extract the user payload from the context.
	payload, ok := stream.Context().Value(payloadHeader).(*token.Payload)
	fmt.Printf("payload: %v\n", payload)
	if !ok {
		fmt.Println("Missing required token")
		return status.Errorf(codes.Internal, "missing required token")
	}

	// Initialize the clients map if it's nil
	server.mu.Lock()
	if server.clients == nil {
		server.clients = make(map[string]pb.GrpcServerService_SendMessageServer)
	}
	fmt.Printf("join stream: %v\n", payload.Username)
	server.clients[payload.Username] = stream
	server.mu.Unlock()

	// Continously receive and forward messages.
	for {
		message, err := stream.Recv()
		fmt.Printf("username: %v\nmessage: %v\n", payload.Username, message)

		if err == io.EOF {
			fmt.Printf("Stream is over. : %v\n", payload.Username)
			// The client has closed the connection.
			break
		}
		if err != nil {
			fmt.Printf("Error occured. : %v %v\n", payload.Username, err)
			return status.Errorf(codes.Internal, "Error receiving message: %v", err)
		}

		createdAt := timestamppb.New(time.Now())

		if message.Message == "Join_room" {
			// Special handling for "Join_room" message.
			// Send a confirmation message back to the sender.
			response := &pb.Message{
				Sender:    "Server", // You can set the sender to "Server" or any other identifer.
				Receiver:  payload.Username,
				Message:   "You have joined the room.",
				CreatedAt: createdAt,
			}
			if err := stream.Send(response); err != nil {
				log.Printf("Error sending confirmation message: %v", err)
			}
			receiverConfirmation := &pb.Message{
				Sender:    "Server", // Sender is the server in this case.
				Receiver:  message.Receiver,
				Message:   fmt.Sprintf("%s has joined the room.", payload.Username),
				CreatedAt: createdAt,
			}
			server.mu.Lock()
			receiver, ok := server.clients[message.Receiver]
			server.mu.Unlock()

			if ok {
				// Send the notification to the receiver.
				if err := receiver.Send(receiverConfirmation); err != nil {
					log.Printf("Error sending notification to %s: %v", message.Receiver, err)
				}
			}

		} else {
			// Normal message handling.
			res, err := services.SendMessage(stream.Context(), message.Message, payload.Username, message.Receiver, &server.dbCollections)
			if err != nil {
				return status.Errorf(codes.Internal, "Error saving message: %v", err)
			}

			idHex := res.ID.Hex()

			// Find the receiver by username.
			server.mu.Lock()
			receiver, ok := server.clients[message.Receiver]
			if ok {
				// Forward the message to the receiver.
				err = receiver.Send(&pb.Message{
					Sender:    payload.Username,
					Receiver:  message.Receiver,
					Message:   message.Message,
					CreatedAt: createdAt,
					Id:        idHex,
				})
				if err != nil {
					log.Printf("Error sending message to %s: %v", message.Receiver, err)
					continue
				}
			}

			sender, ok := server.clients[payload.Username]
			server.mu.Unlock()
			if ok {
				// Send the same message back to the sender as a confirmation.
				err = sender.Send(&pb.Message{
					Sender:    payload.Username,
					Receiver:  message.Receiver,
					Message:   message.Message,
					Id:        idHex,
					CreatedAt: createdAt,
				})
				if err != nil {
					log.Printf("Error sending confirmation message to %s: %v", payload.Username, err)
					continue
				}
			}
		}
	}

	fmt.Printf("%v disconnect.\n", payload.Username)
	// Remove the sender from the clients map when the client disconnects.
	server.mu.Lock()
	delete(server.clients, payload.Username)
	server.mu.Unlock()
	return nil
}

// GetAllMessage retrieves all messages for a user.
func (server *Server) GetAllMessage(ctx context.Context, req *pb.GetAllMessageRequest) (*pb.GetAllMessageResponse, error) {
	// Extract the user payload from the context.
	payload, ok := ctx.Value(payloadHeader).(*token.Payload)
	if !ok {
		return nil, status.Errorf(codes.Internal, "missing required token")
	}

	// Call the GetAllMessage service.
	return services.GetAllMessages(ctx, &server.dbCollections, payload.Username, req.GetReceiver())
}
