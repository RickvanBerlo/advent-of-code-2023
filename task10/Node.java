package task10;

import java.util.HashMap;
import java.util.Map;

public class Node {
    public Pipe pipe;
    public int y;
    public int x;
    public int weight = Integer.MAX_VALUE;
    public boolean loop = false;
    public boolean outside = false;
    public Map<Direction, Node> directions = new HashMap<>();


    public Node(char pipe, int y, int x){
        this.y = y;
        this.x = x;
        this.pipe = determinePipe(pipe);
    }

    public Pipe determinePipe(char pipe){
        return Pipe.fromName(pipe);
    }

    public void exploreNeighbors(Node[][] map){
        if(Pipe.north().contains(pipe) && y != 0){
            directions.put(Direction.North, map[y - 1][x]);
        }
        if(Pipe.east().contains(pipe) && x != (map[y].length -1)){
            directions.put(Direction.East, map[y][x + 1]);
        }
        if(Pipe.west().contains(pipe) && x != 0 ){
            directions.put(Direction.West, map[y][x - 1]);
        }
        if(Pipe.south().contains(pipe) && y != (map.length -1)){
            directions.put(Direction.South, map[y + 1][x]);
        }
    }

    public int getDistance(int steps, Direction previousDirection){
        if(previousDirection == null){
            return directions.entrySet().stream().map(entry -> entry.getValue().getDistance(steps + 1, Direction.getOpposite(entry.getKey()))).mapToInt(v -> v).max().orElse(-1);
        } else {
            if(directions.get(previousDirection) == null){
                return 0;
            } else if(this.pipe.equals(Pipe.STARTING)) {
                this.loop = true;
                return steps;
            } else {
                int tmp = directions.entrySet().stream().filter(entry -> entry.getKey() != previousDirection).map(entry -> entry.getValue().getDistance(steps + 1, Direction.getOpposite(entry.getKey()))).mapToInt(v -> v).findFirst().getAsInt();
                if(tmp != 0){
                    this.loop = true;
                }
                return tmp;
            }
        }
    }

    public void checkIfInsideLoop(Node[][] map){
        if((y - 1) > -1 && (y + 1) < map.length){
            if((x - 1) > -1 && (x + 1) < map[y].length){
                if(!map[y - 1][x - 1].loop){
                    outside = true;
                }
                if(!map[y - 1][x].loop){
                    outside = true;
                }
                if(!map[y - 1][x +1].loop){
                    outside = true;
                }
                if(!map[y][x -1].loop){
                    outside = true;
                }
                if(!map[y][x + 1].loop){
                    outside = true;
                }
                if(!map[y + 1][x - 1].loop){
                    outside = true;
                }
                if(!map[y + 1][x].loop){
                    outside = true;
                }
                if(!map[y + 1][x + 1].loop){
                    outside = true;
                }

            } else {
                outside = true;
            }
        } else {
            outside = true;
        }
                
    }
}
