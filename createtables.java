/*** 3 Entity Tables: Course, Employee, Student;
 *** 1 Weak Entity Table: Arrangement
 *** 2 Relationships: Belong,Enroll
*/

import java.sql.*;
import java.util.*;


public class createtables{
	static final String DB_URL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String USER = "hli36";
    static final String PASS = "200110588"; 
	
	public static void main(String[] args){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			
			
			//create table for entity Course
			
			String createcoursesql = "CREATE TABLE COURSE" + 
								   "(CID INTEGER NOT NULL,"+
								   " DEPT VARCHAR(10) NOT NULL," +
								   " TITLE VARCHAR(64) NOT NULL," +
								   " LEV VARCHAR(13) NOT NULL," +
								   " GPAREQ NUMERIC(1,2) NOT NULL," +
								   " CREDIT INTEGER NOT NULL," +
								   " PRIMARY KEY (CID)) ";
			stmt.executeUpdate(createcoursesql);
			System.out.println("Created table Course in database");
			
    		
    	
		
		

		}catch(SQLException se){
			se.printStackTrace();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
      			try{
         			if(stmt!=null)
            		conn.close();
      				}catch(SQLException se){
      			}
      	try{
         	if(conn!=null)
            	conn.close();
      		}catch(SQLException se){
         							se.printStackTrace();
      								}
			}
		}
		
}
