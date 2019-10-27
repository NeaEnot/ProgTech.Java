import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainForm {

	private JFrame frame;
	
	ExcavatorTractor excavatorTractor;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
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
	public MainForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 703, 424);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new PanelET();
		panel.setBounds(0, 0, 681, 368);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((PanelET) panel).updateET(panel.getSize().width, panel.getSize().height);
				panel.repaint();
			}
		});
		btnCreate.setBounds(15, 16, 77, 29);
		panel.add(btnCreate);
		
		JButton btnLeft = new JButton("L");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((PanelET) panel).MoveTransport(Direction.Left);
				panel.repaint();
			}
		});
		btnLeft.setBounds(514, 323, 48, 29);
		panel.add(btnLeft);
		
		JButton btnDown = new JButton("D");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((PanelET) panel).MoveTransport(Direction.Down);
				panel.repaint();
			}
		});
		btnDown.setBounds(566, 323, 48, 29);
		panel.add(btnDown);
		
		JButton btnRight = new JButton("R");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((PanelET) panel).MoveTransport(Direction.Right);
				panel.repaint();
			}
		});
		btnRight.setBounds(618, 323, 48, 29);
		panel.add(btnRight);
		
		JButton btnUp = new JButton("U");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((PanelET) panel).MoveTransport(Direction.Up);
				panel.repaint();
			}
		});
		btnUp.setBounds(566, 290, 48, 29);
		panel.add(btnUp);
	}
}
