package signupform;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class signup extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup frame = new signup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Connection con = null;
	public signup() {
		
		
		con = (Connection)DB.dbconnect();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SignUp Form");
		lblNewLabel.setBounds(10, 67, 286, 25);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(67, 102, 119, 44);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Last Name");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(67, 141, 119, 44);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Password");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(67, 216, 119, 44);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("User ID");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(67, 179, 119, 44);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Email ID");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3_1.setBounds(67, 260, 119, 44);
		contentPane.add(lblNewLabel_1_3_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(167, 117, 96, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(167, 156, 96, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(167, 195, 96, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(167, 231, 96, 19);
		contentPane.add(passwordField);
		
		textField_4 = new JTextField();
		textField_4.setBounds(167, 270, 96, 19);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("SignUp");
		btnNewButton.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					String first = textField_1.getText();
					String last = textField_2.getText();
					String user = textField_3.getText();
					String password = passwordField.getText();
					String email = textField_4.getText();	
					
					PreparedStatement pst = (PreparedStatement) con.prepareStatement("insert into signup(firstname,lastname,userid,password,email) values(?,?,?,?,?)");
					pst.setString(1, first);
					pst.setString(2, last);
					pst.setString(3, user);
					pst.setString(4, password);
					pst.setString(5, email);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "data added");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					passwordField.setText("");
					textField_4.setText("");
					
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(95, 314, 119, 36);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("LogIn");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login l = new login();
				l.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(95, 407, 119, 36);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("Already a user ?");
		lblNewLabel_1_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_3_1_1.setBounds(10, 365, 286, 44);
		contentPane.add(lblNewLabel_1_3_1_1);
	}
}

























