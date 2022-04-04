package lab.pkg10;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class writeArray {
    
    static public void writeContents(String fileName, int userNumArray[]) throws FileNotFoundException, IOException{
        DataOutputStream fileOut = new DataOutputStream(new FileOutputStream(fileName));
        for(int element = 0; element < userNumArray.length; element++){
            fileOut.writeInt(userNumArray[element]);
        }
        fileOut.close();
    }
}
