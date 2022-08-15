package health.emulator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import health.kafkaservice.monitor.glucose.PersonMonitorGlucose;
import health.kafkaservice.util.PersonSelector;
/**
 * This is the graphic emulator of a glucose monitoring
 * 
 * @author Carlos Torres
 * @email catencio@unsa.edu.pe
 *
 */
public class GlucoseEmulator extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int REFRESH_RATE = 1000; //nanosecs

	private JTextField name;
	private JTextField age;
	private JTextField height;
	private JTextField weight;
	private JLabel l_name;
	private JLabel l_age;
	private JLabel l_height;
	private JLabel l_weight;
	
	private JLabel title;

	private PersonMonitorGlucose monitoredPerson;

	public GlucoseEmulator() {
		setTitle("Glucose monitor");
		
		configureComponents();
		selectRandomPerson();
		
		setLayout( new BorderLayout() );
		
		
		JPanel dataPanel = new JPanel( new GridLayout(4, 2) );
		dataPanel.add( new JLabel("Name:") );
		dataPanel.add(name);
		dataPanel.add( new JLabel("Age:")  );
		dataPanel.add(age);
		dataPanel.add( new JLabel("Height:")  );
		dataPanel.add(height);
		dataPanel.add( new JLabel("Weight:")  );
		dataPanel.add(weight);
		dataPanel.setBorder( BorderFactory.createLineBorder(Color.red) );
		
		
		
		add(title, BorderLayout.NORTH); 
		
		add(new DrawSmartWatch(), BorderLayout.CENTER); 
		
		add(dataPanel, BorderLayout.SOUTH);
		
		
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setSize(500,400);
		setVisible(true);

	}
	
	private void configureComponents() {
		name = new JTextField();
		age = new JTextField();
		height = new JTextField();
		weight = new JTextField();
		
		l_name = new JLabel();
		l_name.setLabelFor(name);
		l_age = new JLabel();
		l_age.setLabelFor(age);
		l_height = new JLabel();
		l_height.setLabelFor(height);
		l_weight = new JLabel();
		l_weight.setLabelFor(weight);
		
		name.setEditable(false);
		age.setEditable(false);
		height.setEditable(false);
		weight.setEditable(false);		
		
		title = new JLabel("Glucose");
		title.setFont( new Font("Verdana", Font.PLAIN, 24));
	}

	private void selectRandomPerson() {
		monitoredPerson = PersonSelector.getRandomPMGlucose();

		this.name.setText(monitoredPerson.getName());
		this.age.setText("" + monitoredPerson.getAge());
		this.height.setText(""+monitoredPerson.getHeight());
		this.weight.setText(""+monitoredPerson.getWeight());
	}
	
	class DrawSmartWatch extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		protected DrawSmartWatch() {
			Timer timer = new Timer();
			timer.schedule(
					new TimerTask() {
						@Override
						public void run() {
							repaint();
						}
					}
			, 0, REFRESH_RATE);
		    /*new Timer(REFRESH_RATE, e -> {
		      repaint();
		    }).start();*/
		  }
		
		public void paintComponent( Graphics g ) {
			try {
				BufferedImage image = ImageIO.read( new File("etc/monitor.jpg") );
				
				int w = image.getWidth(), h = image.getHeight();
				
				g.drawImage( image, 0,0, 500, 250, 0, 0, w, h, null );
				
			} catch (IOException e) {
				System.err.println("IMAGE IS NOT FOUND or CANT BE READ");
				e.printStackTrace();
			}
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.rotate(-0.18);
			g2d.setColor(Color.white); g2d.fillRect(105, 105, 78, 40);
			g2d.setFont(new Font("Arial", Font.PLAIN, 14));
			
			String str_glucose = monitoredPerson.getGlucose() + " mg/dl";
			g2d.setColor(Color.red); g2d.drawString(str_glucose, 110, 130 );
			g2d.dispose();
			
			
			setSize(500,220);
		}
	}

	public static void main(String[] args) {
		new GlucoseEmulator();
	}

}
