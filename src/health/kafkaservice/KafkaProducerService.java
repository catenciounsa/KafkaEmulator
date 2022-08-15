package health.kafkaservice;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import health.entities.RegisterMeasured;
import health.kafkaservice.serialization.RegisterSerializer;

public class KafkaProducerService {	
	private KafkaProducer<String, RegisterMeasured> producer;
	
	private KafkaProducerService() {
		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,  KafkaConfiguration.KAFKA_ADDR);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, RegisterSerializer.class.getName() );
		producer = new KafkaProducer<String, RegisterMeasured>(properties);
		
		
	}
	
	public void record(String topic, RegisterMeasured val) {
		ProducerRecord<String, RegisterMeasured> record = new ProducerRecord<String,RegisterMeasured>(topic, val);
		
		producer.send(record);
		producer.flush();
		//producer.close();
	}
	
	
	private static KafkaProducerService instance = null;
	public static KafkaProducerService getInstance() {
		if( instance == null ) instance = new KafkaProducerService();
		return instance;
	}
}
