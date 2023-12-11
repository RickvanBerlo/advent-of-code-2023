package task9;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.nio.charset.*;
import java.io.*;

public class Base {
    public static void main(String[] args) throws IOException{
        List<String> lines =  Files.readAllLines(Path.of("/Users/rickvanberlo/Projects/advent-of-code-2023/task9/input.txt"), StandardCharsets.UTF_8);
    
        List<List<Long>> inputs = new ArrayList<>();
        List<Long> output = new ArrayList<>();

        for(String line: lines){
            inputs.add(Stream.of(line.split(" ")).map(number -> Long.valueOf(number)).collect(Collectors.toList()));
        }

        Iterator<List<Long>> iterator = inputs.iterator();

        while(iterator.hasNext()){
            boolean foundZero = false;
            List<List<Long>> tmps = new ArrayList<>();
            tmps.add(iterator.next());

            while(!foundZero){
                List<Long> tmp = differences(tmps.get(tmps.size() - 1));
                if(tmp.stream().filter(number -> number == 0l).count() == tmp.size()){
                    foundZero = true;
                } else {
                    tmps.add(tmp);
                }
            }
            Collections.reverse(tmps);

            Iterator<List<Long>> iterator2 = tmps.iterator();
            Long increment = 0l;
            while(iterator2.hasNext()){
                List<Long> input2 = iterator2.next();
                if(input2.stream().filter(number -> number.equals(input2.get(0))).count() == input2.size()){
                    increment = input2.get(0);
                } else {
                    increment = input2.get(0) - increment;
                }
            }

            output.add(increment);
        }

        System.out.println(output.stream().reduce(0l, Long::sum));
    }

    public static List<Long> differences(List<Long> input){
        List<Long> output = new ArrayList<>();
        for(int i = 1; i < input.size(); i++){
            output.add((input.get(i) - input.get(i - 1)));
        }
        return output;
    }

    //solution 1
    // public static void main(String[] args) throws IOException{
    //     List<String> lines =  Files.readAllLines(Path.of("/Users/rickvanberlo/Projects/advent-of-code-2023/task9/input.txt"), StandardCharsets.UTF_8);
    
    //     List<List<Long>> inputs = new ArrayList<>();
    //     List<Long> output = new ArrayList<>();

    //     for(String line: lines){
    //         inputs.add(Stream.of(line.split(" ")).map(number -> Long.valueOf(number)).collect(Collectors.toList()));
    //     }

    //     Iterator<List<Long>> iterator = inputs.iterator();

    //     while(iterator.hasNext()){
    //         boolean foundZero = false;
    //         List<List<Long>> tmps = new ArrayList<>();
    //         tmps.add(iterator.next());

    //         while(!foundZero){
    //             List<Long> tmp = differences(tmps.get(tmps.size() - 1));
    //             if(tmp.stream().filter(number -> number == 0l).count() == tmp.size()){
    //                 foundZero = true;
    //             } else {
    //                 tmps.add(tmp);
    //             }
    //         }
    //         Collections.reverse(tmps);

    //         Iterator<List<Long>> iterator2 = tmps.iterator();
    //         Long increment = 0l;
    //         while(iterator2.hasNext()){
    //             List<Long> input2 = iterator2.next();
    //             if(input2.stream().filter(number -> number.equals(input2.get(0))).count() == input2.size()){
    //                 increment = input2.get(0);
    //             } else {
    //                 increment += input2.get(input2.size() -1);
    //             }
    //         }

    //         output.add(increment);
    //     }

    //     System.out.println(output.stream().reduce(0l, Long::sum));
    // }

    // public static List<Long> differences(List<Long> input){
    //     List<Long> output = new ArrayList<>();
    //     for(int i = 1; i < input.size(); i++){
    //         output.add((input.get(i) - input.get(i - 1)));
    //     }
    //     return output;
    // }
}

