import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class solve {
    public static void main(String[] args){
        ArrayList<String> fileData = getFileData("input.txt");
        String[] fileDataSplit = fileData.getFirst().split(" ");
        ArrayList<Long> fileDataAsLongs = new ArrayList<>();
        for(String el: fileDataSplit){
            fileDataAsLongs.add(Long.parseLong(el));
        }
        /*
            Rules in this order:
            Stones that are 0 : Turn into 1
            Stones with even number of digits: split into 2 stones (Middle Left digit, middle right digit), leading 0's not kept.
            Neither 0 nor even digits: multiplied by 2024.
         */
        for(int i = 0 ; i < 25; i ++){
            for(int j = 0; j < fileDataAsLongs.size(); j++){
                //rule 1
                if(fileDataAsLongs.get(j)==0L){
                    fileDataAsLongs.set(j, 1L);
                }
                //rule 2
                else if(fileDataAsLongs.get(j).toString().length()%2==0L){

                    String temp = fileDataAsLongs.get(j).toString();
                    //System.out.println(j +" " + fileDataAsLongs);
                    Long leftHalf = Long.parseLong(temp.substring(0,temp.length()/2));
                    Long rightHalf = Long.parseLong(temp.substring(temp.length()/2));
                    fileDataAsLongs.set(j, leftHalf);
                    fileDataAsLongs.add(j+1,rightHalf);
                    j++;
                }
                //rule 3
                else{
                    fileDataAsLongs.set(j, fileDataAsLongs.get(j)*2024);
                }
            }
        }
        System.out.println(fileDataAsLongs.size());
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
