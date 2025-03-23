import java.util.Scanner;

public class PasswordChecker {
    //variables
    static CharacterSymbols charSymbols = new CharacterSymbols();
    static Scanner scnr = new Scanner(System.in);
    static String input;
    static int minLength = 8;
    static int maxLength = 16;
    static boolean requirements[] = new boolean[5];

    public static void main(String[] args) {
        charSymbols.SetSymbols();
        
        //program informs user how to use it
        System.out.println("===========================================================================");
        System.out.println("Welcome to Password Checker!");
        System.out.println("This program allows you to create and check passwords");
        
        System.out.println("The program checks if your password: ");
        System.out.println("- is between " + minLength + " and " + maxLength + " characters long");
        System.out.println("- contains at least 1 uppercase letter");
        System.out.println("- contains at least 1 lowercase letter");
        System.out.println("- contains at least 1 number from 0-9");
        System.out.println("- contains at least 1 special characters: ~!@#$%^&*()-=+_");

        System.out.println("");

        //program lets user enter passwords infinitely until they wish to exit
        while (1==1) {
            //user inputs password they'd like to try out, program then filters the password by removing whitespace
            System.out.println("Enter a password and press ENTER: ");
            input = scnr.nextLine();
            input = input.replaceAll("\\s+", "");

            System.out.println("");

            //program checks if password is within certain length
            if(input.length() <= maxLength && input.length() >= minLength){

                requirements[0] = true;

                //end if
            } else {

                requirements[0] = false;

            }//end else

            //program checks all other requirements for password
            requirements[1] = CheckPassword(input, charSymbols.upperLetters, true);

            //program informs user what requirements their password meets
            System.out.println("Your password:");
            System.out.println("- is between " + minLength + " and " + maxLength + " characters long: " + requirements[0]);
            System.out.println("- contains at least 1 uppercase letter: " + requirements[1]);
            System.out.println("- contains at least 1 lowercase letter: " + requirements[2]);
            System.out.println("- contains at least 1 number: " + requirements[3]);
            System.out.println("- contains at least 1 special character: " + requirements[4]);
                
            System.out.println("");
            
            //program informs the user of requirements for strong password
            System.out.println("If your password meets at least 4/5 of the above conditions, then it's in good shape");
            System.out.println("If not, then I suggest finding a stronger password");

            System.out.println("");

            //program asks user if they'd like to enter another password
            System.out.println("Would you like to try again?");
            System.out.println("If so, enter 'yes");
            System.out.println("If not, enter anything else");

            //user inputs answer which is filtered like before
            input = scnr.nextLine();
            input = input.replaceAll("\\s+", "");
            input = input.toLowerCase();

            //program terminates if user doesn't input 'yes'
            if(!(input.equals("yes"))){
                System.out.println("Thanks for playing!");
                break;
            }//end if

        }//end while

    }//end main

    public static boolean CheckPassword(String password, char[] checklist, boolean endLoop){
        //password is stored into an array of characters so its contents can be compared
        char[] passChars = password.toCharArray();

        //password is checked for lowercase, numbers, and special characters
        if(endLoop == true){
            requirements[2] = CheckPassword(password, charSymbols.lowerLetters, false);
            requirements[3] = CheckPassword(password, charSymbols.numbers, false);
            requirements[4] = CheckPassword(password, charSymbols.symbols, false);
        }//end if
        
        //method returns true if password contains at least 1 of the elements in the requirement
       for(char i : checklist){
            for(char j : passChars){
                if (i == j){
                    return true;
                }//end if
            }//end for
       }//end for

       //returns false otherwise
       return false;
    }//end CheckPassword

   
}//end class

class CharacterSymbols{
    //variables
    char upperLetters[];
    char lowerLetters[];
    char numbers[];
    char symbols[];

    public void SetSymbols(){
        //each requirement is represented by a char array with corresponding characters(ex: the requirement for capital letters is represented by a char array for capital letters)
        upperLetters = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L',
        'M','N','O','P','Q','R','S','T','U','V','W','X','Y','X'};
        lowerLetters = upperLetters.clone();
        numbers = new char[]{'1','2','3','4','5','6','7','8','9','0'};
        symbols = new char[]{'~','!','@','#','$','%','^','&','*','(',')','-','=','+','_'};

        //to avoid repitition, contents of capital letters array are cloned into lowercase array and turned lowercase
        for(int i = 0; i < lowerLetters.length; i++){

            lowerLetters[i] = Character.toLowerCase(lowerLetters[i]);

        }//end for
    }//end SetSymbols
}//end class
