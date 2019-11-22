package Forms;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

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

import Classes.ExcavatorTractor;
import Classes.Tractor;
import Classes.Transport;
import Delegates.TransportDelegate;
import Interfaces.ITransport;
import Panels.PanelET;
import Panels.PanelParking;

import java.awt.List;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.MenuEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class ParkingForm {
	
	class Delegate extends TransportDelegate {
		@Override
		public void invoke(ITransport transport) {
			int position = panel.ADD(transport);
			panel.repaint();
		}
	}

	private JFrame frame;
	private JTextField takeTextField;
	PanelParking panel;

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
		frame.setBounds(100, 100, 1154, 688);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new PanelParking();
		panel.setBounds(15, 66, 878, 506);
		frame.getContentPane().add(panel);
		
		JLabel lblTakeTracktor = new JLabel("Take Tracktor");
		lblTakeTracktor.setBounds(970, 298, 126, 20);
		frame.getContentPane().add(lblTakeTracktor);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setBounds(953, 334, 81, 20);
		frame.getContentPane().add(lblPosition);
		
		takeTextField = new JTextField();
		takeTextField.setBounds(1037, 331, 59, 26);
		frame.getContentPane().add(takeTextField);
		takeTextField.setColumns(10);
		
		PanelET takePanel = new PanelET();
		takePanel.setBounds(953, 415, 143, 115);
		frame.getContentPane().add(takePanel);
		
		JLabel lblIsmore = new JLabel("isMore: ");
		lblIsmore.setBounds(970, 549, 126, 20);
		frame.getContentPane().add(lblIsmore);
		
		JLabel lblIsless = new JLabel("isLess: ");
		lblIsless.setBounds(970, 585, 126, 20);
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
		btnTakeTractor.setBounds(970, 370, 126, 29);
		frame.getContentPane().add(btnTakeTractor);
		
		List list = new List();
		for (int i = 0; i < 5; i++) {
			list.add("Level " + i);
		}
		list.setBounds(959, 138, 154, 115);
		list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setLevel(list.getSelectedIndex());
				panel.repaint();
			}
		});
		frame.getContentPane().add(list);
		
		JButton btnAddTractor = new JButton("Add Tractor");
		btnAddTractor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfigForm conf = new ConfigForm(new Delegate());
			}
		});
		btnAddTractor.setBounds(970, 66, 126, 29);
		frame.getContentPane().add(btnAddTractor);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(15, 16, 139, 31);
		frame.getContentPane().add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filechooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
				filechooser.setFileFilter(filter);
				int ret = filechooser.showDialog(null, "Save");                
				if (ret == JFileChooser.APPROVE_OPTION) {
				    File file = filechooser.getSelectedFile();
				    panel.SaveData(file.getAbsolutePath() + ".txt");
				}
			}
		});
		mnFile.add(mntmSave);
		
		JMenuItem mntmLoad = new JMenuItem("Load");
		mntmLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filechooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
				filechooser.setFileFilter(filter);
				int ret = filechooser.showDialog(null, "Load");                
				if (ret == JFileChooser.APPROVE_OPTION) {
				    File file = filechooser.getSelectedFile();
				    panel.LoadData(file.getAbsolutePath());
				    panel.repaint();
				}
			}
		});
		mnFile.add(mntmLoad);
	}
}
