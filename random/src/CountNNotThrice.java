/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Given an array of integers, every element appears thrice except for one which occurs once.

Find that element which does not appear thrice.

Note: Your algorithm should have a linear runtime complexity.

Could you implement it without using extra memory?

Example :

Input : [1, 2, 4, 3, 3, 2, 2, 3, 1, 1]
Output : 4
* 
 * Hint, bit manipulation, to lay each input number's bit count info into a result. 
 * Check the appearance of each bit, if it appears 3X + 1 times, keep the 1 bit. 
 * 
 * 
 * @author qianp
 */
import java.util.*;
        
public class CountNNotThrice {
    
    // DO NOT MODIFY THE LIST
	public int singleNumber(final List<Integer> a) {
           
            int result = 0;
            
            int mask = 0x0001;
            
            for(int i = 0; i < 32; i++) {
                int bitCount = 0;
                
                for (int j = 0; j < a.size(); j++) {
                    int t = (Integer)a.get(j).intValue();
                    
                    if ( (t & ( 1 << i)) != 0) {
                        bitCount++;
                    }
                }
                
                if (bitCount % 3 != 0) {
                    result |= (1 << i);
                }
                
            }
            
            return result;
	};
        
        public static void main(String[] arg){
            
            int[] input = new int[]{1,2,3,3,3,2,2,1,4,1};
            
            List<Integer> a = new ArrayList<Integer>();
            for(int i = 0; i < input.length; i++) {
                a.add(new Integer(input[i]));
            }
        
            int count = new CountNNotThrice().singleNumber(a);
            
            System.out.println("The number doesn't appear thrice is " + count);
        }
}
