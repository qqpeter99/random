/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Problem Statement
    
In one mode of the grafix software package, the user blocks off portions of a masking layer using opaque rectangles. The bitmap used as the masking layer is 400 pixels tall and 600 pixels wide. Once the rectangles have been blocked off, the user can perform painting actions through the remaining areas of the masking layer, known as holes. To be precise, each hole is a maximal collection of contiguous pixels that are not covered by any of the opaque rectangles. Two pixels are contiguous if they share an edge, and contiguity is transitive.

You are given a String[] named rectangles, the elements of which specify the rectangles that have been blocked off in the masking layer. Each String in rectangles consists of four integers separated by single spaces, with no additional spaces in the string. The first two integers are the window coordinates of the top left pixel in the given rectangle, and the last two integers are the window coordinates of its bottom right pixel. The window coordinates of a pixel are a pair of integers specifying the row number and column number of the pixel, in that order. Rows are numbered from top to bottom, starting with 0 and ending with 399. Columns are numbered from left to right, starting with 0 and ending with 599. Every pixel within and along the border of the rectangle defined by these opposing corners is blocked off.

Return a int[] containing the area, in pixels, of every hole in the resulting masking area, sorted from smallest area to greatest.

 
Definition
    	
Class:	grafixMask
Method:	sortedAreas
Parameters:	String[]
Returns:	int[]
Method signature:	int[] sortedAreas(String[] rectangles)
(be sure your method is public)
    
 
Notes
-	Window coordinates are not the same as Cartesian coordinates. Follow the definition given in the second paragraph of the problem statement.
 
Constraints
-	rectangles contains between 1 and 50 elements, inclusive
-	each element of rectangles has the form "ROW COL ROW COL", where: "ROW" is a placeholder for a non-zero-padded integer between 0 and 399, inclusive; "COL" is a placeholder for a non-zero-padded integer between 0 and 599, inclusive; the first row number is no greater than the second row number; the first column number is no greater than the second column number
 
Examples
0)	

{"0 292 399 307"}
Returns: { 116800,  116800 }
 * @author qianp
 */
import java.util.*;
public class grafixMask {
    
    public static int X = 400;
    public static int Y = 600;
    public static boolean[][] table = new boolean[X][Y];
    
    
    private class Pixel {
        public int x;
        public int y;
        
        public Pixel(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public boolean inTable(int x, int y) {
        return x > -1 && x < X && y > -1 && y < Y;
    }
    
    public int[] sortedAreas(String[] rectangles) {
        
        List<Integer> ints = new ArrayList<Integer>();
        
        fillTable(rectangles);
        
        for (int i = 0; i < X; i++) {
            for (int j = 0; j< Y; j++) {
                
                if (!table[i][j]) { // empty, now expand it 
                    Stack<Pixel> sp = new Stack<Pixel>();
                    int area = 0;
                   
                    Pixel p = new Pixel(i, j);
                    sp.push(p);
                    table[i][j] = true;
                    while (!sp.empty()) {
                        
                        int x = p.x;
                        int y = p.y;
                        
                        // check neighbors
                        if (inTable(x-1, y) && !table[x-1][y]){
                            sp.push(new Pixel(x-1, y));
                            table[x-1][y] = true;
                        }
                        if (inTable(x+1, y) && !table[x+1][y]){
                            sp.push(new Pixel(x+1, y));
                            table[x+1][y] = true;
                        }
                        if (inTable(x, y-1) && !table[x][y-1]){
                            sp.push(new Pixel(x, y-1));
                            table[x][y-1] = true;
                        }
                        if (inTable(x, y+1) && !table[x][y+1]){
                            sp.push(new Pixel(x, y+1));
                            table[x][y+1] = true;
                        }
                        
                        p = sp.pop();
                        area++;
                        
                    }
                    if (area > 0) {
                        ints.add(new Integer(area));
                    }
                }
                
                
            }
        }
                
        Collections.sort(ints, Collections.reverseOrder());
      
        int[] r = new int[ints.size()];
        for (int i = 0; i<ints.size(); i++) {
            r[i] = ints.get(i).intValue();
        }
        return r;
    } 
    
    public void fillTable(String[] r) {
            
            for(int i = 0; i < r.length; i++) {
                String s = r[i];
                
                String[] p = s.split(" ");
                int p0 = Integer.parseInt(p[0]);
                int p1 = Integer.parseInt(p[1]);
                int p2 = Integer.parseInt(p[2]);
                int p3 = Integer.parseInt(p[3]);
                
                for (int j = p0; j <= p2; j++){
                    for (int k = p1; k <= p3; k++){
                        table[j][k] = true;
                    }
                }
            }
    }
        
    public static void main(String[] arg){
//        String[] r = new String[]{"0 0 399 1"};
        String[] r = new String[]{"0 292 399 307"};
        long startTime = System.nanoTime();
        int[] ints = new grafixMask().sortedAreas(r);
        long endTime = System.nanoTime();
        
        System.out.println("Result has " + ints.length + " elements!");
        
        for (int i = 0; i<ints.length; i++) {
           System.out.print(ints[i] + " ");
        }
       
        System.out.println("\nWith " + (endTime-startTime)/1000000 + " ms");
        
    };
    
}
