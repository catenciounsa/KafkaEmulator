package health.entities;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextArea;

public class HealthPersonal {
	private final String name;
	private final String charge;
	
	private final Queue<RegisterMeasured> patients;
	public static final int DELAY = 1000; //1000 nanosecs;
	
	public HealthPersonal(String name, String charge, JTextArea console) {
		super();
		this.name = name;
		this.charge = charge;
		patients = new LinkedList<RegisterMeasured>();
		
		Timer attention = new Timer();
		attention.schedule(new TimerTask() {
			
			@Override
			public void run() {
				if( !patients.isEmpty() ) {
					RegisterMeasured patient = patients.poll();
					console.append("Case attended: " + patient.getName() + " - "  + patient.getMeasure()+"\n" );
				}
			}
		}, DELAY);
	}
	
	public String getName() {
		return name;
	}
	public String getCharge() {
		return charge;
	}
	
	public void addPatient( RegisterMeasured regMeasured ) {
		patients.add(regMeasured);
	}
	
	public Queue<RegisterMeasured> getPatients() {
		return patients;
	}
	
}
