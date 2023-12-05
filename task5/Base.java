package task5;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Base {
    public static void main(String[] args) throws IOException{
        List<String> lines =  Files.readAllLines(Path.of("/Users/rickvanberlo/Projects/advent-of-code-2023/task5/input.txt"), StandardCharsets.UTF_8);
        List<Long> seeds = new ArrayList<>();
        Node first = new Node("");
        Node last = first;
        //init
        for(String line : lines){
            if(line.contains("seeds")){
                //solution 1
                //Stream.of(line.split(":")[1].trim().split(" ")).forEach(seed -> seeds.add(Long.valueOf(seed)));
                //solution 2
                seeds = Stream.of(line.split(":")[1].trim().split(" ")).map(seed -> Long.valueOf(seed)).collect(Collectors.toList());
            }
            else if(line.contains("map")){
                if(first.name.equals("")){
                    first.setName(line.split(" ")[0]);
                } else {
                    Node current = new Node(line.split(" ")[0]);
                    last.setNextNode(current);
                    last = current;
                }
            }
            else if(!line.isBlank()){
                last.InitRange(Stream.of(line.trim().split(" ")).map(number -> Long.valueOf(number)).collect(Collectors.toList()));
            }
        }


        //solution 2
        Long smallest = Long.MAX_VALUE;
        Long init = -1l;
        for(Long number: seeds){
            if(init == -1l){
                init = number;
            } else {
                for(Long i = 0l; i < number; i++){
                    Long tmp = first.getLocation(i + init);
                    if(tmp < smallest)
                    smallest = tmp;
                }
                init = -1l;
            }
        }
        System.out.println(smallest);

        //solution 1
        //System.out.println(seeds.stream().map(seed -> first.getLocation(seed)).mapToLong(Long::longValue).min());
    }
}

