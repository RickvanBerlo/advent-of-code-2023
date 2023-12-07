package task7;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.nio.charset.*;
import java.io.*;

public class Base {
    public static void main(String[] args) throws IOException{
        List<String> lines =  Files.readAllLines(Path.of("/Users/rickvanberlo/Projects/advent-of-code-2023/task7/input.txt"), StandardCharsets.UTF_8);
        //solution 2
        List<Character> strength = new ArrayList<>(Arrays.asList('J','2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A'));
        //solution 1
        // List<Character> strength = new ArrayList<>(Arrays.asList('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'));
        List<Hand> rank = new ArrayList<>();

        for(String line: lines){
            String[] devide = line.split(" ");
            Integer value = Integer.valueOf(devide[1]);
            char[] cards = devide[0].toCharArray();
            Map<String, Long> counted = devide[0].chars().mapToObj(i -> (char) i).collect(Collectors.groupingBy(Object::toString, Collectors.counting()));

            Hand currentHand = new Hand(cards,0, value);

            counted.forEach((card, amount) -> {
                //solution 2
                if(!card.equals("J")){
                    switch(amount.intValue()){
                        case 2:
                        if(currentHand.type == 1){
                            currentHand.type = 2;
                        } else if(currentHand.type == 0){
                            currentHand.type = 1;
                        } else if(currentHand.type == 3){
                            currentHand.type = 4;
                        }
                        break;
                        case 3:
                        if(currentHand.type == 1){
                            currentHand.type = 4;
                        } else if(currentHand.type < 3) {
                            currentHand.type = 3;
                        }
                        break;
                        case 4:
                        if(currentHand.type < 5)
                        currentHand.type = 5;
                        break;
                        case 5:
                        if(currentHand.type < 6)
                        currentHand.type = 6;
                        break;
                    }
                }

                // solution 1
                //     switch(amount.intValue()){
                //         case 2:
                //         if(currentHand.type == 1){
                //             currentHand.type = 2;
                //         } else if(currentHand.type == 0){
                //             currentHand.type = 1;
                //         } else if(currentHand.type == 3){
                //             currentHand.type = 4;
                //         }
                //         break;
                //         case 3:
                //         if(currentHand.type == 1){
                //             currentHand.type = 4;
                //         } else {
                //             currentHand.type = 3;
                //         }
                //         break;
                //         case 4:
                //         currentHand.type = 5;
                //         break;
                //         case 5:
                //         currentHand.type = 6;
                //         break;
                //     }
            });

            //solution 2
            switch(counted.getOrDefault("J", 0l).intValue()){
                case 1:
                if(currentHand.type == 5){
                    currentHand.type = 6;
                } else if (currentHand.type == 3){
                    currentHand.type = 5;
                } else if( currentHand.type == 2){
                    currentHand.type = 4;
                } else if( currentHand.type == 1){
                    currentHand.type = 3;
                } else if(currentHand.type == 0){
                    currentHand.type = 1;
                }
                break;
                case 2:
                if(currentHand.type == 3){
                    currentHand.type = 6;
                } else if (currentHand.type == 1){
                    currentHand.type = 5;
                } else if (currentHand.type == 0){
                    currentHand.type = 3;
                }
                break;
                case 3:
                if(currentHand.type == 1){
                    currentHand.type = 6;
                } else {
                    currentHand.type = 5;
                }
                break;
                case 4:
                currentHand.type = 6;
                break;
                case 5:
                currentHand.type = 6;
                break;
            }
            
            //both
            if(rank.isEmpty()){
                rank.add(currentHand);
            } else {
                boolean isAdded = false;
                for(int i = 0; i< rank.size(); i++){
                    Hand hand = rank.get(i);
                    if(currentHand.isStronger(hand, strength)){
                        rank.add(i, currentHand);
                        isAdded = true;
                        break;
                    }
                }
                if(!isAdded){
                    rank.add(currentHand);
                }
            }

        }
        Collections.reverse(rank);

        Long winnings = 0l;
        for(int i = 0; i < rank.size(); i++){
            System.out.println(String.valueOf(rank.get(i).cards) + " " + rank.get(i).type);
            winnings += rank.get(i).value * (i + 1);
        }

        System.out.println(winnings);
        
    }
}

