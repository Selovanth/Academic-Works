package lab.pkg8;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SentenceCapitalizer {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String userString;
        
        System.out.print("Enter any number of sentences ended with a period to have the first letter of each sentence capitalized.\nEnter input: ");
        userString = keyboard.nextLine();
        userString = userString.trim();
        
        System.out.println("The entered input with each sentence capitalized is:\n\n" + capitalizer(userString) + "\n\n");
    }
    
    public static String capitalizer(String str){
        StringTokenizer tokens = new StringTokenizer(str, ".", true);
        String tokenTemp, tokenReturn = "";
        char lCase, uCase, isLetter;
        
        
        while(tokens.hasMoreTokens()){
            int i = 0;
            tokenTemp = tokens.nextToken();
            
            if(tokenTemp == ".")
                tokenTemp = tokens.nextToken();
            
            if(Character.isWhitespace(tokenTemp.charAt(i))){
                while(Character.isWhitespace(tokenTemp.charAt(i))){
                    i++;
                }
                tokenReturn = tokenReturn.concat(" " + tokenTemp.substring(i, (i + 1)).toUpperCase() + tokenTemp.substring((i + 1)));
            }
            else
                tokenReturn = tokenReturn.concat(tokenTemp.substring(0 , 1).toUpperCase() + tokenTemp.substring(1));  
        }        
        return tokenReturn;
    }
}