public class PrintScheduler {
    
    public String getOutput(String[] threads, String[] slices){
        
        String result = "";
        // record the position     
        int[] idx = new int[threads.length]; // default to 0 all
        
        // currThread, currSlice
        int currThread = -1;
        int currSlice = 0;
        for (int i = 0; i < slices.length; i++) {
            // parse the thread id and slice
            String[] parts = slices[i].split(" ");
            currThread = Integer.parseInt(parts[0]);
            currSlice = Integer.parseInt(parts[1]);
            
            int strIdx = 0;
            for (int j=0; j < currSlice; j++) {
                strIdx = (idx[currThread]+j) % threads[currThread].length();
                result += threads[currThread].charAt(strIdx);
            }
            idx[currThread] = (strIdx+1) % threads[currThread].length();
 
        }
        return result;
        
    }
    
}