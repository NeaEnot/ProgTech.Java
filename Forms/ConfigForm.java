package Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Classes.ExcavatorTractor;
import Classes.TrackRollers;
import Classes.TrackRollersCircle;
import Classes.TrackRollersSpokes;
import Classes.Tractor;
import Classes.Transport;
import Delegates.TransportDelegate;
import Interfaces.IRollers;
import Interfaces.ITransport;
import Panels.PanelET;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JInternalFrame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ConfigForm {

	private JFrame frame;
	
	private ITransport tempTractor = null;
	private IRollers tempRollers = null;
	private Color tempColor = null;
	
	TransportDelegate add;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigForm window = new ConfigForm(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ConfigForm(TransportDelegate delegate) {
		add = delegate;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 645, 352);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		frame.setVisible(true);
		
		PanelET panel = new PanelET();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (tempTractor != null) {
					panel.setTransport(tempTractor);
					panel.repaint();
				}
				if (tempRollers != null) {
					if (panel.getTransport() != null) {
						((Tractor) panel.getTransport()).setTrackRollers(tempRollers);
						panel.repaint();
					}
				}
			}
		});
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(195, 42, 115, 94);
		frame.getContentPane().add(panel);
		
		JLabel lblTractor = new JLabel("Tractor");
		lblTractor.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				tempTractor = new Tractor(100, 100, Color.gray);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				tempTractor = null;
			}
		});
		lblTractor.setBounds(15, 16, 69, 20);
		frame.getContentPane().add(lblTractor);
		
		JLabel lblExcavatorTractor = new JLabel("Excavator Tractor");
		lblExcavatorTractor.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				tempTractor = new ExcavatorTractor(100, 100, Color.gray, Color.black, 
													Color.getHSBColor((float) Math.random(), 
																		(float) Math.random(), 
																		(float) Math.random()), 
													true, true, true);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				tempTractor = null;
			}
		});
		lblExcavatorTractor.setBounds(15, 45, 132, 20);
		frame.getContentPane().add(lblExcavatorTractor);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(15, 251, 115, 29);
		frame.getContentPane().add(btnCancel);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				add.invoke(panel.getTransport());
				frame.dispose();
			}
		});
		btnOk.setBounds(15, 206, 115, 29);
		frame.getContentPane().add(btnOk);
		
		JLabel lblTrackRollers = new JLabel("Track Rollers");
		lblTrackRollers.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tempRollers = new TrackRollers((int)(Math.random() * 3) + 4);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				tempRollers = null;
			}
		});
		lblTrackRollers.setBounds(15, 90, 132, 20);
		frame.getContentPane().add(lblTrackRollers);
		
		JLabel lblTrackRollersCircle = new JLabel("Track Rollers Circle");
		lblTrackRollersCircle.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tempRollers = new TrackRollersCircle((int)(Math.random() * 3) + 4);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				tempRollers = null;
			}
		});
		lblTrackRollersCircle.setBounds(15, 116, 165, 20);
		frame.getContentPane().add(lblTrackRollersCircle);
		
		JLabel lblTrackRollersSpokes = new JLabel("Track Rollers Spokes");
		lblTrackRollersSpokes.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tempRollers = new TrackRollersSpokes((int)(Math.random() * 3) + 4);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				tempRollers = null;
			}
		});
		lblTrackRollersSpokes.setBounds(15, 141, 165, 20);
		frame.getContentPane().add(lblTrackRollersSpokes);
		
		JLabel lblMainColor = new JLabel("Main Color");
		lblMainColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (tempColor != null && panel.getTransport() != null) {
					((Tractor) panel.getTransport()).setColor(tempColor);
					tempColor = null;
					panel.repaint();
				}
			}
		});
		lblMainColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainColor.setBounds(195, 170, 115, 20);
		frame.getContentPane().add(lblMainColor);
		
		JLabel lblDopColor = new JLabel("Dop Color");
		lblDopColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (tempColor != null && panel.getTransport() != null && panel.getTransport() instanceof ExcavatorTractor) {
					((ExcavatorTractor) panel.getTransport()).setDopColor(tempColor);
					tempColor = null;
					panel.repaint();
				}
			}
		});
		lblDopColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDopColor.setBounds(195, 210, 115, 20);
		frame.getContentPane().add(lblDopColor);
		
		JPanel panelWhite = new JPanel();
		panelWhite.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tempColor = Color.white;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				tempColor = null;
			}
		});
		panelWhite.setBackground(Color.WHITE);
		panelWhite.setBounds(374, 42, 40, 40);
		frame.getContentPane().add(panelWhite);
		
		JPanel panelBlack = new JPanel();
		panelBlack.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tempColor = Color.black;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				tempColor = null;
			}
		});
		panelBlack.setBackground(Color.BLACK);
		panelBlack.setBounds(429, 42, 40, 40);
		frame.getContentPane().add(panelBlack);
		
		JPanel panelRed = new JPanel();
		panelRed.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tempColor = Color.red;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				tempColor = null;
			}
		});
		panelRed.setBackground(Color.RED);
		panelRed.setBounds(429, 90, 40, 40);
		frame.getContentPane().add(panelRed);
		
		JPanel panelGray = new JPanel();
		panelGray.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tempColor = Color.gray;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				tempColor = null;
			}
		});
		panelGray.setBackground(Color.GRAY);
		panelGray.setBounds(374, 90, 40, 40);
		frame.getContentPane().add(panelGray);
		
		JPanel panelGreen = new JPanel();
		panelGreen.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tempColor = Color.green;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				tempColor = null;
			}
		});
		panelGreen.setBackground(Color.GREEN);
		panelGreen.setBounds(429, 189, 40, 40);
		frame.getContentPane().add(panelGreen);
		
		JPanel panelBlue = new JPanel();
		panelBlue.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tempColor = Color.blue;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				tempColor = null;
			}
		});
		panelBlue.setBackground(Color.BLUE);
		panelBlue.setBounds(374, 189, 40, 40);
		frame.getContentPane().add(panelBlue);
		
		JPanel panelYellow = new JPanel();
		panelYellow.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				tempColor = Color.yellow;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				tempColor = null;
			}
		});
		panelYellow.setBackground(Color.YELLOW);
		panelYellow.setBounds(374, 141, 40, 40);
		frame.getContentPane().add(panelYellow);
		
		JPanel panelMagenta = new JPanel();
		panelMagenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tempColor = Color.magenta;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				tempColor = null;
			}
		});
		panelMagenta.setBackground(Color.MAGENTA);
		panelMagenta.setBounds(429, 141, 40, 40);
		frame.getContentPane().add(panelMagenta);
	}
}
