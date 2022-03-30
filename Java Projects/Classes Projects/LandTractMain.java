package lab.pkg6;
import java.util.Scanner;

public class LandTractMain {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        double length, width;
        
        do{
            System.out.print("Enter the length and width of two tracts of lands you wish to calculcate the area of.\nFirst tract length: ");
            while(!keyboard.hasNextDouble()){
                String input = keyboard.next();
                System.out.printf("%s is not a valid input. Enter a whole number or a decimal: ", input);
            }
            length = keyboard.nextDouble();
            System.out.print("First tract width: ");
            while(!keyboard.hasNextDouble()){
                String input = keyboard.next();
                System.out.printf("%s is not a valid input. Enter a whole number or a decimal: ", input);
            }
            width = keyboard.nextDouble();
        }while(length <= 0 || width <= 0);
        
        LandTract tract1 = new LandTract(length, width);
        System.out.println(tract1);
        
        do{
            System.out.print("\nSecond tract length: ");
            while(!keyboard.hasNextDouble()){
                String input = keyboard.next();
                System.out.printf("%s is not a valid input. Enter a whole number or a decimal: ", input);
            }
            length = keyboard.nextDouble();
            System.out.print("Second tract width: ");
            while(!keyboard.hasNextDouble()){
                String input = keyboard.next();
                System.out.printf("%s is not a valid input. Enter a whole number or a decimal: ", input);
            }
            width = keyboard.nextDouble();
        }while(length <= 0 || width <= 0);
        
        LandTract tract2 = new LandTract(length, width);
        System.out.println(tract2);
        
        if (tract1.equals(tract2))
            System.out.println("The two land tracts are equal");
        else
            System.out.println("The two land tract are not equal");
    }
}