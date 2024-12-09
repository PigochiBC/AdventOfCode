package Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class solve {

    public static void main(String[] args){
        ArrayList<String> fileData = getFileData("input.txt");
        ArrayList<Integer> sortedFileData = new ArrayList<>();
        ArrayList<String> outputFileData = new ArrayList<>();
        for(int i = 0; i < fileData.size(); i++){
            for(int j = 0; j < fileData.get(i).length(); j++){
                sortedFileData.add(Integer.parseInt(fileData.get(i).substring(j,j+1)));
            }
        }
        Integer idCount = 0;
        //sort into .'s and ids
        for(int i = 0; i < sortedFileData.size(); i++){
            if(i%2 == 0){
                for(int j = 0; j <sortedFileData.get(i); j++ ){
                    outputFileData.add(idCount.toString());
                }
                idCount= idCount+1;
            }else{
                for(int k = 0; k < sortedFileData.get(i); k++){
                    outputFileData.add(".");
                }
            }
        }

        //sorting algorithm to swap empty space with rightmost ID
        for(int i = 0; i < outputFileData.size(); i++){
            if(outputFileData.get(i).equals(".")){

                int toSwapIndex = i;
                int lengthOfFile = 0;
                int fileStartIndex = 0;
                for(int j = outputFileData.size()-1; j > i ; j--){
                    if(!outputFileData.get(j).equals(".")){
                        lengthOfFile = 0;
                        for(int x = j; x > i; x--){
                            if(!outputFileData.get(x).equals(".") && outputFileData.get(x).equals(outputFileData.get(j)) ){
                                lengthOfFile++;
                                fileStartIndex = j;
                            }else{
                                break;
                            }
                        }
                        for(int z = 0; z < outputFileData.size(); z++){
                            int lengthOfEmpty = 1;
                            for(int k = outputFileData.size()-1; k < z; k++){
                                if(outputFileData.get(k-1).equals(".")){
                                    lengthOfEmpty++;
                                }else{
                                    lengthOfEmpty = 0;

                                }
                            }
                            if(lengthOfEmpty>=lengthOfFile){
                                for(int y = 0; y < lengthOfFile; y++){
                                    System.out.println(lengthOfFile + " " + lengthOfEmpty);
                                    outputFileData.set(toSwapIndex+y, outputFileData.get(fileStartIndex-y));
                                    outputFileData.set(fileStartIndex-y, ".");
                                    System.out.println(outputFileData);
                                }
                            }
                        }

                    }

                }




            }
        }
        System.out.println(outputFileData);
        //solution
        Long total = 0L;
        for(int i = 0; i < outputFileData.size(); i++){
            if(!outputFileData.get(i).equals(".")){
                total+=Integer.parseInt(outputFileData.get(i))*i;
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
