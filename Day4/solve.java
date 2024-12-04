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
        ArrayList<String> fileData = getFileData("input.txt");
        System.out.println(fileData);

        int total =0;
        for(int i =0; i < fileData.size(); i++){

            for(int j = 0; j < fileData.get(i).length()-2; j++){
                /*
                    Northeast : S*S    M*S
                                *A* OR *A*
                                M*M    M*S

                    Northwest : S*S    S*M
                                *A* OR *A*
                                M*M    S*M

                    Southeast : M*M     M*S
                                *A* OR  *A*
                                S*S     M*S

                    Southwest : S*M     M*M
                                *A* OR  *A*
                                S*M     S*S

                    Make new algorithms for each of the common variations as new 'directions', one South, one North, one East, and one West
                 */
                try{
                    if(checkForWord(fileData, i, j, "NorthEast", 0) )total++;
                    if(checkForWord(fileData, i, j, "NorthWest", 0))total++;
                    if(checkForWord(fileData, i, j, "SouthEast",0))total++;
                    if(checkForWord(fileData, i, j, "SouthWest", 0))total++;
                }catch(IndexOutOfBoundsException e){

                }

            }
        }
        System.out.println(total);
    }

    //recursively check 2D array for XMAS in every direction
    public static boolean checkForWord(ArrayList<String> inputArray, int row, int column, String direction, int iteration){
        String toCheck = "M";
        //change what we're looking for based on input of iteration
        switch(iteration){
            case 1:
                toCheck = "A";
                break;
            case 2:
                toCheck = "S";
                break;
        }
        if((row < 0 || row > inputArray.size() -1 )){
            return false;
        }

        if((column < 0)){
            return false;
        }
        if(column + 1 > inputArray.get(row).length()){

            if(inputArray.get(row).substring(column-1).equals(toCheck)) {
                return toCheck.equals("S");
            }
            return false;
        }
        //working

        if(inputArray.get(row).substring(column,column+1).equals(toCheck)){
            if(toCheck.equals("S")){
                return true;
            }
            switch(direction){

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
