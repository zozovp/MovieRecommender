package db;

import java.sql.*;

public class DBConnect {

	protected Connection conn;
	
	protected String dbName;
	protected String username;
	protected String password;
	
	public DBConnect() {
		dbName = "movie_recommender";
		username = "root";
		password = "passw0rd";
	}
	
	public ResultSet query(String SQL) {
		String host = "jdbc:mysql://localhost:3306/" + dbName;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(host, username, password);
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			
		}
		catch (SQLException err) {
			System.err.println(err.getMessage());
		}
		
		return rs;
	}
	
	public void update(String SQL){
		String host = "jdbc:mysql://localhost:3306/" + dbName;
		
		try {
			conn = DriverManager.getConnection(host, username, password);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(SQL);
			stmt.close();
			conn.close();
		}
		catch (SQLException err) {
			System.err.println(err.getMessage());
		}
	}
}
