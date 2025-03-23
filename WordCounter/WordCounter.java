import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
//import java.io.*;

public class WordCounter {
    //scanner variable
    static Scanner scnr = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        //variables
        String input;
        ArrayList<String> listOfWords = new ArrayList<String>();
        
        //program introduces user
        System.out.println("======================================================");
        System.out.println("Welcome to Word Counter!");
        System.out.println("In this program you can store random words into a file");
        System.out.println("and count how many times each words appears");
        System.out.println("and store THAT into a different file");
        System.out.println("the program comes with a file of random, uncounted words");
        System.out.println("simply follow the prompts and press ENTER to submit a response");
        System.out.println("NOTE: the program will automatically filter out whitespace and replace capital letters with lowercase");

        //program loops so user can write and count as many times as they'd like
        while(1 == 1){
            System.out.println("");

            //program gives user list of options
            System.out.println("The following options are below:");
            System.out.println("- to write a bunch of words into a file, enter 'write'");
            System.out.println("- to count words from the file, enter 'count'");
            System.out.println("enter your input:");
            
            //program gets user input
            input = FilterAnswer();

            System.out.println("");

            
            if(input.equals("write")){
                //program asks user to enter the words they'd like to store
                System.out.println("You have chosen to write to a file");
                System.out.println("Write all the words, separated by spaces, that you'd like to store below: ");

                //user enters words
                input = scnr.nextLine();

                //program stores them in a file
                WriteFile(input);                

            }else if(input.equals("count")){
                //program informs user of their chosen optino
                System.out.println("You have chosen to count words");

                System.out.println("");

                //file with uncounted words is extracted and stored into a list
                listOfWords = ExtractFile();

                System.out.println("");

                //program shows user each word and it number of recursion in the file
                System.out.println("Below are the extracted words followed by how many times they occur: ");

                //words are counted and saved to a new file
                CountWords(listOfWords);                

            }else{
                //program informs user that his input didn't match the options
                System.out.println("Your answer did not match one of our options, please try again");
            }

            System.out.println("");

            //program asks user if they'd like to go again and waits for input
            System.out.println("Would you like to go again? If so, press yes");
            System.out.println("If not, enter any other value");

            //program takes user input
            input = FilterAnswer();

            //loop ends if user wishes to stop
            if(!(input.equals("yes"))){
                System.out.println("Thanks for playing!");
                break;
            }
        }
    }

    public static String FilterAnswer(){
        //method takes user input, program then filters out whitespace and swaps uppercase letters
        String answer = scnr.nextLine();
        answer = answer.replaceAll("\\s+", "");
        answer = answer.toLowerCase();

        //method returns filtered answer
        return answer;
    }

    public static void WriteFile(String sentence){
        try(BufferedWriter bfw = new BufferedWriter(new FileWriter("UnsortedWords.txt"))){
            //method swaps out capitalized letters to avoid capitalization issues
            sentence = sentence.toLowerCase();
            //sentence is split into words and stored into an array
            String[] words = sentence.split(" ");

            //each word is written in it's own line
            for(String word : words){
                bfw.write(word);
                bfw.newLine();
            }

            //method notifies user of successful writing
            System.out.println("Your words have been saved to a file");

        }catch(Exception e){    System.out.println(e); }     
    }

    public static ArrayList<String> ExtractFile(){
        //variables
        ArrayList<String> extractedWords = new ArrayList<String>();
        String extract;

        try(BufferedReader bfr = new BufferedReader(new FileReader("UnsortedWords.txt"))){

            //method extracts a line and from file and adds it to an array list
            do{
                extract = bfr.readLine();
                extractedWords.add(extract);
            }while(extract != null);

            //null element is removed
            extractedWords.remove(extractedWords.size() - 1);


            //method notifies user of successful extraction
            System.out.println("File has been succesfully extracted");

        }catch(Exception e){ System.out.println(e);}

        //method returns the extracted list
        return extractedWords;
    }

    public static void CountWords(ArrayList<String> wordsList){
        //treemap that stores the counted words
        TreeMap<String, Integer> countedWords = new TreeMap<>();

        //method loops as long as string has elements
        while(wordsList.size() != 0){
            //first element & size of list is stored, counter is set to 0
            String word = wordsList.get(0);
            int listSize = wordsList.size();
            int counter = 0;

            //method keeps track of and removes any elements equal to the first
            for(int i = 0; i < listSize; i++ ){
                if(word.equals(wordsList.get(i))){
                    counter++;
                    listSize--;
                    wordsList.remove(i);
                    i--;
                }
            }
            //word along with all the number of instances is printed out so user can see
            System.out.println(word + " " + counter);
            //word along with all the number of instances is stored into a TreeMap
            countedWords.put(word, counter);

        }

        try(BufferedWriter bfw = new BufferedWriter(new FileWriter("SortedWords.txt"))){
            //each key along with its value is stored to a file
            for(Map.Entry<String,Integer> countedWord : countedWords.entrySet()){
                bfw.write(countedWord.getKey() + " " + countedWord.getValue());
                bfw.newLine();
            }

            //user is notified of counted wowrds
            System.out.println("Your counted words have been saved to a file");

        }catch(Exception e){    System.out.println(e); }
    }
}
