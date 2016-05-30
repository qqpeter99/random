/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Count how many ones in a long value
 * @author qianp
 */
public class CountOnes {
    
    public static long bitMask = 0x00000001;
    
    public int ones(long n) {
        
        long t = n;
        int count = 0;
        
        while (t != 0) {
            long t1 = t & bitMask;
            if (t1 != 0) {
                count++;
            }
                        
            t = t >>> 1;
            
        }
       
        return count;
    } 
     
    
    public static void main(String[] arg){
        long i = 9;
        
        
        int count = new CountOnes().ones(i);
        System.out.println("The number of 1 in " + i + " is " + count);
    }
}
