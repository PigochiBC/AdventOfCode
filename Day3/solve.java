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
        ArrayList<String> fileData = getFileData("input.txt");
        ArrayList<String> cases = new ArrayList<>();
        ArrayList<ArrayList<Integer>> caseStartIndexes = new ArrayList<>();
        ArrayList<ArrayList<Integer>> doEndIndexes = new ArrayList<>();
        ArrayList<ArrayList<Integer>> dontEndIndexes = new ArrayList<>();


        boolean canCountIt;
        for(int i = 0; i < fileData.size(); i++){
            Pattern toFind = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)", Pattern.CASE_INSENSITIVE);
            Matcher find = toFind.matcher(fileData.get(i));
            caseStartIndexes.add(new ArrayList<Integer>());
            while(find.find()){
                cases.add(find.group());
                caseStartIndexes.get(i).add(find.start());
            }
            //do() indexes
            toFind = Pattern.compile("do\\(\\)", Pattern.CASE_INSENSITIVE);
            find = toFind.matcher(fileData.get(i));
            doEndIndexes.add(new ArrayList<Integer>());
            while(find.find()){
                doEndIndexes.get(i).add(find.end());
            }
            //dont() indexes per line
            toFind = Pattern.compile("don't\\(\\)", Pattern.CASE_INSENSITIVE);
            find = toFind.matcher(fileData.get(i));
            dontEndIndexes.add(new ArrayList<Integer>());
            while(find.find()){
                dontEndIndexes.get(i).add(find.end());
            }
        }
        System.out.println(doEndIndexes);
        System.out.println(dontEndIndexes);
        System.out.println(caseStartIndexes);


        int total =0;
        int caseStartIndex = 0;
        int currentDoOrDont = doEndIndexes.get(0).get(0);
        int currentDoOrDontIndex = 0;
        for(int i = 0; i < cases.size(); i++){
            //if case start index > current do or dont index, find new currentDoOrDontindex and continue
            if(caseStartIndexes.get(i).get(caseStartIndex) > currentDoOrDont){
                if(doEndIndexes.get(i).get(0)){

                }
            }
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

