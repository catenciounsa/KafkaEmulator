package health.kafkaservice.monitor.glucose;

import java.time.Duration;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import health.entities.RegisterMeasured;
import health.kafkaservice.KafkaConfiguration;
import health.kafkaservice.KafkaProducerService;
import health.kafkaservice.monitor.Monitor;

public class GlucoseTopicMonitor extends Monitor {

	public GlucoseTopicMonitor() {
		
		super("glucose", KafkaConfiguration.GLUCOSE_TOPIC);
	}

	// Approx: https://ncmkak2op1ygpx62dn79ct7z-wpengine.netdna-ssl.com/wp-content/uploads/2021/08/BloodGlucoseChart.jpg
	@Override
	public void run() {
		ConsumerRecords<String,RegisterMeasured> records = consumer.poll(Duration.ofMillis(100));
		
		for(ConsumerRecord<String, RegisterMeasured> record : records ) {
			RegisterMeasured val = record.value();
			int measure = Integer.parseInt(val.getMeasure());
			
			if( measure < 80 ) {
				val.setMeasure("hipoglucemia");
				KafkaProducerService.getInstance().record(KafkaConfiguration.ALERT_TOPIC, val);
			}
			else if ( measure > 125 ) {
				val.setMeasure("hiperglucemia");
				KafkaProducerService.getInstance().record(KafkaConfiguration.ALERT_TOPIC, val);
			}
		}
	}
	
}
