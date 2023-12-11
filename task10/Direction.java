package task10;

public enum Direction {
    North,
    East,
    West,
    South;


    public static Direction getOpposite(Direction direction){
        switch (direction) {
            case North:
                return South;
            case East:
                return West;
            case West:
                return East;
            case South:
                return North;
            default:
                return null;
        }
    }
}
