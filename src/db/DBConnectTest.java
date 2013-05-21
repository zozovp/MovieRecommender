package db;

/*
 * 
 * This class serves as a test for the db connection.
 * 
 */

import java.sql.*;

public class DBConnectTest {
	
	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	
	public static void main(String[] args) {
	
		try {
			String host = "jdbc:mysql://localhost:3306/movie_recommender";
			String username = "root";
			String password = "passw0rd";
		
			conn = DriverManager.getConnection(host, username, password);
	
			String SQL = "SELECT title, genres FROM movies";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			
			while (rs.next()) {
				String userid = rs.getString(1);
				String movieid = rs.getString(2);
				
				System.out.println(userid + ": " + movieid);
				
			}
		}
		catch (SQLException err) {
			System.out.println(err.getMessage());
		}
		finally {
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		}
	}
}
