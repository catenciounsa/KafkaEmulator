package health.kafkaservice.monitor;

import java.util.Arrays;
import java.util.Properties;
import java.util.TimerTask;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import health.entities.RegisterMeasured;
import health.kafkaservice.KafkaConfiguration;
import health.kafkaservice.serializer.RegisterDeserializer;

public abstract class Monitor extends TimerTask {
	protected KafkaConsumer<String, RegisterMeasured> consumer;
	
	public Monitor(String groupId, String topic) {
		Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfiguration.KAFKA_ADDR);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, RegisterDeserializer.class.getName() );
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        
        consumer = new KafkaConsumer<String, RegisterMeasured>(properties);
        consumer.subscribe(Arrays.asList(topic));
	}
}
