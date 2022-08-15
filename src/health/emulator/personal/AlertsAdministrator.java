package health.emulator.personal;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import health.entities.RegisterMeasured;

public class AlertsAdministrator extends javax.swing.table.AbstractTableModel{
	private static final Logger log = LoggerFactory.getLogger(AlertsAdministrator.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] headers = new String[] {"Name", "Sex", "Age", "Height", "Weight", "Measure"};
	
	private final List<RegisterMeasured> alerts;
	
	public AlertsAdministrator(List<RegisterMeasured> alerts) {
		super();
		this.alerts = alerts;
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
		return alerts.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch(col) {
		case 0: return this.alerts.get(row).getName();
		case 1: return this.alerts.get(row).getSex();
		case 2: return this.alerts.get(row).getAge();
		case 3: return this.alerts.get(row).getHeight();
		case 4: return this.alerts.get(row).getWeight();
		case 5: return this.alerts.get(row).getMeasure();
		}
		return null;
	}
	
	public void addPatient(RegisterMeasured patient) {
		log.info("Registering new patient:" + patient.getName() + "," + patient.getMeasure());
		int idx = alerts.size();
		alerts.add(patient);
		fireTableRowsInserted(idx,idx);
	}
}