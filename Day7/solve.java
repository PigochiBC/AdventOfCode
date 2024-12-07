package Day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class solve {
    public static void main(String[] args){
        ArrayList<String> fileData= getFileData("Day7/input.txt");
        ArrayList<Long> toGet = new ArrayList<>();
        ArrayList<ArrayList<Long>> nums = new ArrayList<>();
        for(int i =0; i < fileData.size(); i++){
            ArrayList<Long> numsAlone = new ArrayList<>();
            for(int j = 1; j < fileData.get(i).split(" ").length; j++){
                numsAlone.add(Long.parseLong(fileData.get(i).split(" ")[j]));
            }
            nums.add(numsAlone);
            toGet.add(Long.parseLong(fileData.get(i).split(" ")[0].split(":")[0]));
        }
        //2^n operations per length of combination
        ArrayList<ArrayList<Long>> possibleOutputs = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            ArrayList<Long> currentList = nums.get(i);
            int combinations = (int) Math.pow(3, currentList.size() - 1); 
            ArrayList<Long> output = new ArrayList<>();
            
            int[] operations = new int[currentList.size() - 1];

            //summing algorithm
            for (int mask = 0; mask < combinations; mask++) {
                Long current = currentList.get(0);
                
                for(int opIndex = 0; opIndex < operations.length; opIndex++){
                    switch (operations[opIndex]) {
                        case 0: 
                            current += currentList.get(opIndex + 1);
                            break;
                        case 1:
                            current = multiply(current, currentList.get(opIndex + 1));
                            break;
                        case 2: 
                            current = combineLogic(current, currentList.get(opIndex + 1));
                            break;
                    }
                }
                incrementBase3(operations);
                output.add(current);
                
                
            }
            possibleOutputs.add(output);
            
            
        }
        Long total = 0l;
        for(int i = 0; i < possibleOutputs.size(); i++){
            for(int j = 0; j < possibleOutputs.get(i).size(); j++){
                Long current = possibleOutputs.get(i).get(j);

                if(toGet.get(i).equals(current)){
                    total+=toGet.get(i);
                    break;
                }
            }
        }
        System.out.println(total);
    }
        
    public static void incrementBase3(int[] operations) {
        int n = operations.length;
        for (int i = n - 1; i >= 0; i--) {
            operations[i]++; 
            if (operations[i] == 3) {
                operations[i] = 0;
            } else {
                break;
            }
        }
    }
    public static Long multiply(Long x, Long y){
        return x*y;
    }
    public static Long add(Long x, Long y){
        return x + y;
    }
    public static Long combineLogic(Long x, Long y){
        return Long.parseLong(x + "" + y);
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
