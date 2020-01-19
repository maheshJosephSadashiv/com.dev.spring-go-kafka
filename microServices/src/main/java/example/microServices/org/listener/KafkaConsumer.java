package example.microServices.org.listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import example.microServices.org.github.Github;
import example.microServices.org.github.GithubService;

@Service
public class KafkaConsumer {

	
	@Autowired
	private GithubService githubService;
	
	@Autowired
	private KafkaProducer kafkaProducer = new KafkaProducer();
	
	@KafkaListener(topics = "GitTopic", groupId  = "GetGroup")
	public void consume(String message) {
		System.out.println("recieved message : "+message);
		
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			
				
			if(!message.equals("all"))
			{
				
				Github github = githubService.getDetails(message);
				System.out.println(gson.toJson(github));
				kafkaProducer.post(gson.toJson(github));
			}
			else {
				List<Github> githubList = new ArrayList<Github>();
				githubList = githubService.getAllDetails();
				System.out.println(gson.toJson(githubList));
				kafkaProducer.post(gson.toJson(githubList));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@KafkaListener(topics = "DeleteTopic", groupId  = "GetGroup")
	public void consumeDelete(String message) {
		System.out.println("recieved message : "+message);
		githubService.deleteSpecificDetails(Long.parseLong(message));
		
	}
}
