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

        ArrayList<String> fileData = getFileData("src/input.txt");


        for (int i = 0; i < fileData.size(); i++) {
            String[] noSpaces = fileData.get(i).split(" ");
            if (Integer.parseInt(noSpaces[0]) > Integer.parseInt(noSpaces[1])) {
                decreasing = true;
            } else {
                decreasing = false;
            }
            for (int j = 0; j < noSpaces.length - 1; j++) {
                if ((decreasing && Integer.parseInt(noSpaces[j]) < Integer.parseInt(noSpaces[j + 1]))) {
                    if(canRemoveBadCase){
                        noSpaces = handleWhichBadCase(noSpaces,j);
                        canRemoveBadCase = false;
                        j=-1;
                        continue;
                    }else{
                        System.out.println("CRAZY SHIT HAPPENED: " + Arrays.stream(noSpaces).toList());
                        break;
                    }
                } else if ((!decreasing && Integer.parseInt(noSpaces[j]) > Integer.parseInt(noSpaces[j + 1]))) {
                    if(canRemoveBadCase){
                        noSpaces = handleWhichBadCase(noSpaces,j);
                        canRemoveBadCase = false;
                        j=-1;
                        continue;
                    }else{
                        System.out.println("CRAZY SHIT HAPPENED: " + Arrays.stream(noSpaces).toList());
                        break;
                    }
                }

                if (Math.abs(Integer.parseInt(noSpaces[j]) - Integer.parseInt(noSpaces[j + 1])) >= 1 && Math.abs(Integer.parseInt(noSpaces[j]) - Integer.parseInt(noSpaces[j + 1])) <= 3) {
                    if (j == noSpaces.length - 2) {
                        safeCases++;
                        System.out.println("SAFE: " + Arrays.stream(noSpaces).toList());
                    }
                } else {
                    if(canRemoveBadCase){
                        noSpaces = handleWhichBadCase(noSpaces,j);
                        canRemoveBadCase = false;
                        j=-1;
                    }else{
                        System.out.println("CRAZY SHIT HAPPENED: " + Arrays.stream(noSpaces).toList());
                        break;
                    }
                }
            }
            canRemoveBadCase = true;
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

    public static String[] handleWhichBadCase(String[] input, int problemIndex){
        String[] returnMe = new String[0];

        //test if decreasing is correct
        int dec = 0;
        int inc = 0;
        for (int i = 0; i < input.length - 1; i++) {
            if (Integer.parseInt(input[i]) > Integer.parseInt(input[i + 1])) {
                dec++;
            } else {
                inc++;
            }
        }
        if (inc > dec) {
            decreasing = false;
        } else {
            decreasing = true;
        }

        if(decreasing && Integer.parseInt(input[problemIndex]) <= Integer.parseInt(input[problemIndex+1])){
            returnMe = remove(input, problemIndex);
        }else if(!decreasing && Integer.parseInt(input[problemIndex]) > Integer.parseInt(input[problemIndex+1])){
            returnMe = remove(input, problemIndex);
        }else{
            returnMe = remove(input, problemIndex);
        }

        if(!(Math.abs(Integer.parseInt(input[problemIndex]) - Integer.parseInt(input[problemIndex + 1])) >= 1 && Math.abs(Integer.parseInt(input[problemIndex]) - Integer.parseInt(input[problemIndex + 1])) <= 3)){
            try{
                if(Math.abs(Integer.parseInt(input[problemIndex]) - Integer.parseInt(input[problemIndex+2])) >=1 && (Math.abs(Integer.parseInt(input[problemIndex]) - Integer.parseInt(input[problemIndex+2])) <=3)){
                    returnMe = remove(input,problemIndex+1);
                }else{
                    returnMe = remove(input,problemIndex);
                }
            }catch(IndexOutOfBoundsException e){
                if(Integer.parseInt(input[problemIndex]) > (Integer.parseInt(input[problemIndex+1] + 3))){
                    returnMe = remove(input,problemIndex);
                }else if (Integer.parseInt(input[problemIndex+1]) > (Integer.parseInt(input[problemIndex]) + 3)){
                    returnMe = remove(input,problemIndex+1);
                }else{
                    returnMe = remove(input,problemIndex);
                }
            }
        }
        return returnMe;
    }
}