package lab.pkg8;

public class PasswordVerifier {
    private static int noLower = 0, noUpper = 0, noDigit = 0, noLetter = 0;
    private static boolean allLower = false, allUpper = false, noDigits = false, noLetters = false, tooShort = false, notSatisfactory = false;
    private static char ch1;
    
    public static boolean verifier(String str){
        
        for(int element = 0; element < str.length(); element++){
            ch1 = str.charAt(element);
            if (Character.isLetterOrDigit(ch1) != true){
                noLetter++;
            }
            if(Character.isUpperCase(ch1) != true)
                noUpper++;
            if(Character.isLowerCase(ch1) != true)
                noLower++;
            if(Character.isDigit(ch1) != true)
                noDigit++;
            }
        
            if(noLower == str.length()){
                allLower = true;
                noLower = 0;
            }
            else{
                allLower = false;
                noLower = 0;
            }
            if(noUpper == str.length()){
                allUpper = true;
                noUpper = 0;
            }
            else{
                allUpper = false;
                noUpper = 0;
            }
            if(noDigit == str.length()){
                noDigits = true;
                noDigit = 0;
            }
            else{
                noDigits = false;
                noDigit = 0;
            }
            if(noLetter == str.length()){
                noLetters = true;
                noLetter = 0;
            }
            else{
                noLetters = false;   
                noLetter = 0;
            }
            if(str.length() < 6)
                tooShort = true;
            else
                tooShort = false;
            if(noLetters == true || noDigits == true || allUpper == true || allLower == true || tooShort == true){
                notSatisfactory = true;
            }
            else
                notSatisfactory = false;
        return notSatisfactory;
    }    
}
