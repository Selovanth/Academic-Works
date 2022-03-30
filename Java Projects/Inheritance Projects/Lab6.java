package lab.pkg6;
import java.util.Scanner;


public class Lab6 {

    public static void main(String[] args) {
        double radius = 0, width = 0, length = 0;
        int height, selection = 0;
        Scanner keyboard = new Scanner(System.in);
        
        do{
        System.out.print("What kind of shape do you want to create? \n1. Circle \n2. Rectangle \n3. Cylinder \n\nEnter a selection between 1-3: ");
            while(!keyboard.hasNextInt()){
                String input = keyboard.next();
                System.out.printf("%s is not a valid input. Enter a whole number between 1-3: ", input);
            }
            selection = keyboard.nextInt();
        }while(selection > 3 || selection < 1);
        keyboard.nextLine();
        
        switch (selection){
            case 1:
                do{
                    System.out.print("\nEnter the radius, greater than 0, of the circle you want to create: ");
                    while(!keyboard.hasNextDouble()){
                        String input = keyboard.next();
                        System.out.printf("%s is not a valid measurement. Input the radius of your desired circle as a decimal: ", input);
                    }
                    radius = keyboard.nextDouble();
                }while(radius <= 0);                
                Area circle = new Area(radius);
                System.out.println(circle);
                break;
            case 2:
                do{
                    System.out.print("\nEnter the length and width, greater than 0, of the rectangle you want to create. \nLength: ");
                    while(!keyboard.hasNextDouble()){
                        String input = keyboard.next();
                        System.out.printf("%s is not a valid measurement. Input the length of your desired rectangle as a decimal: ", input);
                    }
                    length = keyboard.nextDouble();
                    System.out.print("Width: ");
                    while(!keyboard.hasNextDouble()){
                        String input = keyboard.next();
                        System.out.printf("%s is not a valid measurement. Input the width of your desired rectangle as a decimal: ", input);
                    }
                    width = keyboard.nextDouble();
                }while(length <= 0|| width <= 0);
                Area rectangle = new Area(length, width);
                System.out.println(rectangle);
                break;
            case 3:
                do{
                    System.out.print("\nEnter the radius and height, greater than 0, of the cylinder you want to create. \nRadius: ");
                    while(!keyboard.hasNextDouble()){
                    String input = keyboard.next();
                    System.out.printf("%s is not a valid measurement. Input the length of your desired cylinder as a decimal: ", input);
                    }
                    radius = keyboard.nextDouble();
                    System.out.print("Height: ");
                    while(!keyboard.hasNextDouble()){
                    String input = keyboard.next();
                    System.out.printf("%s is not a valid measurement. Input the height of your desired cylinder as a whole number: ", input);
                    }
                    height = keyboard.nextInt();
                }while(radius <= 0 || height <= 0);
                Area cylinder = new Area(radius, height);
                System.out.println(cylinder);
                break;
            default:
                System.out.println("\n\nI was unable to create a shape.");
        }
    } 
}