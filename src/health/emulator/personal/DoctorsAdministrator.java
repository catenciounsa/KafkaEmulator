package health.emulator.personal;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import health.entities.HealthPersonal;
import health.entities.RegisterMeasured;

/**
 * It is an implementation of an AbstracTableModel
 * It has problems showing the number of patients associated to each doctor.
 * @author Carlos Atencio-Torres
 * @email catencio@unsa.edu.pe
 */
public class DoctorsAdministrator extends javax.swing.table.AbstractTableModel{
	private static final Logger log = LoggerFactory.getLogger(DoctorsAdministrator.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<HealthPersonal> healthPersonal;
	
	public DoctorsAdministrator(JTextArea console) {
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
		log.info("Assigning a new patient for the pool of doctors" );
		int lowPatients = healthPersonal.get(0).getPatients().size();
		int lowWinner = 0;
		for(int i=1; i<healthPersonal.size(); i++) {
			if( healthPersonal.get(i).getPatients().size() < lowPatients ) {
				lowWinner = i;
				lowPatients = healthPersonal.get(i).getPatients().size();
			}
		}
		
		healthPersonal.get(lowWinner).addPatient(patient);
		setValueAt("berta", lowWinner, 0);

		fireTableCellUpdated(lowWinner, 2);
		//fireTableDataChanged();
		
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
