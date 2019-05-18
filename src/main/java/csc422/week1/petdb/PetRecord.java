package csc422.week1.petdb;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mike
 */
public class PetRecord {
    //instance variables
    private int _id;
    private String _name;
    private int _age;
    
    //constructor
    public PetRecord(int id, String name, int age) { 
        this._id = id;
        this._name = name; 
        this._age = age; 
    } 
    
    //getters and setters
    public int getID() {
        return _id;
    }
    public void setID(int newID) {
        this._id = newID;
    }
    
    public String getName() {
        return _name;
    }
    public void setName(String newName) {
        this._name = newName;
    }
    
    public int getAge() {
        return _age;
    }
    public void setAge(int newAge) {
        this._age = newAge;
    }
    
    public static void displayChoices(ArrayList<PetRecord> petRecords) {
        Scanner reader = new Scanner(System.in);
        System.out.println("ljk");
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("1) View all pets");
        System.out.println("2) Add more pets");
        System.out.println("3) Update an existing pet");
        System.out.println("4) Remove an existing pet");
        System.out.println("5) Search pets by name");
        System.out.println("6) Search pets by age");
        System.out.println("7) Exit program");
        
        int choice = reader.nextInt();
        System.out.println("Your choice: " + choice);
        
        launchChoice(choice, petRecords);
    }
    
    public static void viewPets(ArrayList<PetRecord> petRecords) {
        Scanner reader = new Scanner(System.in);
        String divider = "+----------------------+";
        System.out.println(divider);
        System.out.println("| ID | NAME      | AGE |");
        System.out.println(divider);
        for (PetRecord pr : petRecords) {
            System.out.printf("| %2d | %9s | %3d |\n", pr.getID(), pr.getName(), pr.getAge());
        }
        System.out.println(divider);
        System.out.println("Press any key to continue.");
        String go = reader.next();
        displayChoices(petRecords);
    }
    
    public static void addPets(ArrayList<PetRecord> petRecords) {
        Scanner reader = new Scanner(System.in);
        System.out.println("This feature coming soon in a future release.");
        System.out.println("Press any key to continue.");
        String go = reader.next();
        displayChoices(petRecords);
    }
    
    public static void updatePet(ArrayList<PetRecord> petRecords) {
        Scanner reader = new Scanner(System.in);
        System.out.println("This feature coming soon in a future release.");
        System.out.println("Press any key to continue.");
        String go = reader.next();
        displayChoices(petRecords);
    }
    
    public static void removePet(ArrayList<PetRecord> petRecords) {
        Scanner reader = new Scanner(System.in);
        System.out.println("This feature coming soon in a future release.");
        System.out.println("Press any key to continue.");
        String go = reader.next();
        displayChoices(petRecords);
    }
    
    public static void searchPetsByName(ArrayList<PetRecord> petRecords) {
        Scanner reader = new Scanner(System.in);
        System.out.println("This feature coming soon in a future release.");
        System.out.println("Press any key to continue.");
        String go = reader.next();
        displayChoices(petRecords);
    }
    
    public static void searchPetsByAge(ArrayList<PetRecord> petRecords) {
        Scanner reader = new Scanner(System.in);
        System.out.println("This feature coming soon in a future release.");
        System.out.println("Press any key to continue.");
        String go = reader.next();
        displayChoices(petRecords);
    }
    
    public static void launchChoice(int choice, ArrayList<PetRecord> petRecords) {
        switch(choice) {
            case 1:
                viewPets(petRecords);
                break;
            case 2:
                addPets(petRecords);
                break;
            case 3:
                updatePet(petRecords);
                break;
            case 4:
                removePet(petRecords);
                break;
            case 5:
                searchPetsByName(petRecords);
                break;
            case 6:
                searchPetsByAge(petRecords);
                break;
            case 7:
                System.exit(0);
                break;            
        }
    }
    public static void main(String[] args) {
        ArrayList<PetRecord> petRecords = new ArrayList<PetRecord>();
        petRecords.add( new PetRecord(0,"Spot",10) );
        petRecords.add( new PetRecord(1,"Bear",6) );
        
        displayChoices(petRecords);
    }
}
