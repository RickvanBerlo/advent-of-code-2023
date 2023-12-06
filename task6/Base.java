package task6;

import java.nio.file.*;
import java.util.List;
import java.util.ArrayList;
import java.nio.charset.*;
import java.io.*;
import java.util.stream.*;

public class Base {
    public static void main(String[] args) throws IOException{
        List<String> lines =  Files.readAllLines(Path.of("/Users/rickvanberlo/Projects/advent-of-code-2023/task6/input.txt"), StandardCharsets.UTF_8);
        List<Long> times = new ArrayList<Long>();
        List<Long> distances = new ArrayList<Long>();
        List<Long> succes = new ArrayList<>();

        for(String line : lines){
            if(line.contains("Time")){
                //solution 2
                times = Stream.of(line.split(":")[1].trim().replaceAll("\\s", "").split(",")).map(stringNumber -> Long.valueOf(stringNumber)).collect(Collectors.toList());
                //solution 1
                //times = Stream.of(line.split(":")[1].trim().replaceAll("\\s+", ",").split(",")).map(stringNumber -> Long.valueOf(stringNumber)).collect(Collectors.toList());
            }else if(line.contains("Distance")){
                //solution 1
                //distances = Stream.of(line.split(":")[1].trim().replaceAll("\\s+", ",").split(",")).map(stringNumber -> Long.valueOf(stringNumber)).collect(Collectors.toList());
                //solution 2
                distances = Stream.of(line.split(":")[1].trim().replaceAll("\\s", "").split(",")).map(stringNumber -> Long.valueOf(stringNumber)).collect(Collectors.toList());
            }
        }
        
        for(int i = 0; i < times.size(); i++){
            Long time = times.get(i);
            Long distance = distances.get(i);
            Long succeses = 0l;

            for(int j = 0; j < time; j++){
                Long timeRemaining = time - j;
                Long distanceForTimeRemaining = timeRemaining * j;
                if(distanceForTimeRemaining > distance){
                    succeses += 1;
                }
            }

            succes.add(succeses);
        }

        System.out.println(succes.stream().reduce(1l, (a, b) -> a * b));
    }
}

