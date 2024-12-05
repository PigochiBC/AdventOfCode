package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Arrays;

public class solve {
    static boolean rulesSwitch = false;
    static ArrayList<String> inputStrings = new ArrayList<>();
    public static void main(String[] args){
        ArrayList<String> fileData = getFileData("input.txt");
        ArrayList<Integer> fileDataJustNums;
        ArrayList<Integer> fileDataJustRules;
        ArrayList<Integer> currentInputIteration = getNewIteration(inputStrings, 0);
        /*
         * Algorithm:
         *   1. Get just nums in array to its own arraylist, and rules into its own list
         *   2. Remove irrelevent info from nums and rules by comparing to current iteration of input
         *   3. Sort rules array L-G to make sure all number rules are done at the same time
         *   4. Swap nums array spots based on the rules
         *   5. if given list = sorted list, total+= middle num.
         */
        int total = 0;
        for(int x =0; x < inputStrings.size(); x++){
            fileDataJustNums = new ArrayList<>();
            fileDataJustRules = new ArrayList<>();
            currentInputIteration = getNewIteration(inputStrings, x);

            for(int i =0; i < fileData.size(); i++){
                if(currentInputIteration.contains(Integer.parseInt(fileData.get(i).split("\\|")[0])) && currentInputIteration.contains(Integer.parseInt(fileData.get(i).split("\\|")[1]))){
                    fileDataJustRules.add(Integer.parseInt(fileData.get(i).split("\\|")[0] + "" + fileData.get(i).split("\\|")[1]));
                    if(!fileDataJustNums.contains(Integer.parseInt(fileData.get(i).split("\\|")[0]))) fileDataJustNums.add(Integer.parseInt(fileData.get(i).split("\\|")[0]));
                }
            }
            Collections.sort(fileDataJustRules);

            for(int i = 0; i < currentInputIteration.size(); i++){
                if(!fileDataJustNums.contains(currentInputIteration.get(i))){
                    fileDataJustNums.add(currentInputIteration.get(i));
                }
            }


            ArrayList<Integer> finished = sortArray(fileDataJustNums,fileDataJustRules);
            if(!finished.equals(currentInputIteration)) {

                total+= finished.get(finished.size()/2);
            }else{
            }
        }
        System.out.println(total);

    }

    public static ArrayList<Integer> sortArray(ArrayList<Integer> input, ArrayList<Integer> rules){
        for(int i =0; i < input.size(); i++){
            for(int j = 0; j < rules.size(); j++){

                  //checks if rules at j index starts with the input at input index
                  if(rules.get(j).toString().startsWith(input.get(i).toString())){
                      int lesserThan = input.get(i);
                      int greaterThan = Integer.parseInt(rules.get(j).toString().substring(input.get(i).toString().length()));

                      //check indexes in input up to index lesserThanIndex to move items around.
                      for(int k = 0; k < i; k++){
                          if(input.get(k) == greaterThan){
                              int temp = input.get(k);
                              input.remove(k);
                              input.add(i, temp);
                              i--;
                          }
                      }

                  }
            }
        }
        return input;
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
