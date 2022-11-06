package signupform;
import javax.swing.*;
//import com.mysql.jdbc.ResultSetMetaData;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB 
{
	Connection con = null;
	java.sql.PreparedStatement pst;
	
	public static Connection dbconnect()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:4306/todolist","root","");
			return conn;
		}
		catch(Exception e2)
		{
			System.out.println(e2);
			return null;
		}
	}

}
