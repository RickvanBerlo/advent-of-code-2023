package task10;

import java.util.Arrays;
import java.util.EnumSet;

public enum Pipe {
    HORIZONTAL('-'),
    VERTICAL('|'),
    NORTH_EAST('L'),
    NORTH_WEST('J'),
    SOUTH_EAST('F'),
    SOUTH_WEST('7'),
    GROUND('.'),
    STARTING('S');

    public static EnumSet<Pipe> north(){
        return EnumSet.of(Pipe.NORTH_EAST, Pipe.NORTH_WEST, Pipe.VERTICAL, Pipe.STARTING);
    }

    public static EnumSet<Pipe> east(){
        return EnumSet.of(Pipe.NORTH_EAST, Pipe.SOUTH_EAST, Pipe.HORIZONTAL, Pipe.STARTING);
    }

    public static EnumSet<Pipe> west(){
        return EnumSet.of(Pipe.NORTH_WEST, Pipe.SOUTH_WEST, Pipe.HORIZONTAL, Pipe.STARTING);
    }

    public static EnumSet<Pipe> south(){
        return EnumSet.of(Pipe.SOUTH_EAST, Pipe.SOUTH_WEST, Pipe.VERTICAL, Pipe.STARTING);
    }

    public char asChar() {
        return asChar;
    }

    private final char asChar;

    Pipe(char asChar) {
        this.asChar = asChar;
    }

    public static Pipe fromName(char name){
    return Arrays.stream(Pipe.values()).filter(f -> f.asChar == name).findAny().orElseThrow(IllegalArgumentException::new);
}
}
