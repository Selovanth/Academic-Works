package lab.pkg10;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class FileArrayMain {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String fileToRead, fileToWrite;
        boolean fileWriteGood = true, fileReadGood = true;
        int intArraysize = 0, userIntArray[];
        
        do{
            fileWriteGood = true;
            fileReadGood = true;
            
            System.out.print("Enter the absolute path for an output binary file: ");
            fileToWrite = keyboard.nextLine();
            do{
                System.out.print("\nEnter a size for an integer array: ");         
                while(!keyboard.hasNextInt()){
                    String input = keyboard.next();
                    System.out.printf("%s is invalid. Enter a whole number: ", input);
                }
                intArraysize = keyboard.nextInt();
                if(intArraysize <= 0)
                    System.out.printf("\n%d is invalid. Enter a whole number greater than 0.", intArraysize);
            }while(intArraysize <= 0);
            userIntArray = new int[intArraysize];
            
            
            for(int element = 0; element < userIntArray.length; element++){
                System.out.print("Enter number " + (element + 1) + "\\" + userIntArray.length + ": ");
                while(!keyboard.hasNextInt()){
                    String input = keyboard.next();
                    System.out.printf("%s is invalid. Enter a number: ", input);
                }
                userIntArray[element] = keyboard.nextInt();
            }
            
            try{
                writeArray.writeContents(fileToWrite, userIntArray);
            }catch(FileNotFoundException e) {
                fileReadGood = false;
                System.out.print("\n\n---------------------FILE NOT FOUND---------------------\n\n");
            }
            catch(IOException e){
                fileWriteGood = false;
                System.out.print("\n\n---------------------BAD I/O OR END OF FILE REACHED---------------------");
            }
        }while(fileReadGood == false | fileWriteGood == false);
        keyboard.nextLine();
        
        
        do{            
            fileWriteGood = true;
            fileReadGood = true;
            
            System.out.print("Enter the absolute path for an input binary file: ");
            fileToRead = keyboard.nextLine();
            userIntArray = new int[intArraysize];
            try{
                readArray.readContents(fileToRead, userIntArray);
            }catch(FileNotFoundException e){
                System.out.print("\n\n---------------------FILE NOT FOUND---------------------\n\n");
                fileReadGood = false;
            }
            catch(IOException e){
                fileWriteGood = false;
                System.out.print("\n\n---------------------BAD I/O OR END OF FILE REACHED---------------------");
            }
        }while(fileReadGood == false || fileWriteGood == false);
        System.out.println("\n\n");
    }
}
