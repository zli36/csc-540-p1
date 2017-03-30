/*** 3 Entity Tables: Course, Employee, Student;
 *** 1 Weak Entity Table: Arrangement
 *** 2 Relationships: Belong,Enroll
*/

import java.sql.*;
import java.util.*;


public class createTables{
	static final String DB_URL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String USER = "hli36";
    static final String PASS = "200110588"; 
	
	public static void main(String[] args){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
        ResultSet fs = null;
        Set<String> set = new HashSet<String>();
        Set<String> fset = new HashSet<String>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			//delete existing tables
			rs = stmt.executeQuery("SELECT table_name FROM all_tables WHERE owner='HLI36'");
			System.out.println("Existed table names:");
				while(rs.next()) {
					String tableName = rs.getString("table_name");
					System.out.println(tableName);
					set.add(tableName);
				}


			//drop table admin
			if(set.contains("ADMIN")) {
					stmt.executeUpdate("DROP TABLE ADMIN");
					System.out.println("DROP TABLE ADMIN");
				}
				
			//drop table enroll(offer)
			if(set.contains("ENROLL")) {
					stmt.executeUpdate("DROP TABLE ENROLL");
					System.out.println("DROP TABLE ENROLL");
				}

			//drop table student
			if(set.contains("STUDENT")) {
					stmt.executeUpdate("DROP TABLE STUDENT");
					System.out.println("DROP TABLE STUDENT");
				}

		
			//dropt table arrangement
			if(set.contains("ARRANGEMENT")) {
					stmt.executeUpdate("DROP TABLE ARRANGEMENT");
					System.out.println("DROP TABLE ARRANGEMENT");
				}




			//drop table course
			if(set.contains("COURSE")) {
					stmt.executeUpdate("DROP TABLE COURSE");
					System.out.println("DROP TABLE COURSE");
				}


			//create table for entity Course
			String createCourseSql = "CREATE TABLE COURSE" + 
								   "(CID INTEGER NOT NULL,"+
								   " DEPT VARCHAR(10) NOT NULL," +
								   " TITLE VARCHAR(64) NOT NULL," +
								   " LEV VARCHAR(14) NOT NULL," +
								   " GPAREQ NUMERIC(3,2) NOT NULL," +
								   " PREREQUISITE VARCHAR(15) NOT NULL," +
								   " CREDIT INTEGER NOT NULL," +
								   " CONSTRAINT C_CourseID_Should_be_Unique PRIMARY KEY (CID)) ";
			stmt.executeUpdate(createCourseSql);
			System.out.println("Created table COURSE in database");
			
			//create tables for admin
    		String createAdminSql = "CREATE TABLE ADMIN" + 
								   "(SSN INTEGER NOT NULL," +
								   " EID INTEGER NOT　NULL," +
								   " LASTNAME VARCHAR(15) NOT NULL," +
								   " FIRSTNAME VARCHAR(15) NOT NULL," +
								   " PASSWORD VARCHAR(20) NOT NULL," +
								   " DOB VARCHAR(20) NOT　NULL," +
								   " USERNAME VARCHAR(20) NOT　NULL," +
								   " PRIMARY KEY (EID)) ";
    	
		    stmt.executeUpdate(createAdminSql);
			System.out.println("Created table ADMIN in database");
			
			//create table for entity student
			String createStudentSql = "CREATE TABLE STUDENT" + 
								   "(SID INTEGER NOT NULL," +
								   " FIRSTNAME VARCHAR(15) NOT NULL," +
								   " LASTNAME VARCHAR(15) NOT NULL," +
								   " DOB VARCHAR(20) NOT　NULL," +
								   " DEPT VARCHAR(20) NOT　NULL," +
								   " GPA NUMERIC(4,3)," +
								   " BILL VARCHAR(30)," +
								   " PASSWORD VARCHAR(20) NOT NULL," +
								   " EMAIL VARCHAR(20) NOT　NULL," +
								   " LEV VARCHAR(14) NOT　NULL," +
								   " RESIDENCE VARCHAR(30) NOT　NULL," +
								   " COURSE_LIST VARCHAR(200)," +
								   " RATE VARCHAR(15) NOT NULL," +
								   " MIN_CREDIT INTEGER NOT　NULL," +
								   " MAX_CREDIT INTEGER NOT　NULL," +
								   " NOW_CREDIT INTEGER NOT　NULL," +
								   " PHONE_NUM INTEGER NOT　NULL," +
								   " PRIMARY KEY (SID)) ";


			stmt.executeUpdate(createStudentSql);
			System.out.println("Created table STUDENT in database");


			String createArrangementSql = "CREATE TABLE ARRANGEMENT" + 
								   "(A_DAY VARCHAR(15) NOT NULL," +
								   " A_HOUR VARCHAR(15) NOT NULL," +
								   " SEMESTER VARCHAR(10) NOT NULL," +
								   " A_YEAR VARCHAR(4) NOT　NULL," +
								   " A_LOCATION VARCHAR(15)," +
								   " SECTION_ID VARCHAR(3) NOT NULL," +
								   " DEADLINE VARCHAR(15) NOT NULL," +
								   " ENROLL_SUM INTEGER NOT　NULL," +
								   " WAITING_SUM INTEGER NOT　NULL," +
								   " ENROLL_NOW INTEGER NOT　NULL," +
								   " WAITING_NOW INTEGER NOT NULL," +
								   " FACULTY VARCHAR(20) NOT　NULL," +
								   " CID INTEGER NOT NULL," +
								   //" PRIMARY KEY (CID))";
								   " PRIMARY KEY (CID,SEMESTER,A_YEAR,SECTION_ID)," +
								   " FOREIGN KEY (CID) REFERENCES COURSE ON DELETE CASCADE)";
								   
			stmt.executeUpdate(createArrangementSql);
			System.out.println("Created table ARRANGEMENT in database");

			// String createEnrollSql

			String createEnrollSql = "CREATE TABLE ENROLL" +
						" (SEMESTER VARCHAR(10) NOT NULL," +
						" A_YEAR VARCHAR(4) NOT NULL, " +
						" CID INTEGER NOT NULL," +
						" SECTION_ID VARCHAR(4) NOT NULL," +
						" PERMIT INTEGER NOT NULL," +
						" STATUS VARCHAR(10) NOT NULL," +
						" WL_NUM INTEGER," +
						" SID INTEGER NOT NULL," +
						" CREDIT INTEGER NOT NULL," +
						" GRADE VARCHAR(3) ," +
						" PRIMARY KEY (SEMESTER, A_YEAR, CID, SECTION_ID, SID)," +
						" FOREIGN KEY (CID,SEMESTER,A_YEAR,SECTION_ID) REFERENCES ARRANGEMENT ON DELETE CASCADE," +
						" FOREIGN KEY (SID) REFERENCES STUDENT ON DELETE CASCADE) ";
			

			stmt.executeUpdate(createEnrollSql);
			System.out.println("Created table ENROLL in database");

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