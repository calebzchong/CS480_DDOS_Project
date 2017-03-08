package edu.cpp.cs480;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.TextArea;
import javax.swing.JTextField;


public class GUI {

	private JFrame frmMain;
	private List<AttackLog> attacks;
	private JTextField attackInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMain = new JFrame();
		frmMain.setTitle("DDOS Detection Program");

		frmMain.setBounds(100, 100, 1013, 483);
		frmMain.setBounds(100, 100, 804, 412);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.getContentPane().setLayout(null);
		
		attackInfo = new JTextField();
		attackInfo.setText("Type :      | Probability :       | Action :  ");
		attackInfo.setEditable(false);
		attackInfo.setBounds(335, 330, 423, 28);
		frmMain.getContentPane().add(attackInfo);
		attackInfo.setColumns(10);
		
		TextArea txtLog = new TextArea();
		txtLog.setEditable(false);
		txtLog.setEnabled(false);
		txtLog.setBounds(335, 10, 423, 314);
		frmMain.getContentPane().add(txtLog);
		
		java.awt.List attacksList = new java.awt.List();
		attacksList.setEnabled(false);
		attacksList.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
//				System.out.println(attacksList.getSelectedIndex());
				AttackLog att = attacks.get(attacksList.getSelectedIndex());
				txtLog.setText(att.fullLog);
				attackInfo.setText("Type: " + att.type + " | Probability: " + att.probability + " | Action: " + att.action);
				txtLog.setText(attacks.get(attacksList.getSelectedIndex()).fullLog);
			}
		});
		attacksList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		attacksList.setBounds(101, 10, 315, 348);
		attacksList.setBounds(101, 10, 228, 348);
		frmMain.getContentPane().add(attacksList);
		
		JButton btnNewButton = new JButton("Load");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(null);
				File f = fc.getSelectedFile();
				if ( f != null){
					String path = f.getAbsolutePath();
					try{
						attacks = LogReader.loadLog(path);
						for( int i = 0; i < attacks.size(); i++){
							attacksList.add(attacks.get(i).description);
						}
						attacksList.setEnabled(true);
						txtLog.setEnabled(true);
					} catch ( ArrayIndexOutOfBoundsException poo){
						JOptionPane.showMessageDialog(frmMain,
							    "Not a valid log file.",
							    "Haha",
							    JOptionPane.ERROR_MESSAGE);
					} 
					catch ( Exception ex ){
						ex.printStackTrace();
//						JOptionPane.showMessageDialog(frmMain,
//							    "Something happened",
//							    "Something happened",
//							    JOptionPane.WARNING_MESSAGE);
					}
				} 
			}
		});
		btnNewButton.setBounds(10, 12, 85, 23);
		frmMain.getContentPane().add(btnNewButton);
		
	}
}
