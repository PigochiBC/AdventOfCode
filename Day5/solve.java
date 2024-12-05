package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class solve {
    static boolean rulesSwitch = false;
    static ArrayList<String> inputStrings = new ArrayList<>();
    public static void main(String[] args){
        ArrayList<String> fileData = getFileData("Day5/input.txt");
        ArrayList<Integer> fileDataJustNums = new ArrayList<>();
        ArrayList<Integer> fileDataJustRules = new ArrayList<>();
        ArrayList<Integer> currentInputIteration = getNewIteration(inputStrings, 0);
        System.out.println(currentInputIteration);
        /*
         * Algorithm:
         *   1. Get just nums in array to its own arraylist
         *   2. Remove irrelevent info from nums and rules by comparing to current iteration of input
         *   3. 
         */
        
        for(int i =0; i < fileData.size(); i++){
            if(currentInputIteration.contains(Integer.parseInt(fileData.get(i).split("\\|")[0])) && currentInputIteration.contains(Integer.parseInt(fileData.get(i).split("\\|")[1]))){
                fileDataJustRules.add(Integer.parseInt(fileData.get(i).split("\\|")[0] + "" + fileData.get(i).split("\\|")[1]));
                fileDataJustNums.add(Integer.parseInt(fileData.get(i).split("\\|")[0]));
            } 
        }
        
        System.out.println(fileDataJustRules);
        System.out.println(fileDataJustNums);
    }
    public static void sortArray(ArrayList<Integer> input, ArrayList<Integer> rules){
        for(int i =0; i < input.size(); i++){

            for(int j = 0; j < input.size(); j++){
                //if we find the rule number at index j, check if i > j, telling us if the rule is being followed or not.
                if(rules.get(i) == input.get(j)){
                    if(i > j){
                        int temp = input.get(i);
                        input.set(i, input.get(j));
                        input.set(j, temp);
                    }
                } 
            }
        }
    }
    public static ArrayList<Integer> getNewIteration(ArrayList<String> inputContainer, int iteration){
        ArrayList<Integer> toReturn = new ArrayList<>();
        for(int i =0; i < inputContainer.get(iteration).split(",").length; i++){
            toReturn.add(Integer.parseInt(inputContainer.get(iteration).split(",")[i]));
        }
        return toReturn;
    }

    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.equals("") && !rulesSwitch)
                    fileData.add(line);
                else if(rulesSwitch){
                    inputStrings.add(line);
                }
                else{
                    rulesSwitch = true;
                }
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
}
