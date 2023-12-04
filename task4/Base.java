package task4;

import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.nio.charset.*;
import java.io.*;

public class Base {
    // public static void main(String[] args) throws IOException{
    //     List<String> lines =  Files.readAllLines(Path.of("/Users/rickvanberlo/Projects/advent-of-code-2023/task4/input.txt"), StandardCharsets.UTF_8);

    //     int score = 0;

    //     for(String line : lines){
    //         int cardScore = 0;
    //         String[] devide = line.split("\\|");
    //         List<String> myNumbers = Arrays.asList(devide[1].trim().replace("  ", " ").split(" "));
    //         String[] winningNumbers = devide[0].split(":")[1].trim().replace("  ", " ").split(" ");

    //         for(String winningNumber : winningNumbers){
    //             if(myNumbers.contains(winningNumber)){
    //                 if(cardScore == 0) cardScore++;
    //                 else cardScore += cardScore;
    //             }
    //         }

    //         score += cardScore;
    //     }

    //     System.out.println(score);
        
    // }

    public static void main(String[] args) throws IOException{
        List<String> lines =  Files.readAllLines(Path.of("/Users/rickvanberlo/Projects/advent-of-code-2023/task4/input.txt"), StandardCharsets.UTF_8);
        int magic = 196;
        int[] cards = new int[magic];

        for(int i = 0 ; i < magic; i++ ){
            cards[i] = 1;
        }

        for(String line : lines){
            int matches = 0;
            String[] devide = line.split("\\|");
            List<String> myNumbers = Arrays.asList(devide[1].trim().replace("  ", " ").split(" "));
            String[] winningNumbers = devide[0].split(":")[1].trim().replace("  ", " ").split(" ");
            int cardNumber = Integer.valueOf(devide[0].split(":")[0].replace("   ", ",").replace("  ", ",").replace(" ", ",").split(",")[1]);

            for(String winningNumber : winningNumbers){
                if(myNumbers.contains(winningNumber)){
                    matches += 1;
                }
            }

            for(int i = 0; i < cards[cardNumber - 1]; i++){
                for(int j = cardNumber; j < (cardNumber + matches); j++){
                    if(j < cards.length)
                    cards[j] += 1;
                }
            }

        }

        System.out.println(cards);
        System.out.println(IntStream.of(cards).reduce(0, Integer::sum));
        
    }
}

