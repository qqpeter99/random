/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Reverse bits of an 32 bit unsigned integer

Example 1:

x = 0,

          00000000000000000000000000000000  
=>        00000000000000000000000000000000
return 0

Example 2:

x = 3,

          00000000000000000000000000000011 
=>        11000000000000000000000000000000
return 3221225472

Since java does not have unsigned int, use long
* 
 * @author qianp
 */
public class ReverseBits {
    
    
    public long reverse(long a) {
        
        long result = 0;
        for (int i=0; i<32; i++) {
            
            if ( (a & (1 << i)) != 0) {
                
                result |= (1 << (31 - i));
            }
        }
        
        
        return result & 0xffffffffl; //http://www.javamex.com/java_equivalents/unsigned_arithmetic.shtml
    }
    
    public static void main(String[] arg){
        long i = 1;
        
        
        long count = new ReverseBits().reverse(i);
        System.out.println("The reversed long of " + i + " is " + count);
    }
    
    
}
