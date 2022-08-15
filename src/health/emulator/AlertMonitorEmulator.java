package health.emulator;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import health.emulator.personal.EmergencyAttention;
import health.entities.RegisterMeasured;

public class AlertMonitorEmulator extends JFrame {
	private static final long DELAY = 1000;
	
	private JTable tableAlerts;
	private JTable tableDoctors;
	private JTextArea console;
	private JLabel doctorsLabel;
	private JLabel alertsLabel;
	private JLabel consoleLabel;
	
	private List<RegisterMeasured> alerts;
	private EmergencyAttention attention;
	
	public AlertMonitorEmulator() {
		alerts = Collections.synchronizedList(new ArrayList<>());
		
		console = new JTextArea();
		attention = new EmergencyAttention(console);
		
		Timer timer = new Timer();
		timer.schedule( new AlertTopicMonitor(alerts), 0, DELAY);
		timer.schedule( new TimerTask() {
			@Override
			public void run() {
				if( !alerts.isEmpty() ) {
					attention.addPatient(alerts.remove(0));
				}
				tableDoctors.repaint();
				tableAlerts.repaint();
			}
			
		}, DELAY);
		
		setTitle("Alert Monitor");

		configureComponents();

		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setSize(500, 600);
		setVisible(true);

	}
	
	class AlertTableModel extends javax.swing.table.AbstractTableModel{
		private final List<RegisterMeasured> alertsRef;
		private String[] headers = new String[] {"Name", "Sex", "Age", "Height", "Weight", "Measure"};
		
		public AlertTableModel(List<RegisterMeasured> alertsRef) {
			super( );
			this.alertsRef = alertsRef;
		}
		
		@Override
		public String getColumnName(int column) {
			if( column < headers.length )
				return headers[column];
			return "";
		}

		@Override
		public int getColumnCount() {
			return 6; //name/sex/age/height/weight/measure
		}

		@Override
		public int getRowCount() {
			return alertsRef.size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			switch(col) {
			case 0: return this.alertsRef.get(row).getName();
			case 1: return this.alertsRef.get(row).getSex();
			case 2: return this.alertsRef.get(row).getAge();
			case 3: return this.alertsRef.get(row).getHeight();
			case 4: return this.alertsRef.get(row).getWeight();
			case 5: return this.alertsRef.get(row).getMeasure();
			}
			return null;
		}
		
	}
	
	private void configureComponents() {
		setLayout( null);
		
		doctorsLabel = new JLabel("Health personal for emergency");
		doctorsLabel.setFont( new Font("verdana", Font.PLAIN, 20));
		alertsLabel = new JLabel("Alerts");
		alertsLabel.setFont( new Font("verdana", Font.PLAIN, 20));
		consoleLabel = new JLabel("Attentions");
		consoleLabel.setFont( new Font("verdana", Font.PLAIN, 20));
		
		tableDoctors = new JTable( new EmergencyAttention(console) );
		tableAlerts = new JTable( new AlertTableModel(alerts)  );
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
