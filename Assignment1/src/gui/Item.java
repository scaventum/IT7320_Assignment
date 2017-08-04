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

public class Item extends Core{

	JFrame frmItem;
	String Mode="Blank";
	static JTextField tfItemID;
	private JTextField tfName;
	private JTextField tfBuyPrice;
	private JTextField tfSellPrice;


	/**
	 * Create the application.
	 */
	public Item(String[] Session) {
		initialize(Session);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String[] Session) {
		ArrayList<String> SessionInfo = Core.getRecordRow("Select * From ms_user a Where a.UserID = '"+ Session[0] +"'");
		
		frmItem = new JFrame();
		frmItem.setResizable(false);
		frmItem.setTitle("Item");
		frmItem.setBounds(100, 100, 450, 200);
		frmItem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmItem.getContentPane().setLayout(null);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(10, 11, 60, 23);
		frmItem.getContentPane().add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(80, 11, 60, 23);
		btnEdit.setEnabled(false);
		frmItem.getContentPane().add(btnEdit);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(150, 11, 80, 23);
		btnSave.setEnabled(false);
		frmItem.getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(240, 11, 80, 23);
		frmItem.getContentPane().add(btnCancel);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(330, 11, 80, 23);
		btnDelete.setEnabled(false);
		frmItem.getContentPane().add(btnDelete);
		
		JLabel lbl1 = new JLabel("Item ID");
		lbl1.setBounds(10, 61, 100, 14);
		frmItem.getContentPane().add(lbl1);
		
		tfItemID = new JTextField();
		tfItemID.setColumns(10);
		tfItemID.setBounds(134, 58, 267, 20);
		frmItem.getContentPane().add(tfItemID);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(134, 86, 300, 20);
		tfName.setEditable(false);
		frmItem.getContentPane().add(tfName);
		
		JLabel lbl2 = new JLabel("Item Name");
		lbl2.setBounds(10, 89, 100, 14);
		frmItem.getContentPane().add(lbl2);
		
		JButton btnItemID = new JButton("...");
		btnItemID.setBounds(411, 58, 23, 23);
		frmItem.getContentPane().add(btnItemID);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 45, 424, 2);
		frmItem.getContentPane().add(separator);
		
		tfBuyPrice = new JTextField();
		tfBuyPrice.setEditable(false);
		tfBuyPrice.setColumns(10);
		tfBuyPrice.setBounds(134, 114, 300, 20);
		frmItem.getContentPane().add(tfBuyPrice);
		
		JLabel lblBuyprice = new JLabel("Buy Price");
		lblBuyprice.setBounds(10, 117, 100, 14);
		frmItem.getContentPane().add(lblBuyprice);
		
		tfSellPrice = new JTextField();
		tfSellPrice.setEditable(false);
		tfSellPrice.setColumns(10);
		tfSellPrice.setBounds(134, 142, 300, 20);
		frmItem.getContentPane().add(tfSellPrice);
		
		JLabel lblSellPrice = new JLabel("Sell Price");
		lblSellPrice.setBounds(10, 145, 100, 14);
		frmItem.getContentPane().add(lblSellPrice);
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu navigate = new Menu(Session);
				navigate.frmMenu.setVisible(true);
				frmItem.dispose(); 
			}
		});
		
		tfItemID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((Mode.equals("Blank") || Mode.equals("View")) && Core.getRecordNumber("Select 1 From ms_item a Where a.ItemID = '"+ tfItemID.getText() +"'")==1){
					Mode = "View";
					btnAdd.setEnabled(true);
					btnEdit.setEnabled(true);
					btnSave.setEnabled(false);
					btnDelete.setEnabled(true);
					btnItemID.setEnabled(true);
					tfItemID.setEditable(false);
					tfName.setEditable(false);
					tfBuyPrice.setEditable(false);
					tfSellPrice.setEditable(false);
					
					ArrayList<String> DataHD = Core.getRecordRow("Select ItemID,Name,BuyPrice,SellPrice From ms_item a "
                             								   + "Where a.ItemID = '"+ tfItemID.getText() +"'");
                    tfName.setText(DataHD.get(1));
                    tfBuyPrice.setText(DataHD.get(2));
                    tfSellPrice.setText(DataHD.get(3));
				}
			}
		});
		
		btnItemID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Browser navigate = new Browser("Select ItemID,Name,BuyPrice,SellPrice From ms_item Order By Name ASC",tfItemID);
				navigate.frmBrowser.setVisible(true);
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean transaction=true;
				String Message="";
				if(Core.getRecordNumber("Select 1 From ms_item a Where a.ItemID = '"+ tfItemID.getText() +"'")!=1){
					transaction=false;
					Message="Invalid Item ID";
				}
				if(Core.getRecordNumber("Select 1 From tr_po_dt a Where a.ItemID = '"+ tfItemID.getText() +"'")>0){
					transaction=false;
					Message="Item has been used in transaction";
				}
				if(Core.getRecordNumber("Select 1 From tr_so_dt a Where a.ItemID = '"+ tfItemID.getText() +"'")>0){
					transaction=false;
					Message="Item has been used in transaction";
				}
				
				if(transaction){
					int dialogResult = JOptionPane.showConfirmDialog (null, "Delete Data?","Confirmation",JOptionPane.WARNING_MESSAGE);
						if(dialogResult == JOptionPane.YES_OPTION){
							String strSql="";
							strSql = "Delete From ms_item Where ItemID = '" + tfItemID.getText() + "' ";	
							executeSQL(strSql);
							
							Mode = "Blank";
							btnAdd.setEnabled(true);
							btnEdit.setEnabled(false);
							btnSave.setEnabled(false);
							btnDelete.setEnabled(false);
							btnItemID.setEnabled(true);
							tfItemID.setEditable(true);
							tfName.setEditable(false);
							tfBuyPrice.setEditable(false);
							tfSellPrice.setEditable(false);
							tfItemID.setText("");
							tfName.setText("");
							tfBuyPrice.setText("");
							tfSellPrice.setText("");
	
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
				btnItemID.setEnabled(false);
				tfItemID.setEditable(true);
				tfName.setEditable(true);
				tfBuyPrice.setEditable(true);
				tfSellPrice.setEditable(true);
				tfItemID.setText("");
				tfName.setText("");
				tfBuyPrice.setText("");
				tfSellPrice.setText("");
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			//@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(Mode=="Add"){
					boolean transaction=true;
					String Message="";
					if(tfSellPrice.getText().matches("^-?\\d+$")==false){
						transaction=false;
						Message="Sell Price is invalid";
					}
					if(tfBuyPrice.getText().matches("^-?\\d+$")==false){
						transaction=false;
						Message="Buy Price is invalid";
					}
					if(tfName.getText().isEmpty()){
						transaction=false;
						Message="Item Name cannot be empty";
					}
					if(Core.getRecordNumber("Select 1 From ms_item a Where a.ItemID = '"+ tfItemID.getText() +"'")>0){
						transaction=false;
						Message="Item ID has been used";
					}
					if(tfItemID.getText().isEmpty()){
						transaction=false;
						Message="Item ID cannot be empty";
					}
					if(transaction){
						String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
						String strSql="";
						strSql = "Insert into ms_item (ItemID,Name,BuyPrice,SellPrice,UpdateTime,UpdateUserID) Values ( "
							   + "'" + tfItemID.getText() + "', "
							   + "'" + tfName.getText() + "', "
							   + " " + tfBuyPrice.getText() + " , "
							   + " " + tfSellPrice.getText() + " , "
							   + "'" + timeStamp + "', "
							   + "'" + SessionInfo.get(0) + "' "
							   + ")";	
						executeSQL(strSql);
						      
						Mode = "View";
						btnAdd.setEnabled(true);
						btnEdit.setEnabled(true);
						btnSave.setEnabled(false);
						btnDelete.setEnabled(true);
						btnItemID.setEnabled(true);
						tfName.setEditable(false);
						tfItemID.setEditable(false);
						tfBuyPrice.setEditable(false);
						tfSellPrice.setEditable(false);

					}else{
						JOptionPane.showMessageDialog(null, Message, "Abort", JOptionPane.WARNING_MESSAGE);
					}
				}
				
				if(Mode=="Edit"){
					boolean transaction=true;
					String Message="";
					if(tfSellPrice.getText().matches("^-?\\d+$")==false){
						transaction=false;
						Message="Sell Price is invalid";
					}
					if(tfBuyPrice.getText().matches("^-?\\d+$")==false){
						transaction=false;
						Message="Buy Price is invalid";
					}
					if(tfName.getText().isEmpty()){
						transaction=false;
						Message="Item Name cannot be empty";
					}
					if(transaction){
						String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
						String strSql="";
						strSql = "Update ms_item Set "
							   + "	Name='" + tfName.getText() + "', "
							   + "	BuyPrice=" + tfBuyPrice.getText() + ", "
							   + "	SellPrice=" + tfSellPrice.getText() + ", "
							   + "	UpdateTime='" + timeStamp + "', "
							   + "	UpdateUserID='" + SessionInfo.get(0) + "' "
							   + "Where ItemID='" + tfItemID.getText() + "' ";
						executeSQL(strSql);
						      
						Mode = "View";
						btnAdd.setEnabled(true);
						btnEdit.setEnabled(true);
						btnSave.setEnabled(false);
						btnDelete.setEnabled(true);
						btnItemID.setEnabled(true);
						tfName.setEditable(false);
						tfItemID.setEditable(false);
						tfBuyPrice.setEditable(false);
						tfSellPrice.setEditable(false);
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
				btnItemID.setEnabled(false);
				tfName.setEditable(true);
				tfBuyPrice.setEditable(true);
				tfSellPrice.setEditable(true);
				
			}
		});
	}

}
