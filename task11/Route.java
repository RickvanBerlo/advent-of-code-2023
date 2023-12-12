package task11;

import java.util.List;
import java.util.UUID;

public class Route {
    public UUID id;
    public Long steps;
    public Node locationA;
    public Node locationB;

    public Route(UUID id, Node locationA, Node locationB, List<Integer> universeY, List<Integer> universeX){
        this.id = id;
        this.locationA = locationA;
        this.locationB = locationB;
        this.steps = calculateDistance(locationA, locationB, universeY, universeX);
        System.out.println("between: " + (locationA.y + locationA.multiplierY) + ","+ (locationA.x + locationA.multiplierX) + " and " + (locationB.y + locationB.multiplierY) + "," + (locationB.x + locationB.multiplierX) + ". There are steps: " + this.steps);
    }

    public Long calculateDistance(Node locactionA, Node locationB, List<Integer> universeY, List<Integer> universeX){
        return Long.valueOf(
            Math.abs((locationA.y + locactionA.multiplierY) - (locationB.y + locationB.multiplierY)) 
            + 
            Math.abs((locationA.x + locactionA.multiplierX) - (locationB.x + locationB.multiplierX))
        );
    }
}
