package Day3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

public class solve{
    public static void main(String[] args){
        ArrayList<String> fileData = getFileData("Day3/input.txt");
        
        ArrayList<String> cases = new ArrayList<>();
        for(int i = 1; i < fileData.size(); i++){
            Pattern toFind = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,2}\\)", Pattern.CASE_INSENSITIVE);
                        Matcher find = toFind.matcher(fileData.get(i));
            System.out.println(find.find());
            while(find.find()){
                cases.add(find.group());
            }
            
        }
        
        System.out.println(cases);
        
        ArrayList<String> numsLeft = new ArrayList<>();
        ArrayList<String> numsRight = new ArrayList<>();
        int total =0;
        for(int i = 0; i < cases.size(); i++){
            System.out.println(cases.get(0));
            System.out.println(Arrays.toString(cases.get(i).split("[^0-9]{1,3}")));
            System.out.println(cases.get(i).split("[^0-9]{1,4}")[1].split(",")[0]);
            System.out.println(cases.get(i).split("[^0-9]{1,4}")[2]);
            total+=(Integer.parseInt(cases.get(i).split("[^0-9]{1,4}")[1].split(",")[0]) * Integer.parseInt(cases.get(i).split("[^0-9]{1,4}")[2]));
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

    