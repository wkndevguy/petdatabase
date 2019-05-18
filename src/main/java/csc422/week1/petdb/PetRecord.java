package csc422.week1.petdb;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mike
 */
public class PetRecord {
    //instance variables
    private String _name;
    private int _age;
    
    //constructor
    public PetRecord(String name, int age) { 
        this._name = name; 
        this._age = age; 
    } 
    
    //getters and setters
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
    
    public static void viewPets(ArrayList<PetRecord> petRecords, boolean updating) {
        Scanner reader = new Scanner(System.in);
        String divider = "+----------------------+";
        System.out.println(divider);
        System.out.println("| ID | NAME      | AGE |");
        System.out.println(divider);
        for (PetRecord pr : petRecords) {
            System.out.printf("| %2d | %9s | %3d |\n", petRecords.indexOf(pr), pr.getName(), pr.getAge());
        }
        System.out.println(divider + "\n");
        if(updating) {
            System.out.println("Type the pet's name and press ENTER: ");
            String petName = reader.nextLine();
            System.out.println("Type the pet's age (as a number) and press ENTER: ");
            int petAge = reader.nextInt();
            petRecords.add( new PetRecord(petName, petAge) );

            System.out.println("Please choose one option.");
            System.out.println("1) Add another pet");
            System.out.println("0) Return to main menu");
            int answer = reader.nextInt();
            if(answer == 1) {
                viewPets(petRecords, true);
            } else {
                displayChoices(petRecords);
            }
        } else {
            displayChoices(petRecords);
        }
    }
    
    public static void addPets(ArrayList<PetRecord> petRecords) {
        viewPets(petRecords, true);
    }
    
    public static void updatePet(ArrayList<PetRecord> petRecords) {
        //TODO: update pet functionality
        System.out.println("This feature coming soon in a future release.");
        displayChoices(petRecords);
    }
    
    public static void removePet(ArrayList<PetRecord> petRecords) {
        //TODO: remove Pet functionality
        System.out.println("This feature coming soon in a future release.");
        displayChoices(petRecords);
    }
    
    public static void searchPetsByName(ArrayList<PetRecord> petRecords) {
        //TODO: search pets by name functionality
        System.out.println("This feature coming soon in a future release.");
        displayChoices(petRecords);
    }
    
    public static void searchPetsByAge(ArrayList<PetRecord> petRecords) {
        //TODO: search pets by age functionality
        System.out.println("This feature coming soon in a future release.");
        displayChoices(petRecords);
    }
    
    public static void launchChoice(int choice, ArrayList<PetRecord> petRecords) {
        switch(choice) {
            case 1:
                viewPets(petRecords, false);
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
        petRecords.add( new PetRecord("Spot",10) );
        petRecords.add( new PetRecord("Bear",6) );
        
        displayChoices(petRecords);
    }
}
