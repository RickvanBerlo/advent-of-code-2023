package task8;

import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.nio.charset.*;
import java.io.*;

public class Base {
    public static void main(String[] args) throws IOException{
        List<String> lines =  Files.readAllLines(Path.of("/Users/rickvanberlo/Projects/advent-of-code-2023/task8/input.txt"), StandardCharsets.UTF_8);
        Map<String, Node> map = new HashMap<>();
        char[] directions = new char[0];
        Long steps = 0l;
        Boolean found = false;

        boolean t = true;
        for(String line : lines){
            if(line.isEmpty()){
                t = false;
            } else if(t){
                directions = line.toCharArray();
            } else {
                String[] devide = line.split("=");

                String name = devide[0].trim();
                String[] choices = devide[1].replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\s", "").split(",");
                map.put(name, new Node(choices[0],choices[1]));
            }
        }

        List<String> firsts = map.keySet().stream().filter(key -> key.endsWith("A")).collect(Collectors.toList());
        Long[] loop = new Long[] {0l,0l,0l,0l,0l,0l};

        while(!found){
            Long u = steps % directions.length;
            char direction = directions[u.intValue()];

            for(int i = 0; i < firsts.size(); i++){
                Node tmp = map.get(firsts.get(i));
                firsts.set(i, tmp.decide(direction));
            }

            if(firsts.stream().filter(key -> key.endsWith("Z")).count() == firsts.size()){
                found = true;
            }

            //solution 1
            // first = tmp.decide(direction);

            // if(first.equals("ZZZ")){
            //     found = true;
            // }
            if(firsts.stream().filter(key -> key.endsWith("Z")).count() > 0){
                //System.out.println(steps + " " + firsts.stream().filter(key -> key.endsWith("Z")).count());

                for(int i= 0; i < firsts.size(); i++){
                    if(firsts.get(i).endsWith("Z")){
                        System.out.println("loop " + i + " has found Z within: " + loop[i] + " steps");
                        loop[i] = 0l;
                    }
                }
                
            }
            steps++;

            for(int i = 0; i< loop.length; i++){
                Long j = loop[i] + 1l;
                loop[i] = j;
            }

        }

        System.out.println(steps);
    }
}

