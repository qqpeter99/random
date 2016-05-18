public class TallPeople {
    
    public int[] getPeople(String[] people) {
        
        int[] minEachRow = new int[people.length];
        String[] parts = people[0].split(" ");
        int[] maxEachColumn = new int[parts.length];
            
        for (int i = 0; i < people.length; i++) {
            parts = people[i].split(" ");
                        
            for (int j = 0; j < parts.length; j++) {
                int h = Integer.parseInt(parts[j]);
                if (j == 0) {
                    minEachRow[i] = h; //Key: Initialize the value in the first row
                } else {
                    if (minEachRow[i] > h) {
                        minEachRow[i] = h;
                    }
                }
                
                if (i == 0) {
                    maxEachColumn[j] = h; //Key:Initialize the value in the first column
                } else {
                    if (maxEachColumn[j] < h) {
                        maxEachColumn[j] = h;
                    }
                    
                }
                
            }
        }
        
        int[] ret = new int[2];
        ret[0] = minEachRow[0];
        ret[1] = maxEachColumn[0];
        for (int i = 1; i < minEachRow.length; i++) {
            if (minEachRow[i] > ret[0]){
                ret[0] = minEachRow[i];
            }
            
        }
        
        for (int i = 1; i < maxEachColumn.length; i++) {
            if (maxEachColumn[i] < ret[1]){
                ret[1] = maxEachColumn[i];
            }
            
        }
        
        return ret;
        
    }
    
}