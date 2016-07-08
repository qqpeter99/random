/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example:

Given the array [-2,1,-3,4,-1,2,1,-5,4],

the contiguous subarray [4,-1,2,1] has the largest sum = 6.

For this problem, return the maximum sum.
* 
 * @author qianp
 * 
 * The algorithm 1 is 
 *  1. Find each segment with positive sum
 *  2. Within each such segment, keep tracking the sub sums to get the max sum. 
 * 
 * The algorithm 2 is
 *  To compare the number at ith index with the previous max sum, the greater value is the one to use for continuing the search
 *  It's like I consider if the previous sum is contributing to new sum by adding ith value, if not just use ith value as new start, meanwhile compare to the previous max value
 */
public class MaxSumContinuousArray {
    
    
    public int maxSum(int[] arr) {
        
        // For all negative values
        boolean allNeg = true;
        for(int i =0; i<arr.length; i++){
            int tmp = arr[i]; 
            if (tmp >= 0) {
                allNeg = false;
                break;
            }
        }

        if (allNeg) {
            int result = -10000000;
            for(int i =0; i<arr.length; i++){
                int tmp = arr[i];
                if (result < tmp){
                    result = tmp;
                } 
            }
            return result;
        }
        
        
        
        
        // Normal case
        int maxSumInCurrentSegment = 0;
        int resultMax = 0;
        
        for(int i =0; i<arr.length; i++){
           maxSumInCurrentSegment += arr[i];
           if (maxSumInCurrentSegment < 0) { // If the current segment is negtive
               maxSumInCurrentSegment = 0;
           } else if (resultMax < maxSumInCurrentSegment){ // Else check if the max sum is greater than the max record
               resultMax = maxSumInCurrentSegment;
           }
        }
        
        return resultMax;
    };
    
    public int maxSum2(int[] arr) {
        
        int max = arr[0];
        int tempSum = arr[0];
        
        for(int i=1; i<arr.length; i++){
            
            tempSum = Math.max(tempSum+arr[i], arr[i]);
            max = Math.max(tempSum, max);
        }
        return max;
        
        
    }
    
    public static void main(String[] arg){
        int[]a = new int[]{1,2,3,5};        
        int[]b = new int[]{-1,-20,-4,-8};
        int[]c = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int[]d = new int[]{-2,3,-1};
        
        int result = new MaxSumContinuousArray().maxSum2(d);
                
        System.out.println("The consecutive array with max sum is " + result);
    }
    
}
