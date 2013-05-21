package test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import db.DBQuery;
import algo.ComputeMovieSimilarityBasedOnGenre;
import models.Movie;

public class GenreSimilarity {

	public static void main (String[] args) {
		
		ComputeMovieSimilarityBasedOnGenre computeMovieSimilarityBasedOnGenre = new ComputeMovieSimilarityBasedOnGenre();
		
		DBQuery q = new db.DBQuery();
		List<Movie> movieList = q.getAllMovies();
		List<Movie> movieList2 = movieList;
		
		Iterator<Movie> movieListItr = movieList.iterator();
		while(movieListItr.hasNext()){
			
			Movie movie = (Movie)movieListItr.next();
			
			String genre1 = movie.getGenre();
			
			System.out.println("==============================="+movie.getTitle());
			Date date = new Date();
			System.out.println(date.toString());
			
			Iterator<Movie> movieListItr2 = movieList2.iterator();
			while(movieListItr2.hasNext()){
				
				String genre2 = ((Movie)movieListItr2.next()).getGenre();
				//System.out.println(genre2);
				
				System.out.println(computeMovieSimilarityBasedOnGenre.genreSimilarity(genre1, genre2));
			}
		}
		
		Date date = new Date();
		System.out.println(date.toString());
	}
}
