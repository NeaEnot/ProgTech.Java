import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import java.awt.List;

public class ParkingForm {

	private JFrame frame;
	private JTextField takeTextField;

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
	
	public ParkingForm() {
		initialize();
	}

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
		lblTakeTracktor.setBounds(1073, 422, 126, 20);
		frame.getContentPane().add(lblTakeTracktor);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setBounds(1056, 458, 143, 20);
		frame.getContentPane().add(lblPosition);
		
		takeTextField = new JTextField();
		takeTextField.setBounds(1140, 455, 59, 26);
		frame.getContentPane().add(takeTextField);
		takeTextField.setColumns(10);
		
		PanelET takePanel = new PanelET();
		takePanel.setBounds(1056, 539, 143, 115);
		frame.getContentPane().add(takePanel);
		
		JLabel lblIsmore = new JLabel("isMore: ");
		lblIsmore.setBounds(1073, 673, 126, 20);
		frame.getContentPane().add(lblIsmore);
		
		JLabel lblIsless = new JLabel("isLess: ");
		lblIsless.setBounds(1073, 709, 126, 20);
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
		btnTakeTractor.setBounds(1073, 494, 126, 29);
		frame.getContentPane().add(btnTakeTractor);
		
		List list = new List();
		for (int i = 0; i < 5; i++) {
			list.add("Level " + i);
		}
		list.setBounds(1062, 108, 154, 115);
		list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setLevel(list.getSelectedIndex());
				panel.repaint();
			}
		});
		frame.getContentPane().add(list);
	}
}
