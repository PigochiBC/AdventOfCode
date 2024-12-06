package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class solve {
    public static void main(String[] args){
        ArrayList<String> fileData = getFileData("Day6/input.txt");
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
                System.out.println("IOOBE error caught at (" + guardLocation[0] + "," + guardLocation[1] + ")");
                break;
            }
        }
        int total = 0;
        for(int i =0; i < fileData.size(); i++){
            for(int j = 0; j < fileData.get(i).length(); j++){
                if(fileData.get(i).charAt(j) == "X".toCharArray()[0]){
                    total++;
                }
            }
        }
        System.out.println(total);

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
