package task10;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.nio.charset.*;
import java.io.*;

public class Base {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLACK = "\u001B[30m";
    
    public static void main(String[] args) throws IOException{
        List<String> lines =  Files.readAllLines(Path.of("/Users/rickvanberlo/Projects/advent-of-code-2023/task10/input.txt"), StandardCharsets.UTF_8);
        Node[][] nodeMap = new Node[140][140];
        Node start = null;

        for(int i = 0; i < lines.size(); i++){
            char[] row = lines.get(i).toCharArray();
            for(int j = 0; j < row.length; j++){
                if(row[j] == 'S'){
                    start = new Node(row[j], i, j);
                    nodeMap[i][j] = start;
                } else{
                    nodeMap[i][j] = new Node(row[j], i , j);
                }
            }
        }

        for(Node[] row : nodeMap){
            for(Node node : row){
                node.exploreNeighbors(nodeMap);
            }
        }   

        //solution 1
        System.out.println(start.getDistance(0, null)/2);

        //solution 2
        for(int i = 0; i < lines.size(); i++){
            for(int j = 0; j < nodeMap[i].length; j++){
                Node node = nodeMap[i][j];
                node.checkIfInsideLoop(nodeMap);
                if(node.loop){
                    System.out.print(ANSI_GREEN + node.pipe.asChar() + ANSI_RESET);
                } else if(node.outside == false){
                    System.out.print(ANSI_RED + node.pipe.asChar() + ANSI_RESET);
                } else {
                    System.out.print(ANSI_BLACK + node.pipe.asChar() + ANSI_RESET);
                }
            }
            System.out.println("");
        }
    }
}

