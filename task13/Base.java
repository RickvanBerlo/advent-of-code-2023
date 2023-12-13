package task13;

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

    public static void main(String[] args) throws IOException{
        List<String> lines =  Files.readAllLines(Path.of("/Users/rickvanberlo/Projects/advent-of-code-2023/task13/input.txt"), StandardCharsets.UTF_8);
        List<List<String>> maps = new ArrayList<>();
        Long count = 0l;

        //separate maps;
        List<String> tmpMap = new ArrayList<>();
        for(String line : lines){
            if(line.isBlank() || line.isEmpty()){
                maps.add(tmpMap);
                tmpMap = new ArrayList<>();
            } else {
                tmpMap.add(line);
            }
        }

        for(List<String> map: maps){
            System.out.println("new itteration");
            Map<String, List<Integer>> verticalLine = new HashMap<>();
            Map<String, List<Integer>> horizontalLine = new HashMap<>();
            //calculate Horizontal
            for(int i = 0; i < map.size(); i++){
                if(horizontalLine.containsKey(map.get(i)))  {
                    horizontalLine.get(map.get(i)).add(i);
                } else {
                    horizontalLine.put(map.get(i), new ArrayList<Integer>(Arrays.asList(i)));
                }
            }
            Optional<Entry<String, List<Integer>>> reflection = findRefelection(horizontalLine, map.size() -1);
            if(reflection.isPresent()){
                Integer max = reflection.get().getValue().stream().mapToInt(v -> v).max().getAsInt();
                if(reflection.get().getValue().size() > 2){
                    for(int k = 0; k< reflection.get().getValue().size(); k++){
                        for(int p = 0; p < reflection.get().getValue().size(); p++){
                            Integer tmp = reflection.get().getValue().get(k) - reflection.get().getValue().get(p);
                            if(tmp == 1){
                                max = reflection.get().getValue().get(k);
                            }
                        }
                    }   
                }
                System.out.println("found the hoizontal line! they where line:");
                System.out.println(reflection.get().getKey());
                System.out.println("on line: " + (max-1) +","+ max);
                System.out.println("points are: " + max * 100);
                System.out.println(" ");
                count += max * 100;
            } else {
                //calculate vertical + count
                for(int j = 0; j < map.get(0).length(); j++){
                    char[] line = new char[map.size() -1];
                    for(int k = 0; k < map.size() -1; k++){
                        line[k] = map.get(k).charAt(j);
                    }
                    String key = String.valueOf(line);
                    if(verticalLine.containsKey(key)){
                        verticalLine.get(key).add(j);
                    }else{
                        verticalLine.put(key, new ArrayList<Integer>(Arrays.asList(j)));
                    }
                }

                reflection = findRefelection(verticalLine, map.get(0).length() -1);
                Integer max = reflection.get().getValue().stream().mapToInt(v -> v).max().getAsInt();
                if(reflection.get().getValue().size() > 2){
                    for(int k = 0; k< reflection.get().getValue().size(); k++){
                        for(int p = 0; p < reflection.get().getValue().size(); p++){
                            Integer tmp = reflection.get().getValue().get(k) - reflection.get().getValue().get(p);
                            if(tmp == 1){
                                max = reflection.get().getValue().get(k);
                            }
                        }
                    }   
                }
                System.out.println("found the vertical line! they where line:");
                System.out.println(reflection.get().getKey());
                System.out.println("on line: " + (max-1) +","+ max);
                System.out.println("points are: " + max);
                System.out.println(" ");
                count += max;
            }
        }

        System.out.println(count);
    }

    private static Optional<Entry<String,List<Integer>>> findRefelection(Map<String, List<Integer>> lines, Integer outer){
        return lines.entrySet().stream().filter(entry -> entry.getValue().size() > 1).filter(entry -> {
            for(int k = 0; k< entry.getValue().size(); k++){
                if(entry.getValue().contains(entry.getValue().get(k) - 1) || entry.getValue().contains(entry.getValue().get(k) + 1)){
                    return true;
                }
            }
            return false;
        }).filter(entry -> {
            Integer max = entry.getValue().stream().mapToInt(v-> v).max().getAsInt();
            Integer min = entry.getValue().stream().mapToInt(v-> v).min().getAsInt();
            Integer maxLength = outer;
            Integer minLength = 0;

            if(entry.getValue().size() > 2){
                for(int k = 0; k< entry.getValue().size(); k++){
                    for(int p = 0; p < entry.getValue().size(); p++){
                        Integer tmp = entry.getValue().get(k) - entry.getValue().get(p);
                        if(tmp == 1){
                            max = entry.getValue().get(k);
                        } else if (tmp == -1){
                            min = entry.getValue().get(k);
                        }
                    }
                }   
            }

            while(true){
                if(min == minLength){
                    return true;
                } else if(max == maxLength){
                    return true;
                }
                max += 1;
                min -= 1;
                boolean found = false;
                Iterator<List<Integer>> iterator = lines.values().iterator();
                while(iterator.hasNext()){
                    List<Integer> tmp = iterator.next();
                    if(tmp.contains(max) && tmp.contains(min)){
                        found = true;
                    }
                }
                if(!found){
                    return false;
                }
            }

        }).findFirst();
    }

}

