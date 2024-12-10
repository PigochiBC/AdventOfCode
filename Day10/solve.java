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
        int trailheads = 0;

        System.out.println(fileDataTo2DArr);
        System.out.println(zeroPositions);
        //iterate through every zero positions to check for trailheads
        int trailHeads = 0;
        for(int i = 0; i < zeroPositions.size(); i++){
            //start position
            int zeroX = zeroPositions.get(i).get(0);
            int zeroY = zeroPositions.get(i).get(1);

            //make copy of filedata2D array since Incorrect turns will be turned into "X"
            ArrayList<ArrayList<Integer>> fileDataTo2DArrCopy = new ArrayList<>();
            for(ArrayList<Integer> a: fileDataTo2DArr){
                fileDataTo2DArrCopy.add(a);
            }

            trailheads+=findTrailHeads(zeroX,zeroY,fileDataTo2DArrCopy,0,1);
        }
        System.out.println(hashSet);
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
        //east bound check
        if(currentY - 1 < 0 && canCheckNtoW[2]){
            canCheckNtoW[3] = false;
        }
        if( currentY+1 >= inputArray.get(currentX).size() ){

            canCheckNtoW[1] = false;
        }
        //west bound check

        int temp = trailHeads;
        if(canCheckNtoW[0] && inputArray.get(currentX-1).get(currentY) == toSearch){
            toSearch++;
            if(!(toSearch>9)){
                trailHeads+= findTrailHeads(currentX-1,currentY,inputArray,trailHead,toSearch);

            }else{
                hashSet.add(currentX-1 + "," + currentY);
            }
        }else{
            notFoundCounter++;
        }
        if(canCheckNtoW[1] && inputArray.get(currentX).get(currentY+1) == toSearch){
            toSearch++;
            if(!(toSearch>9)){

                trailHeads+= findTrailHeads(currentX,currentY+1,inputArray,trailHead,toSearch);

            }else{
                hashSet.add(currentX + "," + currentY);
            }
        }else{
            notFoundCounter++;
        }
        if(canCheckNtoW[2] && inputArray.get(currentX+1).get(currentY) == toSearch){
            toSearch++;
            if(!(toSearch>9)){
                trailHeads+= findTrailHeads(currentX+1,currentY,inputArray,trailHead,toSearch);

            }else{
                hashSet.add(currentX + "," + currentY);
            }
        }else{
            notFoundCounter++;
        }
        if(canCheckNtoW[3] && inputArray.get(currentX).get(currentY-1) == toSearch){
            toSearch++;
            if(!(toSearch>9)){
                trailHeads+= findTrailHeads(currentX-1,currentY,inputArray,trailHead,toSearch);
            }else{
                hashSet.add(currentX + "," + currentY);
            }
        }else{
            notFoundCounter++;
        }

        if(notFoundCounter==4){
            return 0;
        }


        return 1;
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
