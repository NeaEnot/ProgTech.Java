import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class ParkingForm {

	private JFrame frame;
	private JTextField takeTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParkingForm window = new ParkingForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ParkingForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 801);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		PanelParking panel = new PanelParking();
		panel.setBounds(15, 16, 1006, 713);
		frame.getContentPane().add(panel);
		
		JButton btnSetTractor = new JButton("Set Tractor");
		btnSetTractor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tractor tractor = new Tractor((int)(Math.random() * 200) + 100, 
											  (int)(Math.random() * 1000) + 1000, 
											  new Color((int)(Math.random() * 256), 
													    (int)(Math.random() * 256), 
													    (int)(Math.random() * 256)));
				int position = panel.ADD(tractor);
				panel.repaint();
			}
		});
		btnSetTractor.setBounds(1036, 16, 207, 29);
		frame.getContentPane().add(btnSetTractor);
		
		JButton btnSetExcavatotTractor = new JButton("Set ExcavatorTractor");
		btnSetExcavatotTractor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ExcavatorTractor excavatorTractor = new ExcavatorTractor((int)(Math.random() * 200) + 100, 
						  												 (int)(Math.random() * 1000) + 1000, 
						  												 new Color((int)(Math.random() * 256), 
						  														   (int)(Math.random() * 256), 
						  														   (int)(Math.random() * 256)),
						  												 new Color((int)(Math.random() * 256), 
						  														   (int)(Math.random() * 256), 
						  														   (int)(Math.random() * 256)),
						  												 new Color((int)(Math.random() * 256), 
						  														   (int)(Math.random() * 256), 
						  														   (int)(Math.random() * 256)),
						  												 true, true, true);
				int position = panel.ADD(excavatorTractor);
				panel.repaint();
			}
		});
		btnSetExcavatotTractor.setBounds(1036, 61, 207, 29);
		frame.getContentPane().add(btnSetExcavatotTractor);
		
		JLabel lblTakeTracktor = new JLabel("Take Tracktor");
		lblTakeTracktor.setBounds(1053, 304, 105, 20);
		frame.getContentPane().add(lblTakeTracktor);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setBounds(1036, 340, 69, 20);
		frame.getContentPane().add(lblPosition);
		
		takeTextField = new JTextField();
		takeTextField.setBounds(1120, 337, 45, 26);
		frame.getContentPane().add(takeTextField);
		takeTextField.setColumns(10);
		
		PanelET takePanel = new PanelET();
		takePanel.setBounds(1036, 421, 127, 115);
		frame.getContentPane().add(takePanel);
		
		JLabel lblIsmore = new JLabel("isMore: ");
		lblIsmore.setBounds(1053, 555, 127, 20);
		frame.getContentPane().add(lblIsmore);
		
		JLabel lblIsless = new JLabel("isLess: ");
		lblIsless.setBounds(1053, 591, 112, 20);
		frame.getContentPane().add(lblIsless);
		
		JButton btnTakeTractor = new JButton("Take Tractor");
		btnTakeTractor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = Integer.parseInt(takeTextField.getText());
				ITransport transport = panel.SUBTRACT(index);
				if (transport != null) {
					takePanel.setTransport(transport);
					
					if (panel.MORE(transport)) {
						lblIsmore.setText("isMore: true");
					} else {
						lblIsmore.setText("isMore: flase");
					}
					
					if (panel.LESS(transport)) {
						lblIsless.setText("isLess: true");
					} else {
						lblIsless.setText("isLess: flase");
					}
				}
				
				panel.repaint();
				takePanel.repaint();
			}
		});
		btnTakeTractor.setBounds(1053, 376, 127, 29);
		frame.getContentPane().add(btnTakeTractor);
	}
}
