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
import javax.swing.JTextArea;

public class DO {

	JFrame frmDO;
	private JTextField tfDONO;
	private JTextField tfDate;
	private JTextField tfSONO;
	private JTable tblDetail;
	private JTextField tfGrandTotal;
	private JTextField tfCustomerID;

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
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu navigate = new Menu(Session);
				navigate.frmMenu.setVisible(true);
				frmDO.dispose(); 
			}
		});
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
		tfDate.setEnabled(false);
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
		tfSONO.setEnabled(false);
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
		lblTotal.setBounds(10, 401, 346, 14);
		frmDO.getContentPane().add(lblTotal);
		
		tfGrandTotal = new JTextField();
		tfGrandTotal.setColumns(10);
		tfGrandTotal.setBounds(366, 398, 168, 20);
		frmDO.getContentPane().add(tfGrandTotal);
		
		tfCustomerID = new JTextField();
		tfCustomerID.setEnabled(false);
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
		taAddress.setEnabled(false);
		taAddress.setEditable(false);
		taAddress.setBounds(366, 86, 168, 51);
		frmDO.getContentPane().add(taAddress);
		
	}
}
