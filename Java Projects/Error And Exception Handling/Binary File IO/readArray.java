package lab.pkg10;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class readArray {
    
    static public void readContents(String fileName, int userNumArray[]) throws FileNotFoundException, IOException{
        DataInputStream fileIn = new DataInputStream(new FileInputStream(fileName));
        for(int element = 0; element < userNumArray.length; element++){
            System.out.print("\nInput file number  " + (element + 1) + "\\" + userNumArray.length + ": " + fileIn.readInt());
        }
    }
}
