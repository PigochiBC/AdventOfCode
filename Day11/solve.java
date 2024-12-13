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
        fileDataAsLongs.put(0L, 0L);
        fileDataAsLongs = calcBlinks(fileDataAsLongs, 0);
        long total = 0;
        for(long x : fileDataAsLongs.keySet()){
            total+=fileDataAsLongs.get(x);
        }
        System.out.println(total);

    }
    public static HashMap<Long,Long > calcBlinks(HashMap<Long, Long> inputHash, int blinks){
        if(blinks==6){
            return inputHash;
        }
        HashMap<Long, Long> newHashMap = new HashMap<>(inputHash);
        for(Long key : inputHash.keySet()){
            long newHashKey = newHashMap.get(key);
            if(key == 0 ){
                newHashMap.put(1L,inputHash.get(key)+newHashMap.get(key));
                newHashMap.put(0L, newHashKey-inputHash.get(key));

            }else if(key.toString().length()%2 == 0L){
                long key1 = Long.parseLong(key.toString().substring(key.toString().length() / 2));
                long key2 = Long.parseLong(key.toString().substring(0,key.toString().length()/2));


                newHashMap.put(key1, inputHash.get(key) + newHashMap.getOrDefault(key1,0L));
                newHashMap.put(key2, inputHash.get(key) + newHashMap.getOrDefault(key2, 0L));
                newHashMap.put(key, newHashKey-inputHash.get(key));
            }
            //rule 3
            else {
                newHashMap.put(2024L*key, inputHash.get(key) + newHashMap.getOrDefault(2024*key,0L));
                newHashMap.put(key, newHashKey-inputHash.get(key));

            }

        }
        System.out.println(newHashMap);
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
