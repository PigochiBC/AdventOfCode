import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class solve {
    static int safeCases = 0;
    static boolean decreasing = false;
    static boolean canRemoveBadCase = true;
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("input.txt");
        for (int i = 0; i < fileData.size(); i++) {
            //wtf even are these next 3 lines??? Stack overflow moment https://stackoverflow.com/questions/1073919/how-to-convert-int-into-listinteger-in-java
            int[] noSpacesAsInts = Arrays.stream(fileData.get(i).split(" ")).mapToInt(Integer::parseInt).toArray();
            List<Integer> noSpacesList = Arrays.stream(noSpacesAsInts).boxed().toList();
            ArrayList<Integer> noSpacesArrayList = new ArrayList<>(noSpacesList);

            if(handleWhichBadCase(noSpacesArrayList)){
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


    public static boolean handleWhichBadCase(ArrayList<Integer> input){
        ArrayList<Integer> temp = (ArrayList)input.clone();
        //check decreasing
        int inc=0,dec=0;
        for(int i = 0; i < temp.size()-1 ;i++){
            if(temp.get(i)>temp.get(i+1)) {
                dec++;
            }else if(temp.get(i)<temp.get(i+1)){
                inc++;
            }
        }
        if(dec>inc){
            decreasing = true;
        }else{
            decreasing = false;
        }
        for(int i = 0; i < input.size(); i++){
            temp = (ArrayList)input.clone();;
            temp.remove(i);

            for(int j = 0; j < temp.size()-1; j++){

                if (decreasing && temp.get(j) < temp.get(j+1)) {
                    break;
                } else if ((!decreasing && temp.get(j) > temp.get(j+1))) {
                    break;

                }

                if (Math.abs(temp.get(j) - temp.get(j+1)) >= 1 && Math.abs(temp.get(j) - temp.get(j+1)) <= 3) {
                    if (j == temp.size() - 2) {
                        System.out.println("Safe: " + input.toString() +": Decreasing: " + decreasing);
                        return true;
                    }
                } else {
                    break;
                }

            }
        }
        System.out.println("Unsafe: " + temp.toString() +": Decreasing: " + decreasing);
        return false;
    }
}