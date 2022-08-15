package health.emulator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import health.entities.RegisterMeasured;

public class AlertMonitorEmulator extends JFrame {
	private static final long DELAY = 1000;
	private List<RegisterMeasured> alerts;
	
	private JTable tableAlerts;
	private JTable tableDoctors;
	private JLabel doctorsLabel;
	private JLabel alertsLabel;
	
	public AlertMonitorEmulator() {
		/*Timer timer = new Timer();
		timer.schedule( new AlertTopicMonitor(alerts), 0, DELAY);
		timer.schedule( new TimerTask() {
			@Override
			public void run() {
				tableAlerts.repaint();
				//tableDoctors.repaint();
			}
			
		}, DELAY);*/
		
		alerts = new ArrayList<RegisterMeasured>();
		
		setTitle("Alert Monitor");

		configureComponents();

		setLayout(new FlowLayout());
		//setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setSize(600, 800);
		setVisible(true);

	}
	
	class AlertTableModel extends javax.swing.table.AbstractTableModel{
		private final List<RegisterMeasured> alertsRef;
		private String[] headers = new String[] {"Name", "Sex", "Age", "Height", "Weight"};
		
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
			case 2: return this.alertsRef.get(row).getHeight();
			case 3: return this.alertsRef.get(row).getWeight();
			case 4: return this.alertsRef.get(row).getMeasure();
			}
			return null;
		}
		
	}
	
	private void configureComponents() {
		tableDoctors = new JTable( new Object[][] { {"Martin", "nursey"},
													 {"Joana", "nursey"},
													 {"Sarah", "doctor"} }, new Object [] {"Name", "occupation" } );
		
		tableAlerts = new JTable( new AlertTableModel(alerts)  );
		doctorsLabel = new JLabel("Health personal for emergency");
		alertsLabel = new JLabel("Alerts");
		setLayout(new BorderLayout());
		
		JScrollPane tableDoctorScroll = new JScrollPane(tableDoctors);
		tableDoctorScroll.setBorder(BorderFactory.createLineBorder(Color.red));
		tableDoctorScroll.setBounds(0, 0, 100, 50);
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add( doctorsLabel, BorderLayout.NORTH );
		panel1.add( tableDoctorScroll, BorderLayout.CENTER );

		JScrollPane tableAlertScroll = new JScrollPane(tableAlerts);
		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.add( alertsLabel, BorderLayout.NORTH );
		panel2.add( tableAlertScroll, BorderLayout.CENTER );
		
		add(panel1);
		add(panel2);
	}

	public static void main(String[] args) {
		new AlertMonitorEmulator();
	}
}
