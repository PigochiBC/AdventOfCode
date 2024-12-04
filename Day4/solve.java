package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class solve {
    public static void main(String[] args){
        ArrayList<String> fileData = getFileData("Day4/input.txt");
        System.out.println(fileData);

        int total =0;
        for(int i =0; i < fileData.size(); i++){
            for(int j = 0; j < fileData.get(i).length(); j++){
                if(checkForWord(fileData, i, j, "North", 0))total++;
                else if(checkForWord(fileData, i, j, "East", 0))total++;
                else if(checkForWord(fileData, i, j, "South", 0))total++;
                else if(checkForWord(fileData, i, j, "West", 0))total++;
                else if(checkForWord(fileData, i, j, "NorthEast", 0))total++;
                else if(checkForWord(fileData, i, j, "NorthWest", 0))total++;
                else if(checkForWord(fileData, i, j, "SouthEast",0))total++;
                else if(checkForWord(fileData, i, j, "SouthWest", 0))total++;
            }
        }
        System.out.println(total);
    }

    //recursively check 2D array for XMAS in every direction
    public static boolean checkForWord(ArrayList<String> inputArray, int row, int column, String direction, int iteration){
        String toCheck = "X";
        //change what we're looking for based on input of iteration
        switch(iteration){
            case 1:
                toCheck = "M";
                break;
            case 2:
                toCheck = "A";
                break;
            case 3:
                toCheck = "S";
                break;
        }
        if(row-1 < 0 || row +1 > inputArray.size()){
            return false;
        }
        if(column-1 <0 || column +1 > inputArray.get(row).length()){
            return false;
        }
        if(inputArray.get(row).substring(column,column+1).equals(toCheck)){
            if(toCheck.equals("S")){
                System.out.println("Found Case at " + row + ":" + column);
                return true;
            }
            switch(direction){
                case "North":
                    return checkForWord(inputArray, row-1, column, direction, iteration+1);
                case "East":
                    return checkForWord(inputArray, row, column+1, direction, iteration+1);
                case "South":
                    return checkForWord(inputArray, row+1, column, direction, iteration+1);
                case "West":
                    return checkForWord(inputArray, row, column-1, direction, iteration+1);
                case "NorthEast":
                    return checkForWord(inputArray, row-1, column+1, direction, iteration+1);
                case "SouthEast":
                    return checkForWord(inputArray, row+1, column+1, direction, iteration+1);
                case "SouthWest":
                    return checkForWord(inputArray, row+1, column-1, direction, iteration+1);
                case "NorthWest":
                    return checkForWord(inputArray, row-1, column-1, direction, iteration+1);
            }
        }

        return false;
    }
    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.equals(""))
                    fileData.add(line);
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
}
