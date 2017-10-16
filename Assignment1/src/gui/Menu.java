package gui;


import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JSeparator;

import model.Core;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class Menu extends Core{

	JFrame frmMenu;


	/**
	 * Create the application.
	 */
	public Menu(String[] Session) {
		initialize(Session);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String[] Session) {
		frmMenu = new JFrame();
		frmMenu.setTitle("Menu");
		frmMenu.setResizable(false);
		frmMenu.setBounds(100, 100, 450, 455);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(null);
		
		JButton btnUser = new JButton("User");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User navigate = new User(Session);
				navigate.frmUser.setVisible(true);
				frmMenu.dispose(); 
			}
		});
		btnUser.setBounds(10, 105, 424, 23);
		frmMenu.getContentPane().add(btnUser);
		
		JButton btnItem = new JButton("Item");
		btnItem.setBounds(10, 139, 424, 23);
		btnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item navigate = new Item(Session);
				navigate.frmItem.setVisible(true);
				frmMenu.dispose(); 
			}
		});
		frmMenu.getContentPane().add(btnItem);
		
		JButton btnPartner = new JButton("Partner");
		btnPartner.setBounds(10, 173, 424, 23);
		btnPartner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Partner navigate = new Partner(Session);
				navigate.frmPartner.setVisible(true);
				frmMenu.dispose(); 
			}
		});
		frmMenu.getContentPane().add(btnPartner);
		
		JLabel lblMaster = new JLabel("Master");
		lblMaster.setBounds(10, 80, 424, 14);
		frmMenu.getContentPane().add(lblMaster);
		
		JLabel lblTransaction = new JLabel("Transaction");
		lblTransaction.setBounds(10, 207, 424, 14);
		frmMenu.getContentPane().add(lblTransaction);
		
		JButton btnPO = new JButton("Purchase Order");
		btnPO.setBounds(10, 232, 424, 23);
		btnPO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PO navigate = new PO(Session);
				navigate.frmPO.setVisible(true);
				frmMenu.dispose(); 
			}
		});
		frmMenu.getContentPane().add(btnPO);
		
		JButton btnGR = new JButton("Goods Receiving");
		btnGR.setLocation(10, 266);
		btnGR.setSize(424, 23);

		btnGR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GR navigate = new GR(Session);
				navigate.frmGR.setVisible(true);
				frmMenu.dispose(); 
			}
		});
		frmMenu.getContentPane().add(btnGR);
		
		JButton btnSO = new JButton("Sales Order");
		btnSO.setBounds(10, 300, 424, 23);
		btnSO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SO navigate = new SO(Session);
				navigate.frmSO.setVisible(true);
				frmMenu.dispose(); 
			}
		});
		frmMenu.getContentPane().add(btnSO);
		
		JButton btnDO = new JButton("Delivery Order");
		btnDO.setBounds(10, 334, 424, 23);
		btnDO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DO navigate = new DO(Session);
				navigate.frmDO.setVisible(true);
				frmMenu.dispose(); 
			}
		});
		frmMenu.getContentPane().add(btnDO);
		
		JLabel lblReport = new JLabel("Report");
		lblReport.setBounds(10, 368, 424, 14);
		frmMenu.getContentPane().add(lblReport);
		
		JButton btnStock = new JButton("Stock");
		btnStock.setBounds(10, 393, 424, 23);
		btnStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stock navigate = new Stock(Session);
				navigate.frmStock.setVisible(true);
				frmMenu.dispose(); 
			}
		});
		frmMenu.getContentPane().add(btnStock);
		
		ArrayList<String> SessionInfo = Core.getRecordRow("Select * From ms_user a "
		     	                                  + "Where a.UserID = '"+ Session[0] +"'");
		JLabel lblUsername = new JLabel(SessionInfo.get(0) + " - " + SessionInfo.get(1));
		lblUsername.setBounds(10, 11, 325, 14);
		frmMenu.getContentPane().add(lblUsername);
		
		String timeStamp = new SimpleDateFormat("dd MMM yyyy, HH:mm").format(Calendar.getInstance().getTime());
		JLabel lblDate = new JLabel(timeStamp.toString());
		lblDate.setBounds(10, 36, 424, 14);
		frmMenu.getContentPane().add(lblDate);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login navigate = new Login();
				navigate.frmLogin.setVisible(true);
				frmMenu.dispose(); 
			}
		});
		btnLogout.setForeground(Color.RED);
		btnLogout.setBounds(345, 7, 89, 23);
		frmMenu.getContentPane().add(btnLogout);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 67, 424, 2);
		frmMenu.getContentPane().add(separator);
	}
}
