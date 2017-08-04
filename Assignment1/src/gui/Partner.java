package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Core;

import javax.swing.JSeparator;
import javax.swing.JTextArea;

public class Partner extends Core {

	JFrame frmPartner;
	JTextField tfPartnerID;
	private JTextField tfName;
	String Mode="Blank";

	/**
	 * Create the application.
	 */
	public Partner(String[] Session) {
		initialize(Session);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String[] Session) {
		ArrayList<String> SessionInfo = Core.getRecordRow("Select * From ms_user a Where a.UserID = '"+ Session[0] +"'");
		
		frmPartner = new JFrame();
		frmPartner.setResizable(false);
		frmPartner.setTitle("Partner");
		frmPartner.setBounds(100, 100, 450, 200);
		frmPartner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPartner.getContentPane().setLayout(null);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(10, 11, 60, 23);
		frmPartner.getContentPane().add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(80, 11, 60, 23);
		btnEdit.setEnabled(false);
		frmPartner.getContentPane().add(btnEdit);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(150, 11, 80, 23);
		btnSave.setEnabled(false);
		frmPartner.getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(240, 11, 80, 23);
		frmPartner.getContentPane().add(btnCancel);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(330, 11, 80, 23);
		btnDelete.setEnabled(false);
		frmPartner.getContentPane().add(btnDelete);
		
		JLabel lbl1 = new JLabel("Partner ID");
		lbl1.setBounds(10, 61, 100, 14);
		frmPartner.getContentPane().add(lbl1);
		
		tfPartnerID = new JTextField();
		tfPartnerID.setColumns(10);
		tfPartnerID.setBounds(134, 58, 267, 20);
		frmPartner.getContentPane().add(tfPartnerID);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(134, 86, 300, 20);
		tfName.setEditable(false);
		frmPartner.getContentPane().add(tfName);
		
		JLabel lbl2 = new JLabel("Partner Name");
		lbl2.setBounds(10, 89, 100, 14);
		frmPartner.getContentPane().add(lbl2);
		
		JButton btnPartnerID = new JButton("...");
		btnPartnerID.setBounds(411, 58, 23, 23);
		frmPartner.getContentPane().add(btnPartnerID);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 45, 424, 2);
		frmPartner.getContentPane().add(separator);
		
		JLabel lblBuyprice = new JLabel("Address");
		lblBuyprice.setBounds(10, 117, 100, 14);
		frmPartner.getContentPane().add(lblBuyprice);
		
		JTextArea taAddress = new JTextArea();
		taAddress.setEditable(false);
		taAddress.setBounds(134, 117, 300, 44);
		frmPartner.getContentPane().add(taAddress);
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu navigate = new Menu(Session);
				navigate.frmMenu.setVisible(true);
				frmPartner.dispose(); 
			}
		});
		
		tfPartnerID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((Mode.equals("Blank") || Mode.equals("View")) && Core.getRecordNumber("Select 1 From ms_partner a Where a.PartnerID = '"+ tfPartnerID.getText() +"'")==1){
					Mode = "View";
					btnAdd.setEnabled(true);
					btnEdit.setEnabled(true);
					btnSave.setEnabled(false);
					btnDelete.setEnabled(true);
					btnPartnerID.setEnabled(true);
					tfPartnerID.setEditable(false);
					tfName.setEditable(false);
					taAddress.setEditable(false);
					
					ArrayList<String> DataHD = Core.getRecordRow("Select PartnerID,Name,Address From ms_partner a "
                             								   + "Where a.PartnerID = '"+ tfPartnerID.getText() +"'");
                    tfName.setText(DataHD.get(1));
                    taAddress.setText(DataHD.get(2));
				}
			}
		});
		
		btnPartnerID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Browser navigate = new Browser("Select PartnerID,Name,Address From ms_partner Order By Name ASC",tfPartnerID);
				navigate.frmBrowser.setVisible(true);
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean transaction=true;
				String Message="";
				if(Core.getRecordNumber("Select 1 From ms_partner a Where a.PartnerID = '"+ tfPartnerID.getText() +"'")!=1){
					transaction=false;
					Message="Invalid Partner ID";
				}
				if(Core.getRecordNumber("Select 1 From tr_po_hd a Where a.SupplierID = '"+ tfPartnerID.getText() +"'")>0){
					transaction=false;
					Message="Partner has been used in transaction";
				}
				if(Core.getRecordNumber("Select 1 From tr_so_hd a Where a.CustomerID = '"+ tfPartnerID.getText() +"'")>0){
					transaction=false;
					Message="Partner has been used in transaction";
				}
				
				if(transaction){
					int dialogResult = JOptionPane.showConfirmDialog (null, "Delete Data?","Confirmation",JOptionPane.WARNING_MESSAGE);
						if(dialogResult == JOptionPane.YES_OPTION){
							String strSql="";
							strSql = "Delete From ms_partner Where PartnerID = '" + tfPartnerID.getText() + "' ";	
							executeSQL(strSql);
							
							Mode = "Blank";
							btnAdd.setEnabled(true);
							btnEdit.setEnabled(false);
							btnSave.setEnabled(false);
							btnDelete.setEnabled(false);
							btnPartnerID.setEnabled(true);
							tfPartnerID.setEditable(true);
							tfName.setEditable(false);
							taAddress.setEditable(false);
							tfPartnerID.setText("");
							tfName.setText("");
							taAddress.setText("");
	
						}
				}else{
					JOptionPane.showMessageDialog(null, Message, "Abort", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mode = "Add";
				btnAdd.setEnabled(false);
				btnEdit.setEnabled(false);
				btnSave.setEnabled(true);
				btnDelete.setEnabled(false);
				btnPartnerID.setEnabled(false);
				tfPartnerID.setEditable(true);
				tfName.setEditable(true);
				taAddress.setEditable(true);
				tfPartnerID.setText("");
				tfName.setText("");
				taAddress.setText("");
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			//@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(Mode=="Add"){
					boolean transaction=true;
					String Message="";
					if(tfName.getText().isEmpty()){
						transaction=false;
						Message="Partner Name cannot be empty";
					}
					if(Core.getRecordNumber("Select 1 From ms_partner a Where a.PartnerID = '"+ tfPartnerID.getText() +"'")>0){
						transaction=false;
						Message="Partner ID has been used";
					}
					if(tfPartnerID.getText().isEmpty()){
						transaction=false;
						Message="Partner ID cannot be empty";
					}
					if(transaction){
						String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
						String strSql="";
						strSql = "Insert into ms_partner (PartnerID,Name,Address,UpdateTime,UpdateUserID) Values ( "
							   + "'" + tfPartnerID.getText() + "', "
							   + "'" + tfName.getText() + "', "
							   + "'" + taAddress.getText() + "' , "
							   + "'" + timeStamp + "', "
							   + "'" + SessionInfo.get(0) + "' "
							   + ")";	
						executeSQL(strSql);
						      
						Mode = "View";
						btnAdd.setEnabled(true);
						btnEdit.setEnabled(true);
						btnSave.setEnabled(false);
						btnDelete.setEnabled(true);
						btnPartnerID.setEnabled(true);
						tfName.setEditable(false);
						tfPartnerID.setEditable(false);
						taAddress.setEditable(false);

					}else{
						JOptionPane.showMessageDialog(null, Message, "Abort", JOptionPane.WARNING_MESSAGE);
					}
				}
				
				if(Mode=="Edit"){
					boolean transaction=true;
					String Message="";
					if(tfName.getText().isEmpty()){
						transaction=false;
						Message="Partner Name cannot be empty";
					}
					if(transaction){
						String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
						String strSql="";
						strSql = "Update ms_partner Set "
							   + "	Name='" + tfName.getText() + "', "
							   + "	Address='" + taAddress.getText() + "', "
							   + "	UpdateTime='" + timeStamp + "', "
							   + "	UpdateUserID='" + SessionInfo.get(0) + "' "
							   + "Where PartnerID='" + tfPartnerID.getText() + "' ";
						executeSQL(strSql);
						      
						Mode = "View";
						btnAdd.setEnabled(true);
						btnEdit.setEnabled(true);
						btnSave.setEnabled(false);
						btnDelete.setEnabled(true);
						btnPartnerID.setEnabled(true);
						tfName.setEditable(false);
						tfPartnerID.setEditable(false);
						taAddress.setEditable(false);
					}else{
						JOptionPane.showMessageDialog(null, Message, "", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mode = "Edit";
				btnAdd.setEnabled(false);
				btnEdit.setEnabled(false);
				btnSave.setEnabled(true);
				btnDelete.setEnabled(false);
				btnPartnerID.setEnabled(false);
				tfName.setEditable(true);
				taAddress.setEditable(true);
				
			}
		});
	}
}
