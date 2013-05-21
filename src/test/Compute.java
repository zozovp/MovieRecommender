package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import models.Similarity;

import db.DBQuery;

import algo.CosSimilarity;
import algo.PearsonCorrelationSimilarity;

public class Compute {

	public static void main (String[] args) {
		
		DBQuery dbQuery = new DBQuery();
		/*
		 * get all movie id for calculation preparation
		 */
		
		List<Integer> movieList = dbQuery.getAllMovieId();
		List<Integer> movieList2 = movieList;

		Iterator<Integer> movieItr = movieList.iterator();
		
		while(movieItr.hasNext()){
			
			Date date = new Date();
			System.out.println(date.toString());
			int movieId1 = movieItr.next();
			Iterator<Integer> movieItr2 = movieList2.iterator();
			
			/*
			 * create table to put the matrix result
			 */
			dbQuery.createTable(movieId1);
			
			while(movieItr2.hasNext()){
				
				int movieId2 = movieItr2.next();
				
				double similarity = getPearsonSimilarityMatrix(movieId1,movieId2);
			
				if (Double.isNaN(similarity)){
					similarity = 0;
				}
				
				dbQuery.insertRecord(movieId1, movieId2, similarity);
				
				System.out.print(movieId1+" - ");
				System.out.print(movieId2+" - ");
				System.out.println(similarity);
			}
		}
	}
	
	private static double getPearsonSimilarityMatrix(int movieId1, int movieId2){
		
		List<Integer> m1int = new ArrayList<Integer>();
		List<Integer> m2int = new ArrayList<Integer>();
		DBQuery dbQuery = new DBQuery();
		/*
		 * get all values from database
		 */
		List<Similarity> matrixList = dbQuery.getMatrix(movieId1, movieId2);
		
		Iterator<Similarity> matrixListItr = matrixList.iterator();
		while(matrixListItr.hasNext()){
			Similarity similarity = matrixListItr.next();
			
			/*
			 * check is the value 0 or not
			 */
			if(similarity.getRating1()==0){
				m1int.add(null);
			}
			else{
				m1int.add(similarity.getRating1());
			}
			
			/*
			 * check is the value 0 or not
			 */
			if(similarity.getRating2()==0){
				m2int.add(null);
			}
			else{
				m2int.add(similarity.getRating2());
			}
		}
		
		
		/*
		 * call cosine similarity method
		 */
		double similarity = PearsonCorrelationSimilarity.sim(m1int, m2int);
		
		return similarity;
		
	}
	
	
}
