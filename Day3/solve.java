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
        ArrayList<ArrayList<Integer>> caseStartIndexes = new ArrayList<>();

        for(int i = 0; i < fileData.size(); i++){
            Pattern toFind = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)", Pattern.CASE_INSENSITIVE);
            Matcher find = toFind.matcher(fileData.get(i));
            caseStartIndexes.add(new ArrayList<Integer>());
            while(find.find()){
                cases.add(find.group());
                caseStartIndexes.get(i).add(find.start());
            }

        }
        boolean canPass = true;
        int currentIndex = 0;
        int currentCase = 0;
        int total =0;
        for(int i = 0; i < fileData.size(); i++){
           for(int j = 0; j < fileData.get(i).length(); j++){
                try{
                    if(fileData.get(i).substring(j,j+4).equals("do()")){
                        canPass = true;
                    }else if(fileData.get(i).substring(j,j+7).equals("don't()")){
                        canPass = false;
                    }
                }catch(IndexOutOfBoundsException e){}   
                try{
                    if(canPass){
                        if(j == caseStartIndexes.get(i).get(currentIndex)){
                            total+=(Integer.parseInt(cases.get(currentCase).split("[^0-9]{1,4}")[1].split(",")[0]) * Integer.parseInt(cases.get(currentCase).split("[^0-9]{1,4}")[2]));
                            currentIndex++;
                            currentCase++;
                        }
                    }else{
                        if(j == caseStartIndexes.get(i).get(currentIndex)){
                            currentIndex++;
                            currentCase++;
                        }
                    }
                }catch(IndexOutOfBoundsException e){

                }
           }
           currentIndex = 0;
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

    