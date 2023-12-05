package task5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {
    public String name;
    public Node next = null;
    List<List<Long>> ranges = new ArrayList<>();


    public Node(String name){
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setNextNode(Node node){
        this.next = node;
    }

    public void InitRange(List<Long> range){
        ranges.add(range);
    }

    public Long getLocation(Long seed){
        Long maybeNewSeed = seed;
        for(List<Long> range: ranges){
            if(seed >= range.get(1) && seed <= (range.get(1) + (range.get(2) -1))){
                maybeNewSeed = seed + (range.get(0) - range.get(1));
            }
        }
        
        if(next == null)
        return maybeNewSeed;
        return next.getLocation(maybeNewSeed);

    }
}
