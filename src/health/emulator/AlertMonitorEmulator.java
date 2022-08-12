package health.emulator;

import java.util.Timer;

import health.kafkaservice.monitor.AlertTopicMonitor;

public class AlertMonitorEmulator {
	private static final long DELAY = 1000;
	public AlertMonitorEmulator() {
		Timer timer = new Timer();
		timer.schedule( new AlertTopicMonitor(), 0, DELAY);
	}
	
	public static void main(String[] args) {
		new HealthMonitorEmulator();
	}
}
