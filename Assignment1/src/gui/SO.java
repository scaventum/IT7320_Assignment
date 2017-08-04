package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class SO {

	JFrame frmSO;
	private JTextField tfSONO;
	private JTextField tfDate;
	private JTextField tfCustomerID;
	private JTable tblDetail;
	private JTextField tfGrandTotal;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SO window = new SO();
					window.frmSO.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

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
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu navigate = new Menu(Session);
				navigate.frmMenu.setVisible(true);
				frmSO.dispose(); 
			}
		});
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
		tfDate.setEnabled(false);
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
		tfCustomerID.setEnabled(false);
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
		scrollPane.setViewportView(tblDetail);
		tblDetail.setEnabled(false);
		tblDetail.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item ID", "Item Name", "Retail Price", "Qty", "Price"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Double.class, Double.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tblDetail.getColumnModel().getColumn(0).setResizable(false);
		tblDetail.getColumnModel().getColumn(2).setResizable(false);
		tblDetail.getColumnModel().getColumn(4).setResizable(false);
		tblDetail.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		JLabel lblTotal = new JLabel("Grand Total");
		lblTotal.setBounds(70, 369, 286, 14);
		frmSO.getContentPane().add(lblTotal);
		
		tfGrandTotal = new JTextField();
		tfGrandTotal.setColumns(10);
		tfGrandTotal.setBounds(366, 366, 168, 20);
		frmSO.getContentPane().add(tfGrandTotal);
		
		JButton btnDetail = new JButton("+");
		btnDetail.setEnabled(false);
		btnDetail.setBounds(10, 365, 50, 23);
		frmSO.getContentPane().add(btnDetail);
		
	}
}
