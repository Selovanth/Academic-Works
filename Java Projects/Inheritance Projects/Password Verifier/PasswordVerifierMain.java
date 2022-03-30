package lab.pkg8;
import java.util.Scanner;



public class PasswordVerifierMain {

 
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String userPassword;
        boolean notSatisfactory = true;
        
        System.out.print("Enter a password with these traits:\n1. At least 6 characters long\n2. At least 1 upper and lowercase letter\n3. At least one digit\n\n");
        System.out.print("Enter password: ");
        userPassword = keyboard.nextLine();
        
        do {            
         notSatisfactory = PasswordVerifier.verifier(userPassword);
         if (notSatisfactory == true){
             System.out.print("The entered password did not meet the requirements.\nEnter password: ");
             userPassword = keyboard.nextLine();
         }
        }while(notSatisfactory == true);
    }    
}
