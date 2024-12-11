import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class solve {
    static HashSet<String> hashSet = new HashSet<>();

    public static void main(String[] args){
        ArrayList<String> fileData = getFileData("input.txt");
        ArrayList<ArrayList<Integer>> fileDataTo2DArr = sortFileData(fileData);
        ArrayList<ArrayList<Integer>> zeroPositions = getZeroPositions(fileDataTo2DArr);

        System.out.println(fileDataTo2DArr);
        System.out.println(zeroPositions);
        //iterate through every zero positions to check for trailheads
        int trailHeads = 0;
        for(int i = 0; i < zeroPositions.size(); i++){
            //start position
            int zeroX = zeroPositions.get(i).get(0);
            int zeroY = zeroPositions.get(i).get(1);

            //make copy of filedata2D array since Incorrect turns will be turned into "X"
            ArrayList<ArrayList<Integer>> fileDataTo2DArrCopy = new ArrayList<>(fileDataTo2DArr);

            trailHeads+=findTrailHeads(zeroX,zeroY,fileDataTo2DArrCopy,0,1 );
        }
        System.out.println(trailHeads);
    }
    public static int findTrailHeads(int currentX, int currentY, ArrayList<ArrayList<Integer>> inputArray, int trailHead, int toSearch){
        //North - East - South - West
        boolean[] canCheckNtoW = new boolean[]{true,true,true,true};
        int trailHeads = trailHead;
        int notFoundCounter = 0;
        //north bound check
        if(currentX - 1 < 0 ){
            canCheckNtoW[0] = false;
        }
        //south bound check
        if (currentX+1 >= inputArray.size() ){
            canCheckNtoW[2] = false;
        }
        //westbound check
        if(currentY - 1 < 0 ){
            canCheckNtoW[3] = false;
        }
        //eastbound check
        if( currentY+1 >= inputArray.get(currentX).size() ){
            canCheckNtoW[1] = false;
        }

        if(canCheckNtoW[0] && inputArray.get(currentX-1).get(currentY) == toSearch){
            if(!(toSearch>9)){
                trailHeads= trailHeads + findTrailHeads(currentX-1,currentY,inputArray,trailHeads,toSearch+1);
            }
        }else{
            notFoundCounter++;
        }
        if(canCheckNtoW[1] && inputArray.get(currentX).get(currentY+1) == toSearch){
            if(!(toSearch>9)){
                trailHeads= trailHeads + findTrailHeads(currentX,currentY+1,inputArray,trailHeads,toSearch+1);
            }
        }else{
            notFoundCounter++;
        }
        if(canCheckNtoW[2] && inputArray.get(currentX+1).get(currentY) == toSearch){
            if(!(toSearch>9)){
                trailHeads= trailHeads + findTrailHeads(currentX+1,currentY,inputArray,trailHeads,toSearch+1);
            }

        }else{
            notFoundCounter++;
        }
        if(canCheckNtoW[3] && inputArray.get(currentX).get(currentY-1) == toSearch){
            if(!(toSearch>9)){
                System.out.println(trailHeads);
                trailHeads= trailHeads + findTrailHeads(currentX,currentY-1,inputArray,trailHeads,toSearch+1);
                System.out.println(trailHeads);
            }
        }else{
            notFoundCounter++;
        }

        if(notFoundCounter==4){
            return 0;
        }

        return trailHead+1;
    }

    //put fileData into 2d Array
    public static ArrayList<ArrayList<Integer>> sortFileData(ArrayList<String> input){
        ArrayList<ArrayList<Integer>> returnMe= new ArrayList<>();
        for(String str:input){
            ArrayList<Integer> strArr = new ArrayList<>();
            for(int i = 1; i <= str.length(); i++){
                strArr.add(Integer.parseInt(str.substring(i-1,i)));
            }
            returnMe.add(strArr);
        }
        return returnMe;
    }
    public static ArrayList<ArrayList<Integer>> getZeroPositions(ArrayList<ArrayList<Integer>> input){
        ArrayList<ArrayList<Integer>> returnMe = new ArrayList<>();
        for(int i = 0; i < input.size(); i++){
            for(int j = 0; j < input.get(i).size(); j++){
                ArrayList<Integer> zeroPosSingular = new ArrayList<>();
                if(input.get(i).get(j) == 0){
                    zeroPosSingular.add(i);
                    zeroPosSingular.add(j);
                    returnMe.add(zeroPosSingular);
                }
            }
        }
        return returnMe;
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
