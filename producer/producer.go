package main

import (
	"fmt"

	"sarama-v1"
)

func main() {
	for true {
		fmt.Println("\nEnter your choice: \n1. Add details of GIT users to DATABASE \n2. Display all details \n3. DELETE details of git users")
		var ch int
		fmt.Scanln(&ch)
		switch ch {
		case 1:
			fmt.Println("Enter the user name ")
			var usrname string
			fmt.Scanln(&usrname)
			SendMessage(usrname)
			break
		case 2:
			SendMessage("all")
			break
		case 3:
			fmt.Println("Enter the user ID of the user")
			var id string
			fmt.Scanln(&id)
			deleteMessage(id)
			break
		default:
			return
		}
	}

}

func SendMessage(message string) {
	config := sarama.NewConfig()
	config.Producer.RequiredAcks = sarama.WaitForAll
	config.Producer.Retry.Max = 5
	config.Producer.Return.Successes = true

	brokers := []string{"localhost:9092"}
	producer, err := sarama.NewSyncProducer(brokers, config)
	if err != nil {
		panic(err)
	}

	defer func() {
		if err := producer.Close(); err != nil {
			panic(err)
		}
	}()

	topic := "GitTopic"
	msg := &sarama.ProducerMessage{
		Topic: topic,
		Value: sarama.StringEncoder(message),
	}

	partition, offset, err := producer.SendMessage(msg)
	if err != nil {
		panic(err)
	}

	fmt.Printf("Message is stored in topic(%s)/partition(%d)/offset(%d)\n", topic, partition, offset)

}

func deleteMessage(message string) {
	config := sarama.NewConfig()
	config.Producer.RequiredAcks = sarama.WaitForAll
	config.Producer.Retry.Max = 5
	config.Producer.Return.Successes = true

	brokers := []string{"localhost:9092"}
	producer, err := sarama.NewSyncProducer(brokers, config)
	if err != nil {
		panic(err)
	}

	defer func() {
		if err := producer.Close(); err != nil {
			panic(err)
		}
	}()

	topic := "DeleteTopic"
	msg := &sarama.ProducerMessage{
		Topic: topic,
		Value: sarama.StringEncoder(message),
	}

	partition, offset, err := producer.SendMessage(msg)
	if err != nil {
		panic(err)
	}

	fmt.Printf("Message is stored in topic(%s)/partition(%d)/offset(%d)\n", topic, partition, offset)
}
