package task11;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class Node {
    public boolean universe;
    public UUID id;
    public Integer y;
    public Integer x;
    public Map<UUID, Route> routes = new HashMap<>();
    public Long multiplierX;
    public Long multiplierY;

    public Node(boolean universe){
        this.universe = universe;
        this.id = UUID.randomUUID();
    }

    public void setMultiplier(Long y, Long x){
        this.multiplierX = x;
        this.multiplierY = y;
    }

    public void setPosition(Integer y, Integer x){
        this.y = y;
        this.x = x;
    }

    public void addRoute(Route route){
        if(route.locationA.id.equals(this.id)){
            routes.put(route.locationB.id, route);
        } else {
            routes.put(route.locationA.id, route);
        }
    }

}
