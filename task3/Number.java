package task3;

import java.util.ArrayList;
import java.util.List;

public class Number {
    public String number = "";
    public boolean nearChar = false;
    public boolean nearGear = false;
    public int[] gearPosition = new int[2];
    public List<int[]> positions = new ArrayList<>();

    public Number(){

    }

    public void addNumber(char number){
        this.number += number;
    }

    public Integer getNumber(){
        return Integer.valueOf(number);
    }

    public boolean checkNearChar(char[][] map){
        positions.forEach(position -> {
            for(int i = -1; i < 2; i++){
                for(int j = -1; j < 2; j++){
                    try{
                        char tmp = map[position[0] + i][position[1] + j];
                        if(Character.getNumericValue(tmp) < 0 && tmp != '.'){
                            nearChar = true;
                        }
                    }catch(Exception e){

                    }
                }
            }
        });
        return nearChar;
    }

    //for solution 2
    public boolean checkNearGearOrChar(char[][] map){
        positions.forEach(position -> {
            for(int i = -1; i < 2; i++){
                for(int j = -1; j < 2; j++){
                    try{
                        char tmp = map[position[0] + i][position[1] + j];
                        if(Character.getNumericValue(tmp) < 0 && tmp != '.'){
                            if(tmp == '*'){
                                nearGear = true;
                                gearPosition = new int[]{position[0] + i,position[1] + j};
                            } else {
                                nearChar = true;
                            }
                        }
                    }catch(Exception e){

                    }
                }
            }
        });
        return nearGear || nearChar;
    }
}
