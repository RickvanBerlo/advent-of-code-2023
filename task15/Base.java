package task15;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import java.nio.charset.*;
import java.io.*;

public class Base {
    public static List<String> iteration = new ArrayList<>();

    //solution 1
    // public static void main(String[] args) throws IOException{
    //     List<String> lines =  Files.readAllLines(Path.of("/Users/rickvanberlo/Projects/advent-of-code-2023/task15/input.txt"), StandardCharsets.UTF_8);

    //     Integer counter = 0;

    //     String[] array = lines.get(0).split(",");
    //     List<Integer> outcomes = new ArrayList<>();

    //     for(int i = 0; i < array.length; i++){
    //         counter = 0;
    //         char[] values = array[i].toCharArray();
    //         for(int j = 0; j < values.length; j++){
    //             counter += (int)(values[j]);
    //             counter = (counter * 17);
    //             counter = (counter % 256);
    //         }
    //         outcomes.add(counter);
    //     }

    //     System.out.println(outcomes.stream().mapToInt(v -> v).sum());
       
    // }

    //solution 2
    public static void main(String[] args) throws IOException{
        List<String> lines =  Files.readAllLines(Path.of("/Users/rickvanberlo/Projects/advent-of-code-2023/task15/input.txt"), StandardCharsets.UTF_8);

        Box[] boxes = new Box[256];
        for(int i = 0; i< boxes.length; i++){
            boxes[i] = new Box();
        }

        String[] array = lines.get(0).split(",");

        for(int i = 0; i < array.length; i++){
            if(array[i].contains("=")){
                String[] values = array[i].split("=");
                Integer number = stringToAscii(values[0]);
                Integer focalLength = Integer.valueOf(values[1]);

                if(boxes[number].lenses.stream().filter(lens -> lens.label.equals(values[0])).count() > 0){
                    List<Lens> tmp = boxes[number].lenses.stream().map(lens -> {
                        if(lens.label.equals(values[0])){
                            lens.vocalLength = focalLength;
                        }
                        return lens;
                    }).collect(Collectors.toList());

                    boxes[number].lenses = tmp;
                } else {
                    boxes[number].lenses.add(new Lens(values[0], focalLength));
                }
            } else if(array[i].contains("-")) {
                String label = array[i].substring(0, array[i].length() - 1);
                Integer number = stringToAscii(label);

                List<Lens> tmp = boxes[number].lenses.stream().filter(lens -> !lens.label.equals(label)).collect(Collectors.toList());
                boxes[number].lenses = tmp;
            }
        }

        Long counter = 0l;

        for(int i =0; i< boxes.length; i++){
            for(int j =0; j < boxes[i].lenses.size(); j++){
                System.out.println("checking box: " + i+ " position: " + j);
                System.out.println(boxes[i].lenses.get(j).label + " with vocal: "+ boxes[i].lenses.get(j).vocalLength +" gets value: " + (i + 1) * (j+ 1) * boxes[i].lenses.get(j).vocalLength);
                counter += (i + 1) * (j + 1) * boxes[i].lenses.get(j).vocalLength;
            }
        }

        System.out.println(counter);
       
    }

    public static int stringToAscii(String value){
        int counter = 0;
        char[] values = value.toCharArray();
        for(int j = 0; j < values.length; j++){
            counter += (int)(values[j]);
            counter = (counter * 17);
            counter = (counter % 256);
        }
        return counter;

    }

}

