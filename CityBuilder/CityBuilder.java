import java.util.*;
import java.io.*;

public class CityBuilder extends CityHall {
    Scanner scnr = new Scanner(System.in);
    int districts;
    int buildings;
    ArrayList<String> districtList = new ArrayList<String>();
    String[] district;

    House standardHouse = new House();
    Apartment standardApartment = new Apartment();
    Retail localMarket = new Retail();
    Restaurant familyRestaurant = new Restaurant();
    EmergencyService PoliceStation = new EmergencyService();
    
    public void main(String[] args) throws Exception {
        CityManagement();
    }

    public void CityManagement(){
        //program introduces user
        System.out.println("Welcome to city manager, this program lets you create cities by creating districts");
        System.out.println("Each district has 8 buildings and a road in the middle");
        System.out.println("Simply follow the prompts and press ENTER to input a value");
        System.out.println("");
        String input;

        while(1==1){
            //program gives user list of buildings, inputing one of them gives their purpose
            System.out.println("Below are our the following buildings you can build with, enter them to learn their functions");
            System.out.println("- House");
            System.out.println("- Apartment");
            System.out.println("- Local Market");
            System.out.println("- Family Restaurant");
            System.out.println("- Police Station");

            System.out.println("");

            //program gives user option to create a district
            System.out.println("If you'd like to create a district, enter 'create'");

            //user enters input, program filters it by removing whitespace and switching uppercase letters
            input =  scnr.nextLine();
            input = input.replaceAll("\\s+", "");
            input = input.toLowerCase();

            System.out.println("-------------------------------------------------");

            //program takes different directions based on user input, first 5 print out definition for a building, 6th one lets user create district
            switch(input){
                case "house" :
                    standardHouse.PrintPurpose();
                break;
                case "apartment" :
                    standardApartment.PrintPurpose();
                break;
                case "localmarket" :
                    localMarket.PrintPurpose();
                break;
                case "familyrestaurant" :
                    familyRestaurant.PrintPurpose();
                break;
                case "policestation" :
                    System.out.println("A police station is a base of operations for police officers");                
                break;
                case "create":
                    //program asks user for name of new district
                    System.out.println("You have chosen to create a district");
                    System.out.println("But first, you need a name, enter it below:");

                    //user enters input and answer is filtered
                    input = scnr.nextLine();
                    input = input.replaceAll("\\s+", "");
                    districtList.add(input);

                    //district is created
                    AddBuildings(input);
                    
                break;
                default:
                    //program informs user that their input isn't an option
                    System.out.println("Your input did not match our options, please try again");
                break;
            }
            //program asks user if they'd like to go again
            System.out.println("-------------------------------------------------");
            System.out.println("Would you like to go again? if so enter 'yes'");
            System.out.println("If not, enter any other value");

            //user enters input which is then filtered
            input = scnr.nextLine();
            input = input.replaceAll("\\s+", "");
            input = input.toLowerCase();

            //program breaks loop if user doesn't input 'yes'
            if(!(input.equals("yes"))){
                System.out.println("Thanks for playing!");
                break;
            }
            
        }



    }

    private int CreateDistrict(String name){
        //variables
        int input;
        name += ".txt";
        String[] buildings = {"House", "Apartment", "Local Market", "Family Restaurant", "Police Station"};
        district = new String[8];

        try(BufferedWriter bfw = new BufferedWriter(new FileWriter(name))){
            //program asks user to enter each individual building
            for(int i = 0; i < 8; i++){
                //program gives user options for buildings
                System.out.println("Our building options are below, enter the corresponding number to add it to your district:");
                System.out.println("NOTE: you will have to do this 8 times(1 per building) before the district is complete");
                System.out.println("1. House");
                System.out.println("2. Apartment");
                System.out.println("3. Local Market");
                System.out.println("4. Family Restaurant");
                System.out.println("5. Police Station");
                System.out.println("");

                //user inputs corresponding number
                input = scnr.nextInt();

                //building is added to an array for district
                district[i] = buildings[input - 1];
            }

            //each building is written to a file
            for(String d : district){
                bfw.write(d);
                bfw.newLine();
            }

            //user is notified that district has been created
            System.out.println("Your new district has been created!");

        }catch(Exception e){ System.out.println(e); }
        return 8;
    }//same as method above
    private int CreateDistrict(String name, int totalBuildings){
        int input;
        name += ".txt";
        String[] buildings = {"House", "Apartment", "Local Market", "Family Restaurant", "Police Station"};
        district = new String[8];
        try(BufferedWriter bfw = new BufferedWriter(new FileWriter(name))){
            for(int i = 0; i < 8; i++){
                System.out.println("Our building options are below, enter the corresponding number to add it to your district:");
                System.out.println("1. House");
                System.out.println("2. Apartment");
                System.out.println("3. Local Market");
                System.out.println("4. Family Restaurant");
                System.out.println("5. Police Station");
                System.out.println("");

                input = scnr.nextInt();

                district[i] = buildings[input];
            }

            for(String building : buildings){
                bfw.write(building);
                bfw.newLine();
            }
            
        }catch(Exception e){ System.out.println(e); }
        return totalBuildings;

    }

    protected void AddBuildings(String district){
        //8 new buildings are added to the city
        buildings += CreateDistrict(district);
        scnr.nextLine();

    }

    //constructors
    public CityBuilder(){
        districts = 1;
    }
    public CityBuilder(int districtNum, int buildingsNum){
        districts = districtNum;
        buildings = buildingsNum;
    }
}


class GovernmentBuilding extends Building{
   public String department;
   private static ArrayList<String> departments;
   protected double budget;

   public GovernmentBuilding(){
    department = "Justice";
   }
   public GovernmentBuilding(String departmentName, double budgetAMT){
    department = departmentName;
    budget = budgetAMT;
   }
}

class CityHall extends GovernmentBuilding {
    private double taxes;
    private int employees;

    public CityHall(){
        taxes = 200.00;
        employees = 30;
    }
    public CityHall(double taxRate, int employeesNum){
        taxes = taxRate;
        employees = employeesNum;
    }

    protected void CollectTaxes(){
        System.out.println("All citizens have been taxed " + taxes + "$ in taxes");
    }

    public void ChangeTaxes(double newTaxes){
        taxes = newTaxes;
    }
    public void ChangeTaxes(double newTaxes, String[] targets){
        taxes = newTaxes;
        for(String target : targets){
            System.out.print(target + " ");
        }

        System.out.println("will get a new taxrate of " + newTaxes + "$");
    }
}

class EmergencyService extends GovernmentBuilding implements BuildCode{
    public static int phoneNumber = 911;
    private boolean isUsed;
    private int personnel;

    public EmergencyService(){
        isUsed = true;
        personnel = 5;
    }
    public EmergencyService(int personnelNum, boolean used){
        isUsed = used;
        personnel = personnelNum;
    }

    public void RespondToEmErgency(String[] services){
        System.out.print(" There's an emergency! ");
        for(String service : services){
            System.out.print(service + " ");
        }
        System.out.println(" are on their way!");
    }
    public void RespondToEmErgency(String[] services, String adress){
        System.out.print(" There's an emergency! ");
        for(String service : services){
            System.out.print(service + " ");
        }
        System.out.println(" are on their way to " + adress);
    }
}

interface BuildCities {
    public void CityManagement();
    
}