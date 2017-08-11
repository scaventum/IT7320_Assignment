package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.Core;

import javax.swing.JSeparator;

public class Stock extends Core{

	JFrame frmStock;
	JTextField tfItemID;
	JTextField tfSupplierID;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Create the application.
	 */
	public Stock(String[] Session) {
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
		
		frmStock = new JFrame();
		frmStock.setTitle("Stock");
		frmStock.setResizable(false);
		frmStock.setBounds(100, 100, 550, 450);
		frmStock.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStock.getContentPane().setLayout(null);
		
		tfItemID = new JTextField();
		tfItemID.setColumns(10);
		tfItemID.setBounds(95, 58, 135, 20);
		frmStock.getContentPane().add(tfItemID);
		
		JLabel lblItemId = new JLabel("Item ID");
		lblItemId.setBounds(10, 62, 75, 14);
		frmStock.getContentPane().add(lblItemId);
		
		JButton btnItemID = new JButton("...");
		btnItemID.setBounds(240, 58, 23, 23);
		frmStock.getContentPane().add(btnItemID);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 524, 322);
		frmStock.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setEnabled(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {"Item ID", "Item Name", "Qty"}
		) 
			{
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] {false, false, false, false, true, false};
				public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		table.getColumnModel().getColumn(0).setCellRenderer(readonly);
		table.getColumnModel().getColumn(1).setCellRenderer(readonly);
		table.getColumnModel().getColumn(2).setCellRenderer(readonly);
		showData();
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(10, 11, 80, 23);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu navigate = new Menu(Session);
				navigate.frmMenu.setVisible(true);
				frmStock.dispose(); 
			}
		});
		frmStock.getContentPane().add(btnCancel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 45, 524, 2);
		frmStock.getContentPane().add(separator);
		
		btnItemID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Browser navigate = new Browser("Select a.ItemID,a.Name From ms_item a",tfItemID);
				navigate.frmBrowser.setVisible(true);
			}
		});
		
		tfItemID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showData();
			}
		});
	}
	
	public void showData(){
		String strCondition="";
		if(!tfItemID.getText().equals("")) {
			strCondition=" And a.ItemID = '" + tfItemID.getText() + "' ";
		}
		String strSql = "Select a.ItemID,b.Name,Sum(a.Qty) as Qty From ba_stock a "
			      + "Inner Join ms_item b On b.ItemID = a.ItemID "
			      + "Where 1=1 "
			      + " " + strCondition + " "
			      + "Group By a.ItemID,b.Name "
				  + "Order By a.ItemID Asc";

	  // It creates and displays the table
		FillTable(table, strSql);
		table.setEnabled(false);
	}
}
