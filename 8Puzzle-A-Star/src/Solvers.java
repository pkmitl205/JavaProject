import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author Pakon
 */
public class Solvers {
    public static enum SOLVE_METHOD{A_STAR};

    public static long times;
    
    //solve the current position with A* search
    public static Map<String, byte[]> aStar(byte[] current){
        PriorityQueue<State> q = new PriorityQueue<>();
        Map<String, Integer> dist = new HashMap<>();
        Map<String, byte[]> parent = new HashMap<>();
        
        times = 0;

        dist.put(str(current), 0);
        
        q.add(new State(current, 0));
        
        //A* Algorithm ...
        while(!q.isEmpty()){
            State currentState = q.poll();
            times++;
            if(Arrays.equals(currentState.getBoard(), BoardControl.GOAL)){
                break;
            }
            for(State child : currentState.getNextStates()){
                if(dist.getOrDefault(str(child.getBoard()), Integer.MAX_VALUE) > child.getCost()){                    
                    parent.put(str(child.getBoard()), currentState.getBoard());
                    dist.put(str(child.getBoard()), child.getCost());
                    q.add(child);
                }
            }
        }
        
        return parent;
    }

    public static String str(byte[] arr){
        String str = "";
        for(int i = 0 ; i < arr.length ; ++i){
            str += String.valueOf(arr[i]);
        }
        return str;
    }
   
}
