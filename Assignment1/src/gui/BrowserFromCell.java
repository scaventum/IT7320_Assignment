package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.Core;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;

public class BrowserFromCell extends Core{

	JFrame frmBrowser;
	private JTable tblBrowser;
	private JTextField tfFilter;
	int columnsNumber=0;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public BrowserFromCell(String strSql,JTable tbl,int x,int y) {
		initialize(strSql,tbl,x,y);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String strSql,JTable tbl,int x,int y) {
		MySQLConnect();
		
		frmBrowser = new JFrame();
		frmBrowser.setTitle("Browser");
		frmBrowser.setBounds(100, 100, 450, 330);
		frmBrowser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBrowser.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 414, 206);
		frmBrowser.getContentPane().add(scrollPane);
		
		tblBrowser = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
				return false;               
	        };
		};
		tblBrowser.isEditing();
		scrollPane.setViewportView(tblBrowser);
		tblBrowser.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(335, 259, 89, 23);
		frmBrowser.getContentPane().add(btnCancel);
		
		JLabel lblBrowser = new JLabel("Fill the box with selected row in");
		lblBrowser.setBounds(10, 263, 196, 14);
		frmBrowser.getContentPane().add(lblBrowser);
		
		JLabel lblHighlighted = new JLabel("highlighted");
		lblHighlighted.setForeground(Color.CYAN);
		lblHighlighted.setBounds(190, 264, 69, 14);
		frmBrowser.getContentPane().add(lblHighlighted);
		
		JLabel lblColumn = new JLabel("column");
		lblColumn.setBounds(255, 263, 62, 14);
		frmBrowser.getContentPane().add(lblColumn);
		
		tfFilter = new JTextField();
		tfFilter.setBounds(10, 11, 414, 20);
		frmBrowser.getContentPane().add(tfFilter);
		tfFilter.setColumns(10);
		
		try{
			@SuppressWarnings("static-access")
			Statement stmt=super.con.createStatement();  
			ResultSet rs=stmt.executeQuery(strSql);  
			ResultSetMetaData rsmd = rs.getMetaData();
			columnsNumber = rsmd.getColumnCount();
			String[] ColumnName = new String[columnsNumber];
			@SuppressWarnings("rawtypes")
			Class[] ColumnType = new Class[columnsNumber];
			
			for(int i=0;i<columnsNumber;i++){
				ColumnName[i]=rsmd.getColumnName(i+1);
			}for(int i=0;i<columnsNumber;i++){
				ColumnType[i]=String.class;
			}
			
			if(columnsNumber>0){
				tblBrowser.setModel(new DefaultTableModel(
					new Object[][] {
					},
					ColumnName
				) {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					@SuppressWarnings({ "rawtypes" })
					Class[] columnTypes = ColumnType;
					@SuppressWarnings({ "rawtypes", "unchecked" })
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				
			    DefaultTableModel model = (DefaultTableModel) tblBrowser.getModel();
			    while(rs.next())  {
			    	String[] ColumnValue = new String[columnsNumber];
			    	for(int i=0;i<columnsNumber;i++){
			    		ColumnValue[i]=rs.getString(i+1);
					}
			    	Object[] row= ColumnValue;
			    	model.addRow(row);
			    }
			    DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
			    cellRenderer.setBackground(new Color(224, 255, 255));
			    tblBrowser.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
			    DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
			    headerRenderer.setBackground(new Color(192, 255, 255));
			    tblBrowser.getColumnModel().getColumn(0).setHeaderRenderer(headerRenderer);

				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tblBrowser.getModel());
				tblBrowser.setRowSorter(sorter);
				
			};
		    
		}catch(Exception e){ System.out.println(e);} 
		
		
		 
		tblBrowser.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent evnt) {
		        if (evnt.getClickCount() == 2) {
					//System.out.println(y);

				    DefaultTableModel model = (DefaultTableModel) tbl.getModel();
		        	String returnValue=tblBrowser.getModel().getValueAt(tblBrowser.convertRowIndexToModel(tblBrowser.getSelectedRow()), 0).toString();
		        	
		        	model.setValueAt(returnValue, y, x);
		        	boolean success = tbl.editCellAt(y, x);
		            if (success) {
			              boolean toggle = false;
			              boolean extend = false;
			              tbl.changeSelection(y, x, toggle, extend);
		            }
		        	frmBrowser.dispose();
		        }
		    }
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmBrowser.dispose(); 
			}
		});

		tfFilter.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tblBrowser.getModel());
				tblBrowser.setRowSorter(sorter);
				
				RowFilter<? super TableModel, ? super Integer> rf = null;
				//If current expression doesn't parse, don't update.
				try {
					rf = RowFilter.regexFilter(tfFilter.getText(),0);
				} catch (java.util.regex.PatternSyntaxException e) {
					return;
				}
				sorter.setRowFilter(rf);
			}
			
			public void keyReleased(KeyEvent event) {
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tblBrowser.getModel());
				tblBrowser.setRowSorter(sorter);
					
		    	RowFilter<? super TableModel, ? super Integer> rf = null;
		    	 //If current expression doesn't parse, don't update.
		    	try {
		    		rf = RowFilter.regexFilter(tfFilter.getText(),0);
		    	} catch (java.util.regex.PatternSyntaxException e) {
		    		return;
		    	}
		    	sorter.setRowFilter(rf);
			}
		});
	}
	
	
}
