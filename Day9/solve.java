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

        /*
             Start with a length of IDs
             THEN iterate to find a length of .'s <= length of IDs
         */
        for(int i = outputFileData.size()-1; i >=0; i--){
            int lengthOfEmpty = 0;
            int emptyStartIndex = 0;
            int lengthOfFile = 0;
            int fileStartIndex = 0;

            if(!outputFileData.get(i).equals(".")){
                //get the length of nums
                for(int j = i; j >= 0; j--){
                    if(outputFileData.get(j).equals(".") || !outputFileData.get(j).equals(outputFileData.get(i))){
                        //set file index to j to get the string of num bers
                        //set i to j to find next number available
                        fileStartIndex = j+1;
                        i = j+1;
                        break;
                    }else{
                        lengthOfFile++;
                    }
                }

                for(int jj=0; jj <= i; jj++){
                    if(outputFileData.get(jj).equals(".")){
                        lengthOfEmpty++;
                        if(lengthOfFile <= lengthOfEmpty){
                            for(int k = 0; k < lengthOfFile; k++){
                                outputFileData.set(emptyStartIndex+1+k, outputFileData.get(fileStartIndex+k));
                                outputFileData.set(fileStartIndex+k, ".");
                            }
                            //end loop to make sure its not replaced in more than one spot
                            break;
                        }
                    }else{
                        emptyStartIndex = jj - lengthOfEmpty;
                        lengthOfEmpty = 0;
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
