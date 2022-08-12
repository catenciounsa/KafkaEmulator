package health.kafkaservice.monitor.heart;

import health.entities.RegisterMeasured;
import health.kafkaservice.KafkaConfiguration;
import health.kafkaservice.monitor.MonitoredPerson;

/**
 * This is a service that emulates a smart watch monitoring the heart
 * 
 * @author Carlos Atencio-Torres
 * @email catencio@unsa.edu.pe
 */
public class PersonSmartwatchHeart extends MonitoredPerson {
	
	private int bpm;
	private static final int MIN_BPM= 60;
	private static final int MAX_BPM= 110;

	public PersonSmartwatchHeart(String name, char sex, int age, int height, int weight) {
		super(KafkaConfiguration.HEART_TOPIC,name, sex, age, height, weight);
		this.bpm = 0;
	}

	public int getBpm() {
		return bpm;
	}

	public void setBpm(int bpm) {
		this.bpm = bpm;
	}
	
	@Override
	public RegisterMeasured nextMeasure() {
		this.bpm = MIN_BPM + (int) ((MAX_BPM - MIN_BPM) * Math.random());
		return new RegisterMeasured(name, sex, age, height, weight, ""+bpm);
	}
	
	@Override
	public RegisterMeasured alertOnPurpose() {
		if( Math.random() < 0.5 ) 
			this.bpm = (int)(MIN_BPM/2);
		else
			this.bpm = (int)(MAX_BPM*1.5);
		return new RegisterMeasured(name, sex, age, height, weight, ""+bpm);
	}
	
}
