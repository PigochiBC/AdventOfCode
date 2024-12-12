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
            fileDataAsLongs.put(Long.parseLong(el), 2L);
        }
        fileDataAsLongs.put(1L, 0L);

        calcBlinks(fileDataAsLongs, 0);
    }
    public static HashMap<Long,Long > calcBlinks(HashMap<Long, Long> inputHash, int blinks){
        if(blinks==75){
            return inputHash;
        }
        HashMap<Long, Long> newHashMap = new HashMap<>(inputHash);
        for(Long key : inputHash.keySet()){
            if(key == 0 && inputHash.get(0L) > 0){
                newHashMap.replace(1L, inputHash.get(0L));
                newHashMap.replace(0L, 0L);
            }else if(key.toString().length()%2 == 0L && inputHash.get(key) > 0){
                long key1 = Long.parseLong(key.toString().substring(key.toString().length() / 2));
                long key2 = Long.parseLong(key.toString().substring(0,key.toString().length()/2));

                newHashMap.putIfAbsent(key1, inputHash.get(key));
                newHashMap.putIfAbsent(key2, inputHash.get(key));

                newHashMap.replace(key1,inputHash.get(key));
                newHashMap.replace(key2, inputHash.get(key));
                newHashMap.replace(key, 0L);
            }
            //rule 3
            else if(inputHash.get(key) > 0){
                newHashMap.putIfAbsent(2024L*key, inputHash.get(key));
                newHashMap.replace(2024L*key, inputHash.get(key));
                newHashMap.replace(key, 0L);

            }

        }
        System.out.println(inputHash);
        return calcBlinks(newHashMap, blinks+1);
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
