/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Count the recursive call numbers
 * The code is to calculate the minimum the path value by traverse the table, and generate objects in 2^n manner, 1=>2=>4=>8
 * The time complexity is 2^
 * -----
 * | 1 |
 * -----
 * 
 * =>
 * 
 * -----------
 * | 1 | 1.1 |
 * -----------
 * |1.2| 
 * 
 * =>
 * 
 * -------------------------
 * | 1   | 1.1         | 1.1.1
 * -------------------------
 * |1.2  | 1.1.2, 1.2.1|
 * |1.2.2|
 * 
 * @author qianp
 */
import java.util.*;

public class countFuncCalls {
    
    private int counter = 0;
    
    int findMinPath(int[][] V, int r, int c) {
        counter++;
        int R = V.length;
        int C = V[0].length;
        if (r >= R || c >= C) return 100000000; // Infinity
        if (r == R - 1 && c == C - 1) return 0;
        return V[r][c] + Math.min(findMinPath(V, r + 1, c), findMinPath(V, r, c + 1));
    }
    
    public static void main(String[] arg){
        int[][] a = new int[][]{{0,0,0,5},{1,1,1,5},{2,2,2,5}};
        
        countFuncCalls cfc = new countFuncCalls();
        int result = cfc.findMinPath(a, 0, 0);
                
        System.out.println("Function findMinPaths gets called " + cfc.counter + " times with result " + result);
    }
}
