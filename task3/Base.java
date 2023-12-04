package task3;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.charset.*;
import java.io.*;

public class Base {
    // public static void main(String[] args) throws IOException{
    //     List<String> lines =  Files.readAllLines(Path.of("/Users/rickvanberlo/Projects/advent-of-code-2023/task3/input.txt"), StandardCharsets.UTF_8);
    //     int magic = 140;
    //     List<Number> numbers = new ArrayList<>();
    //     char[][] map = new char[magic][magic];
    //     //create 
    //     for(int i = 0; i < magic; i++){
    //         Number number = null;
    //         char[] line = lines.get(i).toCharArray();
    //         for(int j = 0; j < magic; j++){
    //             map[i][j] = line[j];
    //             Integer digit = Character.getNumericValue(line[j]);
    //             if(digit < 0 && number != null){
    //                 numbers.add(number);
    //                 number = null;
    //             } else if (digit >= 0) {
    //                 if(number == null)
    //                 number = new Number();
    //                 number.addNumber(line[j]);
    //                 number.positions.add(new int[]{i, j});
    //             }
    //         }
    //         if(number != null)
    //             numbers.add(number);
    //         number = null;
    //     }

    //     numbers.forEach(number -> number.checkNearChar(map));
    //     System.out.println(numbers.stream().filter(number -> number.nearChar).map(number -> Integer.valueOf(number.number)).reduce(0, Integer::sum));
    // }

    public static void main(String[] args) throws IOException{
        List<String> lines =  Files.readAllLines(Path.of("/Users/rickvanberlo/Projects/advent-of-code-2023/task3/input.txt"), StandardCharsets.UTF_8);
        int magic = 140;
        List<Number> numbers = new ArrayList<>();
        Map<String, List<Integer>> gears = new HashMap<>(); 
        char[][] map = new char[magic][magic];
        //create 
        for(int i = 0; i < magic; i++){
            Number number = null;
            char[] line = lines.get(i).toCharArray();
            for(int j = 0; j < magic; j++){
                map[i][j] = line[j];
                Integer digit = Character.getNumericValue(line[j]);
                if(digit < 0 && number != null){
                    numbers.add(number);
                    number = null;
                } else if (digit >= 0) {
                    if(number == null)
                    number = new Number();
                    number.addNumber(line[j]);
                    number.positions.add(new int[]{i, j});
                }
            }
            if(number != null)
                numbers.add(number);
            number = null;
        }

        numbers.forEach(number -> number.checkNearGearOrChar(map));
        numbers.stream().filter(number -> number.nearGear).forEach(number -> {
            if(gears.get(number.gearPosition[0] + "," + number.gearPosition[1]) == null)
                gears.put(number.gearPosition[0] + "," + number.gearPosition[1], new ArrayList<>());
            gears.get(number.gearPosition[0] + "," + number.gearPosition[1]).add(Integer.valueOf(number.number));
        }
        );
        Integer totalGears = gears.values().stream().map(list -> {
            if(list.size() == 2)
            return list.get(0) * list.get(1);
            return 0;
        }).reduce(0,Integer::sum);

        System.out.println(totalGears);
    }
}
