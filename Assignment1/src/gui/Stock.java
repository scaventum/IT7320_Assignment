package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Core;

import javax.swing.JSeparator;

public class Stock extends Core{

	JFrame frmStock;
	JTextField tfItemID;
	JTextField tdSupplierID;
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
		
		tdSupplierID = new JTextField();
		tdSupplierID.setColumns(10);
		tdSupplierID.setBounds(366, 58, 135, 20);
		frmStock.getContentPane().add(tdSupplierID);
		
		JButton btnSupplierID = new JButton("...");
		btnSupplierID.setBounds(511, 58, 23, 23);
		frmStock.getContentPane().add(btnSupplierID);
		
		JLabel lblSupplierId = new JLabel("Supplier ID");
		lblSupplierId.setBounds(281, 62, 75, 14);
		frmStock.getContentPane().add(lblSupplierId);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 524, 322);
		frmStock.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setEnabled(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {"Supplier ID","Supplier Name","Item ID", "Item Name", "Qty"}
		) 
			{
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] {false, false, false, false, true, false};
				public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		JButton button = new JButton("Delete");
		button.setEnabled(false);
		button.setBounds(330, 11, 80, 23);
		frmStock.getContentPane().add(button);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(240, 11, 80, 23);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu navigate = new Menu(Session);
				navigate.frmMenu.setVisible(true);
				frmStock.dispose(); 
			}
		});
		frmStock.getContentPane().add(btnCancel);
		
		JButton button_2 = new JButton("Save");
		button_2.setEnabled(false);
		button_2.setBounds(150, 11, 80, 23);
		frmStock.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("Edit");
		button_3.setEnabled(false);
		button_3.setBounds(80, 11, 60, 23);
		frmStock.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("Add");
		button_4.setEnabled(false);
		button_4.setBounds(10, 11, 60, 23);
		frmStock.getContentPane().add(button_4);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 45, 524, 2);
		frmStock.getContentPane().add(separator);
	}
}
