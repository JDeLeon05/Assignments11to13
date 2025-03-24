public abstract class Residential extends Building{
    public int bedrooms;
    private int inhabitants;
    protected boolean isRental;

    public Residential(){
        bedrooms = 2;
        inhabitants = 4;
    }
    public Residential(int bedroomNum, int inhabitantsNum, boolean Rental){
        bedrooms = bedroomNum;
        inhabitants = inhabitantsNum;
        isRental = Rental;
    }

    protected void addBedroom(){
        bedrooms++;
        System.out.println("A new bedroom has been added");
    }
    protected void addBedroom(int newBedrooms){
        bedrooms += newBedrooms;
        System.out.println(newBedrooms + " new bedrooms have been added");
    }
}

class House extends Residential implements BuildCode{
    private String owner;
    protected double expenses;

    public House(){
        expenses = 1200.00;
        owner = null;
    }
    public House(double expensesNum, String houseOwner){
        expenses = expensesNum;
        owner = houseOwner;
    }

    public void ChangeOwner(){
        System.out.println(owner + " has sold the house");
        owner = "none";
    }
    public void ChangeOwner(String newOwner){
        System.out.println(owner + " has sold the house, " + newOwner + " is the new owner");
        owner = newOwner;
    }

    @Override public void PrintPurpose(){
        System.out.println("A house is a building where a person or family lives");
    }
}

class Apartment extends Residential implements BuildCode{
    public double rent;
    private String landlord;
    protected int rooms;

    public Apartment(){
        rooms = 4;
        rent = 600.00;
    }
    public Apartment(int roomsNum, double rentAMT, String landlordName){
        rooms = roomsNum;
        rent = rentAMT;
        landlord = landlordName;
    }

    public void Evict(String name, int apartment){
        System.out.println(name + " from room " + apartment + " has been givent an eviction notice");
    }
    public void Evict(String[] names){
        for( String name : names){
            System.out.print(name + " ");
        }
        System.out.println("have been given an eviction notice");
    }

    @Override public void PrintPurpose(){
        System.out.println("An apartment is a building with many rooms rented out to different people");
    }
    
}
