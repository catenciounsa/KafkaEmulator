package health.emulator;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import health.emulator.personal.AlertsAdministrator;
import health.emulator.personal.DoctorsAdministrator;
import health.entities.RegisterMeasured;
import health.kafkaservice.monitor.AlertTopicMonitor;

public class AlertMonitorEmulator extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final long DELAY = 200;
	
	private JTable tableAlerts;
	private JTable tableDoctors;
	private JTextArea console;
	private JLabel doctorsLabel;
	private JLabel alertsLabel;
	private JLabel consoleLabel;
	
	private List<RegisterMeasured> alerts;
	private DoctorsAdministrator doctorAttention;
	private AlertsAdministrator alertAttention;
	
	public AlertMonitorEmulator() {
		alerts = Collections.synchronizedList(new ArrayList<>());
		
		console = new JTextArea();
		doctorAttention = new DoctorsAdministrator(console);
		alertAttention = new AlertsAdministrator(alerts);
		
		Timer timer = new Timer();
		timer.schedule( new AlertTopicMonitor(alertAttention), 0,  DELAY);
		timer.schedule( new TimerTask() {
			@Override
			public void run() {
				if( !alerts.isEmpty() ) {
					doctorAttention.addPatient(alerts.remove(0));
					tableDoctors.revalidate();
				}
			}
			
		}, 0, DELAY);
		
		setTitle("Alert Monitor");

		configureComponents();

		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setSize(500, 600);
		setVisible(true);

	}
	
	private void configureComponents() {
		setLayout( null);
		
		doctorsLabel = new JLabel("Health personal for emergency");
		doctorsLabel.setFont( new Font("verdana", Font.PLAIN, 20));
		alertsLabel = new JLabel("Alerts");
		alertsLabel.setFont( new Font("verdana", Font.PLAIN, 20));
		consoleLabel = new JLabel("Attentions");
		consoleLabel.setFont( new Font("verdana", Font.PLAIN, 20));
		
		tableDoctors = new JTable( doctorAttention );
		tableAlerts = new JTable( alertAttention  );
		tableDoctors.setFont(new Font("verdana", Font.PLAIN, 16));
		
		JScrollPane tableDoctorScroll = new JScrollPane(tableDoctors);
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add( doctorsLabel, BorderLayout.NORTH );
		panel1.add( tableDoctorScroll, BorderLayout.CENTER );
		panel1.setBounds(50, 0, 380, 110);
		
		JScrollPane consoleScroll = new JScrollPane(console);
		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.add( consoleLabel, BorderLayout.NORTH );
		panel2.add( consoleScroll, BorderLayout.CENTER );
		panel2.setBounds(20, 120, 450, 200 );
		/*JButton launcher = new JButton("ADDROW");
		panel2.add( launcher, BorderLayout.EAST );
		launcher.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "HOLA");
				alertAttention.addPatient( new RegisterMeasured("Test", 'X', 17, 120, 60, "Alert test"));
			}
		});*/

		JScrollPane tableAlertScroll = new JScrollPane(tableAlerts);
		JPanel panel3 = new JPanel(new BorderLayout());
		panel3.add( alertsLabel, BorderLayout.NORTH );
		panel3.add( tableAlertScroll, BorderLayout.CENTER );
		panel3.setBounds(20,330, 450, 220);
		
		add(panel1 );
		add(panel2 );
		add(panel3 );
	}

	public static void main(String[] args) {
		new AlertMonitorEmulator();
	}
}
