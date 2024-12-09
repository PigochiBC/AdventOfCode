package Day8;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class solve {
    public static void main(String[] args){
        ArrayList<String> fileData =getFileData("Day8/input.txt");
        ArrayList<String> antiNodeLocations= new ArrayList<>();
        ArrayList<Character> nodes = new ArrayList<>();
        

        for(int i = 0; i < fileData.size(); i++){
            antiNodeLocations.add("");

            for(int j=0; j < fileData.get(i).length(); j++){
                antiNodeLocations.set(i, antiNodeLocations.get(i)+".");
                if(fileData.get(i).charAt(j)!=".".toCharArray()[0]){
                    if(!nodes.contains(fileData.get(i).charAt(j)))nodes.add(fileData.get(i).charAt(j));
                }
            }
        }
        
        for(int i = 0; i < nodes.size(); i++){
            ArrayList<String> nodesAlone = removeExtraNodes(fileData,nodes.get(i));
            int nodeAppearanceInFileData = calculateNodeAppearance( fileData, nodes.get(i));
            
            //Iterate through every node location and compare to the others
            for(int j = 0; j < nodeAppearanceInFileData; j++){
                ArrayList<ArrayList<Integer>> location = location(fileData, nodes.get(i));

                //location of current node
                int xLoc = location.get(j).get(0);
                int yLoc = location.get(j).get(1);
                
                //compare to other nodes
                for(int x = 0; x < location.size(); x++){
                    
                    if(x!=j){
                        int xTarget = location.get(x).get(0);
                        int yTarget = location.get(x).get(1);

                        int rise = xTarget - xLoc;
                        int run = yTarget - yLoc;

                        int currentX = xLoc, currentY = yLoc;
                        while (true) {
                            currentX += rise;
                            currentY += run;
                            
                            
                            if (currentX < 0 || currentX >= nodesAlone.size() || currentY < 0 || currentY >= nodesAlone.get(currentX).length()) {

                                break;
                            }

                            if (antiNodeLocations.get(currentX).charAt(currentY) == '.') {
                                
                                String updatedRow = antiNodeLocations.get(currentX).substring(0, currentY) + "#" + antiNodeLocations.get(currentX).substring(currentY + 1);
                                antiNodeLocations.set(currentX, updatedRow);
                            } 
                        }
                    }
                    
                }
                
               
            }    

            
        }
        for(String element:antiNodeLocations){
            System.out.println(element);
        }  
        int total = 0;    
        for(int i = 0; i < antiNodeLocations.size(); i++){
            for(int j = 0; j < antiNodeLocations.get(i).length();j++ ){
                if(antiNodeLocations.get(i).charAt(j)=='#'){
                    total++;
                }
            }
        }   
        System.out.println(total);
    }
    public static int calculateNodeAppearance(ArrayList<String> fileData, char toFind){
        int nodeAppearanceInFileData = 0;
        for(int j = 0; j < fileData.size(); j++){
            for(int k = 0; k < fileData.size(); k++){
                if(fileData.get(j).charAt(k)==toFind){
                    nodeAppearanceInFileData++;
                }
            }
        }
        return nodeAppearanceInFileData;
    }
    public static ArrayList<ArrayList<Integer>> location(ArrayList<String> fileData, char toFind){
        ArrayList<Integer> location = new ArrayList<>();
        ArrayList<ArrayList<Integer>> locationArray = new ArrayList<>();
        for(int j = 0; j < fileData.size(); j++){
            location = new ArrayList<>();
            for(int k = 0; k < fileData.size(); k++){
                if(fileData.get(j).charAt(k)==toFind){
                    location.add(j);
                    location.add(k);
                    locationArray.add(location);
                }
            }
            
        }
        return locationArray;
    }
    public static ArrayList<String> removeExtraNodes(ArrayList<String> input, char nodeToIsolate){
        ArrayList<String> copy = new ArrayList<>();
        for(String x : input){
            copy.add(x);
        }
        for(int i = 0; i < copy.size(); i++){
            for(int j = 0; j < copy.get(i).length(); j++){
                if(copy.get(i).charAt(j)!=nodeToIsolate && copy.get(i).charAt(j)!=".".toCharArray()[0]){
                    copy.set(i, copy.get(i).substring(0,j) + "." + copy.get(i).substring(j+1));
                }
            }
        }
        return copy;
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
