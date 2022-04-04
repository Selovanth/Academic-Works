package lab.pkg10;
import java.util.Scanner;


public class TestScoresMain {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        TestScores userTests;
        double testScoresIn[];
        int numOfTest = 0;
        boolean badTests = false;
        
        
        do{
            do{        
                System.out.print("How many test scores are you entering?: ");
                while(!keyboard.hasNextInt()){
                String input = keyboard.next();
                System.out.printf("%s is invalid. Enter a positive number: ", input);
                }
                numOfTest = keyboard.nextInt();
            }while(numOfTest <= 0);
            testScoresIn = new double[numOfTest];
            
            try{
                for(int element = 0; element < testScoresIn.length; element++){
                System.out.print("Enter score for test number " + (element + 1) + ": ");
                    while(!keyboard.hasNextInt()){
                        String input = keyboard.next();
                        System.out.printf("%s is invalid. Enter a number: ", input);
                    }
                testScoresIn[element] = keyboard.nextInt();
                }
                userTests = new TestScores(testScoresIn);
                System.out.printf("\nAverage of all tests is: %.2f", userTests.testScoresAverage());
                System.out.println("\n\n\n");
            }catch(IllegalArgumentException e){
                badTests = true;
                System.out.print(e.getMessage());
                for(int element = 0; element < testScoresIn.length; element++){
                    if(testScoresIn[element] < 0)
                        System.out.print("Test score #" + (element + 1) + " is invalid. Test scores entered must be positive.\n");
                }
            }    
        }while(badTests == true);
    }
}