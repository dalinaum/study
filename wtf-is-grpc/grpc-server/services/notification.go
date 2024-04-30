package services

import (
	"context"
	"fmt"
	"log"
	"math/rand"
	"time"

	"github.com/dalinaum/grpc-server/db/model"
	"github.com/dalinaum/grpc-server/pb"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/mongo"
)

// NotificationNewUser is a function that listens for new user additions in a MongoDB collection and sends notifications.
func NotificationNewUser(collection *mongo.Collection, notificationCh chan *pb.NotificationMessage) {
	// Create a context with a timeout
	ctx := context.Background()
	// defer cancel() // Deferred cancel() call, if needed.

	fmt.Println("Notification service called")

	// Define a pipeline to watch for changes (insertions)
	pipeline := []bson.M{
		{
			"$match": bson.M{
				"operationType": "insert", // Watch for insertions
				"fullDocument.name": bson.M{
					"$exists": true, // Ensure the 'name' field exists
				},
			},
		},
	}

	// Create a change stream
	changeStream, err := collection.Watch(ctx, pipeline)

	if err != nil {
		fmt.Println("Error creating change stream error:", err)
		return
	}

	fmt.Println("Listening for new user additions...")

	// Start a goroutine to handle changes from the stream
	for changeStream.Next(ctx) {
		var changeDocument bson.M
		if err := changeStream.Decode(&changeDocument); err != nil {
			log.Println("Error decoding change document:", err)
			continue
		}
		fullDocumentJSON, err := bson.MarshalExtJSON(changeDocument["fullDocument"], false, false)
		if err != nil {
			log.Println("Error converting fullDocument to JSON:", err)
			continue
		}
		// Extract the updated user name from the change document
		var user model.UserModel
		fmt.Println("user:", string(fullDocumentJSON))
		if err := bson.UnmarshalExtJSON(fullDocumentJSON, false, &user); err != nil {
			log.Println("Error unmarshaling user:", err)
			continue
		}
		rand.New(rand.NewSource(time.Now().UnixNano()))

		// Generate a random int32
		randomInt := rand.Int31()
		// Create a new user notification
		newUserNotification := &pb.NotificationMessage{
			Title:       "A new family member",
			Description: user.Name + " just arrived, send 'hi' message to connect.",
			Id:          int32(randomInt),
		}

		// Send the notification to the channel
		notificationCh <- newUserNotification
	}

	if err := changeStream.Err(); err != nil {
		log.Println("Error in change stream:", err)
	}
}
