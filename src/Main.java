import health.emulator.AlertMonitorEmulator;
import health.emulator.GlucoseEmulator;
import health.emulator.HealthMonitorEmulator;
import health.emulator.SmartWatchHeartEmulator;
import health.kafkaservice.KafkaConfiguration;

/**
 * This is the manager of the jar file
 * 
 * The command must look like:
 * 
 * java jar KafkaEmulator --function=HEART --address=localhost:9092
 * 
 * @author Carlos Atencio-Torres
 * @email catencio@unsa.edu.pe
 */
public class Main {
	
	public static void main(String[] args) {
		String function = null;
		String server = null;
		for( String arg : args ) {
			if( arg.startsWith("--function=") ) {
				function = arg.substring(11);
			} else if ( arg.startsWith("--address=")) {
				server = arg.substring(10);
			}
		}
		if( function == null || server == null) {
			messageProperties();
			System.exit(0);
		}
		
		// Changing Kafka address
		KafkaConfiguration.KAFKA_ADDR = server;
		
		// Selecting the correct emulator		
		switch( function ) {
		case "HEART":	new SmartWatchHeartEmulator(); break;
		case "GLUCOSE": new GlucoseEmulator(); break;
		case "ALERT": new AlertMonitorEmulator(); break;
		case "HEART_GLUCOSE": new HealthMonitorEmulator(); break;
		}
	}
	
	public static void messageProperties() {
		System.out.println("Please respect the format: (eg.)");
		System.out.println("java jar KafkaEmulator --function=HEART --address=192.168.1.56:22");
		System.out.println("Valid functions: HEART | GLUCOSE | ALERT | HEART_GLUCOSE");
		System.out.println("HEART");
	}
}
