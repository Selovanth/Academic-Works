package lab.pkg9;
import java.util.Scanner;

public class PersonandCustomers {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String phoneNumber[], name[], address[];
        char mailList[];
        int entryNumbers = 0, loopCount = 0, dashCount = 0, digitCount = 0, customerID[];
        boolean goodNumber = true;
        Customer customer[];
        
        System.out.print("How many customers do you want to enter data for?\nAmount: ");
        
        do{
            while(!keyboard.hasNextInt()){
                String input = keyboard.next();
                System.out.printf("%s is invalid. Enter a number greater than 0: ", input);
            }
            entryNumbers = keyboard.nextInt();
            if(entryNumbers <= 0){
                System.out.print(entryNumbers + " is invalid. Enter a number greater than 0: ");
            }
            keyboard.nextLine();
        }while(entryNumbers <= 0);
        customer = new Customer[entryNumbers];
        phoneNumber = new String[entryNumbers];
        name = new String[entryNumbers];
        address = new String[entryNumbers];
        mailList = new char[entryNumbers];
        customerID = new int[entryNumbers];
        
        
        
        do{            
            System.out.print("Enter an ID number for customer " + (loopCount + 1) + ": ");
            while(!keyboard.hasNextInt() || !keyboard.hasNextDouble()){
                String input = keyboard.next();
                System.out.printf("%s is invalid. Enter numbers for the customer ID: ", input);
            }
            customerID[loopCount] = keyboard.nextInt();
            loopCount++;
        }while(loopCount < entryNumbers);
        loopCount = 0;
        
        keyboard.nextLine();
        do{
            System.out.print("Enter a 10 digit phone number for customer " + (loopCount + 1) + ": ");
            phoneNumber[loopCount] = keyboard.nextLine();
            String temp = phoneNumber[loopCount];
            phoneNumber[loopCount] = temp.trim();
            for(int element = 0; element < phoneNumber[loopCount].length(); element++){
                if(Character.isAlphabetic(phoneNumber[loopCount].charAt(element)) || Character.isWhitespace(phoneNumber[loopCount].charAt(element))){
                    System.out.printf("%s is invalid. Enter only numbers.\n", phoneNumber[loopCount]);
                    goodNumber = false;
                    break;
                }
                else
                    goodNumber = true;
            }
            if(phoneNumber[loopCount].length() < 10 || phoneNumber[loopCount].length() > 10)
                System.out.printf("%s is invalid. Enter a 10 digit phone number", phoneNumber[loopCount]);
            if(goodNumber == true && phoneNumber[loopCount].length() == 10)
                loopCount++;            
        }while(loopCount < entryNumbers);
        loopCount = 0;
        
        
        
        do{
            System.out.print("Enter a name for customer " + (loopCount + 1) + ": ");
            name[loopCount] = keyboard.nextLine();
            loopCount++;
        }while(loopCount < entryNumbers);
        loopCount = 0;

        
        
        
        do{            
            System.out.print("Enter an address for customer " + (loopCount + 1) + ": ");
            address[loopCount] = keyboard.nextLine();
            loopCount++;
        }while(loopCount < entryNumbers);
        loopCount = 0;

        
        
        
        do{            
            System.out.print("Subscribe customer " + (loopCount + 1) + " to the mailing list? Y/N: ");
            mailList[loopCount] = keyboard.nextLine().charAt(0);
            loopCount++;
        }while(loopCount < entryNumbers);
        
        for(int element = 0; element < customer.length; element++){
            customer[element] = new Customer(name[element], address[element], phoneNumber[element], mailList[element]);
        }
        
        for(int element = 0; element < customer.length; element++){
            System.out.print("\t\n\nCUSTOMER DETAILS\n--------------------------------------------\nCUSTOMER #" + (customer[element].getCustomerID() + 1) + "\n------------\nName: " + customer[element].getname());
            System.out.print("\nAddress: " + customer[element].getaddress() + "\nPhone Number: " + customer[element].getphone() + "\nAdd to Mailing List: ");
            if(customer[element].getMailStatus())
                System.out.print("Y");
            else
                System.out.print("N");
        }
        
        System.out.print("\n\n\n");
    }
}
