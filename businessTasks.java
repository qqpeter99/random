import java.util.Vector;

public class BusinessTasks {
    
    public String getTask(String[] list, int n) {
       
        Vector<String> tasks = new Vector<String>();
        for (int i = 0; i < list.length; i++)
            tasks.add(list[i]);
        
        int tasksToRemove = list.length - 1;
        int currStart = 0;
        int currTarget = 0;
        while (tasksToRemove > 0) {
            currTarget = (currStart + (n - 1)) % tasks.size();
            tasks.remove(currTarget);
            currStart = currTarget % tasks.size();
            tasksToRemove--;
        }
        return tasks.get(0);
        
    }
}