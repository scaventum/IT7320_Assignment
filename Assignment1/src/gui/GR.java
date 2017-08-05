package gui;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Core;

public class GR extends Core{

	JFrame frmGR;
	JTextField tfGRNO;
	private JTextField tfDate;
	private JTextField tfPONO;
	private JTable tblDetail;
	private JTextField tfGrandTotal;
	private JTextField tfSupplierID;
	String Mode="Blank";

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GR window = new GR();
					window.frmGR.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public GR(String[] Session) {
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
		
		frmGR = new JFrame();
		frmGR.setResizable(false);
		frmGR.setTitle("Goods Receiving");
		frmGR.setBounds(100, 100, 550, 425);
		frmGR.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGR.getContentPane().setLayout(null);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(10, 11, 60, 23);
		frmGR.getContentPane().add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(80, 11, 60, 23);
		btnEdit.setEnabled(false);
		frmGR.getContentPane().add(btnEdit);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(150, 11, 80, 23);
		btnSave.setEnabled(false);
		frmGR.getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(240, 11, 80, 23);
		frmGR.getContentPane().add(btnCancel);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(330, 11, 80, 23);
		btnDelete.setEnabled(false);
		frmGR.getContentPane().add(btnDelete);
		
		JLabel lbl1 = new JLabel("GR No.");
		lbl1.setBounds(10, 61, 75, 14);
		frmGR.getContentPane().add(lbl1);
		
		tfGRNO = new JTextField();
		tfGRNO.setColumns(10);
		tfGRNO.setBounds(95, 58, 135, 20);
		frmGR.getContentPane().add(tfGRNO);
		
		tfDate = new JTextField();
		tfDate.setColumns(10);
		tfDate.setBounds(95, 86, 168, 20);
		tfDate.setEditable(false);
		frmGR.getContentPane().add(tfDate);
		
		JLabel lbl2 = new JLabel("GR Date");
		lbl2.setBounds(10, 89, 75, 14);
		frmGR.getContentPane().add(lbl2);
		
		JButton btnGRNO = new JButton("...");
		btnGRNO.setBounds(240, 57, 23, 23);
		frmGR.getContentPane().add(btnGRNO);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 45, 524, 2);
		frmGR.getContentPane().add(separator);
		
		tfPONO = new JTextField();
		tfPONO.setEditable(false);
		tfPONO.setColumns(10);
		tfPONO.setBounds(366, 58, 135, 20);
		frmGR.getContentPane().add(tfPONO);
		
		JButton btnPONO = new JButton("...");
		btnPONO.setEnabled(false);
		btnPONO.setBounds(511, 57, 23, 23);
		frmGR.getContentPane().add(btnPONO);
		
		JLabel lblSupplierId = new JLabel("PO No.");
		lblSupplierId.setBounds(281, 61, 75, 14);
		frmGR.getContentPane().add(lblSupplierId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 117, 524, 241);
		frmGR.getContentPane().add(scrollPane);
		
		tblDetail = new JTable();
		tblDetail.setToolTipText("");
		tblDetail.setEnabled(false);
		scrollPane.setViewportView(tblDetail);
		tblDetail.setModel(new DefaultTableModel(new Object[][] {},new String[] {"Item ID", "Item Name", "Retail Price","Qty Remain", "Qty", "Price"}) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {false, false, false, false, true, false};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblDetail.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		JLabel lblTotal = new JLabel("Grand Total");
		lblTotal.setBounds(10, 369, 346, 14);
		frmGR.getContentPane().add(lblTotal);
		
		tfGrandTotal = new JTextField();
		tfGrandTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		tfGrandTotal.setEditable(false);
		tfGrandTotal.setColumns(10);
		tfGrandTotal.setBounds(366, 366, 168, 20);
		frmGR.getContentPane().add(tfGrandTotal);
		
		tfSupplierID = new JTextField();
		tfSupplierID.setEditable(false);
		tfSupplierID.setColumns(10);
		tfSupplierID.setBounds(366, 86, 168, 20);
		frmGR.getContentPane().add(tfSupplierID);
		
		JLabel lblSupplierId_1 = new JLabel("Supplier ID");
		lblSupplierId_1.setBounds(281, 89, 75, 14);
		frmGR.getContentPane().add(lblSupplierId_1);
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String timeStamp = new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime());
				Mode = "Add";
				btnAdd.setEnabled(false);
				btnEdit.setEnabled(false);
				btnSave.setEnabled(true);
				btnDelete.setEnabled(false);
				btnGRNO.setEnabled(false);
				btnPONO.setEnabled(true);
				tfGRNO.setEditable(false);
				tfPONO.setEditable(false);
				tfDate.setEditable(false);
				tfSupplierID.setEditable(false);
				tblDetail.setEnabled(true);
				ClearTable(tblDetail);
				tfGRNO.setText("");
				tfPONO.setText("");
				tfDate.setText(timeStamp);
				tfSupplierID.setText("");
			    tblDetail.getColumnModel().getColumn(0).setCellRenderer(readonly);
			    tblDetail.getColumnModel().getColumn(1).setCellRenderer(readonly);
			    tblDetail.getColumnModel().getColumn(2).setCellRenderer(readonly);
			    tblDetail.getColumnModel().getColumn(3).setCellRenderer(readonly);
			    tblDetail.getColumnModel().getColumn(4).setCellRenderer(editable);
			    tblDetail.getColumnModel().getColumn(5).setCellRenderer(readonly);
			}
		});
		
		btnPONO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Browser navigate = new Browser("Select a.PONO,DATE_FORMAT(a.date, '%d-%b-%Y') as 'PO Date',b.Name "
						                     + "From tr_po_hd a "
						                     + "Inner join ms_partner b On b.PartnerID = a.SupplierID "
						                     + "Where (Select sum(Qty) From tr_po_dt i Where i.PONO = a.PONO) > "
						                     + "      ifnull((Select sum(k.Qty) From tr_gr_hd j "
						                     + "       Inner Join tr_gr_dt k On k.GRNO = j.GRNO"
						                     + "       Where j.PONO = a.PONO),0) "
						                     + "Order By a.PONO DESC",tfPONO);
				
				navigate.frmBrowser.setVisible(true);
			}
		});
		
		tfPONO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				ArrayList<String> DataHD = Core.getRecordRow("Select PONO,SupplierID From tr_po_hd a "
                         								   + "Where a.PONO = '"+ tfPONO.getText() +"'");
				tfPONO.setText(DataHD.get(0));
				tfSupplierID.setText(DataHD.get(1));
				  
				String strSql = "Select a.ItemID,b.Name,a.RetailPrice,"
						      + "a.Qty-ifnull((Select sum(k.Qty) From tr_gr_hd j "
		                      + "              Inner Join tr_gr_dt k On k.GRNO = j.GRNO  "
		                      + "              Where j.PONO = a.PONO And k.ItemID = a.ItemID),0),"
						      + "0,(a.RetailPrice*a.Qty) as Price "
	                          + "From tr_po_dt a "
	                          + "Inner Join ms_item b On b.ItemID=a.ItemID "
	                          + "Where a.PONO = '"+ tfPONO.getText() +"' Order By a.ItemID Asc";

			    // It creates and displays the table
				FillTable(tblDetail, strSql);

		        TableModel model = tblDetail.getModel();
			    int totalPrice=0;
            	for(int i=0;i<model.getRowCount();i++){
            		totalPrice+=Integer.parseInt(model.getValueAt(i, 5).toString());
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
            		if(Integer.parseInt(model.getValueAt(i, 4).toString()) > Integer.parseInt(model.getValueAt(i, 3).toString())){
            			validQty=false;
            		}
            	}
            	
            	if(totalQty<=0 || !validQty){
            		transaction=false;
					Message="Invalid Qty";
				}
                	
				if(transaction){
					if(Mode=="Add"){
						String GRFormat = "GR/"+new SimpleDateFormat("yyyy/MM/").format(Calendar.getInstance().getTime());
						int GRLastInt = 1;
						if(Core.getRecordNumber("Select Right(a.GRNO,3) ai "
	                            			   +"From tr_gr_hd a "
	                                           +"Where a.GRNO LIKE '" + GRFormat + "%' "
	                                           +"Order By a.GRNO Desc Limit 0,1")==1){
							
							ArrayList<String> GRLast = Core.getRecordRow("Select Right(a.GRNO,3) ai "
						                                                +"From tr_gr_hd a "
						                                                +"Where a.GRNO LIKE '" + GRFormat + "%' "
						                                                +"Order By a.GRNO Desc");
							GRLastInt = Integer.parseInt(GRLast.get(0))+1;
						}
						String GRLastStr = String.format("%03d", GRLastInt);
						tfGRNO.setText(GRFormat+GRLastStr);
					}
					
					String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
					ArrayList<String> strSqls = new ArrayList<String>();
					String strSql="";

					strSql = "Delete From ba_stock Where TransactionNO = '" + tfGRNO.getText() + "' ";	
					strSqls.add(strSql);
					strSql = "Delete From tr_gr_dt Where GRNO = '" + tfGRNO.getText() + "' ";	
					strSqls.add(strSql);
					strSql = "Delete From tr_gr_hd Where GRNO = '" + tfGRNO.getText() + "' ";	
					strSqls.add(strSql);
					
					strSql = "Insert into tr_gr_hd (GRNO,Date,PONO,UpdateTime,UpdateUserID) Values ( "
							   + "'" + tfGRNO.getText() + "', "
							   + "'" + timeStamp + "', "
							   + "'" + tfPONO.getText() + "' , "
							   + "'" + timeStamp + "', "
							   + "'" + SessionInfo.get(0) + "' "
							   + ")";	
					strSqls.add(strSql);
					
                	for(int i=0;i<model.getRowCount();i++){
                		strSql = "Insert into tr_gr_dt (GRNO,ItemID,Qty) Values ( "
								   + "'" + tfGRNO.getText() + "', "
								   + "'" + model.getValueAt(i, 0).toString() + "', "
								   + "" + Integer.parseInt(model.getValueAt(i, 4).toString()) + "  "
								   + ")";	
						strSqls.add(strSql);
						
						if(Integer.parseInt(model.getValueAt(i, 4).toString())>0){
							strSql = "Insert into ba_stock (ItemID,TransactionNO,Qty) Values ( "
									   + "'" + model.getValueAt(i, 0).toString() + "', "
									   + "'" + tfGRNO.getText() + "', "
									   + "" + Integer.parseInt(model.getValueAt(i, 4).toString()) + "  "
									   + ")";	
							strSqls.add(strSql);
						}
                	}
					
					executeSQLs(strSqls);
				      
					Mode = "Blank";
					tfGRNO.requestFocus();
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
		
//		btnEdit.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(isEditDelete()){
//					String timeStamp = new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime());
//					Mode = "Edit";
//					btnAdd.setEnabled(false);
//					btnEdit.setEnabled(false);
//					btnSave.setEnabled(true);
//					btnDelete.setEnabled(false);
//					btnPONO.setEnabled(false);
//					btnGRNO.setEnabled(false);
//					tfGRNO.setEnabled(true);
//					tfPONO.setEnabled(true);
//					tfSupplierID.setEditable(false);
//					tblDetail.setEnabled(true);
//					tfDate.setText(timeStamp);
//				    tblDetail.getColumnModel().getColumn(0).setCellRenderer(readonly);
//				    tblDetail.getColumnModel().getColumn(1).setCellRenderer(readonly);
//				    tblDetail.getColumnModel().getColumn(2).setCellRenderer(readonly);
//				    tblDetail.getColumnModel().getColumn(3).setCellRenderer(readonly);
//				    tblDetail.getColumnModel().getColumn(4).setCellRenderer(editable);
//				    tblDetail.getColumnModel().getColumn(5).setCellRenderer(readonly);
//				}
//			}
//		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu navigate = new Menu(Session);
				navigate.frmMenu.setVisible(true);
				frmGR.dispose(); 
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

							strSql = "Delete From ba_stock Where TransactionNO = '" + tfGRNO.getText() + "' ";	
							strSqls.add(strSql);
							strSql = "Delete From tr_gr_dt Where GRNO = '" + tfGRNO.getText() + "' ";	
							strSqls.add(strSql);
							strSql = "Delete From tr_gr_hd Where GRNO = '" + tfGRNO.getText() + "' ";	
							strSqls.add(strSql);
							executeSQLs(strSqls);
							
							Mode = "Blank";
							btnAdd.setEnabled(true);
							btnEdit.setEnabled(false);
							btnSave.setEnabled(false);
							btnDelete.setEnabled(false);
							btnPONO.setEnabled(true);
							tfGRNO.setEditable(true);
							tfSupplierID.setEditable(false);
							tfPONO.setEditable(false);
							tfDate.setEditable(false);
							tfGRNO.setText("");
							tfSupplierID.setText("");
							tfPONO.setText("");
							tfDate.setText("");
							tfGrandTotal.setText("");
							ClearTable(tblDetail);
							tblDetail.setEnabled(false);
							btnPONO.setEnabled(false);
	
						}
					}else{
						JOptionPane.showMessageDialog(null, Message, "Abort", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		btnGRNO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Browser navigate = new Browser("Select a.GRNO,DATE_FORMAT(a.date, '%d-%b-%Y') as 'GR Date',a.PONO,c.Name "
						                     + "From tr_gr_hd a "
						                     + "Inner join tr_po_hd b On b.PONO = a.PONO "
						                     + "Inner join ms_partner c On c.PartnerID = b.SupplierID "
						                     + "Order By a.GRNO DESC",tfGRNO);
				navigate.frmBrowser.setVisible(true);
			}
		});
		
		tfGRNO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((Mode.equals("Blank") || Mode.equals("View")) && Core.getRecordNumber("Select 1 From tr_gr_hd a Where a.GRNO = '"+ tfGRNO.getText() +"'")==1){
					Mode = "View";
					btnAdd.setEnabled(true);
					btnEdit.setEnabled(false);
					btnSave.setEnabled(false);
					btnDelete.setEnabled(true);
					btnGRNO.setEnabled(true);
					btnPONO.setEnabled(false);
					tfGRNO.setEditable(false);
					tfPONO.setEditable(false);
					tfDate.setEditable(false);
					tfSupplierID.setEditable(false);
					
					ArrayList<String> DataHD = Core.getRecordRow("Select a.GRNO,DATE_FORMAT(a.date, '%d-%b-%Y'),a.PONO,b.SupplierID From tr_gr_hd a "
							                                   + "Inner Join tr_po_hd b On b.PONO = a.PONO "
                             								   + "Where a.GRNO = '"+ tfGRNO.getText() +"'");
					tfGRNO.setText(DataHD.get(0));
					tfDate.setText(DataHD.get(1));
					tfPONO.setText(DataHD.get(2));
					tfSupplierID.setText(DataHD.get(3));
					  
					String strSql = "Select a.ItemID,b.Name,d.RetailPrice,'---',a.Qty,(d.RetailPrice*a.Qty) as Price "
							                       + "From tr_gr_dt a "
							                       + "Inner Join ms_item b On b.ItemID=a.ItemID "
							                       + "Inner Join tr_gr_hd c On c.GRNO=a.GRNO "
							                       + "Inner Join tr_po_dt d On d.PONO=c.PONO And d.ItemID=a.ItemID "
							                       + "Where a.GRNO = '"+ tfGRNO.getText() +"' Order By a.ItemID Asc";

				    // It creates and displays the table
					FillTable(tblDetail, strSql);
				    tblDetail.getColumnModel().getColumn(0).setCellRenderer(readonly);
				    tblDetail.getColumnModel().getColumn(1).setCellRenderer(readonly);
				    tblDetail.getColumnModel().getColumn(2).setCellRenderer(readonly);
				    tblDetail.getColumnModel().getColumn(3).setCellRenderer(readonly);
				    tblDetail.getColumnModel().getColumn(4).setCellRenderer(readonly);
				    tblDetail.getColumnModel().getColumn(5).setCellRenderer(readonly);
				    tblDetail.setEnabled(false);

			        TableModel model = tblDetail.getModel();
				    int totalPrice=0;
                	for(int i=0;i<model.getRowCount();i++){
                		totalPrice+=Integer.parseInt(model.getValueAt(i, 5).toString());
                	}
                	
                	DecimalFormat formatter = new DecimalFormat("#,###.00");
                	tfGrandTotal.setText(formatter.format(totalPrice)+"");
				}
			}
		});
		
	}
	
	public boolean isEditDelete(){
		boolean result=true;
		String Message="";
		TableModel model = tblDetail.getModel();
		for(int i=0;i<model.getRowCount();i++){
			if(Core.getStockTrans(tfGRNO.getText(),model.getValueAt(i, 0).toString())<0){
				result=false;
				Message="Invalid stock qty for Item ID "+ model.getValueAt(i, 0).toString() +" if GR is deleted";
			}
		}
		if(!result)JOptionPane.showMessageDialog(null, Message, "Abort", JOptionPane.WARNING_MESSAGE);
		return result;
	}
}
