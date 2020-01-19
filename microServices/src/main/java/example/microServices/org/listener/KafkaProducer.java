package example.microServices.org.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("kafka")
public class KafkaProducer {
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	KafkaProducer(){}
	
	public void post(String message) {
		kafkaTemplate.send("ResultTopic",message);
	}
	
}
