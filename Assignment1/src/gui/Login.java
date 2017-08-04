package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;

import model.Core;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends Core {

	JFrame frmLogin;
	private JTextField tfUserID;
	private JPasswordField pfPassword;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}
	
	@SuppressWarnings("deprecation")
	public void validateLogin() {
		int login;
		login=Core.getRecordNumber("Select 1 From ms_user a "
				     	   + "Where a.UserID = '"+ tfUserID.getText() +"'"
				           + "  And a.Password = '"+ pfPassword.getText() +"'");
		if(login==1){
			String[] Session=new String[] {
				tfUserID.getText(), pfPassword.getText()
			};
			Menu navigate = new Menu(Session);
			navigate.frmMenu.setVisible(true);
			frmLogin.dispose(); 
		}else{
			JOptionPane.showMessageDialog(null, "Invalid User ID or Password", "Abort", JOptionPane.WARNING_MESSAGE);
			pfPassword.setText("");
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		super.MySQLConnect();
		
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setResizable(false);
		frmLogin.setBounds(100, 100, 450, 130);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		tfUserID = new JTextField();
		tfUserID.setBounds(134, 10, 300, 20);
		frmLogin.getContentPane().add(tfUserID);
		tfUserID.setColumns(10);
		
		JLabel lblUserID = new JLabel("User ID");
		lblUserID.setBounds(10, 13, 100, 14);
		frmLogin.getContentPane().add(lblUserID);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 43, 100, 14);
		frmLogin.getContentPane().add(lblPassword);

		pfPassword = new JPasswordField();
		pfPassword.setBounds(134, 41, 300, 20);
		frmLogin.getContentPane().add(pfPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(10, 70, 424, 23);
		frmLogin.getContentPane().add(btnLogin);

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validateLogin();
			}
		});
		
		tfUserID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					validateLogin();
		        }
			}
		});
		
		pfPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					validateLogin();
		        }
			}
		});
		
	}
}
