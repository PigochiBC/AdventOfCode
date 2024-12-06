package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class solve {
    public static void main(String[] args){
        ArrayList<String> fileData = getFileData("Day6/input.txt");
        int[] guardLocation = new int[2];
        String direction="N";
        for(int i = 0; i < fileData.size(); i ++){
            for(int j = 0; j < fileData.get(i).length(); j++){
                if(fileData.get(i).charAt(j)==("^".toCharArray()[0])){
                    guardLocation[0] = i;
                    guardLocation[1] = j;
                }
            }
        }
        System.out.println(guardLocation[0] + ":" + guardLocation[1]);
        //make sure this dont run forevr
        while(true){
            try{
                switch(direction){
                    case "N":
                        if(fileData.get(guardLocation[0]-1).charAt(guardLocation[1]) == "#".toCharArray()[0]){
                            direction = "E";
                            fileData.set(guardLocation[0], fileData.get(guardLocation[0]).substring(0,guardLocation[1]) + "X" + fileData.get(guardLocation[0]).substring(guardLocation[1]+1));
                            guardLocation[1] = guardLocation[1] + 1;
                            fileData.set(guardLocation[0], fileData.get(guardLocation[0]).substring(0,guardLocation[1]) + "^" + fileData.get(guardLocation[0]).substring(guardLocation[1]+1));
                        }else{
                            fileData.set(guardLocation[0], fileData.get(guardLocation[0]).substring(0,guardLocation[1]) + "X" + fileData.get(guardLocation[0]).substring(guardLocation[1]+1));
                            guardLocation[0] = guardLocation[0] - 1;
                            fileData.set(guardLocation[0], fileData.get(guardLocation[0]).substring(0,guardLocation[1]) + "^" + fileData.get(guardLocation[0]).substring(guardLocation[1]+1));
                            
                        }
                        break;
                    case "E":
                        if(fileData.get(guardLocation[0]).charAt(guardLocation[1]+1) == "#".toCharArray()[0]){
                            direction = "S";
                            fileData.set(guardLocation[0], fileData.get(guardLocation[0]).substring(0,guardLocation[1]) + "X" + fileData.get(guardLocation[0]).substring(guardLocation[1]+1));
                            guardLocation[0] = guardLocation[0] + 1;
                            fileData.set(guardLocation[0], fileData.get(guardLocation[0]).substring(0,guardLocation[1]) + "^" + fileData.get(guardLocation[0]).substring(guardLocation[1]+1));
                        }else{
                            fileData.set(guardLocation[0], fileData.get(guardLocation[0]).substring(0,guardLocation[1]) + "X" + fileData.get(guardLocation[0]).substring(guardLocation[1]+1));
                            guardLocation[1] = guardLocation[1] + 1;
                            fileData.set(guardLocation[0], fileData.get(guardLocation[0]).substring(0,guardLocation[1]) + "^" + fileData.get(guardLocation[0]).substring(guardLocation[1]+1));    
                        }
                        break;
                    case "S":
                        if(fileData.get(guardLocation[0]+1).charAt(guardLocation[1]) == "#".toCharArray()[0]){
                            direction = "W";
                            fileData.set(guardLocation[0], fileData.get(guardLocation[0]).substring(0,guardLocation[1]) + "X" + fileData.get(guardLocation[0]).substring(guardLocation[1]+1));
                            guardLocation[1] = guardLocation[1] - 1;
                            fileData.set(guardLocation[0], fileData.get(guardLocation[0]).substring(0,guardLocation[1]) + "^" + fileData.get(guardLocation[0]).substring(guardLocation[1]+1));
                        }else{
                            fileData.set(guardLocation[0], fileData.get(guardLocation[0]).substring(0,guardLocation[1]) + "X" + fileData.get(guardLocation[0]).substring(guardLocation[1]+1));
                            guardLocation[0] = guardLocation[0] + 1;
                            fileData.set(guardLocation[0], fileData.get(guardLocation[0]).substring(0,guardLocation[1]) + "^" + fileData.get(guardLocation[0]).substring(guardLocation[1]+1));
                            
                        }
                        break;
                    case "W":
                        if(fileData.get(guardLocation[0]).charAt(guardLocation[1]-1) == "#".toCharArray()[0]){
                            direction = "N";
                            fileData.set(guardLocation[0], fileData.get(guardLocation[0]).substring(0,guardLocation[1]) + "X" + fileData.get(guardLocation[0]).substring(guardLocation[1]+1));
                            guardLocation[0] = guardLocation[0] - 1;
                            fileData.set(guardLocation[0], fileData.get(guardLocation[0]).substring(0,guardLocation[1]) + "^" + fileData.get(guardLocation[0]).substring(guardLocation[1]+1));
                        }else{
                            fileData.set(guardLocation[0], fileData.get(guardLocation[0]).substring(0,guardLocation[1]) + "X" + fileData.get(guardLocation[0]).substring(guardLocation[1]+1));
                            guardLocation[1] = guardLocation[1] - 1;
                            fileData.set(guardLocation[0], fileData.get(guardLocation[0]).substring(0,guardLocation[1]) + "^" + fileData.get(guardLocation[0]).substring(guardLocation[1]+1));
                            
                        }
                        break;
                    }
                    
            }catch(IndexOutOfBoundsException e){
                fileData.set(guardLocation[0], fileData.get(guardLocation[0]).substring(0,guardLocation[1]) + "X" + fileData.get(guardLocation[0]).substring(guardLocation[1]+1));
                break;
            }
        }

        System.out.println(part2(fileData));

    }

    /*
        Algorithm:
        Step 1. Create an array with the grid values for every X
        Step 2. Make a copy of the input array, replacing 1 X with a #,
        Step 3. Run a new simulation of guard path
        Step 4. Check for infinite loop, and add to the total if case is found
     */
    public static int part2(ArrayList<String> fileDataInput){
        String direction = "N";
        int[] guardLocation = new int[]{6,4};
        ArrayList<String> fileData = new ArrayList<>();
            for (String row : fileDataInput) {
                fileData.add(new String(row));
            }
        int max = 100000;
        int total = 0;
        for(int i = 0; i < fileData.size(); i++){
            for(int j = 0; j < fileData.get(i).length(); j++){
                fileData = new ArrayList<>();
                for (String row : fileDataInput) {
                    fileData.add(new String(row));
                }
                if(!(i==94 && j==73))fileData.set(i, fileData.get(i).substring(0,j) + "#" + fileData.get(i).substring(j+1));
                int currentIteration = 0;
                direction = "N";
                guardLocation = new int[]{94,73};
                while(true){
                    if(currentIteration > max){
                        total++;
                        break;
                    }
                    try{
                        switch(direction){
                            case "N":
                                if(fileData.get(guardLocation[0]-1).charAt(guardLocation[1]) == "#".toCharArray()[0]){
                                    direction = "E";
                                }else{
                                    guardLocation[0] = guardLocation[0] - 1;

                                }
                                break;
                            case "E":
                                if(fileData.get(guardLocation[0]).charAt(guardLocation[1]+1) == "#".toCharArray()[0]){
                                    direction = "S";
                                }else{
                                    guardLocation[1] = guardLocation[1] + 1;
                                }
                                break;
                            case "S":
                                if(fileData.get(guardLocation[0]+1).charAt(guardLocation[1]) == "#".toCharArray()[0]){
                                    direction = "W";
                                }else{
                                    guardLocation[0] = guardLocation[0] + 1;
                                }
                                break;
                            case "W":
                                if(fileData.get(guardLocation[0]).charAt(guardLocation[1]-1) == "#".toCharArray()[0]){
                                    direction = "N";
                                }else{
                                    guardLocation[1] = guardLocation[1] - 1;

                                }
                                break;
                        }

                    }catch(IndexOutOfBoundsException e){
                        fileData.set(guardLocation[0], fileData.get(guardLocation[0]).substring(0,guardLocation[1]) + "X" + fileData.get(guardLocation[0]).substring(guardLocation[1]+1));
                        break;
                    }
                    
                    currentIteration++;
                }
                
                
            }   
        }
        return total;
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