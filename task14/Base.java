package task14;

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

import task14.Node;

import java.nio.charset.*;
import java.io.*;

public class Base {
    public static List<String> iteration = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        List<String> lines =  Files.readAllLines(Path.of("/Users/rickvanberlo/Projects/advent-of-code-2023/task14/input.txt"), StandardCharsets.UTF_8);
        Node[][] map = createMap(lines);
        Long totalWeight = 0l;
        boolean foundLoop = false;
        Long counter = 0l;

        List<String> loop = new ArrayList<String>();

        while(!foundLoop){
            map = tilledNorth(map);
            map = tilledWest(map);
            map = tilledSouth(map);
            map = tilledEast(map);

            String unique = getMapInString(map);
            if(iteration.contains(unique)){
                if(loop.isEmpty()){
                    System.out.println(counter);
                    System.out.println("first encounter: " + unique);
                    loop.add(unique);
                } else if(unique.equals(loop.get(0))){
                    System.out.println("found first again! size is:" + loop.size());
                    foundLoop = true;
                } else {
                    loop.add(unique);
                }
            }else {
                iteration.add(unique);
                counter++;
            }
        }

        // Long cycles = 1000000000l;

        // Long place =  (cycles % loop.size() - 6);

        // System.out.println(place);

        for(int b = 0; b< loop.size(); b++){
            map = createMap(List.of(loop.get(b).split(",")));


            for(int i = 0; i< map.length; i++){
                for(int j = 0; j < map[i].length; j++){
                    if(map[i][j] != null && map[i][j].rock && !map[i][j].square){
                        totalWeight += (map.length - i);
                    }
                }
            }

            System.out.println("totalWeight: " + totalWeight);
            totalWeight = 0l;
        }
    }

    public static String getMapInString(Node[][] map){
        StringBuilder builder = new StringBuilder();
         for(int i = 0; i< map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] != null && map[i][j].rock && !map[i][j].square){
                    builder.append("O");
                }
                else if(map[i][j] != null && map[i][j].rock && map[i][j].square){
                    builder.append("#");
                } else {
                    builder.append(".");
                } 
            }
            builder.append(",");
        }
        return builder.toString();
    }

    public static Node[][] createMap(List<String> lines){
        Integer size = lines.get(0).length();
        Node[][] map = new Node[size][size];
        for(int i = 0; i< lines.size(); i++){
            for(int j = 0; j < lines.get(i).length(); j++){
                if(lines.get(i).charAt(j) == 'O'){
                    map[i][j] = new Node(true, false);
                } else if (lines.get(i).charAt(j) == '#'){
                    map[i][j] = new Node(true, true);
                }
            }
        }
        return map;
    }

    public static void printMap(Node[][] map){
        for(int i = 0; i< map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] != null && map[i][j].rock && !map[i][j].square){
                    System.out.print("O");
                }
                else if(map[i][j] != null && map[i][j].rock && map[i][j].square){
                    System.out.print("#");
                } else {
                    System.out.print(".");
                } 
            }
            System.out.println();
        }
    }

    public static Node[][] tilledNorth(Node[][] map){
        Node[][] newMap = new Node[map.length][map.length];
        //calculate Tilled North
        for(int i = 0; i< map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] != null && map[i][j].rock && !map[i][j].square){
                    Point placeAfterTilledNorth = map[i][j].calculateTilledNorth(map, new Point(i, j));
                    newMap[placeAfterTilledNorth.y][placeAfterTilledNorth.x] = map[i][j];
                } else if(map[i][j] != null && map[i][j].rock && map[i][j].square){
                    newMap[i][j] = map[i][j];
                }
            }
        }
        return newMap;
    }

    public static Node[][] tilledWest(Node[][] map){
        Node[][] newMap = new Node[map.length][map.length];
        //calculate Tilled North
        for(int i = 0; i< map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] != null && map[i][j].rock && !map[i][j].square){
                    Point placeAfterTilledNorth = map[i][j].calculateTilledWest(map, new Point(i, j));
                    newMap[placeAfterTilledNorth.y][placeAfterTilledNorth.x] = map[i][j];
                } else if(map[i][j] != null && map[i][j].rock && map[i][j].square){
                    newMap[i][j] = map[i][j];
                }
            }
        }
        return newMap;
    }


    public static Node[][] tilledSouth(Node[][] map){
        Node[][] newMap = new Node[map.length][map.length];
        //calculate Tilled North
        for(int i = 0; i< map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] != null && map[i][j].rock && !map[i][j].square){
                    Point placeAfterTilledNorth = map[i][j].calculateTilledSouth(map, new Point(i, j));
                    newMap[placeAfterTilledNorth.y][placeAfterTilledNorth.x] = map[i][j];
                } else if(map[i][j] != null && map[i][j].rock && map[i][j].square){
                    newMap[i][j] = map[i][j];
                }
            }
        }
        return newMap;
    }

    public static Node[][] tilledEast(Node[][] map){
        Node[][] newMap = new Node[map.length][map.length];
        //calculate Tilled North
        for(int i = 0; i< map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] != null && map[i][j].rock && !map[i][j].square){
                    Point placeAfterTilledNorth = map[i][j].calculateTilledEast(map, new Point(i, j));
                    newMap[placeAfterTilledNorth.y][placeAfterTilledNorth.x] = map[i][j];
                } else if(map[i][j] != null && map[i][j].rock && map[i][j].square){
                    newMap[i][j] = map[i][j];
                }
            }
        }
        return newMap;
    }

}

