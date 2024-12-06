package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class solve {
    public static void main(String[] args){
        ArrayList<String> fileData = getFileData("input.txt");
        System.out.println(fileData);
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
    public static int part2(ArrayList<String> input){
        ArrayList<ArrayList<Integer>> xLocations = new ArrayList<>();
        ArrayList<String> copy;
        //step 1
        for(int i = 0; i < input.size(); i++){
            for(int j = 0; j < input.get(i).length(); j++){
                if(input.get(i).charAt(j) == "X".toCharArray()[0]){
                    ArrayList<Integer> xCoord = new ArrayList<>();
                    xCoord.add(i);
                    xCoord.add(j);
                    xLocations.add(xCoord);
                }
            }
        }

        //step 2 and 3 and 4
        copy = (ArrayList)input.clone();
        int total = 0;
        for(int i = 0; i < xLocations.size(); i++){
            try{
                if(!((xLocations.get(i).get(0) == 94) && (xLocations.get(i).get(1) == 73)))copy.set(xLocations.get(i).get(0), copy.get(xLocations.get(i).get(0)).substring(0,xLocations.get(i).get(1)) + "#" + copy.get(xLocations.get(i).get(0)).substring(xLocations.get(i).get(1)+1));
            }catch(IndexOutOfBoundsException e){
                System.out.println("IOOBE caught during step 2");
            }
            String direction = "N";
            int[] guardLocation = new int[]{94,73};
            long currentTime = System.currentTimeMillis();
            while(true){

                if(System.currentTimeMillis() > currentTime + 10){
                    System.out.println(total);
                    total++;
                    break;
                }

                try{
                    switch(direction){
                        case "N":
                            if(copy.get(guardLocation[0]-1).charAt(guardLocation[1]) == "#".toCharArray()[0]){
                                direction = "E";
                                guardLocation[1] = guardLocation[1] + 1;
                                copy.set(guardLocation[0], copy.get(guardLocation[0]).substring(0,guardLocation[1]) + "^" + copy.get(guardLocation[0]).substring(guardLocation[1]+1));
                            }else{
                                guardLocation[0] = guardLocation[0] - 1;
                                copy.set(guardLocation[0], copy.get(guardLocation[0]).substring(0,guardLocation[1]) + "^" + copy.get(guardLocation[0]).substring(guardLocation[1]+1));

                            }
                            break;
                        case "E":
                            if(copy.get(guardLocation[0]).charAt(guardLocation[1]+1) == "#".toCharArray()[0]){
                                direction = "S";
                                guardLocation[0] = guardLocation[0] + 1;
                                copy.set(guardLocation[0], copy.get(guardLocation[0]).substring(0,guardLocation[1]) + "^" + copy.get(guardLocation[0]).substring(guardLocation[1]+1));
                            }else{
                                guardLocation[1] = guardLocation[1] + 1;
                                copy.set(guardLocation[0], copy.get(guardLocation[0]).substring(0,guardLocation[1]) + "^" + copy.get(guardLocation[0]).substring(guardLocation[1]+1));
                            }
                            break;
                        case "S":
                            if(copy.get(guardLocation[0]+1).charAt(guardLocation[1]) == "#".toCharArray()[0]){
                                direction = "W";
                                guardLocation[1] = guardLocation[1] - 1;
                                copy.set(guardLocation[0], copy.get(guardLocation[0]).substring(0,guardLocation[1]) + "^" + copy.get(guardLocation[0]).substring(guardLocation[1]+1));
                            }else{
                                guardLocation[0] = guardLocation[0] + 1;
                                copy.set(guardLocation[0], copy.get(guardLocation[0]).substring(0,guardLocation[1]) + "^" + copy.get(guardLocation[0]).substring(guardLocation[1]+1));

                            }
                            break;
                        case "W":
                            if(copy.get(guardLocation[0]).charAt(guardLocation[1]-1) == "#".toCharArray()[0]){
                                direction = "N";
                                guardLocation[0] = guardLocation[0] - 1;
                                copy.set(guardLocation[0], copy.get(guardLocation[0]).substring(0,guardLocation[1]) + "^" + copy.get(guardLocation[0]).substring(guardLocation[1]+1));
                            }else{
                                guardLocation[1] = guardLocation[1] - 1;
                                copy.set(guardLocation[0], copy.get(guardLocation[0]).substring(0,guardLocation[1]) + "^" + copy.get(guardLocation[0]).substring(guardLocation[1]+1));
                            }
                            break;
                    }

                }catch(IndexOutOfBoundsException e){
                    break;
                }
            }
            copy = (ArrayList)input.clone();
        }


        System.out.println(xLocations);
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
