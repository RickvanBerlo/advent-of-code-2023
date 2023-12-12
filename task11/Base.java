package task11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.charset.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Base {
    //solution2
    // this can also solve task 1. you only need to change the multiplier
    public static void main(String[] args) throws IOException{
        List<String> lines =  Files.readAllLines(Path.of("/Users/rickvanberlo/Projects/advent-of-code-2023/task11/input.txt"), StandardCharsets.UTF_8);
    
       List<List<Node>> map = new ArrayList<>();

       List<Node> universes = new ArrayList<>();
       Map<UUID, Route> routes = new HashMap<>();

       List<Integer> universesX = new ArrayList<>();
       List<Integer> universesY = new ArrayList<>();

       for(int i = 0; i < lines.size(); i++){
            char[] spaces = lines.get(i).toCharArray();
            List<Node> row = new ArrayList<>();
            for(int j = 0; j < spaces.length ; j++){
                if(spaces[j] == '#'){
                    row.add(new Node(true));
                    if(!universesY.contains(i)){
                        universesY.add(i);
                    }
                    if(!universesX.contains(j)){
                        universesX.add(j);
                    }
                } else {
                    row.add(new Node(false));
                }
            }
            map.add(row);
       }

       Long multiplierY = 0l;
       for(int i = 0; i < map.size(); i++){
            Long multiplierX= 0l;
            if(!universesY.contains(i)){
                multiplierY += 999999;
            }
            for(int j = 0; j < map.get(i).size(); j++){
                if(!universesX.contains(j)){
                    multiplierX += 999999;
                }
                if(map.get(i).get(j).universe){
                    map.get(i).get(j).setMultiplier(multiplierY, multiplierX);
                }
            }
        }

       //store positions in node
       for(int i = 0; i < map.size(); i++){
            for(int j = 0; j < map.get(i).size(); j++){
                Node node = map.get(i).get(j);
                node.setPosition(i,j);
                if(node.universe){
                    universes.add(node);
                }
            }
        }

        //caluclate route for every universe
        universes.forEach(universeA -> {
            System.out.println("check universe:" + universeA.x + " " + universeA.y);
            universes.forEach(universeB -> {
                if(!universeA.id.equals(universeB.id)){
                    Integer count = universeA.routes.size();
                    routes.forEach((key, value) -> {
                        if(universeA.id.equals(value.locationA.id) && universeB.id.equals(value.locationB.id)){
                            universeA.addRoute(value);
                        } 
                        if(universeB.id.equals(value.locationA.id) && universeA.id.equals(value.locationB.id)){
                            universeA.addRoute(value);
                        } 
                    });
                    if(count == universeA.routes.size()){
                        UUID idRoute = UUID.randomUUID();
                        routes.put(idRoute, new Route(idRoute, universeA, universeB, universesY, universesX));
                        universeA.addRoute(routes.get(idRoute));
                    }
                }
            });
        });

        System.out.println(routes.values().stream().map(route -> route.steps).mapToLong(v -> v).sum());
    }

    //solution 1
    // public static void main(String[] args) throws IOException{
    //     List<String> lines =  Files.readAllLines(Path.of("/Users/rickvanberlo/Projects/advent-of-code-2023/task11/input.txt"), StandardCharsets.UTF_8);
    
    //    List<List<Node>> map = new ArrayList<>();

    //    List<Node> universes = new ArrayList<>();
    //    Map<UUID, Route> routes = new HashMap<>();

    //    List<Integer> universesX = new ArrayList<>();
    //    List<Integer> universesY = new ArrayList<>();

    //    for(int i = 0; i < lines.size(); i++){
    //         char[] spaces = lines.get(i).toCharArray();
    //         List<Node> row = new ArrayList<>();
    //         for(int j = 0; j < spaces.length ; j++){
    //             if(spaces[j] == '#'){
    //                 row.add(new Node(true));
    //                 if(!universesY.contains(i)){
    //                     universesY.add(i);
    //                 }
    //                 if(!universesX.contains(j)){
    //                     universesX.add(j);
    //                 }
    //             } else {
    //                 row.add(new Node(false));
    //             }
    //         }
    //         map.add(row);
    //    }
    //    //add horizontal rows
    //    for(int i = map.size() - 1; i > 0; i--){
    //         if(!universesY.contains(i)){
    //             map.add(i, new ArrayList<>(map.get(i)));
    //         }
    //    }

    //    //add vertical colums
    //    for(int i = 0; i < map.size(); i++){
    //         for(int j = map.get(i).size() - 1; j > 0; j--){
    //             if(!universesX.contains(j)){
    //                 map.get(i).add(j, new Node(false));
    //             }
    //         }
    //    }

    //    //store positions in node
    //    for(int i = 0; i < map.size(); i++){
    //         for(int j = 0; j < map.get(i).size(); j++){
    //             Node node = map.get(i).get(j);
    //             node.setPosition(i,j);
    //             if(node.universe){
    //                 universes.add(node);
    //             }
    //         }
    //     }

    //     //caluclate route for every universe
    //     universes.forEach(universeA -> {
    //         System.out.println("check universe:" + universeA.x + " " + universeA.y);
    //         universes.forEach(universeB -> {
    //             Integer count = universeA.routes.size();
    //             routes.forEach((key, value) -> {
    //                 if(universeA.id.equals(value.locationA.id) && universeB.id.equals(value.locationB.id)){
    //                     universeA.addRoute(value);
    //                 } 
    //                 if(universeB.id.equals(value.locationA.id) && universeA.id.equals(value.locationB.id)){
    //                     universeA.addRoute(value);
    //                 } 
    //             });
    //             if(count == universeA.routes.size()){
    //                 UUID idRoute = UUID.randomUUID();
    //                 routes.put(idRoute, new Route(idRoute, universeA, universeB));
    //                 universeA.addRoute(routes.get(idRoute));
    //             }
    //         });
    //     });

    //     System.out.println(routes.values().stream().map(route -> route.steps).mapToInt(v -> v).sum());
    // }
}

