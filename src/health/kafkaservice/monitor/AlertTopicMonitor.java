package health.kafkaservice.monitor;

import java.time.Duration;

import javax.swing.JOptionPane;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import health.entities.RegisterMeasured;
import health.kafkaservice.KafkaConfiguration;

public class AlertTopicMonitor extends Monitor {

	public AlertTopicMonitor() {
		super("alert", KafkaConfiguration.ALERT_TOPIC);
	}

	@Override
	public void run() {
		ConsumerRecords<String,RegisterMeasured> records = consumer.poll(Duration.ofMillis(100));
		
		for(ConsumerRecord<String, RegisterMeasured> record : records ) {
			RegisterMeasured val = record.value();
			JOptionPane.showMessageDialog(null, val.getName() + " => " + val.getMeasure() );
		}
	}

}
