package health.kafkaservice.util;

import health.kafkaservice.monitor.glucose.PersonMonitorGlucose;
import health.kafkaservice.monitor.heart.PersonSmartwatchHeart;

/**
 * This is a builder of Person Smart watch Heart or Glucose
 * 
 * @author Carlos Atencio-Torres
 * @email catencio@unsa.edu.pe
 */
public class PersonSelector {
	public static final int MAX_AGE = 100;
	public static final int MIN_AGE = 10;
	
	public static final int MAX_WEIGHT = 200;
	public static final int MIN_WEIGHT = 50;
	
	public static final int MAX_HEIGHT = 150;
	public static final int MIN_HEIGHT = 100;
			
	public static String name[]= {"Ana Zeballos", "Brenda Quispe", "Cristina Torres", 
								  "Dionisio Santos", "Elmer Robles", "Fernando Olivares" };
	
	public static PersonSmartwatchHeart getRandomPSWHeart() {
		int sel = (int)( Math.random()*name.length );
		char sex = (sel < name.length/2)?'F':'M'; 
		int age = (int) ((MAX_AGE-MIN_AGE) * Math.random() + MIN_AGE);
		int height = (int) ((MAX_HEIGHT-MIN_HEIGHT) * Math.random() + MIN_HEIGHT);
		int weight = (int) ((MAX_WEIGHT-MIN_WEIGHT) * Math.random() + MIN_WEIGHT);
		return new PersonSmartwatchHeart( name[sel], sex, age, height, weight );
	}
	
	public static PersonMonitorGlucose getRandomPMGlucose() {
		int sel = (int)( Math.random()*name.length );
		char sex = (sel < name.length/2)?'F':'M'; 
		int age = (int) ((MAX_AGE-MIN_AGE) * Math.random() + MIN_AGE);
		int height = (int) ((MAX_HEIGHT-MIN_HEIGHT) * Math.random() + MIN_HEIGHT);
		int weight = (int) ((MAX_WEIGHT-MIN_WEIGHT) * Math.random() + MIN_WEIGHT);
		return new PersonMonitorGlucose( name[sel], sex, age, height, weight );
	}
}
