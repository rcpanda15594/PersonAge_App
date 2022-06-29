package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class AgeOfPerson {
private static final String AGE_QUERY="SELECT (SYSDATE-DOB)/365 FROM PERSON_DATE WHERE PNO=?";
	public static void main(String[] args) {
		Scanner scn=null;
		int pno=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			scn=new Scanner(System.in);
			if(scn!=null)
				System.out.println("Enter Person No:");
			   pno=scn.nextInt();
	//register jdbc driver
			   Class.forName("oracle.jdbc.OracleDriver");
	//establish connection
			   con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
   //create preparedstatement	
			   if(con!=null)
				   ps=con.prepareStatement(AGE_QUERY);
 //set param values
			   if(ps!=null)
				   ps.setInt(1,pno);
//execute query
			   if(ps!=null)
				   rs=ps.executeQuery();
			   
//print resutlset
			   if(rs!=null)
				   if(rs.next())
					   System.out.println("Age:"+rs.getInt(1));
				   else
					   System.out.println("Record not found");
		}//try
		catch(Exception e) {
			e.printStackTrace();
		}//catch
		finally {
			
			//close jdbc objects

		    try {
				
				if(rs!=null)
					rs.close();
				
			}catch(Exception se) {
				se.printStackTrace();
			}//catch	
				

		    try {
				
				if(ps!=null)
					ps.close();
				
			}catch(Exception se) {
				se.printStackTrace();
			}//catch
			    try {
					
					if(con!=null)
						con.close();
					
				}catch(Exception se) {
					se.printStackTrace();
				}//catch
				
				
			     try {
					
					if(scn!=null)
						scn.close();
					
				}catch(Exception se) {
					se.printStackTrace();
				}//catch
			     
			}//finally
	}//method

}//class
