package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.Movie;
import models.Similarity;

public class DBQuery extends DBConnect {
	
	protected int rating;
	protected String genre;

	public int getUserRatingOfMovie (int uID, int mID) {
		
		try {
			ResultSet rs = query("SELECT rating FROM ratings WHERE userid = " + uID + " And movieid = " + mID + ";");
			
			rs.next();
			rating = rs.getInt(1);
		}
		catch (SQLException err) {
			System.err.println(err.getMessage());
		}
		
		return rating;
	}
	
	public String getMovieGenre (int mID) {
			
		try {
			ResultSet rs = query("SELECT genres FROM movies WHERE movieid = " + mID + ";");
			
			rs.next();
			genre = rs.getString(1);		
			
		}
		catch (SQLException err) {
			System.err.println(err.getMessage());
		}
		
		return genre;
	}
	
	public List<Movie> getAllMovies () {
		
		List<Movie> movies = new ArrayList<Movie>();
		
		try {
			ResultSet rs = query("SELECT MovieID, Title, Genres FROM movies;");
			
			while(rs.next()){
				Movie movie = new Movie();
				movie.setMovieId(rs.getInt(1));
				movie.setTitle(rs.getString(2));
				movie.setGenre(rs.getString(3));
				movies.add(movie);
			}		
			
		}
		catch (SQLException err) {
			System.err.println(err.getMessage());
		}
		
		return movies;
	}
	
	
	public List<Integer> getAllMovieId(){
		
		List<Integer> movies = new ArrayList<Integer>();
		
		try {
			ResultSet rs = query("SELECT MovieID FROM movies;");
			
			
			while(rs.next()){
				movies.add(rs.getInt(1));
			}
					
			
		}
		catch (SQLException err) {
			System.err.println(err.getMessage());
		}
		
		return movies;
		
	}
	
	
	public List<Similarity> getMatrix(int movieId1, int movieId2){
		
		List<Similarity> result = new ArrayList<Similarity>();
		
		try {
//			String sql="SELECT  UserID, "+
//					 			 "MAX(IF(`MovieID` = "+movieId1+",Rating,NULL)) AS '"+movieId1+"', "+
//					 			 "MAX(IF(`MovieID` = "+movieId2+",Rating,NULL)) AS '"+movieId2+"' "+
//					 			 "FROM ratings "+
//								 "GROUP   BY UserID;";
		
			String sql="SELECT a.userID, a.rating, b.rating "+
					   "from ratings a "+
					   "INNER JOIN ratings b USING (userID) "+
					   "WHERE a.movieID="+ movieId1 +" AND b.movieID="+ movieId2 +";";
			
			//System.out.println(sql);
			
			ResultSet rs = query(sql);
			
			while(rs.next()){
				Similarity similarity = new Similarity();
				similarity.setUserId(rs.getInt(1));
				similarity.setRating1(rs.getInt(2));
				similarity.setRating2(rs.getInt(3));
				
				result.add(similarity);
			}
		}
		catch (SQLException err) {
			System.err.println(err.getMessage());
		}
		
		
		
		return result;
		
	}
	
	public void createTable(int movieId){
		update("DROP TABLE CORR_M_"+movieId+";");
		update("CREATE TABLE CORR_M_"+movieId+" ( "+
				"movie_id int, "+
				"`"+movieId+"` double, "+
				"PRIMARY KEY ( movie_id ));");
	}
	
	public void insertRecord(int movieId1, int movieId2, double similarity){
		String sql="INSERT INTO CORR_M_"+movieId1+" VALUES ( "+
				movieId2+", "+
				similarity+");";
		update(sql);
	}
	
	
}
