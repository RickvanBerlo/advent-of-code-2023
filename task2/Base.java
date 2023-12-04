package task2;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.nio.charset.*;
import java.io.*;

public class Base {
    // public static void main(String[] args) throws IOException{
    //     List<String> lines =  Files.readAllLines(Path.of("/Users/rickvanberlo/Projects/advent-of-code-2023/task2/input.txt"), StandardCharsets.UTF_8);
        
    //     List<Integer> games = new ArrayList<>();

    //     Map<String, Integer> cubesInGame = new HashMap<>(){{
    //         put("green", 13);
    //         put("blue", 14);
    //         put("red", 12);
    //     }};

    //     for(String line : lines){
    //         boolean possible = true;
    //         System.out.println(line);

    //         String[] game = line.split(":");

    //         String[] rounds = game[1].trim().split(";");

    //         for(String round : rounds){
    //             String[] draws = round.trim().split(",");

    //             for(String draw : draws){
    //                 String[] input = draw.trim().split(" ");
    //                 if(Integer.valueOf(input[0]) > cubesInGame.get(input[1])){
    //                     possible = false;
    //                 }
    //             }
    //         }

    //         if(possible)
    //         games.add(Integer.valueOf(game[0].trim().split(" ")[1]));

    //     }

    //     System.out.println(games.stream().mapToInt(Integer::intValue).sum());

    // }

    public static void main(String[] args) throws IOException{
        List<String> lines =  Files.readAllLines(Path.of("/Users/rickvanberlo/Projects/advent-of-code-2023/task2/input.txt"), StandardCharsets.UTF_8);
        
        List<Integer> games = new ArrayList<>();

        for(String line : lines){
            int[] maxDice = {0,0,0};
            System.out.println(line);

            String[] game = line.split(":");

            String[] rounds = game[1].trim().split(";");

            for(String round : rounds){
                String[] draws = round.trim().split(",");

                for(String draw : draws){
                    String[] input = draw.trim().split(" ");
                    switch(input[1]){
                        case "red":
                        if(Integer.valueOf(input[0]) > maxDice[0])
                        maxDice[0] = Integer.valueOf(input[0]);
                        break;
                        case "blue":
                        if(Integer.valueOf(input[0]) > maxDice[1])
                        maxDice[1] = Integer.valueOf(input[0]);
                        break;
                        case "green":
                        if(Integer.valueOf(input[0]) > maxDice[2])
                        maxDice[2] = Integer.valueOf(input[0]);
                        break;
                    }
                }
            }
            games.add(IntStream.of(maxDice).reduce(1, (a, b) -> a * b));

        }

        System.out.println(games.stream().mapToInt(Integer::intValue).sum());

    }
}
