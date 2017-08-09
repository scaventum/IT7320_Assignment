package gui;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Core;

import javax.swing.JTextArea;

public class DO extends Core{

	JFrame frmDO;
	JTextField tfDONO;
	private JTextField tfDate;
	JTextField tfSONO;
	JTable tblDetail;
	private JTextField tfGrandTotal;
	private JTextField tfCustomerID;
	String Mode="Blank";

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DO window = new DO();
					window.frmDO.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public DO(String[] Session) {
		initialize(Session);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String[] Session) {
		DefaultTableCellRenderer readonly = new DefaultTableCellRenderer();
		DefaultTableCellRenderer editable = new DefaultTableCellRenderer();
		readonly.setBackground(new Color(224, 224, 224));
		editable.setBackground(new Color(255, 255, 255));
		
		ArrayList<String> SessionInfo = Core.getRecordRow("Select * From ms_user a Where a.UserID = '"+ Session[0] +"'");
		
		frmDO = new JFrame();
		frmDO.setResizable(false);
		frmDO.setTitle("Delivery Order");
		frmDO.setBounds(100, 100, 550, 455);
		frmDO.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDO.getContentPane().setLayout(null);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(10, 11, 60, 23);
		frmDO.getContentPane().add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(80, 11, 60, 23);
		btnEdit.setEnabled(false);
		frmDO.getContentPane().add(btnEdit);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(150, 11, 80, 23);
		btnSave.setEnabled(false);
		frmDO.getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(240, 11, 80, 23);
		frmDO.getContentPane().add(btnCancel);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(330, 11, 80, 23);
		btnDelete.setEnabled(false);
		frmDO.getContentPane().add(btnDelete);
		
		JLabel lbl1 = new JLabel("DO No.");
		lbl1.setBounds(10, 61, 75, 14);
		frmDO.getContentPane().add(lbl1);
		
		tfDONO = new JTextField();
		tfDONO.setColumns(10);
		tfDONO.setBounds(95, 58, 135, 20);
		frmDO.getContentPane().add(tfDONO);
		
		tfDate = new JTextField();
		tfDate.setColumns(10);
		tfDate.setBounds(95, 86, 168, 20);
		tfDate.setEditable(false);
		frmDO.getContentPane().add(tfDate);
		
		JLabel lbl2 = new JLabel("DO Date");
		lbl2.setBounds(10, 89, 75, 14);
		frmDO.getContentPane().add(lbl2);
		
		JButton btnDONO = new JButton("...");
		btnDONO.setBounds(240, 57, 23, 23);
		frmDO.getContentPane().add(btnDONO);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 45, 524, 2);
		frmDO.getContentPane().add(separator);
		
		tfSONO = new JTextField();
		tfSONO.setEditable(false);
		tfSONO.setColumns(10);
		tfSONO.setBounds(95, 115, 135, 20);
		frmDO.getContentPane().add(tfSONO);
		
		JButton btnSONO = new JButton("...");
		btnSONO.setEnabled(false);
		btnSONO.setBounds(240, 114, 23, 23);
		frmDO.getContentPane().add(btnSONO);
		
		JLabel lblSupplierId = new JLabel("SO No.");
		lblSupplierId.setBounds(10, 118, 75, 14);
		frmDO.getContentPane().add(lblSupplierId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 146, 524, 241);
		frmDO.getContentPane().add(scrollPane);
		
		tblDetail = new JTable();
		tblDetail.setToolTipText("");
		tblDetail.setEnabled(false);
		scrollPane.setViewportView(tblDetail);
		tblDetail.setModel(new DefaultTableModel(new Object[][] {},new String[] {"Item ID", "Item Name", "Retail Price","Qty Remain","Qty Stock", "Qty", "Price"}) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {false, false, false, false, false, true, false};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblDetail.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		JLabel lblTotal = new JLabel("Grand Total");
		lblTotal.setBounds(10, 401, 346, 14);
		frmDO.getContentPane().add(lblTotal);
		
		tfGrandTotal = new JTextField();
		tfGrandTotal.setEditable(false);
		tfGrandTotal.setColumns(10);
		tfGrandTotal.setBounds(366, 398, 168, 20);
		frmDO.getContentPane().add(tfGrandTotal);
		
		tfCustomerID = new JTextField();
		tfCustomerID.setEditable(false);
		tfCustomerID.setColumns(10);
		tfCustomerID.setBounds(366, 58, 168, 20);
		frmDO.getContentPane().add(tfCustomerID);
		
		JLabel lblSupplierId_1 = new JLabel("Customer ID");
		lblSupplierId_1.setBounds(281, 61, 75, 14);
		frmDO.getContentPane().add(lblSupplierId_1);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(281, 89, 75, 14);
		frmDO.getContentPane().add(lblAddress);
		
		JTextArea taAddress = new JTextArea();
		taAddress.setWrapStyleWord(true);
		taAddress.setEditable(false);
		taAddress.setBounds(366, 86, 168, 51);
		frmDO.getContentPane().add(taAddress);
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String timeStamp = new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime());
				Mode = "Add";
				btnAdd.setEnabled(false);
				btnEdit.setEnabled(false);
				btnSave.setEnabled(true);
				btnDelete.setEnabled(false);
				btnDONO.setEnabled(false);
				btnSONO.setEnabled(true);
				tfDONO.setEditable(false);
				tfSONO.setEditable(false);
				tfDate.setEditable(false);
				tfCustomerID.setEditable(false);
				taAddress.setEditable(true);
				tblDetail.setEnabled(true);
				ClearTable(tblDetail);
				tfDONO.setText("");
				tfSONO.setText("");
				taAddress.setText("");
				tfDate.setText(timeStamp);
				tfCustomerID.setText("");
			    tblDetail.getColumnModel().getColumn(0).setCellRenderer(readonly);
			    tblDetail.getColumnModel().getColumn(1).setCellRenderer(readonly);
			    tblDetail.getColumnModel().getColumn(2).setCellRenderer(readonly);
			    tblDetail.getColumnModel().getColumn(3).setCellRenderer(readonly);
			    tblDetail.getColumnModel().getColumn(4).setCellRenderer(readonly);
			    tblDetail.getColumnModel().getColumn(5).setCellRenderer(editable);
			    tblDetail.getColumnModel().getColumn(6).setCellRenderer(readonly);
			}
		});
		
		btnSONO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Browser navigate = new Browser("Select a.SONO,DATE_FORMAT(a.date, '%d-%b-%Y') as 'PO Date',b.Name "
						                     + "From tr_so_hd a "
						                     + "Inner join ms_partner b On b.PartnerID = a.CustomerID "
						                     + "Where (Select sum(Qty) From tr_so_dt i Where i.SONO = a.SONO) > "
						                     + "      ifnull((Select sum(k.Qty) From tr_do_hd j "
						                     + "       Inner Join tr_do_dt k On k.DONO = j.DONO"
						                     + "       Where j.SONO = a.SONO),0) "
						                     + "Order By a.SONO DESC",tfSONO);
				
				navigate.frmBrowser.setVisible(true);
			}
		});
		
		tfSONO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				ArrayList<String> DataHD = Core.getRecordRow("Select a.SONO,a.CustomerID,b.Address From tr_so_hd a "
						                                   + "Inner Join ms_partner b On b.PartnerID = a.CustomerID "
                         								   + "Where a.SONO = '"+ tfSONO.getText() +"'");
				tfSONO.setText(DataHD.get(0));
				tfCustomerID.setText(DataHD.get(1));
				taAddress.setText(DataHD.get(2));
				  
				String strSql = "Select a.ItemID,b.Name,a.RetailPrice,"
						      + "a.Qty-ifnull((Select sum(k.Qty) From tr_do_hd j "
		                      + "              Inner Join tr_do_dt k On k.DONO = j.DONO  "
		                      + "              Where j.SONO = a.SONO And k.ItemID = a.ItemID),0),"
						      + "ifnull((Select sum(j.Qty) From ba_stock j "
		                      + "              Where j.ItemID = a.ItemID),0),"
						      + "0,0 as Price "
	                          + "From tr_so_dt a "
	                          + "Inner Join ms_item b On b.ItemID=a.ItemID "
	                          + "Where a.SONO = '"+ tfSONO.getText() +"' Order By a.ItemID Asc";

			    // It creates and displays the table
				FillTable(tblDetail, strSql);

		        TableModel model = tblDetail.getModel();
			    int totalPrice=0;
            	for(int i=0;i<model.getRowCount();i++){
            		totalPrice+=Integer.parseInt(model.getValueAt(i, 6).toString());
            	}
            	
            	DecimalFormat formatter = new DecimalFormat("#,###.00");
            	tfGrandTotal.setText(formatter.format(totalPrice)+"");
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean transaction=true;
				String Message="";

		        TableModel model = tblDetail.getModel();
				int totalQty=0;
				boolean validQty=true;
            	for(int i=0;i<model.getRowCount();i++){
            		totalQty+=Integer.parseInt(model.getValueAt(i, 4).toString());
            		if(Integer.parseInt(model.getValueAt(i, 5).toString()) > Integer.parseInt(model.getValueAt(i, 3).toString())){
            			validQty=false;
            		}
            		if(Integer.parseInt(model.getValueAt(i, 5).toString()) > Integer.parseInt(model.getValueAt(i, 4).toString())){
            			validQty=false;
            		}
            	}
            	
            	if(totalQty<=0 || !validQty){
            		transaction=false;
					Message="Invalid Qty";
				}
                	
				if(transaction){
					if(Mode=="Add"){
						String DOFormat = "DO/"+new SimpleDateFormat("yyyy/MM/").format(Calendar.getInstance().getTime());
						int DOLastInt = 1;
						if(Core.getRecordNumber("Select Right(a.DONO,3) ai "
	                            			   +"From tr_do_hd a "
	                                           +"Where a.DONO LIKE '" + DOFormat + "%' "
	                                           +"Order By a.DONO Desc Limit 0,1")==1){
							
							ArrayList<String> DOLast = Core.getRecordRow("Select Right(a.DONO,3) ai "
						                                                +"From tr_do_hd a "
						                                                +"Where a.DONO LIKE '" + DOFormat + "%' "
						                                                +"Order By a.DONO Desc");
							DOLastInt = Integer.parseInt(DOLast.get(0))+1;
						}
						String DOLastStr = String.format("%03d", DOLastInt);
						tfDONO.setText(DOFormat+DOLastStr);
					}
					
					String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
					ArrayList<String> strSqls = new ArrayList<String>();
					String strSql="";

					strSql = "Delete From ba_stock Where TransactionNO = '" + tfDONO.getText() + "' ";	
					strSqls.add(strSql);
					strSql = "Delete From tr_do_dt Where DONO = '" + tfDONO.getText() + "' ";	
					strSqls.add(strSql);
					strSql = "Delete From tr_do_hd Where DONO = '" + tfDONO.getText() + "' ";	
					strSqls.add(strSql);
					
					strSql = "Insert into tr_do_hd (DONO,Date,SONO,Address,UpdateTime,UpdateUserID) Values ( "
							   + "'" + tfDONO.getText() + "', "
							   + "'" + timeStamp + "', "
							   + "'" + tfSONO.getText() + "' , "
							   + "'" + taAddress.getText() + "' , "
							   + "'" + timeStamp + "', "
							   + "'" + SessionInfo.get(0) + "' "
							   + ")";	
					strSqls.add(strSql);
					
                	for(int i=0;i<model.getRowCount();i++){
                		strSql = "Insert into tr_do_dt (DONO,ItemID,Qty) Values ( "
								   + "'" + tfDONO.getText() + "', "
								   + "'" + model.getValueAt(i, 0).toString() + "', "
								   + "" + Integer.parseInt(model.getValueAt(i, 5).toString()) + "  "
								   + ")";	
						strSqls.add(strSql);
						
						if(Integer.parseInt(model.getValueAt(i, 4).toString())>0){
							strSql = "Insert into ba_stock (ItemID,TransactionNO,Qty) Values ( "
									   + "'" + model.getValueAt(i, 0).toString() + "', "
									   + "'" + tfDONO.getText() + "', "
									   + "" + Integer.parseInt(model.getValueAt(i, 5).toString())*(-1) + "  "
									   + ")";	
							strSqls.add(strSql);
						}
                	}
					
					executeSQLs(strSqls);
				      
					Mode = "Blank";
					tfDONO.requestFocus();
					try {
	                    Robot robot = new Robot();
	                    robot.keyPress(KeyEvent.VK_ENTER);
	                } catch (AWTException awte) {
	                	awte.printStackTrace();
	                }

				}else{
					JOptionPane.showMessageDialog(null, Message, "Abort", JOptionPane.WARNING_MESSAGE);
				}
			
				
				
			}
		});
		
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu navigate = new Menu(Session);
				navigate.frmMenu.setVisible(true);
				frmDO.dispose(); 
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(isEditDelete()){
					boolean transaction=true;
					String Message="";
					
					if(transaction){
						int dialogResult = JOptionPane.showConfirmDialog (null, "Delete Data?","Confirmation",JOptionPane.WARNING_MESSAGE);
						if(dialogResult == JOptionPane.YES_OPTION){
							ArrayList<String> strSqls = new ArrayList<String>();
							String strSql="";

							strSql = "Delete From ba_stock Where TransactionNO = '" + tfDONO.getText() + "' ";	
							strSqls.add(strSql);
							strSql = "Delete From tr_do_dt Where DONO = '" + tfDONO.getText() + "' ";	
							strSqls.add(strSql);
							strSql = "Delete From tr_do_hd Where DONO = '" + tfDONO.getText() + "' ";	
							strSqls.add(strSql);
							executeSQLs(strSqls);
							
							Mode = "Blank";
							btnAdd.setEnabled(true);
							btnEdit.setEnabled(false);
							btnSave.setEnabled(false);
							btnDelete.setEnabled(false);
							btnSONO.setEnabled(true);
							tfDONO.setEditable(true);
							tfCustomerID.setEditable(false);
							taAddress.setEditable(false);
							tfSONO.setEditable(false);
							tfDate.setEditable(false);
							tfDONO.setText("");
							tfCustomerID.setText("");
							taAddress.setText("");
							tfSONO.setText("");
							tfDate.setText("");
							tfGrandTotal.setText("");
							ClearTable(tblDetail);
							tblDetail.setEnabled(false);
							btnSONO.setEnabled(false);
	
						}
					}else{
						JOptionPane.showMessageDialog(null, Message, "Abort", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		btnDONO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Browser navigate = new Browser("Select a.DONO,DATE_FORMAT(a.date, '%d-%b-%Y') as 'DO Date',a.SONO,c.Name "
						                     + "From tr_do_hd a "
						                     + "Inner join tr_so_hd b On b.SONO = a.SONO "
						                     + "Inner join ms_partner c On c.PartnerID = b.CustomerID "
						                     + "Order By a.DONO DESC",tfDONO);
				navigate.frmBrowser.setVisible(true);
			}
		});
		
		tfDONO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((Mode.equals("Blank") || Mode.equals("View")) && Core.getRecordNumber("Select 1 From tr_do_hd a Where a.DONO = '"+ tfDONO.getText() +"'")==1){
					Mode = "View";
					btnAdd.setEnabled(true);
					btnEdit.setEnabled(false);
					btnSave.setEnabled(false);
					btnDelete.setEnabled(true);
					btnDONO.setEnabled(true);
					btnSONO.setEnabled(false);
					tfDONO.setEditable(false);
					tfSONO.setEditable(false);
					taAddress.setEditable(false);
					tfDate.setEditable(false);
					tfCustomerID.setEditable(false);
					
					ArrayList<String> DataHD = Core.getRecordRow("Select a.DONO,DATE_FORMAT(a.date, '%d-%b-%Y'),a.SONO,b.CustomerID,a.Address From tr_do_hd a "
							                                   + "Inner Join tr_so_hd b On b.SONO = a.SONO "
                             								   + "Where a.DONO = '"+ tfDONO.getText() +"'");
					tfDONO.setText(DataHD.get(0));
					tfDate.setText(DataHD.get(1));
					tfSONO.setText(DataHD.get(2));
					tfCustomerID.setText(DataHD.get(3));
					taAddress.setText(DataHD.get(4));
					  
					String strSql = "Select a.ItemID,b.Name,d.RetailPrice,'---','---',a.Qty,(d.RetailPrice*a.Qty) as Price "
							                       + "From tr_do_dt a "
							                       + "Inner Join ms_item b On b.ItemID=a.ItemID "
							                       + "Inner Join tr_do_hd c On c.DONO=a.DONO "
							                       + "Inner Join tr_so_dt d On d.SONO=c.SONO And d.ItemID=a.ItemID "
							                       + "Where a.DONO = '"+ tfDONO.getText() +"' Order By a.ItemID Asc";

				    // It creates and displays the table
					FillTable(tblDetail, strSql);
				    tblDetail.getColumnModel().getColumn(0).setCellRenderer(readonly);
				    tblDetail.getColumnModel().getColumn(1).setCellRenderer(readonly);
				    tblDetail.getColumnModel().getColumn(2).setCellRenderer(readonly);
				    tblDetail.getColumnModel().getColumn(3).setCellRenderer(readonly);
				    tblDetail.getColumnModel().getColumn(4).setCellRenderer(readonly);
				    tblDetail.getColumnModel().getColumn(5).setCellRenderer(readonly);
				    tblDetail.getColumnModel().getColumn(6).setCellRenderer(readonly);
				    tblDetail.setEnabled(false);

			        TableModel model = tblDetail.getModel();
				    int totalPrice=0;
                	for(int i=0;i<model.getRowCount();i++){
                		totalPrice+=Integer.parseInt(model.getValueAt(i, 6).toString());
                	}
                	
                	DecimalFormat formatter = new DecimalFormat("#,###.00");
                	tfGrandTotal.setText(formatter.format(totalPrice)+"");
				}
			}
		});
		
		tblDetail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				setTableModelListener();
			}
		});
	}
	
	private void setTableModelListener() {
        TableModel model = tblDetail.getModel();
		TableModelListener tableModelListener = new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {

                if (e.getType() == TableModelEvent.UPDATE) {
                    
                    int row = e.getFirstRow();
                    int quantity = Integer.parseInt(model.getValueAt(row, 5).toString());
                    int price = Integer.parseInt(model.getValueAt(row, 2).toString());
                    int value = quantity * price;
                    
                    if(Integer.parseInt(model.getValueAt(row, 6).toString())!=value) {
                    	model.setValueAt(value, row, 6);
                    }
                    int totalPrice=0;
                	for(int i=0;i<model.getRowCount();i++){
                		totalPrice+=Integer.parseInt(model.getValueAt(i, 6).toString());
                	}
                	
                	DecimalFormat formatter = new DecimalFormat("#,###");
                	tfGrandTotal.setText(formatter.format(totalPrice)+"");
                }
               
            }
        };
        model.addTableModelListener(tableModelListener);
    }
	
	public boolean isEditDelete(){
		boolean result=true;
		String Message="";
		
		if(!result)JOptionPane.showMessageDialog(null, Message, "Abort", JOptionPane.WARNING_MESSAGE);
		return result;
	}
}
