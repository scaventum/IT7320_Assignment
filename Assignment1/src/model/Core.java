package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Core {
	protected static Connection con;
	
	public void MySQLConnect() {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_assignment1","root",""); 
			
		}catch(Exception e){JOptionPane.showMessageDialog(null, e, "Abort", JOptionPane.WARNING_MESSAGE);} 
	}
	
	public static int getRecordNumber(String query){
		int result=0;
		try{ 
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery(query);  
			while(rs.next())  {
				result++;
			}
		}catch(Exception e){JOptionPane.showMessageDialog(null, e, "Abort", JOptionPane.WARNING_MESSAGE);}  
	
		return result;
	}
	
	public static ArrayList<String> getRecordRow(String query){
		
		ArrayList<String> result = new ArrayList<String>();
		try{ 
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery(query);  
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while(rs.next())  {
				for(int i=0;i<columnsNumber;i++){
					result.add(rs.getString(i+1));
				}
				break;
			}
		}catch(Exception e){JOptionPane.showMessageDialog(null, e, "Abort", JOptionPane.WARNING_MESSAGE);}  
		
		return result;
	}
	
	public static ResultSet getRecordRows(String query){
		
		ResultSet rs = null;
		try{ 
			Statement stmt=con.createStatement();  
			rs=stmt.executeQuery(query);  
		}catch(Exception e){JOptionPane.showMessageDialog(null, e, "Abort", JOptionPane.WARNING_MESSAGE);}  
		
		return rs;
	}
	
	public void executeSQL(String query){
		try{ 
			Statement stmt=con.createStatement();  
			stmt.executeUpdate(query);  
		}catch(Exception e){JOptionPane.showMessageDialog(null, e, "Abort", JOptionPane.WARNING_MESSAGE);}  
	
	}
	
	public void executeSQLs(ArrayList<String> queries){
		try{ 
			con.setAutoCommit(false);
			Statement stmt=con.createStatement();
			for(int i=0;i<queries.size();i++){
				stmt.executeUpdate(queries.get(i));
			}
			con.commit();
		}catch(SQLException se){
			JOptionPane.showMessageDialog(null, se, "Abort", JOptionPane.WARNING_MESSAGE);
			try{
				if(con!=null)
					con.rollback();
			}catch(SQLException se2){
				JOptionPane.showMessageDialog(null, se2, "Abort", JOptionPane.WARNING_MESSAGE);
			}
	   }catch(Exception e){
		   {JOptionPane.showMessageDialog(null, e, "Abort", JOptionPane.WARNING_MESSAGE);} 
	   } 
	
	}
	
	public static int getStockTrans(String TransactionNO,String ItemID){
		
		int result = 0;
		try{ 
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("Select sum(qty) From ba_stock Where ItemID = '"+ ItemID +"' And TransactionNO != '" + TransactionNO + "' ");  
			while(rs.next())  {
				result+=rs.getInt(1);
			}
		}catch(Exception e){JOptionPane.showMessageDialog(null, e, "Abort", JOptionPane.WARNING_MESSAGE);}  
		
		return result;
	}
	
	public void FillTable(JTable table, String Query)
	{
	    try
	    {
	        Statement stat = con.createStatement();
	        ResultSet rs = stat.executeQuery(Query);

	        //To remove previously added rows
	        while(table.getRowCount() > 0) 
	        {
	            ((DefaultTableModel) table.getModel()).removeRow(0);
	        }
	        
	        int columns = rs.getMetaData().getColumnCount();
	        while(rs.next())
	        {  
	            Object[] row = new Object[columns];
	            for (int i = 1; i <= columns; i++)
	            {  
	                row[i - 1] = rs.getObject(i);
	            }
	            ((DefaultTableModel) table.getModel()).insertRow(rs.getRow()-1,row);
	        }

	        //rs.close();
	        //stat.close();
	        //con.close();
	    }
	    catch(SQLException e)
	    {JOptionPane.showMessageDialog(null, e, "Abort", JOptionPane.WARNING_MESSAGE);}
	}
	
	public void ClearTable(JTable table)
	{
        while(table.getRowCount() > 0) 
        {
            ((DefaultTableModel) table.getModel()).removeRow(0);
        }
	}
}
