package algo;

public class CosSimilarity {

	/*
	 *  x and y are vectors
	 */
    public static double sim(int[] x, int[] y) {
        double dp = dotProduct(x,y);
        double magX = magnitude(x);
        double magY = magnitude(y);
        return (dp)/(magX*magY);
    }
    
    /*
     * SUM of (x[i] * y[i])
     */
    private static double dotProduct(int[] x, int[] y) {
        double sum=0;
        for(int i=0;i<x.length;i++)
        {
            sum = sum + x[i]*y[i];
            System.out.println(sum);
        }
        return sum;
    }
    
    /*
     * SQRT(SUM(x[i]^2))
     * SQRT(SUM(y[i]^2))
     */
    private static double magnitude(int[] vector) {
        double sum_mag=0;
        for(int i=0;i<vector.length;i++)
        {
            sum_mag = sum_mag + vector[i]*vector[i];
        }
        return Math.sqrt(sum_mag);
    }
}
