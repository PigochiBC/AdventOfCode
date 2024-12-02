import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class solve {
    static int safeCases = 0;
    static boolean decreasing = false;
    static boolean canRemoveBadCase = true;
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("input.txt");
        for (int i = 0; i < fileData.size(); i++) {
            String[] noSpaces = fileData.get(i).split(" ");

            if(handleWhichBadCase(noSpaces)){
                safeCases++;
            }
        }
        System.out.println(safeCases);


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
        } catch (FileNotFoundException e) {
            return fileData;
        }
    }

    //https://www.geeksforgeeks.org/remove-an-element-at-specific-index-from-an-array-in-java/ I didnt feel like translating nospaces[] into an array list
    public static String[] remove(String[] arr, int in) {

        if (arr == null || in < 0 || in >= arr.length) {
            return arr;
        }

        String[] arr2 = new String[arr.length - 1];
        for (int i = 0, k = 0; i < arr.length; i++) {
            if (i == in)
                continue;

            arr2[k++] = arr[i];
        }
        return arr2;
    }

    public static boolean handleWhichBadCase(String[] input){

        for(int i = 0; i < input.length; i++){
            String[] currentIteration = remove(input, i);
            if (Integer.parseInt(input[0]) > Integer.parseInt(input[1])) {
                decreasing = true;
            } else {
                decreasing = false;
            }
            int switches = 0;
            for(int j = 0; j < currentIteration.length-1; j++){
                if(Integer.parseInt(currentIteration[j]) > Integer.parseInt(currentIteration[j+1])){
                    decreasing=true;
                }
                else{
                    decreasing = false;
                }
                if ((decreasing && Integer.parseInt(currentIteration[j]) < Integer.parseInt(currentIteration[j + 1]))) {
                    break;
                } else if ((!decreasing && Integer.parseInt(currentIteration[j]) > Integer.parseInt(currentIteration[j + 1]))) {
                    break;
                }

                if (Math.abs(Integer.parseInt(currentIteration[j]) - Integer.parseInt(currentIteration[j + 1])) >= 1 && Math.abs(Integer.parseInt(currentIteration[j]) - Integer.parseInt(currentIteration[j + 1])) <= 3) {
                    if (j == currentIteration.length - 2) {
                        return true;
                    }
                } else {
                    break;
                }
            }
        }

        return false;
    }
}