package lab.pkg6;
import java.util.Scanner;


public class CarpetCalculator {

    public static void main(String[] args) {
        double length, width, carpetCost;
        Scanner keyboard = new Scanner(System.in);
        
        do{
            System.out.print("Enter the positive length and width of a room you which to calculate the carpeting cost of.\nLength: ");
            while(!keyboard.hasNextDouble()){
                String input = keyboard.next();
                System.out.printf("%s is not a valid input. Enter the length of your room: ", input);
            }
            length = keyboard.nextDouble();
            System.out.print("Width: ");
            while(!keyboard.hasNextDouble()){
                String input = keyboard.next();
                System.out.printf("%s is not a valid input. Enter the width of your room: ", input);
            }
            width = keyboard.nextDouble();
            System.out.print("Enter the cost of carpet per square foot as a positive number: ");
            while(!keyboard.hasNextDouble()){
                String input = keyboard.next();
                System.out.printf("%s is not a valid input. Enter the cost of carpet per square foot: ", input);
            }
            carpetCost = keyboard.nextDouble();
        }while(length <= 0 || width <= 0 || carpetCost <= 0);
        
        RoomDimension newRoom = new RoomDimension(length, width);
        RoomCarpet roomCost = new RoomCarpet(newRoom, carpetCost);
        System.out.println(roomCost);
        
    }
}
