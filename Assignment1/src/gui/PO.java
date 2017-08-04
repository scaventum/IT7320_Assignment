package gui;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

public class PO extends Core {

	JFrame frmPO;
	JTextField tfPONO;
	private JTextField tfDate;
	JTextField tfSupplierID;
	static JTable tblDetail;
	private JTextField tfGrandTotal;
	String Mode="Blank";

	/**
	 * Create the application.
	 */
	public PO(String[] Session) {
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
		
		frmPO = new JFrame();
		frmPO.setResizable(false);
		frmPO.setTitle("Purchase Order");
		frmPO.setBounds(100, 100, 550, 425);
		frmPO.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPO.getContentPane().setLayout(null);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(10, 11, 60, 23);
		frmPO.getContentPane().add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(80, 11, 60, 23);
		btnEdit.setEnabled(false);
		frmPO.getContentPane().add(btnEdit);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(150, 11, 80, 23);
		btnSave.setEnabled(false);
		frmPO.getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(240, 11, 80, 23);
		frmPO.getContentPane().add(btnCancel);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(330, 11, 80, 23);
		btnDelete.setEnabled(false);
		frmPO.getContentPane().add(btnDelete);
		
		JLabel lbl1 = new JLabel("PO No.");
		lbl1.setBounds(10, 61, 75, 14);
		frmPO.getContentPane().add(lbl1);
		
		tfPONO = new JTextField();
		tfPONO.setColumns(10);
		tfPONO.setBounds(95, 58, 135, 20);
		frmPO.getContentPane().add(tfPONO);
		
		tfDate = new JTextField();
		tfDate.setColumns(10);
		tfDate.setBounds(95, 86, 168, 20);
		tfDate.setEditable(false);
		frmPO.getContentPane().add(tfDate);
		
		JLabel lbl2 = new JLabel("PO Date");
		lbl2.setBounds(10, 89, 75, 14);
		frmPO.getContentPane().add(lbl2);
		
		JButton btnPONO = new JButton("...");
		btnPONO.setBounds(240, 57, 23, 23);
		frmPO.getContentPane().add(btnPONO);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 45, 524, 2);
		frmPO.getContentPane().add(separator);
		
		tfSupplierID = new JTextField();
		tfSupplierID.setEditable(false);
		tfSupplierID.setColumns(10);
		tfSupplierID.setBounds(366, 58, 135, 20);
		frmPO.getContentPane().add(tfSupplierID);
		
		JButton btnSupplierID = new JButton("...");
		btnSupplierID.setEnabled(false);
		btnSupplierID.setBounds(511, 57, 23, 23);
		frmPO.getContentPane().add(btnSupplierID);
		
		JLabel lblSupplierId = new JLabel("Supplier ID");
		lblSupplierId.setBounds(281, 61, 75, 14);
		frmPO.getContentPane().add(lblSupplierId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 117, 524, 241);
		frmPO.getContentPane().add(scrollPane);
		
		tblDetail = new JTable();
		tblDetail.setToolTipText("");
		tblDetail.setEnabled(false);
		scrollPane.setViewportView(tblDetail);
		tblDetail.setModel(new DefaultTableModel(new Object[][] {},new String[] {"Item ID", "Item Name", "Retail Price", "Qty", "Price"}) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {true, false, true, true, false};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblDetail.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));

		JLabel lblTotal = new JLabel("Grand Total");
		lblTotal.setBounds(70, 369, 286, 14);
		frmPO.getContentPane().add(lblTotal);
		
		tfGrandTotal = new JTextField();
		tfGrandTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		tfGrandTotal.setEditable(false);
		tfGrandTotal.setColumns(10);
		tfGrandTotal.setBounds(366, 366, 168, 20);
		frmPO.getContentPane().add(tfGrandTotal);
		
		JButton btnDetail = new JButton("+");
		btnDetail.setEnabled(false);
		btnDetail.setBounds(10, 365, 50, 23);
		frmPO.getContentPane().add(btnDetail);
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String timeStamp = new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime());
				Mode = "Add";
				btnAdd.setEnabled(false);
				btnEdit.setEnabled(false);
				btnSave.setEnabled(true);
				btnDelete.setEnabled(false);
				btnPONO.setEnabled(false);
				btnDetail.setEnabled(true);
				btnSupplierID.setEnabled(true);
				tfPONO.setEditable(false);
				tfDate.setEditable(false);
				tfSupplierID.setEditable(true);
				tblDetail.setEnabled(true);
				ClearTable(tblDetail);
				tfPONO.setText("");
				tfDate.setText(timeStamp);
				tfSupplierID.setText("");
			    tblDetail.getColumnModel().getColumn(0).setCellRenderer(editable);
			    tblDetail.getColumnModel().getColumn(1).setCellRenderer(readonly);
			    tblDetail.getColumnModel().getColumn(2).setCellRenderer(editable);
			    tblDetail.getColumnModel().getColumn(3).setCellRenderer(editable);
			    tblDetail.getColumnModel().getColumn(4).setCellRenderer(readonly);
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			//@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				boolean transaction=true;
				String Message="";

		        TableModel model = tblDetail.getModel();
				ArrayList<String> ItemList = new ArrayList<String>();
				boolean uniqueItem=true;
				boolean validQty=true;
				boolean validItem=true;
            	for(int i=0;i<model.getRowCount();i++){
            		if(Integer.parseInt(model.getValueAt(i, 3).toString())<=0){
            			validQty=false;
            		}
            		if(Core.getRecordNumber("Select 1 From ms_item a Where a.ItemID = '"+ model.getValueAt(i, 0).toString() +"'")!=1){
            			validItem=false;
    				}
            		if(!ItemList.contains(model.getValueAt(i, 0).toString())){
            			ItemList.add(model.getValueAt(i, 0).toString());
            			uniqueItem = true;
            		}else{
            			uniqueItem = false;
            		}
            	}
            	
            	if(!validQty){
            		transaction=false;
					Message="Invalid Qty exists";
				}
            	if(!validItem){
            		transaction=false;
					Message="Invalid Item ID exists";
				}
            	if(!uniqueItem){
            		transaction=false;
					Message="Duplicate Item ID exists";
				}
            	if(model.getRowCount()<1){
            		transaction=false;
					Message="Item detail is empty";
            	}
				if(Core.getRecordNumber("Select 1 From ms_partner a Where a.PartnerID = '"+ tfSupplierID.getText() +"'")!=1){
					transaction=false;
					Message="Supplier ID is invalid";
				}
				
				
                	
				if(transaction){
					if(Mode=="Add"){
						String POFormat = "PO/"+new SimpleDateFormat("yyyy/MM/").format(Calendar.getInstance().getTime());
						int POLastInt = 1;
						if(Core.getRecordNumber("Select Right(a.PONO,3) ai "
	                            			   +"From tr_po_hd a "
	                                           +"Where a.PONO LIKE '" + POFormat + "%' "
	                                           +"Order By a.PONO Desc Limit 0,1")==1){
							
							ArrayList<String> POLast = Core.getRecordRow("Select Right(a.PONO,3) ai "
						                                                +"From tr_po_hd a "
						                                                +"Where a.PONO LIKE '" + POFormat + "%' "
						                                                +"Order By a.PONO Desc");
							POLastInt = Integer.parseInt(POLast.get(0))+1;
						}
						String POLastStr = String.format("%03d", POLastInt);
						tfPONO.setText(POFormat+POLastStr);
					}
					
					String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
					ArrayList<String> strSqls = new ArrayList<String>();
					String strSql="";

					strSql = "Delete From tr_po_dt Where PONO = '" + tfPONO.getText() + "' ";	
					strSqls.add(strSql);
					strSql = "Delete From tr_po_hd Where PONO = '" + tfPONO.getText() + "' ";	
					strSqls.add(strSql);
					
					strSql = "Insert into tr_po_hd (PONO,Date,SupplierID,UpdateTime,UpdateUserID) Values ( "
							   + "'" + tfPONO.getText() + "', "
							   + "'" + timeStamp + "', "
							   + "'" + tfSupplierID.getText() + "' , "
							   + "'" + timeStamp + "', "
							   + "'" + SessionInfo.get(0) + "' "
							   + ")";	
					strSqls.add(strSql);
					
                	for(int i=0;i<model.getRowCount();i++){
                		strSql = "Insert into tr_po_dt (PONO,ItemID,RetailPrice,Qty) Values ( "
								   + "'" + tfPONO.getText() + "', "
								   + "'" + model.getValueAt(i, 0).toString() + "', "
								   + "" + Integer.parseInt(model.getValueAt(i, 2).toString()) + ",  "
								   + "" + Integer.parseInt(model.getValueAt(i, 3).toString()) + "  "
								   + ")";	
						strSqls.add(strSql);
                	}
					
					executeSQLs(strSqls);
				      
					Mode = "Blank";
					tfPONO.requestFocus();
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
				frmPO.dispose(); 
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isEditDelete()){
					String timeStamp = new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime());
					Mode = "Edit";
					btnAdd.setEnabled(false);
					btnEdit.setEnabled(false);
					btnSave.setEnabled(true);
					btnDelete.setEnabled(false);
					btnPONO.setEnabled(false);
					btnSupplierID.setEnabled(true);
					btnDetail.setEnabled(true);
					tfSupplierID.setEditable(true);
					tblDetail.setEnabled(true);
					tfDate.setText(timeStamp);
				    tblDetail.getColumnModel().getColumn(0).setCellRenderer(editable);
				    tblDetail.getColumnModel().getColumn(1).setCellRenderer(readonly);
				    tblDetail.getColumnModel().getColumn(2).setCellRenderer(editable);
				    tblDetail.getColumnModel().getColumn(3).setCellRenderer(editable);
				    tblDetail.getColumnModel().getColumn(4).setCellRenderer(readonly);
				}
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

							strSql = "Delete From tr_po_dt Where PONO = '" + tfPONO.getText() + "' ";	
							strSqls.add(strSql);
							strSql = "Delete From tr_po_hd Where PONO = '" + tfPONO.getText() + "' ";	
							strSqls.add(strSql);
							executeSQLs(strSqls);
							
							Mode = "Blank";
							btnAdd.setEnabled(true);
							btnEdit.setEnabled(false);
							btnSave.setEnabled(false);
							btnDelete.setEnabled(false);
							btnPONO.setEnabled(true);
							tfPONO.setEditable(true);
							tfSupplierID.setEditable(false);
							tfDate.setEditable(false);
							tfPONO.setText("");
							tfSupplierID.setText("");
							tfDate.setText("");
							tfGrandTotal.setText("");
							ClearTable(tblDetail);
							tblDetail.setEnabled(false);
							btnSupplierID.setEnabled(false);
							btnDetail.setEnabled(false);
	
						}
					}else{
						JOptionPane.showMessageDialog(null, Message, "Abort", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		btnPONO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Browser navigate = new Browser("Select a.PONO,DATE_FORMAT(a.date, '%d-%b-%Y') as 'PO Date',b.Name "
						                     + "From tr_po_hd a "
						                     + "Inner join ms_partner b On b.PartnerID = a.SupplierID "
						                     + "Order By a.PONO DESC",tfPONO);
				navigate.frmBrowser.setVisible(true);
			}
		});
		
		tfPONO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((Mode.equals("Blank") || Mode.equals("View")) && Core.getRecordNumber("Select 1 From tr_po_hd a Where a.PONO = '"+ tfPONO.getText() +"'")==1){
					Mode = "View";
					btnAdd.setEnabled(true);
					btnEdit.setEnabled(true);
					btnSave.setEnabled(false);
					btnDelete.setEnabled(true);
					btnPONO.setEnabled(true);
					btnSupplierID.setEnabled(false);
					btnDetail.setEnabled(false);
					tfPONO.setEditable(false);
					tfDate.setEditable(false);
					tfSupplierID.setEditable(false);
					
					ArrayList<String> DataHD = Core.getRecordRow("Select PONO,DATE_FORMAT(a.date, '%d-%b-%Y'),SupplierID From tr_po_hd a "
                             								   + "Where a.PONO = '"+ tfPONO.getText() +"'");
					tfPONO.setText(DataHD.get(0));
					tfDate.setText(DataHD.get(1));
					tfSupplierID.setText(DataHD.get(2));
					  
					String strSql = "Select a.ItemID,b.Name,a.RetailPrice,a.Qty,(a.RetailPrice*a.Qty) as Price "
							                       + "From tr_po_dt a "
							                       + "Inner Join ms_item b On b.ItemID=a.ItemID "
							                       + "Where a.PONO = '"+ tfPONO.getText() +"' Order By a.ItemID Asc";

				    // It creates and displays the table
					FillTable(tblDetail, strSql);
				    tblDetail.getColumnModel().getColumn(0).setCellRenderer(readonly);
				    tblDetail.getColumnModel().getColumn(1).setCellRenderer(readonly);
				    tblDetail.getColumnModel().getColumn(2).setCellRenderer(readonly);
				    tblDetail.getColumnModel().getColumn(3).setCellRenderer(readonly);
				    tblDetail.getColumnModel().getColumn(4).setCellRenderer(readonly);
				    tblDetail.setEnabled(false);

			        TableModel model = tblDetail.getModel();
				    int totalPrice=0;
                	for(int i=0;i<model.getRowCount();i++){
                		totalPrice+=Integer.parseInt(model.getValueAt(i, 4).toString());
                	}
                	
                	DecimalFormat formatter = new DecimalFormat("#,###.00");
                	tfGrandTotal.setText(formatter.format(totalPrice)+"");
				}
			}
		});
		
		btnSupplierID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Browser navigate = new Browser("Select PartnerID,Name,Address From ms_partner Order By Name ASC",tfSupplierID);
				navigate.frmBrowser.setVisible(true);
			}
		});
		
		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tblDetail.getModel();
				model.addRow(new Object[]{"", "", 0, 0, 0});
			}
		});
		

		tblDetail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int x=tblDetail.convertRowIndexToModel(tblDetail.getSelectedColumn());
				int y=tblDetail.convertRowIndexToModel(tblDetail.getSelectedRow());
				if (e.getKeyCode()==KeyEvent.VK_DELETE){
					removeSelectedRows();
		        }
				if (e.getKeyCode()==KeyEvent.VK_CONTROL){
					if(x==0){
						
						BrowserFromCell navigate = new BrowserFromCell("Select ItemID,Name,BuyPrice From ms_item Order By Name ASC",tblDetail,x, y);
						navigate.frmBrowser.setVisible(true);
					}
				    
				}
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
                    int column = e.getColumn();
                	//System.out.println(column);
                    if (column == 0) {
                    	
                        if(Core.getRecordNumber("Select 1 From ms_item a Where a.ItemID = '"+ model.getValueAt(row, 0) +"'")==1){
                        	ArrayList<String> DataHD = Core.getRecordRow("Select Name,BuyPrice From ms_item a Where a.ItemID = '"+ model.getValueAt(row, 0) +"'");
                        	model.setValueAt(DataHD.get(0), row, 1);
    		    			model.setValueAt(Integer.parseInt(DataHD.get(1)), row, 2);
    		    			int quantity = Integer.parseInt(model.getValueAt(row, 3).toString());
	                        int price = Integer.parseInt(model.getValueAt(row, 2).toString());
	                        int value = quantity * price;
	                        model.setValueAt(value, row, 4);
                        }else{
                        	//model.setValueAt("", row, 0);
                        	model.setValueAt("", row, 1);
    		    			model.setValueAt(0, row, 2);
    		    			model.setValueAt(0, row, 3);
    		    			model.setValueAt(0, row, 4);
                        }
                    	
                    } else if (column == 2 || column == 3) {
                        if(Core.getRecordNumber("Select 1 From ms_item a Where a.ItemID = '"+ model.getValueAt(row, 0) +"'")==1){
	                        int quantity = Integer.parseInt(model.getValueAt(row, 3).toString());
	                        int price = Integer.parseInt(model.getValueAt(row, 2).toString());
	                        int value = quantity * price;
	                        model.setValueAt(value, row, 4);
                        }
                    }
                    int totalPrice=0;
                	for(int i=0;i<model.getRowCount();i++){
                		totalPrice+=Integer.parseInt(model.getValueAt(i, 4).toString());
                	}
                	
                	DecimalFormat formatter = new DecimalFormat("#,###");
                	tfGrandTotal.setText(formatter.format(totalPrice)+"");
                }
               
            }
        };
        model.addTableModelListener(tableModelListener);
    }
	
	public void removeSelectedRows(){
		Boolean IsDelete=false;
		if(tblDetail.getModel().getValueAt(tblDetail.getSelectedRow(), 0).toString().isEmpty()){
			IsDelete=true;
		}else{
			int dialogResult = JOptionPane.showConfirmDialog (null, "Delete Row?","Confirmation",JOptionPane.WARNING_MESSAGE);
			if(dialogResult == JOptionPane.YES_OPTION){
				IsDelete=true;
			}else{
				IsDelete=false;
			}
		}
		
		
		if(IsDelete){
			DefaultTableModel model = (DefaultTableModel) tblDetail.getModel();
			int[] rows = tblDetail.getSelectedRows();
			for(int i=0;i<rows.length;i++){
				model.removeRow(rows[i]-i);
			}
			tblDetail.clearSelection();
		}
		
	}
	
	public boolean isEditDelete(){
		boolean result=true;
		String Message="";
		if(Core.getRecordNumber("Select 1 From tr_gr_hd a Where a.PONO = '"+ tfPONO.getText() +"'")>0){
			result=false;
			Message="PO No. "+ tfPONO.getText() +" already has GR";
		}
		if(!result)JOptionPane.showMessageDialog(null, Message, "Abort", JOptionPane.WARNING_MESSAGE);
		return result;
	}
	
}
