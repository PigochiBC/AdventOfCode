package Day10;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class solve {
    static HashSet<String> hashSet = new HashSet<>();
    static int counter = 0;
    static int zeroX = 0;
    static int zeroY = 0;
    public static void main(String[] args){
        ArrayList<String> fileData = getFileData("Day10/input.txt");
        ArrayList<ArrayList<Integer>> fileDataTo2DArr = sortFileData(fileData);
        ArrayList<ArrayList<Integer>> zeroPositions = getZeroPositions(fileDataTo2DArr);
        int trailheads = 0;
        
        System.out.println(fileDataTo2DArr);
        System.out.println(zeroPositions);
        //iterate through every zero positions to check for trailheads
        for(int i = 0; i < zeroPositions.size(); i++){
            //start position
             zeroX = zeroPositions.get(i).get(0);
             zeroY = zeroPositions.get(i).get(1);

            ArrayList<ArrayList<Integer>> fileDataTo2DArrCopy = new ArrayList<>();
            for(ArrayList<Integer> a: fileDataTo2DArr){
                fileDataTo2DArrCopy.add(a);
            }

            trailheads+=findTrailHeads(zeroX,zeroY,fileDataTo2DArrCopy,0,1, 0);
            
        }
        System.out.println(counter);
        for(int i = 0; i < hashSet.size(); i++){
            System.out.println(i+1);
        }
    }
    public static int findTrailHeads(int currentX, int currentY, ArrayList<ArrayList<Integer>> inputArray, int trailHead, int toSearch, int iteration){
        //North - East - South - West
        boolean[] canCheckNtoW = new boolean[]{true,true,true,true};
        int trailHeads = 0;
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
        if(currentY - 1 < 0){
            canCheckNtoW[3] = false;
        }
        if( currentY+1 >= inputArray.get(currentX).size() ){

            canCheckNtoW[1] = false;
        }
        //west bound check
        if(canCheckNtoW[0] && inputArray.get(currentX-1).get(currentY) == toSearch){
            if(!(toSearch>9)){
                
                trailHeads+= findTrailHeads(currentX-1,currentY,inputArray,trailHeads,toSearch+1, iteration+1);
                
            }
        }else{
            notFoundCounter++;
        }
        if(canCheckNtoW[1] && inputArray.get(currentX).get(currentY+1) == toSearch){
            if(!(toSearch>9)){

                trailHeads+= findTrailHeads(currentX,currentY+1,inputArray,trailHeads,toSearch+1, iteration+1);
                
            }
        }else{
            notFoundCounter++;
        }
        if(canCheckNtoW[2] && inputArray.get(currentX+1).get(currentY) == toSearch){
            if(!(toSearch>9)){
                trailHeads+= findTrailHeads(currentX+1,currentY,inputArray,trailHeads,toSearch+1, iteration+1);

            }
        }else{
            notFoundCounter++;
        }
        if(canCheckNtoW[3] && inputArray.get(currentX).get(currentY-1) == toSearch){
            if(!(toSearch>9)){
                trailHeads+= findTrailHeads(currentX,currentY-1,inputArray,trailHeads,toSearch+1, iteration+1);
            }
        }else{
            notFoundCounter++;
        }
        
        if(notFoundCounter==4 && toSearch!=10){
            return 0;
        }
        if(toSearch==10){
            counter++;
        }
        return trailHead;
        
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
            s.close();
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
}