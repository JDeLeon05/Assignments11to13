import java.util.ArrayList;

public abstract class Store extends Building{
    private double income;
    private double expenses;
    protected ArrayList<String> products = new ArrayList<>();

    public Store(){
        income = 80000.00;
        expenses = 40000.00;
    }
    public Store( double earnings, double costs){
        income = earnings;
        expenses = costs;
    }

    public void HireEmployee(boolean passedInterview, String name){
        if(passedInterview == true){
            System.out.println(name + " has been hired");
        } else{
            System.out.println(name + " was not a suitable employee");
        }
    }
    public void HireEmployee(String name, String position){
        System.out.println(name + " Has been hired for position of " + position);
    }

}

class Restaurant extends Store implements BuildCode{
    private boolean FDAapproved;
    protected int tables;

    public Restaurant(){
        tables = 4;
        FDAapproved = false;
    }
    public Restaurant(int tableNum, boolean approvedFDA){
        FDAapproved = approvedFDA;
        tables = tableNum;
    }

    public void AddTable(int tablesNum, int seatsperTable){
        System.out.println(tablesNum + " new tables with " + seatsperTable + " seats each");
    }
    public void AddTable(int tablesNum){
        System.out.println(tablesNum + " new tables with 4 seats have been added");
    }

    @Override public void PrintPurpose(){
        System.out.println("A restuarant is a store which sells prepared food for customers to eat");
    }
}

final class Retail extends Store implements BuildCode{
    protected String size;
    private int employees;

    public Retail(){
        size = "local";
    }
    public Retail(String sizeClass, int employeesNum){
        size = sizeClass;
        employees = employeesNum;
    }

    private void DeclareBankrupcy(){
        System.out.println("We declare bankrupcy but no one knows");
    }
    public void DeclareBankrupcy(String statement){
        System.out.println("This company is no longer able to compete, so it is with the heavy heart, that I declare bankrupcy");
    }

    
    @Override public void PrintPurpose(){
        System.out.println("A retail store, also known as a market, focuses on selling a wide variety of products");
    }

}