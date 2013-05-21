package algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PearsonCorrelationSimilarity {
	/*
	 *  x and y are vectors
	 */
    public static double sim(List<Integer> item1, List<Integer> item2) {
    	
    	Map<Integer,List<Integer>> mapResult = filter(item1, item2);
    	Map<Integer,Double> mapResultAvg = average(mapResult);
    	Double dividend = dividend(mapResultAvg, mapResult);
    	Double divisorItem1 = divisorItem1(mapResultAvg, mapResult);
    	Double divisorItem2 = divisorItem2(mapResultAvg, mapResult);
    	
   
    	double result= dividend / (divisorItem1*divisorItem2);
    	
    	return result;
    }

    /* 
     * calculate divisor for item2
     */
    private static Double divisorItem2(Map<Integer,Double> mapResultAvg, Map<Integer,List<Integer>> mapResultFilter){
 		
    	Double totalDivisor = new Double(0);
    	
    	List<Integer> item2 = mapResultFilter.get(2);
  
    	Iterator<Integer> listItr = item2.iterator();
    	int i=0;
    	while(listItr.hasNext()){    		
    		totalDivisor = totalDivisor +  Math.pow((item2.get(i)-mapResultAvg.get(2)),2);
    		listItr.next();
    		i++;
    	}    	
    	return Math.sqrt(totalDivisor);
    }
    
    
   /* 
    * calculate divisor for item1
    */
   private static Double divisorItem1(Map<Integer,Double> mapResultAvg, Map<Integer,List<Integer>> mapResultFilter){
		
   	Double totalDivisor = new Double(0);
   	
   	List<Integer> item1 = mapResultFilter.get(1);
   	
   	Iterator<Integer> listItr = item1.iterator();
   	int i=0;
   	while(listItr.hasNext()){    		
   		totalDivisor = totalDivisor +  Math.pow((item1.get(i)-mapResultAvg.get(1)),2);
   		listItr.next();
   		i++;
   	}    	
   	return Math.sqrt(totalDivisor);
   }
   
    
    /*
     * calculate dividend
     */
    private static Double dividend(Map<Integer,Double> mapResultAvg, Map<Integer,List<Integer>> mapResultFilter){
		
    	Double totalDividend = new Double(0);
    	
    	List<Integer> item1 = mapResultFilter.get(1);
    	List<Integer> item2 = mapResultFilter.get(2);
    	
    	
    	Iterator<Integer> listItr = item1.iterator();
    	int i=0;
    	while(listItr.hasNext()){    		
    		totalDividend = totalDividend + (item1.get(i)-mapResultAvg.get(1))*(item2.get(i)-mapResultAvg.get(2));
    		listItr.next();
    		i++;
    	}    	
    	return totalDividend;
    }
    
    
    /*
     * throw user that does not rate both movies
     */
    private static Map<Integer,List<Integer>> filter(List<Integer> item1, List<Integer> item2){
    	
    	Map<Integer,List<Integer>> resultMap = new HashMap<Integer,List<Integer>>();
    	List<Integer> resultI1 = new ArrayList<Integer>();
    	List<Integer> resultI2 = new ArrayList<Integer>();
    	
    	/*
    	 * check total item
    	 */
    	if (item1.size() != item2.size()){
    		System.out.println("Warning! item size does not same");
    	}
    	
    	//System.out.println(item1.get(1));
    	
    	Iterator<Integer> listItr = item1.iterator();
    	int i=0;
    	while(listItr.hasNext()){
    		if (listItr.next() != null){
    			if(item2.get(i) != null){
    				resultI1.add(item1.get(i));
    				resultI2.add(item2.get(i));
    			}
    		}
    		i++;
    	}
    	
    	resultMap.put(1, resultI1);
    	resultMap.put(2, resultI2);
    		
		return resultMap;
    	
    }
    
    /*
     * get rating average for each movie
     */
    private static Map<Integer,Double> average(Map<Integer,List<Integer>> source){
    	
    	
    	Map<Integer,Double> resultMap = new HashMap<Integer,Double>();
    	
    	
    	
    	/*
    	 * rating average for movie1
    	 */
    	int totalRatingMovie1 = 0;
    	Iterator<Integer> list1Itr = source.get(1).iterator();
    	while(list1Itr.hasNext())
    	{
    		int test = list1Itr.next();
    		totalRatingMovie1 = totalRatingMovie1 +test;
    	}
    	
    	resultMap.put(1,Double.valueOf((double) totalRatingMovie1)/ Double.valueOf((double) source.get(1).size()));
    	
    	
    	/*
    	 * rating average for movie2
    	 */
    	int totalRatingMovie2 = 0;
    	Iterator<Integer> list2Itr = source.get(2).iterator();
    	while(list2Itr.hasNext())
    	{
    		totalRatingMovie2 = totalRatingMovie2 +list2Itr.next();
    	}
    	
    	resultMap.put(2,Double.valueOf((double) totalRatingMovie2)/ Double.valueOf((double) source.get(2).size()));
    	
		return resultMap;

    }
}
