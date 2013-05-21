package algo;



public class ComputeMovieSimilarityBasedOnGenre {

	public double genreSimilarity(String genre1, String genre2){
		//db.DBQuery q = new db.DBQuery();
		
		//String query1 = q.getMovieGenre(1);
		//String query2 = q.getMovieGenre(3);
		
		String[] m1str = genre1.split("\\|");
		String[] m2str = genre2.split("\\|");
		
		int[] m1int = new int[18];
		for (int i=0; i<m1str.length; i++) {
			int index = intGenre(m1str[i]);
			m1int[index] = 1;
		}
		
		int[] m2int = new int[18];
		for (int i=0; i<m2str.length; i++) {
			int index = intGenre(m2str[i]);
			m2int[index] = 1;
		}
		
		double similarity = CosSimilarity.sim(m1int, m2int);
		
		return similarity;
	}
	
	public static int intGenre (String strGenre) {
		int num = 0;
		
		if (strGenre.equals("Action")) {
			num = 1;
		} else if (strGenre.equals("Adventure")) {
			num = 2;
		} else if (strGenre.equals("Animation")) {
			num = 3;
		} else if (strGenre.equals("Children's")) {
			num = 4;
		} else if (strGenre.equals("Comedy")) {
			num = 5;
		} else if (strGenre.equals("Crime")) {
			num = 6;
		} else if (strGenre.equals("Documentary")) {
			num = 7;
		} else if (strGenre.equals("Drama")) {
			num = 8;
		} else if (strGenre.equals("Fantasy")) {
			num = 9;
		} else if (strGenre.equals("Film-Noir")) {
			num = 10;
		} else if (strGenre.equals("Horror")) {
			num = 11;
		} else if (strGenre.equals("Musical")) {
			num = 12;
		} else if (strGenre.equals("Mystery")) {
			num = 13;
		} else if (strGenre.equals("Romance")) {
			num = 14;
		} else if (strGenre.equals("Sci-Fi")) {
			num = 15;
		} else if (strGenre.equals("Thriller")) {
			num = 16;
		} else if (strGenre.equals("War")) {
			num = 17;
		} else if (strGenre.equals("Westernr")) {
			num = 18;
		} else {
			num = 0;
		}
		
		return num;
	}
	
}
