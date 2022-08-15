package health.kafkaservice.monitor;

import java.util.Timer;
import java.util.TimerTask;

import health.entities.Register;
import health.entities.RegisterMeasured;
import health.kafkaservice.KafkaProducerService;

/**
 * Person information for a health system 
 * 
 * @author Carlos Atencio-Torres
 * @email catencio@unsa.edu.pe
 */

public abstract class MonitoredPerson extends Register {
	protected final String topic;
	protected Timer timer;
	protected KafkaProducerService service = KafkaProducerService.getInstance(); 
	public static final int REFRESH_RATE = 1000; //nanosecs
	public static final double ALERT_CONDITION_PROB = 0.4;
	
	public MonitoredPerson(String topic, String name, char sex, int age, int height, int weight) {
		super(name, sex, age, height, weight);
		this.topic = topic;
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				RegisterMeasured measure = nextMeasure();
				service.record(getTopic(), measure);
			}
		}, 0, REFRESH_RATE);
		/*timer = new Timer( REFRESH_RATE, e-> {
			RegisterMeasured measure = nextMeasure();
			service.record(topic, measure);
		});
		timer.start();*/
		
	}
	
	public String getTopic() {
		return topic;
	}



	/**
	 * It should return the value to be stored in Kafka
	 * @return
	 */
	public abstract RegisterMeasured nextMeasure();
	
	/**
	 * This method should generate a measure that generate an alert
	 * @return
	 */
	public abstract RegisterMeasured alertOnPurpose();
		
}
