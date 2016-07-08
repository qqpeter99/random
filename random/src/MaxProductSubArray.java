/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author qianp
 */
import java.util.*;

public class MaxProductSubArray {
    
    private int maxP = 0;
    
    public int maxProduct(final List<Integer> a) {
        
        List<Integer> newa = new ArrayList<Integer>();
        System.out.print("Processing array: ");
        String s = "";
        for(int i=0; i<a.size(); i++){
            //if(a.get(i).intValue() !=0) {
                newa.add(a.get(i));
                s += a.get(i).toString() + " ";
            //}
        }
        System.out.println(s);
        
        if (newa.size() == 0) return 0;

        if (newa.size() == 1) {
            return newa.get(0).intValue();
        }
        if (newa.size() == 2) {
            return Math.max(Math.max(newa.get(0).intValue(), newa.get(1).intValue()), newa.get(0).intValue() * newa.get(1).intValue());
        }
        
        int ai0 = newa.get(0).intValue();
        int ai1 = newa.get(1).intValue();
        
        int am = ai0 * ai1;
        
        int ai2=newa.get(2).intValue();
        
        int result = 0;
        if (ai2 * ai1 >= am * ai2) {
            List<Integer> newa1 = new ArrayList<Integer>();
            newa1.addAll(newa);
            newa1.remove(0);
            result = maxProduct(newa1);
        } else if (ai2 * ai1 < am * ai2){
            List<Integer> newa1 = new ArrayList<Integer>();
            newa1.addAll(newa);
            newa1.remove(0);
            newa1.remove(0);
            newa1.add(0, new Integer(am));
            result = maxProduct(newa1);
        }
        return result;
    };
    
    
    // For example [0, -3, 2, -3, 0, 5, 6, 0]
    // For each non-zero section, one max positive product and one max negative product need to be tracked
    // If the next item in array is negative, use the max negative product to multiply. 
    // The key technique here is to swap two maximums in this case. 
    public int maxProduct1(final List<Integer> a) {
        
        
        int maxP = a.get(0).intValue();
        
        int tmpMin = maxP, tmpMax = maxP;
        
        for(int j=1; j<a.size(); j++){
            int ai = a.get(j).intValue();
            
            if (ai < 0) {
                int tmp = tmpMin;
                tmpMin = tmpMax;
                tmpMax = tmp;
            }
            
            tmpMax = Math.max(ai, ai * tmpMax);
            tmpMin = Math.min(ai, ai * tmpMin);
           
            
            maxP = Math.max(maxP, tmpMax);
        }
        return maxP;
    };
    
    public static void main(String[] arg){
        int[] t = new int[]{0,-3,-2,0,-2,0,3,4,0}; 
        int[] t1 = new int[]{0,-3,2,-3,0};
        int[] t2 = new int[]{0, 0, 0, -3, -2, 0, 1, 0, 0, 0, 0, 0, -2, 0, 0, 0, 3, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, -3, 0, 0, 0, 0, -1, 0, 2, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, -2, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, -3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, 0, 0, 0, 0, 0, 0, 0, -1, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] t3 = new int[]{-4,0,-5,0};
        int[] t4 = new int[]{0,0,3,2,0,2,8,0};
        
        int[] tarr = t1;
        List<Integer> a = new ArrayList<Integer>();
        
        for(int i = 0; i < tarr.length; i++) {
            a.add(new Integer(tarr[i]));
        }
            
                
        int result = new MaxProductSubArray().maxProduct1(a);
                
        System.out.println("The consecutive array with max product is " + result);
        
        
    }
    
}
