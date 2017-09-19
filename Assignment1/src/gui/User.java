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

import javax.swing.JPasswordField;
import javax.swing.JSeparator;

public class User extends Core {

	JFrame frmUser;
	String Mode="Blank";
	static JTextField tfUserID;
	private JPasswordField pfPassword;
	private JTextField tfName;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public User(String[] Session) {
		initialize(Session);
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String[] Session) {
		ArrayList<String> SessionInfo = Core.getRecordRow("Select * From ms_user a Where a.UserID = '"+ Session[0] +"'");

		frmUser = new JFrame();
		frmUser.setResizable(false);
		frmUser.setTitle("User");
		frmUser.setBounds(100, 100, 450, 170);
		frmUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUser.getContentPane().setLayout(null);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(10, 11, 60, 23);
	
		frmUser.getContentPane().add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(80, 11, 60, 23);
		btnEdit.setEnabled(false);
		frmUser.getContentPane().add(btnEdit);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(150, 11, 80, 23);
		btnSave.setEnabled(false);
		frmUser.getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(240, 11, 80, 23);
		frmUser.getContentPane().add(btnCancel);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(330, 11, 80, 23);
		btnDelete.setEnabled(false);
		frmUser.getContentPane().add(btnDelete);
		
		JLabel lblUserID = new JLabel("User ID");
		lblUserID.setBounds(10, 61, 100, 14);
		frmUser.getContentPane().add(lblUserID);
		
		tfUserID = new JTextField();
		tfUserID.setColumns(10);
		tfUserID.setBounds(134, 58, 267, 20);
		frmUser.getContentPane().add(tfUserID);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 116, 100, 14);
		frmUser.getContentPane().add(lblPassword);
		
		pfPassword = new JPasswordField();
		pfPassword.setBounds(134, 114, 300, 20);
		pfPassword.setEditable(false);
		frmUser.getContentPane().add(pfPassword);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(134, 86, 300, 20);
		tfName.setEditable(false);
		frmUser.getContentPane().add(tfName);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 89, 100, 14);
		frmUser.getContentPane().add(lblUsername);
		
		JButton btnUserID = new JButton("...");

		btnUserID.setBounds(411, 58, 23, 23);
		frmUser.getContentPane().add(btnUserID);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 45, 424, 2);
		frmUser.getContentPane().add(separator);
		
		tfUserID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((Mode.equals("Blank") || Mode.equals("View")) && Core.getRecordNumber("Select 1 From ms_user a Where a.UserID = '"+ tfUserID.getText() +"'")==1){
					Mode = "View";
					btnAdd.setEnabled(true);
					btnEdit.setEnabled(true);
					btnSave.setEnabled(false);
					btnDelete.setEnabled(true);
					btnUserID.setEnabled(true);
					tfName.setEditable(false);
					tfUserID.setEditable(false);
					pfPassword.setEditable(false);
					
					ArrayList<String> DataHD = Core.getRecordRow("Select UserID,Name,Password From ms_user a "
                             								   + "Where a.UserID = '"+ tfUserID.getText() +"'");
                    tfName.setText(DataHD.get(1));
                    pfPassword.setText(DataHD.get(2));
				}
			}
		});
		
		btnUserID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Browser navigate = new Browser("Select UserID,Name From ms_user Order By Name ASC",tfUserID);
				navigate.frmBrowser.setVisible(true);
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu navigate = new Menu(Session);
				navigate.frmMenu.setVisible(true);
				frmUser.dispose(); 
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean transaction=true;
				String Message="";
				if(Core.getRecordNumber("Select 1 From ms_user a Where a.UserID = '"+ tfUserID.getText() +"'")!=1){
					transaction=false;
					Message="Invalid User ID";
				}
				if(tfUserID.getText().equals(SessionInfo.get(0))){
					transaction=false;
					Message="User ID is currently logged in";
				}
				
				if(transaction){
					int dialogResult = JOptionPane.showConfirmDialog (null, "Delete Data?","Confirmation",JOptionPane.WARNING_MESSAGE);
						if(dialogResult == JOptionPane.YES_OPTION){
							String strSql="";
							strSql = "Delete From ms_user Where UserID = '" + tfUserID.getText() + "' ";	
							executeSQL(strSql);
							
							Mode = "Blank";
							btnAdd.setEnabled(true);
							btnEdit.setEnabled(false);
							btnSave.setEnabled(false);
							btnDelete.setEnabled(false);
							btnUserID.setEnabled(true);
							tfUserID.setEditable(true);
							tfName.setEditable(false);
							pfPassword.setEditable(false);
							tfUserID.setText("");
							tfName.setText("");
							pfPassword.setText("");
	
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
				btnUserID.setEnabled(false);
				tfUserID.setEditable(true);
				tfName.setEditable(true);
				pfPassword.setEditable(true);
				tfUserID.setText("");
				tfName.setText("");
				pfPassword.setText("");
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			//@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(Mode=="Add"){
					String pwd = ""+new String(pfPassword.getPassword());
					boolean transaction=true;
					String Message="";
					if(pwd.isEmpty()){
						transaction=false;
						Message="Password cannot be empty";
					}
					if(tfName.getText().isEmpty()){
						transaction=false;
						Message="Username cannot be empty";
					}
					if(Core.getRecordNumber("Select 1 From ms_user a Where a.UserID = '"+ tfUserID.getText() +"'")>0){
						transaction=false;
						Message="User ID has been used";
					}
					if(tfUserID.getText().isEmpty()){
						transaction=false;
						Message="User ID cannot be empty";
					}
					if(transaction){
						String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
						String strSql="";
						strSql = "Insert into ms_user (UserID,Name,Password,UpdateTime,UpdateUserID) Values ( "
							   + "'" + tfUserID.getText() + "', "
							   + "'" + tfName.getText() + "', "
							   + "'" + pwd + "', "
							   + "'" + timeStamp + "', "
							   + "'" + SessionInfo.get(0) + "' "
							   + ")";	
						executeSQL(strSql);
						      
						Mode = "View";
						btnAdd.setEnabled(true);
						btnEdit.setEnabled(true);
						btnSave.setEnabled(false);
						btnDelete.setEnabled(true);
						btnUserID.setEnabled(true);
						tfName.setEditable(false);
						tfUserID.setEditable(false);
						pfPassword.setEditable(false);

					}else{
						JOptionPane.showMessageDialog(null, Message, "Abort", JOptionPane.WARNING_MESSAGE);
					}
				}
				
				if(Mode=="Edit"){
					boolean transaction=true;
					String Message="";
					String pwd = ""+new String(pfPassword.getPassword());
					if(pwd.isEmpty()){
						transaction=false;
						Message="Password cannot be empty";
					}
					if(tfName.getText().isEmpty()){
						transaction=false;
						Message="Username cannot be empty";
					}
					if(transaction){
						String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
						String strSql="";
						strSql = "Update ms_user Set "
							   + "	Name='" + tfName.getText() + "', "
							   + "	Password='" + pwd + "', "
							   + "	UpdateTime='" + timeStamp + "', "
							   + "	UpdateUserID='" + SessionInfo.get(0) + "' "
							   + "Where UserID='" + tfUserID.getText() + "' ";
						executeSQL(strSql);
						      
						Mode = "View";
						btnAdd.setEnabled(true);
						btnEdit.setEnabled(true);
						btnSave.setEnabled(false);
						btnDelete.setEnabled(true);
						btnUserID.setEnabled(true);
						tfName.setEditable(false);
						tfUserID.setEditable(false);
						pfPassword.setEditable(false);
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
				btnUserID.setEnabled(false);
				tfName.setEditable(true);
				pfPassword.setEditable(true);
				
			}
		});
	}

}
