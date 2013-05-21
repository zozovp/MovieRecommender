package algo;

public class AdjustedCosSimilarity {

	/*
	 *  x and y are vectors
	 */
    public static double sim(int[] x, int[] y, double[] avg) {		
    	double dp = dotProduct(x, y, avg);
        double magX = magnitude(x, avg);
        double magY = magnitude(y, avg);
        
        
        return (dp)/(magX*magY);
    }
    
    /*
     * SUM of (x[i] * y[i])
     */
    private static double dotProduct(int[] x, int[] y, double[] avg) {
        double sum=0;
        for(int i=0;i<x.length;i++)
        {
            sum = sum + ((x[i] - avg[i]) * (y[i] - avg[i]));
        }
        return sum;
    }
    
    /*
     * SQRT(SUM(x[i]^2))
     * SQRT(SUM(y[i]^2))
     */
    private static double magnitude(int[] vector, double[] avg) {
        double sum_mag=0;
        for(int i=0;i<vector.length;i++)
        {
            sum_mag = sum_mag + ((vector[i] - avg[i])*(vector[i] - avg[i]));
        }
        return Math.sqrt(sum_mag);
    }
}
