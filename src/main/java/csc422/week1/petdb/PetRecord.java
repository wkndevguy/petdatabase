package csc422.week1.petdb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("1) View all pets");
        System.out.println("2) Add more pets");
        System.out.println("3) Update an existing pet");
        System.out.println("4) Remove an existing pet");
        System.out.println("5) Search pets by name");
        System.out.println("6) Search pets by age");
        System.out.println("7) Load Pets");
        System.out.println("8) Save Pets");
        System.out.println("9) Exit program");
        
        int choice = reader.nextInt();
        System.out.println("Your choice: " + choice);
        
        launchChoice(choice, petRecords);
    }
    
    public static void loadPets(ArrayList<PetRecord> petRecords) {
        File file = new File("pets.txt");

        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String [] arrayLine = line.split(" ");
                petRecords.add(new PetRecord(arrayLine[0], Integer.parseInt(arrayLine[1])));
            }
            input.close();
            viewPets(petRecords, 5);
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: File not found.");
        }
    }
    
    public static void savePets(ArrayList<PetRecord> petRecords) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("pets.txt"));
            for (PetRecord pr : petRecords) {
                pw.println(pr.getName() + " " + pr.getAge());
            }
            pw.close();
            System.out.println("Pet records have been saved");
            displayChoices(petRecords);
        } catch (IOException ex) {
            System.out.println("ERROR: There has been an IO error.");
            displayChoices(petRecords);
        }
    }
    
    public static void viewPets(ArrayList<PetRecord> petRecords, int nextStep) {
        System.out.println();
        //Header
        String divider = "+----------------------+";
        System.out.println(divider);
        System.out.println("| ID | NAME      | AGE |");
        System.out.println(divider);
        //List
        for (PetRecord pr : petRecords) {
            System.out.printf("| %2d | %9s | %3d |\n", petRecords.indexOf(pr), pr.getName(), pr.getAge());
        }
        //Footer
        System.out.println(divider + "\n");
        System.out.println(petRecords.size() + " rows in set.\n");
        switch(nextStep) {
            case 0:
                addPets(petRecords);
            case 1:
                searchPetsByName(petRecords);
            case 2:
                searchPetsByAge(petRecords);
            case 3:
                updatePet(petRecords);
            case 4:
                removePet(petRecords);
            default:
                displayChoices(petRecords);
        }
    }

    public static void viewPetsFilteredByName(ArrayList<PetRecord> petRecords, String petName) {
        int filteredResultCount = 0;
        System.out.println();
        //Header
        String divider = "+----------------------+";
        System.out.println(divider);
        System.out.println("| ID | NAME      | AGE |");
        System.out.println(divider);
        //List
        for (PetRecord pr : petRecords) {
            if(pr.getName().equals(petName)) {
                filteredResultCount ++;
                System.out.printf("| %2d | %9s | %3d |\n", petRecords.indexOf(pr), pr.getName(), pr.getAge());
            }
        }
        //Footer
        System.out.println(divider + "\n");
        System.out.println(filteredResultCount + " rows in set.\n");
        displayChoices(petRecords);
    }

    public static void viewPetsFilteredByAge(ArrayList<PetRecord> petRecords, int petAge) {
        int filteredResultCount = 0;
        System.out.println();
        //Header
        String divider = "+----------------------+";
        System.out.println(divider);
        System.out.println("| ID | NAME      | AGE |");
        System.out.println(divider);
        //List
        for (PetRecord pr : petRecords) {
            if(pr.getAge() == petAge) {
                filteredResultCount ++;
                System.out.printf("| %2d | %9s | %3d |\n", petRecords.indexOf(pr), pr.getName(), pr.getAge());                
            }
        }
        //Footer
        System.out.println(divider + "\n");
        System.out.println(filteredResultCount + " rows in set.\n");
                displayChoices(petRecords);
    }
    
    public static void addPets(ArrayList<PetRecord> petRecords) {
        if(petRecords.size()<5) {
            Scanner reader = new Scanner(System.in);
            boolean valid = false;
            String petName = "";
            while(!valid) {
                System.out.println("Type the pet's name and press ENTER: ");
                petName = reader.next();
                if(!petName.isEmpty()) {
                    valid = true;
                }
            }
            boolean tooOld = true;
            int petAge = 0;
            while(tooOld) {
                System.out.println("Type the pet's age (as a number in range 1-20) and press ENTER: ");
                try {
                    String strPetAge = reader.next();
                    petAge = Integer.parseInt(strPetAge);
                    if(petAge <= 20) {
                        tooOld = false;
                    } else {
                        System.out.println("Must be a number in range 1-20. Please try again.");
                    }
                } catch (NumberFormatException e){
                    System.out.println("Must be a number in range 1-20. Please try again. (CATCH)");
                }
            }
            petRecords.add( new PetRecord(petName, petAge) );

            System.out.println("Please choose one option.");
            System.out.println("1) Add another pet");
            System.out.println("0) Return to main menu");
            int answer = reader.nextInt();
            if(answer == 1) {
                viewPets(petRecords, 0);
            } else {
                displayChoices(petRecords);
            }
        } else {
            System.out.println("Limit of 5 pets has been reached. Please remove one or more pets.");
            displayChoices(petRecords);
        }
    }
    
    public static void searchPetsByName(ArrayList<PetRecord> petRecords) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Type the pet's name and press ENTER: ");
        String petName = reader.nextLine();
        //would be a SELECT STATEMENT in case of real DB
        viewPetsFilteredByName(petRecords, petName);
    }
    
    public static void searchPetsByAge(ArrayList<PetRecord> petRecords) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Type the pet's age (as a number) and press ENTER: ");
        int petAge = reader.nextInt();
        //would be a SELECT STATEMENT in case of real DB
        viewPetsFilteredByAge(petRecords, petAge);
    }
    
    public static void updatePet(ArrayList<PetRecord> petRecords) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Type the pet's ID (as a number) and press ENTER: ");
        int petID = reader.nextInt();
        PetRecord pr = petRecords.get(petID);
        boolean valid = false;
        String petName = "";
        while(!valid) {
            System.out.println("Type the pet's name and press ENTER: ");
            petName = reader.next();
            if(!petName.isEmpty()) {
                valid = true;
            }
        }
        boolean tooOld = true;
        int petAge = 0;
        while(tooOld) {
            System.out.println("Type the pet's age (as a number in range 1-20) and press ENTER: ");
            try {
                String strPetAge = reader.next();
                petAge = Integer.parseInt(strPetAge);
                if(petAge <= 20) {
                    tooOld = false;
                } else {
                    System.out.println("Must be a number in range 1-20. Please try again.");
                }
            } catch (NumberFormatException e){
                System.out.println("Must be a number in range 1-20. Please try again. (CATCH)");
            }
        }
        petRecords.set(petID, new PetRecord(petName, petAge));
        System.out.printf("Pet ID %d has been changed from %s, age %d to %s, age %d.", petID, pr.getName(), pr.getAge(), petName, petAge);
        viewPets(petRecords, 5);
    }
    
    public static void removePet(ArrayList<PetRecord> petRecords) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Type the pet's ID (as a number) and press ENTER: ");
        int petID = reader.nextInt();
        PetRecord pr = petRecords.get(petID);
        petRecords.remove(petID);
        System.out.printf("%s, age %d, has been deleted.", pr.getName(), pr.getAge());
        viewPets(petRecords, 5);
    }
    
    public static void launchChoice(int choice, ArrayList<PetRecord> petRecords) {
        switch(choice) {
            case 1:
                viewPets(petRecords, 5);
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
                loadPets(petRecords);
                break;
            case 8:
                savePets(petRecords);
                break;
            case 9:
                System.exit(0);
                break;            
        }
    }
    public static void main(String[] args) {
        ArrayList<PetRecord> petRecords = new ArrayList<>();
        //petRecords.add( new PetRecord("Spot",10) );
        //petRecords.add( new PetRecord("Bear",6) );
        System.out.println("Pet Database Program.");

        displayChoices(petRecords);
    }
}
