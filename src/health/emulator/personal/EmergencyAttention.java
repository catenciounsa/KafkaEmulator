package health.emulator.personal;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

import health.entities.HealthPersonal;
import health.entities.RegisterMeasured;

public class EmergencyAttention extends javax.swing.table.AbstractTableModel{
	private List<HealthPersonal> healthPersonal;
	
	public EmergencyAttention(JTextArea console) {
		healthPersonal = new ArrayList<HealthPersonal>();
		
		healthPersonal.add( new HealthPersonal("Ana Santos", "doctor", console) );
		healthPersonal.add( new HealthPersonal("Victor Bueno", "nurse", console) );
		healthPersonal.add( new HealthPersonal("Sara Mucho", "nurse", console) );
		healthPersonal.add( new HealthPersonal("Raul Matasanos", "doctor", console) );
	}
	
	/**
	 * Add a new patient - incidence
	 * This method is not synchronized.
	 * @param patient
	 */
	public void addPatient(RegisterMeasured patient) {
		int lowPatients = healthPersonal.get(0).getPatients().size();
		int lowWinner = 0;
		for(int i=1; i<healthPersonal.size(); i++) {
			if( healthPersonal.get(i).getPatients().size() < lowPatients ) {
				lowWinner = i;
				lowPatients = healthPersonal.get(i).getPatients().size();
			}
		}
		healthPersonal.get(lowWinner).addPatient(patient);
	}

	@Override
	public String getColumnName(int column) {
		if( column == 0 ) return "Name";
		if( column == 1 ) return "Occupation";
		if( column == 2 ) return "In charge";
		return ""; //indefined
	}
	
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return healthPersonal.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		if( col == 0 ) return healthPersonal.get(row).getName();
		if( col == 1 ) return healthPersonal.get(row).getCharge();
		if( col == 2 ) return healthPersonal.get(row).getPatients().size();
		return "";
	}
	
}
