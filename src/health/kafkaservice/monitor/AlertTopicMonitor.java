package health.kafkaservice.monitor;

import java.time.Duration;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import health.entities.RegisterMeasured;
import health.kafkaservice.KafkaConfiguration;

public class AlertTopicMonitor extends Monitor {
	private final List<RegisterMeasured> alertsReference;

	public AlertTopicMonitor(List<RegisterMeasured> alertsReference) {
		super("alert", KafkaConfiguration.ALERT_TOPIC);
		this.alertsReference = alertsReference;
	}

	@Override
	public void run() {
		ConsumerRecords<String,RegisterMeasured> records = consumer.poll(Duration.ofMillis(100));
		
		for(ConsumerRecord<String, RegisterMeasured> record : records ) {
			RegisterMeasured val = record.value();
			alertsReference.add(val);
		}
	}

}
