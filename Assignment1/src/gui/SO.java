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
import javax.swing.SwingConstants;
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

public class SO extends Core{

	JFrame frmSO;
	JTextField tfSONO;
	private JTextField tfDate;
	JTextField tfCustomerID;
	JTable tblDetail;
	private JTextField tfGrandTotal;
	String Mode="Blank";


	/**
	 * Create the application.
	 */
	public SO(String[] Session) {
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
		
		frmSO = new JFrame();
		frmSO.setResizable(false);
		frmSO.setTitle("Sales Order");
		frmSO.setBounds(100, 100, 550, 425);
		frmSO.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSO.getContentPane().setLayout(null);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(10, 11, 60, 23);
		frmSO.getContentPane().add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(80, 11, 60, 23);
		btnEdit.setEnabled(false);
		frmSO.getContentPane().add(btnEdit);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(150, 11, 80, 23);
		btnSave.setEnabled(false);
		frmSO.getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(240, 11, 80, 23);
		frmSO.getContentPane().add(btnCancel);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(330, 11, 80, 23);
		btnDelete.setEnabled(false);
		frmSO.getContentPane().add(btnDelete);
		
		JLabel lbl1 = new JLabel("SO No.");
		lbl1.setBounds(10, 61, 75, 14);
		frmSO.getContentPane().add(lbl1);
		
		tfSONO = new JTextField();
		tfSONO.setColumns(10);
		tfSONO.setBounds(95, 58, 135, 20);
		frmSO.getContentPane().add(tfSONO);
		
		tfDate = new JTextField();
		tfDate.setColumns(10);
		tfDate.setBounds(95, 86, 168, 20);
		tfDate.setEditable(false);
		frmSO.getContentPane().add(tfDate);
		
		JLabel lbl2 = new JLabel("SO Date");
		lbl2.setBounds(10, 89, 75, 14);
		frmSO.getContentPane().add(lbl2);
		
		JButton btnSONO = new JButton("...");
		btnSONO.setBounds(240, 57, 23, 23);
		frmSO.getContentPane().add(btnSONO);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 45, 524, 2);
		frmSO.getContentPane().add(separator);
		
		tfCustomerID = new JTextField();
		tfCustomerID.setEditable(false);
		tfCustomerID.setColumns(10);
		tfCustomerID.setBounds(366, 58, 135, 20);
		frmSO.getContentPane().add(tfCustomerID);
		
		JButton btnCustomerID = new JButton("...");
		btnCustomerID.setEnabled(false);
		btnCustomerID.setBounds(511, 57, 23, 23);
		frmSO.getContentPane().add(btnCustomerID);
		
		JLabel lblSupplierId = new JLabel("Customer ID");
		lblSupplierId.setBounds(281, 61, 75, 14);
		frmSO.getContentPane().add(lblSupplierId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 117, 524, 241);
		frmSO.getContentPane().add(scrollPane);
		

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
		frmSO.getContentPane().add(lblTotal);
		
		tfGrandTotal = new JTextField();
		tfGrandTotal.setColumns(10);
		tfGrandTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		tfGrandTotal.setEditable(false);
		tfGrandTotal.setBounds(366, 366, 168, 20);
		frmSO.getContentPane().add(tfGrandTotal);
		
		JButton btnDetail = new JButton("+");
		btnDetail.setEnabled(false);
		btnDetail.setBounds(10, 365, 50, 23);
		frmSO.getContentPane().add(btnDetail);
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu navigate = new Menu(Session);
				navigate.frmMenu.setVisible(true);
				frmSO.dispose(); 
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String timeStamp = new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime());
				Mode = "Add";
				btnAdd.setEnabled(false);
				btnEdit.setEnabled(false);
				btnSave.setEnabled(true);
				btnDelete.setEnabled(false);
				btnSONO.setEnabled(false);
				btnDetail.setEnabled(true);
				btnCustomerID.setEnabled(true);
				tfSONO.setEditable(false);
				tfDate.setEditable(false);
				tfCustomerID.setEditable(true);
				tblDetail.setEnabled(true);
				ClearTable(tblDetail);
				tfSONO.setText("");
				tfDate.setText(timeStamp);
				tfCustomerID.setText("");
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
				if(Core.getRecordNumber("Select 1 From ms_partner a Where a.PartnerID = '"+ tfCustomerID.getText() +"'")!=1){
					transaction=false;
					Message="Supplier ID is invalid";
				}
				
				
                	
				if(transaction){
					if(Mode=="Add"){
						String SOFormat = "SO/"+new SimpleDateFormat("yyyy/MM/").format(Calendar.getInstance().getTime());
						int SOLastInt = 1;
						if(Core.getRecordNumber("Select Right(a.SONO,3) ai "
	                            			   +"From tr_so_hd a "
	                                           +"Where a.SONO LIKE '" + SOFormat + "%' "
	                                           +"Order By a.SONO Desc Limit 0,1")==1){
							
							ArrayList<String> SOLast = Core.getRecordRow("Select Right(a.SONO,3) ai "
						                                                +"From tr_so_hd a "
						                                                +"Where a.SONO LIKE '" + SOFormat + "%' "
						                                                +"Order By a.SONO Desc");
							SOLastInt = Integer.parseInt(SOLast.get(0))+1;
						}
						String SOLastStr = String.format("%03d", SOLastInt);
						tfSONO.setText(SOFormat+SOLastStr);
					}
					
					String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
					ArrayList<String> strSqls = new ArrayList<String>();
					String strSql="";

					strSql = "Delete From tr_so_dt Where SONO = '" + tfSONO.getText() + "' ";	
					strSqls.add(strSql);
					strSql = "Delete From tr_so_hd Where SONO = '" + tfSONO.getText() + "' ";	
					strSqls.add(strSql);
					
					strSql = "Insert into tr_so_hd (SONO,Date,CustomerID,UpdateTime,UpdateUserID) Values ( "
							   + "'" + tfSONO.getText() + "', "
							   + "'" + timeStamp + "', "
							   + "'" + tfCustomerID.getText() + "' , "
							   + "'" + timeStamp + "', "
							   + "'" + SessionInfo.get(0) + "' "
							   + ")";	
					strSqls.add(strSql);
					
                	for(int i=0;i<model.getRowCount();i++){
                		strSql = "Insert into tr_so_dt (SONO,ItemID,RetailPrice,Qty) Values ( "
								   + "'" + tfSONO.getText() + "', "
								   + "'" + model.getValueAt(i, 0).toString() + "', "
								   + "" + Integer.parseInt(model.getValueAt(i, 2).toString()) + ",  "
								   + "" + Integer.parseInt(model.getValueAt(i, 3).toString()) + "  "
								   + ")";	
						strSqls.add(strSql);
                	}
					
					executeSQLs(strSqls);
				      
					Mode = "Blank";
					tfSONO.requestFocus();
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
				frmSO.dispose(); 
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
					btnSONO.setEnabled(false);
					btnCustomerID.setEnabled(true);
					btnDetail.setEnabled(true);
					tfCustomerID.setEditable(true);
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

							strSql = "Delete From tr_so_dt Where SONO = '" + tfSONO.getText() + "' ";	
							strSqls.add(strSql);
							strSql = "Delete From tr_so_hd Where SONO = '" + tfSONO.getText() + "' ";	
							strSqls.add(strSql);
							executeSQLs(strSqls);
							
							Mode = "Blank";
							btnAdd.setEnabled(true);
							btnEdit.setEnabled(false);
							btnSave.setEnabled(false);
							btnDelete.setEnabled(false);
							btnSONO.setEnabled(true);
							tfSONO.setEditable(true);
							tfCustomerID.setEditable(false);
							tfDate.setEditable(false);
							tfSONO.setText("");
							tfCustomerID.setText("");
							tfDate.setText("");
							tfGrandTotal.setText("");
							ClearTable(tblDetail);
							tblDetail.setEnabled(false);
							btnCustomerID.setEnabled(false);
							btnDetail.setEnabled(false);
	
						}
					}else{
						JOptionPane.showMessageDialog(null, Message, "Abort", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		btnSONO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Browser navigate = new Browser("Select a.SONO,DATE_FORMAT(a.date, '%d-%b-%Y') as 'SO Date',b.Name "
						                     + "From tr_so_hd a "
						                     + "Inner join ms_partner b On b.PartnerID = a.CustomerID "
						                     + "Order By a.SONO DESC",tfSONO);
				navigate.frmBrowser.setVisible(true);
			}
		});
		
		tfSONO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((Mode.equals("Blank") || Mode.equals("View")) && Core.getRecordNumber("Select 1 From tr_so_hd a Where a.SONO = '"+ tfSONO.getText() +"'")==1){
					Mode = "View";
					btnAdd.setEnabled(true);
					btnEdit.setEnabled(true);
					btnSave.setEnabled(false);
					btnDelete.setEnabled(true);
					btnSONO.setEnabled(true);
					btnCustomerID.setEnabled(false);
					btnDetail.setEnabled(false);
					tfSONO.setEditable(false);
					tfDate.setEditable(false);
					tfCustomerID.setEditable(false);
					
					ArrayList<String> DataHD = Core.getRecordRow("Select SONO,DATE_FORMAT(a.date, '%d-%b-%Y'),CustomerID From tr_so_hd a "
                             								   + "Where a.SONO = '"+ tfSONO.getText() +"'");
					tfSONO.setText(DataHD.get(0));
					tfDate.setText(DataHD.get(1));
					tfCustomerID.setText(DataHD.get(2));
					  
					String strSql = "Select a.ItemID,b.Name,a.RetailPrice,a.Qty,(a.RetailPrice*a.Qty) as Price "
							                       + "From tr_so_dt a "
							                       + "Inner Join ms_item b On b.ItemID=a.ItemID "
							                       + "Where a.SONO = '"+ tfSONO.getText() +"' Order By a.ItemID Asc";

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
		
		btnCustomerID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Browser navigate = new Browser("Select PartnerID,Name,Address From ms_partner Order By Name ASC",tfCustomerID);
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
						
						BrowserFromCell navigate = new BrowserFromCell("Select ItemID,Name,SellPrice From ms_item Order By Name ASC",tblDetail,x, y);
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
                        	ArrayList<String> DataHD = Core.getRecordRow("Select Name,SellPrice From ms_item a Where a.ItemID = '"+ model.getValueAt(row, 0) +"'");
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
		if(Core.getRecordNumber("Select 1 From tr_do_hd a Where a.SONO = '"+ tfSONO.getText() +"'")>0){
			result=false;
			Message="PO No. "+ tfSONO.getText() +" already has GR";
		}
		if(!result)JOptionPane.showMessageDialog(null, Message, "Abort", JOptionPane.WARNING_MESSAGE);
		return result;
	}
}
