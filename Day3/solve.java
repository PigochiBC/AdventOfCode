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
        boolean canCountIt = true;
        int currentDoIndex = 0;
        int currentDontIndex = 0;
        int total =0;
        int count = 0;
        System.out.println(doEndIndexes);
        for(int i =0 ; i < caseStartIndexes.size(); i++){
            for(int j = 0; j < caseStartIndexes.get(i).size(); j++){
                count++;
                if (canCountIt){
                    try{
                        if(doEndIndexes.get(i).get(currentDoIndex) < caseStartIndexes.get(i).get(j)){
                            try{
                                if(doEndIndexes.get(i).get(currentDoIndex+1) > dontEndIndexes.get(i).get(currentDontIndex) ){
                                    currentDoIndex++;
                                    canCountIt = false;
                                }
                            }catch(IndexOutOfBoundsException e){
                                canCountIt = false;
                                currentDoIndex = 0;
                            }
                        }else{
                            total+=(Integer.parseInt(cases.get(count).split("[^0-9]{1,4}")[1].split(",")[0]) * Integer.parseInt(cases.get(count).split("[^0-9]{1,4}")[2]));
                        }
                    }catch(IndexOutOfBoundsException e){
                        if(doEndIndexes.get(i).get(1) < caseStartIndexes.get(i).get(j)){
                            try{
                                if(doEndIndexes.get(i).get(currentDoIndex+1) > dontEndIndexes.get(i).get(currentDontIndex) ){
                                    currentDoIndex++;
                                    canCountIt = false;
                                }
                            }catch(IndexOutOfBoundsException s){
                                canCountIt = false;
                                currentDoIndex = 0;
                            }
                        }else{
                            total+=(Integer.parseInt(cases.get(count).split("[^0-9]{1,4}")[1].split(",")[0]) * Integer.parseInt(cases.get(count).split("[^0-9]{1,4}")[2]));
                        }
                    }
                }else{
                    try{
                        if(dontEndIndexes.get(i).get(currentDontIndex) < caseStartIndexes.get(i).get(j)){
                            if(dontEndIndexes.get(i).get(currentDontIndex+1) > dontEndIndexes.get(i).get(currentDontIndex) ){
                                currentDontIndex++;
                                canCountIt = true;
                            }
                        }
                    }catch(IndexOutOfBoundsException e){
                        canCountIt = true;
                        currentDontIndex = 0;
                    }

                }

            }
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

