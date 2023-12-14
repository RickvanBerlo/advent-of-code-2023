package task14;

public class Node {
    public boolean rock;
    public boolean square;

    public Node(boolean rock, boolean square){
        this.rock = rock;
        this.square = square;
    }

    public Point calculateTilledNorth(Node[][] map, Point point){
        if(point.y == 0){
            return point;
        } else {
            int counterRoundedRock = 0;
            for(int y = point.y - 1; y > -1; y--){
                if(map[y][point.x] != null && map[y][point.x].rock && map[y][point.x].square){
                    return new Point((y + 1) + counterRoundedRock, point.x);
                } else if(map[y][point.x] != null && map[y][point.x].rock && !map[y][point.x].square) {
                    counterRoundedRock++;
                }
            }
            return new Point(counterRoundedRock, point.x);
        }
    }

    public Point calculateTilledWest(Node[][] map, Point point){
        if(point.x == 0){
            return point;
        } else {
            int counterRoundedRock = 0;
            for(int x = point.x - 1; x > -1; x--){
                if(map[point.y][x] != null && map[point.y][x].rock && map[point.y][x].square){
                    return new Point(point.y,(x + 1) + counterRoundedRock);
                } else if(map[point.y][x] != null && map[point.y][x].rock && !map[point.y][x].square) {
                    counterRoundedRock++;
                }
            }
            return new Point(point.y, counterRoundedRock);
        }
    }

    public Point calculateTilledSouth(Node[][] map, Point point){
        if(point.y == map.length -1){
            return point;
        } else {
            int counterRoundedRock = 0;
            for(int y = point.y + 1; y < map.length; y++){
                if(map[y][point.x] != null && map[y][point.x].rock && map[y][point.x].square){
                    return new Point(((y - 1) - counterRoundedRock), point.x);
                } else if(map[y][point.x] != null && map[y][point.x].rock && !map[y][point.x].square) {
                    counterRoundedRock++;
                }
            }
            return new Point(((map.length - 1) - counterRoundedRock), point.x);
        }
    }

    public Point calculateTilledEast(Node[][] map, Point point){
        if(point.x == map.length - 1){
            return point;
        } else {
            int counterRoundedRock = 0;
            for(int x = point.x + 1; x < map.length; x++){
                if(map[point.y][x] != null && map[point.y][x].rock && map[point.y][x].square){
                    return new Point(point.y,(x - 1) - counterRoundedRock);
                } else if(map[point.y][x] != null && map[point.y][x].rock && !map[point.y][x].square) {
                    counterRoundedRock++;
                }
            }
            return new Point(point.y, ((map.length -1) - counterRoundedRock));
        }
    }
}
