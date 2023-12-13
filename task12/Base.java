package task12;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.nio.charset.*;
import java.io.*;

public class Base {

    public static Map<String, Integer> counts = new HashMap<>();

    public static void main(String[] args) throws IOException{
        List<String> lines =  Files.readAllLines(Path.of("/Users/rickvanberlo/Projects/advent-of-code-2023/task12/test.txt"), StandardCharsets.UTF_8);

        for(String line : lines){
            String[] split = line.split(" ");
            regex(Stream.of(split[1].split(",")).mapToInt(v -> Integer.valueOf(v)).toArray(), split[0].toCharArray());
        }

        System.out.println(counts.values().stream().mapToInt(v -> v).sum());
    }

    public static void regex(int[] sequence, char[] input){

        //only for solution 2
        input = (String.valueOf(input) + "?" + String.valueOf(input) + "?"+ String.valueOf(input) +"?"+ String.valueOf(input) +"?"+ String.valueOf(input)).toCharArray();
        sequence = Stream.concat(Stream.concat(Stream.concat(Stream.concat(Arrays.stream(sequence).boxed(), Arrays.stream(sequence).boxed()),Arrays.stream(sequence).boxed()), Arrays.stream(sequence).boxed()),Arrays.stream(sequence).boxed()).mapToInt(v -> v).toArray();

        StringBuilder regexBuilder = new StringBuilder();
        regexBuilder.append("^\\.*");
        for(int i = 0; i < sequence.length; i++){
            regexBuilder.append("#{" + sequence[i] + "}");
            if(i != sequence.length - 1){
                regexBuilder.append("\\.+");
            }
        }
        regexBuilder.append("\\.*$");
        String regex = regexBuilder.toString();
        calculate(input, 0, regex, String.valueOf(input));
    }

    public static void calculate(char str[], int index, String regex, String original)
    {
        if (index == str.length)
        {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(String.valueOf(str));
            if(matcher.find()){
                if(counts.containsKey(original)){
                    counts.replace(original, counts.get(original) + 1);
                } else {
                    counts.put(original, 1);
                }

            }
            return;
        }
 
        if (str[index] == '?')
        {
            // replace '?' by '0' and recurse
            str[index] = '.';
            calculate(str, index + 1,regex, original);
             
            // replace '?' by '1' and recurse
            str[index] = '#';
            calculate(str, index + 1, regex, original);
             
            // NOTE: Need to backtrack as string
            // is passed by reference to the
            // function
            str[index] = '?';
        }
        else
            calculate(str, index + 1, regex, original);
    }

    //regex
    // ^\.*#{2}\.*#{3}\.*#{2}\.*$

    // public static Set<String> getAllPossibilities(char[] input){
    //     Set<String> posibilities = new HashSet<>();

    //     for(int i = 0; i < input.length; i++){
    //         for(int j = i + 1; j < input.length; j++){

    //         }
    //     }


    // }
}

