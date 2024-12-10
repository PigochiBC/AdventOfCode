import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class solve {

    public static void main(String[] args){
        ArrayList<String> fileData = getFileData("input.txt");
        ArrayList<ArrayList<Integer>> fileDataTo2DArr = sortFileData(fileData);
        ArrayList<ArrayList<Integer>> zeroPositions = getZeroPositions(fileDataTo2DArr);
        int trailheads = 0;

        System.out.println(fileDataTo2DArr);
        System.out.println(zeroPositions);
        //iterate through every zero positions to check for trailheads
        for(int i = 0; i < zeroPositions.size(); i++){
            //start position
            int zeroX = zeroPositions.get(i).get(0);
            int zeroY = zeroPositions.get(i).get(1);

            //make copy of filedata2D array since Incorrect turns will be turned into "X"
            ArrayList<ArrayList<Integer>> fileDataTo2DArrCopy = new ArrayList<>();
            for(ArrayList<Integer> a: fileDataTo2DArr){
                fileDataTo2DArrCopy.add(a);
            }

            int searchingFor = 1;
            int currentXPos = zeroX;
            int currentYPos = zeroY;

            int[] checkDeadEnd = new int[]{zeroX,zeroY};
            while(true){
                boolean stuck = false;

                while(true){
                    //check North
                    if(currentXPos - 1 >= 0){
                        if(fileDataTo2DArrCopy.get(currentXPos-1).get(currentYPos) == searchingFor){
                            currentXPos = currentXPos-1;
                            checkDeadEnd = new int[]{currentXPos,currentYPos};
                            searchingFor++;
                            break;
                        }else{
                            System.out.println("North Fail");
                            System.out.println("Didnt find " + searchingFor + " at " + (currentXPos-1) + " " + currentYPos);
                            System.out.println(fileDataTo2DArrCopy.get(currentXPos-1));
                        }
                    }
                    //check south
                    if(currentXPos + 1 < fileDataTo2DArrCopy.size()){
                        if(fileDataTo2DArrCopy.get(currentXPos+1).get(currentYPos) == searchingFor){
                            currentXPos = currentXPos+1;
                            checkDeadEnd = new int[]{currentXPos,currentYPos};
                            searchingFor++;
                            break;
                        }
//                        else{
//                            System.out.println("South Fail: ");
//                            System.out.println(fileDataTo2DArrCopy.get(currentXPos+1));
//                            System.out.println("Didnt find " + searchingFor + " at " + (currentXPos+1) + " " + currentYPos);
//                        }
                    }
                    //check East
                    if(currentYPos + 1 < fileDataTo2DArrCopy.get(currentXPos).size()){
                        if(fileDataTo2DArrCopy.get(currentXPos).get(currentYPos+1) == searchingFor){
                            currentYPos = currentYPos+1;
                            checkDeadEnd = new int[]{currentXPos,currentYPos};
                            searchingFor++;
                            break;
                        }
//                        else{
//                            System.out.println("East Fail");
//                            System.out.println(fileDataTo2DArrCopy.get(currentXPos));
//                            System.out.println("Didnt find " + searchingFor + " at " + currentXPos + " " + (currentYPos+1));
//                        }
                    }
                    //check west
                    if(currentYPos - 1 >= 0){
                        if(fileDataTo2DArrCopy.get(currentXPos).get(currentYPos-1) == searchingFor){
                            currentYPos = currentYPos-1;
                            checkDeadEnd = new int[]{currentXPos,currentYPos};
                            searchingFor++;
                            break;
                        }else{
                            //go back a space
                            System.out.println("West Fail");
                            System.out.println("Going back a space at " + currentXPos + " " + currentYPos);
                            System.out.println(fileDataTo2DArrCopy.get(currentXPos));
                            fileDataTo2DArrCopy.get(checkDeadEnd[0]).set(checkDeadEnd[1], -1);
                            if((currentXPos - 1 > 0 && fileDataTo2DArrCopy.get(currentXPos-1).get(currentYPos) == -1) || currentXPos - 1 <0 &&
                                    (currentXPos + 1 < fileDataTo2DArrCopy.size() && fileDataTo2DArrCopy.get(currentXPos+1).get(currentYPos) == -1) || currentXPos + 1 >= fileDataTo2DArrCopy.size()){
                                stuck = true;
                                break;
                            }
                            currentXPos = zeroX;
                            currentYPos = zeroY;
                            searchingFor--;

                        }
                    }
                    if(!(currentYPos + 1 < fileDataTo2DArrCopy.get(currentXPos).size()) && !(currentXPos + 1 < fileDataTo2DArrCopy.size() || currentXPos - 1 > 0)){

                        fileDataTo2DArrCopy.get(checkDeadEnd[0]).set(checkDeadEnd[1], -1);
                        if(currentYPos == previousYPos){
                            stuck = true;
                            break;
                        }
                        currentXPos = zeroX;
                        currentYPos = zeroY;
                        searchingFor--;
                    }
                }

                if(searchingFor>=9){
                    trailheads++;
                    searchingFor = 1;
                    fileDataTo2DArrCopy.get(checkDeadEnd[0]).set(checkDeadEnd[1], -1);

                }
                if(stuck)break;
                currentXPos = zeroX;
                currentYPos = zeroY;
            }
            System.out.println(trailheads);
        }
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
