package signupform;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

//import todolist.ResultSetMetaData;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class todo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					todo frame = new todo();
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
	
	Connection con=null;
	public todo() {
		
		con = (Connection) DB.dbconnect();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 509, 609);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("My Contacts List");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblNewLabel.setBounds(20, 10, 446, 51);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(41, 67, 70, 34);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(121, 71, 257, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Phone:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(41, 103, 70, 34);
		contentPane.add(lblNewLabel_1_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textArea.setBounds(121, 108, 257, 26);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String imp = textField.getText();
					String other = textArea.getText();
					
					PreparedStatement pst = (PreparedStatement)con.prepareStatement("insert into todo(important,other) values(?,?)");
					
					pst.setString(1,imp);
					pst.setString(2,other);
					 
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Contact added");
					textField.setText("");
					textArea.setText("");
					
					int a;
					PreparedStatement pst1 = (PreparedStatement)con.prepareStatement("select * from todo");
					ResultSet rs = pst1.executeQuery();
					ResultSetMetaData rd = (ResultSetMetaData) rs.getMetaData();
					a=rd.getColumnCount();
					DefaultTableModel df = (DefaultTableModel)table.getModel();
					df.setRowCount(0);
					
					while(rs.next())
					{
						Vector v2 = new Vector();
						for(int i=1;i<a;i++)
						{
							v2.add(rs.getString("id"));
							v2.add(rs.getString("important"));
							v2.add(rs.getString("other"));
						}
						df.addRow(v2); 
					}

				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
				 
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(41, 147, 122, 34);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("EDIT");
		btnNewButton_1.addActionListener(new ActionListener() {
			private PreparedStatement pst;

			public void actionPerformed(ActionEvent e) {
				DefaultTableModel df = (DefaultTableModel)table.getModel();
				
				int s = table.getSelectedRow();
				int id = Integer.parseInt(df.getValueAt(s,0).toString());
						
				try
				{
					String imp = textField.getText();
					String other = textArea.getText();
					
					pst = con.prepareStatement("update todo set important=?,other=? where id=?");
					
					pst.setString(1,imp);
					pst.setString(2,other);
					pst.setInt(3,id);
					 
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Contact Updated");
					textField.setText("");
					textArea.setText("");
					
					int a;
					pst = (PreparedStatement)con.prepareStatement("select * from todo");
					ResultSet rs = pst.executeQuery();
					ResultSetMetaData rd = (ResultSetMetaData) rs.getMetaData();
					a=rd.getColumnCount();
					DefaultTableModel df1 = (DefaultTableModel)table.getModel();
					df1.setRowCount(0);
					
					while(rs.next())
					{
						Vector v2 = new Vector();
						for(int i=1;i<a;i++)
						{
							v2.add(rs.getString("id"));
							v2.add(rs.getString("important"));
							v2.add(rs.getString("other"));
						}
						df.addRow(v2); 
					}
					
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(183, 147, 122, 34);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("DONE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.setBounds(183, 526, 122, 34);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("DELETE");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel df = (DefaultTableModel)table.getModel();
				
				int s = table.getSelectedRow();
				int id = Integer.parseInt(df.getValueAt(s,0).toString());
						
				try
				{
					PreparedStatement pst = con.prepareStatement("DELETE FROM todo WHERE id=?");
					pst.setInt(1,id);
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Contact deleted");
					textField.setText("");
					textArea.setText("");
					
					int a;
					pst = (PreparedStatement)con.prepareStatement("select * from todo");
					ResultSet rs = pst.executeQuery();
					ResultSetMetaData rd = (ResultSetMetaData) rs.getMetaData();
					a=rd.getColumnCount();
					DefaultTableModel df1 = (DefaultTableModel)table.getModel();
					df1.setRowCount(0);
					
					while(rs.next())
					{
						Vector v2 = new Vector();
						for(int i=1;i<a;i++)
						{
							v2.add(rs.getString("id"));
							v2.add(rs.getString("important"));
							v2.add(rs.getString("other"));
						}
						df.addRow(v2); 
					}
					
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2_1.setBounds(333, 147, 122, 34);
		contentPane.add(btnNewButton_2_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 191, 475, 325);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel df = (DefaultTableModel)table.getModel();
				int selected = table.getSelectedRow();
				int id = Integer.parseInt(df.getValueAt(selected, 0).toString());
				textField.setText(df.getValueAt(selected, 1).toString());
				textArea.setText(df.getValueAt(selected, 2).toString());
				btnNewButton.setEnabled(false);
			}
		});
		
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Phone"
			}
		));
		
		JPanel panel = new JPanel();
		scrollPane.setColumnHeaderView(panel);
		panel.setLayout(null);
	}
}
