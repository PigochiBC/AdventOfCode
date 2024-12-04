package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class solve {
    public static void main(String[] args){
        ArrayList<String> fileData = getFileData("input.txt");
        System.out.println(fileData);

        int total =0;
        for(int i =0; i < fileData.size()-2; i++){

            for(int j = 0; j < fileData.get(i).length()-2; j++){
                /*
                    Northeast : S*S    M*S
                                *A* OR *A*
                                M*M    M*S

                    Northwest : S*S    S*M
                                *A* OR *A*
                                M*M    S*M

                    Southeast : M*M     M*S
                                *A* OR  *A*
                                S*S     M*S

                    Southwest : S*M     M*M
                                *A* OR  *A*
                                S*M     S*S

                    Check diagonals -> check if they spell SAM / MAS -> next index.
                    Array Max = length -2 (starting upper left for all)

                 */
                try{
                    if(checkForWord(fileData, i, j)){
                        total++;
                    }
                }catch(IndexOutOfBoundsException e){

                }

            }
        }
        System.out.println(total);
    }

    //recursively check 2D array for XMAS in every direction
    public static boolean checkForWord(ArrayList<String> inputArray, int row, int column){
        String neDiagonal = inputArray.get(row).charAt(column) + "" + inputArray.get(row+1).charAt(column+1) + "" + inputArray.get(row+2).charAt(column+2);
        String seDiagonal = inputArray.get(row+2).charAt(column) + "" + inputArray.get(row+1).charAt(column+1) + "" + inputArray.get(row).charAt(column+2);
        boolean samOrmasNE = neDiagonal.equals("SAM") || neDiagonal.equals("MAS");
        boolean samOrMasSE = seDiagonal.equals("SAM") || seDiagonal.equals("MAS");
        return samOrMasSE && samOrmasNE;
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
