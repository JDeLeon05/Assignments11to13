import java.util.*;
import java.io.*;
public abstract class Building implements BuildCode{
    private int floors;
    private String adress;
    private static ArrayList<String> buildingTypes = new ArrayList<>();
    
    public Building(){
        floors = 2;
        adress = null;
    }
    public Building(int floornum, String buildingAdress){
        floors = floornum;
        adress = buildingAdress;
    }

    protected static void CountBuildings(int buildings){
        System.out.println("Your have " + buildings + " buildings");
    }

    protected void AddFloor(){
        floors++;
    }
    protected void AddFloor(String buildingName){
        floors++;
        System.out.println("A floor has been added to " + buildingName);
    }
    
    public void PrintPurpose(){
        System.out.println("The general purpose of a building is to shelter people from the outside,");
        System.out.println("different building may also have more specific purposes.");
    }

}

interface BuildCode {
    public void PrintPurpose();
}
