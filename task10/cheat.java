package task10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class cheat {
     private static char[][] charArray = null;
    private static int numRows = 0;
    private static int numCols = 0;

    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("task10/input.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Read the file line by line to find the maximum length of lines
            List<String> lines = new ArrayList<>();
            String line;
            numCols = 0;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
                numCols = Math.max(numCols, line.length());
            }

            // Calculate the number of rows and columns in the file
            numRows = lines.size();

            // Create a 2D character array with the calculated dimensions
            charArray = new char[numRows][numCols];
            Point startPoint = new Point(0,0);
            // Populate the 2D array with file contents
            for (int i = 0; i < numRows; i++) {
                line = lines.get(i);
                char[] chars = line.toCharArray();
                for (int j = 0; j < numCols; j++) {
                    var c = chars[j];
                    charArray[i][j] = c;
                    if(c == 'S'){
                        startPoint = new Point(j, i);
                    }
                }
            }

            // Close the BufferedReader and FileReader
            bufferedReader.close();
            fileReader.close();

            Boolean isConnectedNorth = canGoNorth(startPoint);
            Boolean isConnectedSouth = canGoSouth(startPoint);
            Boolean isConnectedWest = canGoWest(startPoint);
            Boolean isConnectedEast = canGoEast(startPoint);
            var nextPoint = startPoint;
            var startChar = 'X';
            if(isConnectedNorth && isConnectedSouth){
                startChar = '|';
            }else if(isConnectedWest && isConnectedEast){
                startChar = '-';
            }else if(isConnectedNorth && isConnectedEast){
                startChar = 'L';
            }else if(isConnectedNorth && isConnectedWest){
                startChar = 'J';
            }else if(isConnectedSouth && isConnectedEast){
                startChar = 'F';
            }else if(isConnectedSouth && isConnectedWest){
                startChar = '7';
            }
        
            charArray[startPoint.y][startPoint.x] = startChar;

            var points = FindPath(startPoint);
            var enclosedCells = CalculateEnclosedCells(points);
            System.out.println("Enclosed cells: " + enclosedCells);
        } catch (IOException e) {
            // Handle any potential IO exception
            e.printStackTrace();
        }
    }

    public static Map<Point, Point> FindPath(Point start) {
        Map<Point, Point> pointMap = new HashMap<>();
        var current = start;
        var visitedCells = 0;
        while(true){
            pointMap.put(current, current);
            visitedCells += 1;

            if(isConnectedNorth(current)){
                var pos = current.GetNorth();
                if(pos.equals(start) && visitedCells > 2){
                    break;
                }
                if(!pointMap.containsKey(pos)){
                    current = pos;
                    continue;
                }
            }

            if(isConnectedSouth(current)){
                var pos = current.GetSouth();
                if(pos.equals(start) && visitedCells > 2){
                    break;
                }
                if(!pointMap.containsKey(pos)){
                    current = pos;
                    continue;
                }
            }

            if(isConnectedEast(current)){
                var pos = current.GetEast();
                if(pos.equals(start) && visitedCells > 2){
                    break;
                }
                if(!pointMap.containsKey(pos)){
                    current = pos;
                    continue;
                }
            }

            if(isConnectedWest(current)){
                var pos = current.GetWest();
                if(pos.equals(start) && visitedCells > 2){
                    break;
                }
                if(!pointMap.containsKey(pos)){
                    current = pos;
                    continue;
                }
            }
        }

        return pointMap;
    }

    public static int CalculateEnclosedCells(Map<Point, Point> pointMap){
        var sum = 0;
        for(int i = 0; i < numRows; i++){
            final int currentY = i;
            List<Integer> wallsOnRow = pointMap.entrySet().stream()
                    .filter(entry -> entry.getValue().y == currentY)
                    .sorted(Map.Entry.comparingByKey((p1, p2) -> Integer.compare(p1.x, p2.x)))
                    .map(entry -> entry.getKey().x)
                    .collect(Collectors.toList());

            var enclosed = false;
            for(int j = 0; j < numCols; j++){
                var c = charArray[i][j];
                var isWall = wallsOnRow.contains(j);
                if(isWall && "|JL".contains(String.valueOf(c))){
                    System.out.print("|");
                    enclosed = !enclosed;
                }else if(enclosed && !isWall){
                    System.out.print("X");
                    sum++;
                }else{
                    System.out.print(".");
                }
            }
            System.out.println();
        }

        return sum;
    }

    private static boolean isConnectedNorth(Point point) {
        if(point.y == 0)
            return false;
        if(!"|JL".contains(String.valueOf(charArray[point.y][point.x]))){
            return false;
        }
        return "|7F".contains(String.valueOf(charArray[point.y - 1][point.x]));
    }
    private static boolean isConnectedSouth(Point point) {
        if(point.y + 1 >= numRows)
            return false;

        if(!"|7F".contains(String.valueOf(charArray[point.y][point.x]))){
            return false;
        }
        return "|LJ".contains(String.valueOf(charArray[point.y + 1][point.x]));
    }
    private static boolean isConnectedWest(Point point) {
        if(point.x == 0)
            return false;
        
        if(!"-J7".contains(String.valueOf(charArray[point.y][point.x]))){
            return false;
        }
        return "-LF".contains(String.valueOf(charArray[point.y][point.x - 1]));
    }
    private static boolean isConnectedEast(Point point) {
        if(point.x + 1 >= numCols)
            return false;
        if(!"-LF".contains(String.valueOf(charArray[point.y][point.x]))){
            return false;
        }
        return "-J7".contains(String.valueOf(charArray[point.y][point.x + 1]));
    }

    private static boolean canGoNorth(Point point) {
        if(point.y == 0)
            return false;
        return "|7F".contains(String.valueOf(charArray[point.y - 1][point.x]));
    }
    private static boolean canGoSouth(Point point) {
        if(point.y + 1 >= numRows)
            return false;
        return "|LJ".contains(String.valueOf(charArray[point.y + 1][point.x]));
    }
    private static boolean canGoWest(Point point) {
        if(point.x == 0)
            return false;
        return "-LF".contains(String.valueOf(charArray[point.y][point.x - 1]));
    }
    private static boolean canGoEast(Point point) {
        if(point.x + 1 >= numCols)
            return false;
        return "-J7".contains(String.valueOf(charArray[point.y][point.x + 1]));
    }
}
