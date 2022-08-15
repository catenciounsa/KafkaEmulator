package health.kafkaservice.monitor.glucose;

import health.entities.RegisterMeasured;
import health.kafkaservice.KafkaConfiguration;
import health.kafkaservice.monitor.MonitoredPerson;

/**
 * This is a service that emulates a smart watch monitoring the glucose
 * 
 * @author Carlos Atencio-Torres
 * @email catencio@unsa.edu.pe
 */

public class PersonMonitorGlucose extends MonitoredPerson {
	private int glucose;
	private static final int MIN_GLUCOSE = 80; //normal for most conditions
	private static final int MAX_GLUCOSE = 125; //normal for most conditions

	public PersonMonitorGlucose(String name, char sex, int age, int height, int weight) {
		super(KafkaConfiguration.GLUCOSE_TOPIC, name, sex, age, height, weight);
		this.glucose = 0;
	}

	public int getGlucose() {
		return glucose;
	}

	public void setGlucose(int glucose) {
		this.glucose = glucose;
	}

	@Override
	public RegisterMeasured nextMeasure() {
		if( Math.random() <= ALERT_CONDITION_PROB )
			return alertOnPurpose();
		else 
			this.glucose = MIN_GLUCOSE + (int) ((MAX_GLUCOSE - MIN_GLUCOSE) * Math.random()); //normal condition
		return  new RegisterMeasured(name, sex, age, height, weight, ""+glucose);
	}

	@Override
	public RegisterMeasured alertOnPurpose() {
		if( Math.random() < 0.5 ) 
			this.glucose = (int)(MIN_GLUCOSE/2);
		else
			this.glucose = (int)(MAX_GLUCOSE*1.5);
		return new RegisterMeasured(name, sex, age, height, weight, ""+glucose);
	}
	
}
