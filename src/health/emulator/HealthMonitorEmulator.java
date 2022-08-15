package health.emulator;

import java.util.Timer;

import health.kafkaservice.monitor.glucose.GlucoseTopicMonitor;
import health.kafkaservice.monitor.heart.HeartTopicMonitor;

public class HealthMonitorEmulator {
	private static final long DELAY = 2000;
	public HealthMonitorEmulator() {
		Timer timer = new Timer();
		timer.schedule( new HeartTopicMonitor(), 0, DELAY);
		timer.schedule( new GlucoseTopicMonitor(), 0, DELAY);
	}
	
	public static void main(String[] args) {
		new HealthMonitorEmulator();
	}
}
