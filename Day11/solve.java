import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class solve {
    public static void main(String[] args){
        ArrayList<String> fileData = getFileData("input.txt");
        String[] fileDataSplit = fileData.getFirst().split(" ");
        HashMap<Long, Long> fileDataAsLongs = new HashMap<>();
        for(String el: fileDataSplit){
            fileDataAsLongs.put(Long.parseLong(el), 1L);
        }
        fileDataAsLongs.put(1L, 0L);

        /*
            Rules in this order:
            Stones that are 0 : Turn into 1
            Stones with even number of digits: split into 2 stones (Middle Left digit, middle right digit), leading 0's not kept.
            Neither 0 nor even digits: multiplied by 2024.
         */
        calcBlinks(fileDataAsLongs, 0);
        System.out.println(fileDataAsLongs);
    }
    public static HashMap<Long,Long > calcBlinks(HashMap<Long, Long> inputHash, int blinks){
        if(blinks==75){
            return inputHash;
        }
        System.out.println(inputHash);
        for(Long key : inputHash.keySet()){
            //rule 1
            if(key == 0 && inputHash.get(0L) > 0){
                inputHash.put(1L, inputHash.get(1L)+1L);
                inputHash.put(0L, inputHash.get(0L)-1L);
                calcBlinks(inputHash, blinks+1);
            }
            if(key == 1 && inputHash.get(1L) > 0){
                inputHash.put(1L, inputHash.get(1L)-1L);
                inputHash.put(2024L, inputHash.get(2024L) -1L);
            }
        }
        return null;
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
