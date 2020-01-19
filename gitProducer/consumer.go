package main

import (
	"fmt"

	"sarama-v1"
)

func main() {

	config := sarama.NewConfig()
	config.Consumer.Return.Errors = true


	brokers := []string{"localhost:9092"}


	master, err := sarama.NewConsumer(brokers, config)
	if err != nil {
		panic(err)
	}

	defer func() {
		if err := master.Close(); err != nil {
			panic(err)
		}
	}()

	topic := "ResultTopic"

	consumer, err := master.ConsumePartition(topic, 0, sarama.OffsetOldest)
	if err != nil {
		panic(err)
	}


	for message := range consumer.Messages(){
	fmt.Printf("Message claimed: value = %s, offset : %d, topic = %s \n\n\n", string(message.Value), sarama.OffsetOldest, message.Topic)
}
}
