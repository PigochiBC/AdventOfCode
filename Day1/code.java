package Day1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class code {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("Day1/input.txt");
        ArrayList<Integer> leftSideData = new ArrayList<>();
        ArrayList<Integer> rightSideData = new ArrayList<>();
        for(int i = 0; i < fileData.size(); i++){
            leftSideData.add(Integer.parseInt(fileData.get(i).split("   ")[0]));
            rightSideData.add(Integer.parseInt(fileData.get(i).split("   ")[1]));
        }
        sort(leftSideData);
        sort(rightSideData);
        int multiplier = 0;
        int total = 0;
        for(int i = 0; i < leftSideData.size(); i++){
            for(int j = 0; j < rightSideData.size(); j++){
                if(rightSideData.get(j).equals(leftSideData.get(i))){
                    multiplier++;
                }
            }
            total+=leftSideData.get(i)*multiplier;
            multiplier=0;
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

    public static void sort(ArrayList<Integer> arr){
        for(int i = 0; i < arr.size(); i ++){
            int min=i;
            for(int j = i + 1; j< arr.size(); j++){
                if(arr.get(j) < arr.get(min)){
                    min = j;
                }   
            }
            int temp = arr.get(min);
            arr.set(min,arr.get(i));
            arr.set(i,temp);
        }
    }
}