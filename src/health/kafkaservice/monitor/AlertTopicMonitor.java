package health.kafkaservice.monitor;

import java.time.Duration;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import health.emulator.personal.AlertsAdministrator;
import health.entities.RegisterMeasured;
import health.kafkaservice.KafkaConfiguration;


public class AlertTopicMonitor extends Monitor {
	private static final Logger log = LoggerFactory.getLogger(AlertTopicMonitor.class);
	private final AlertsAdministrator alertAttention;
	public static final int DELAY = 100;

	public AlertTopicMonitor(AlertsAdministrator alertAttention) {
		super("alert", KafkaConfiguration.ALERT_TOPIC);
		this.alertAttention = alertAttention;
	}

	@Override
	public void run() {
		ConsumerRecords<String,RegisterMeasured> records = consumer.poll(Duration.ofMillis(DELAY));
		
		for(ConsumerRecord<String, RegisterMeasured> record : records ) {
			log.info("NEWS => new records have been read, exactly: " + records.count());
			RegisterMeasured val = record.value();
			alertAttention.addPatient(val);
		}
	}

}
