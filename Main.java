import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.ClassNotFoundException;

class Main {
    public static void main(String[] args) throws Exception {
        // add scanner
        Scanner scan = new Scanner(System.in);

        // declare variables
        char repeat;
        int input = 0;
        String name, brand, style, searchName, deleteName;
        double newPrice, usedPrice;

        //create array list
        ArrayList<Shoe> shoes = new ArrayList<>();

        //create shoe objects
        Shoe shoe1 = new Shoe("AirForce", "Nike", "Leisure", 90.00, 50.00);
        Shoe shoe2 = new Shoe("OldSkool", "Vans", "Skateboarding", 60.00, 30.00);
        Shoe shoe3 = new Shoe("Creepers", "Puma", "Fashion", 70.00, 50.00);
        Shoe shoe4 = new Shoe("Converse", "Converse", "Leisure", 45.00, 20.00);
        Shoe shoe5 = new Shoe("Jordans", "Nike", "Basketball", 130.00, 100.00);
        
        //add shoes to shoe list
        shoes.add(shoe1);
        shoes.add(shoe2);
        shoes.add(shoe3);
        shoes.add(shoe4);
        shoes.add(shoe5);

        //write the shoe array to the binary file
        try {
            ObjectOutputStream writeStream = new ObjectOutputStream(new FileOutputStream("data.dat"));
            writeStream.writeObject(shoes);
            writeStream.close();

        } catch (IOException e) {
            System.out.println("IOException");
        }

        //welcome line
        System.out.println("\nWelcome to Alyana's Shoe List!\n");

        //do while loop
        do {

            // ask user to enter a number for which action they would like to do with mismatch exception
            try {
            System.out.println(
                    "Which action would you like to do? \n 1. Add a new item \n 2. Delete an item \n 3. Search data on an existing item \n 4. Review statistics \n 5. Print all items");
                    input = scan.nextInt();
                    System.out.println();
                // if user does not enter correct number, go through correction while loop
                    while (input < 1 || input > 5) {
                    System.out.println("Please enter a number that is listed above: ");
                    input = scan.nextInt();
                    System.out.println();
                }
            } catch (InputMismatchException ex) {
                System.out.println("Error: Invalid Input.");
            }


            // if statement for add
            if (input == 1) {
                System.out.print("Please enter Name, Brand, Style, Price(new), Price(use) --without commas: ");
                name = scan.next();
                brand = scan.next();
                style = scan.next();
                newPrice = scan.nextDouble();
                usedPrice = scan.nextDouble();
                Shoe newShoe = new Shoe(name, brand, style, newPrice, usedPrice);
                shoes.add(newShoe);
                
                //write updated list to data file
                try {
                    ObjectOutputStream writeStream = new ObjectOutputStream(new FileOutputStream("data.dat"));
                    writeStream.writeObject(shoes);
                    writeStream.close();

                } catch (IOException e) {
                    System.out.println("IOException");
                }

            }

            // if statement for delete
            if (input == 2) {
                System.out.print("Please enter the name of the shoes you would like to delete: ");
                deleteName = scan.next();
                System.out.println(Shoe.deleteShoe(shoes, deleteName));
                
                // write updated list to data file
                try {
                    ObjectOutputStream writeStream = new ObjectOutputStream(new FileOutputStream("data.dat"));
                    writeStream.writeObject(shoes);
                    writeStream.close();

                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }

            // if statement for search
            if (input == 3) {
                System.out.print("Please enter the name of the shoes you would like to search for: ");
                searchName = scan.next();
                System.out.println(Shoe.searchList(shoes, searchName));
            }

            // if statement for review
            if (input == 4) {
                try {
                    ObjectInputStream readStream = new ObjectInputStream(new FileInputStream("data.dat"));
                    ArrayList<Shoe> shoeList1 = (ArrayList<Shoe>) readStream.readObject();
                    System.out.println("Number of items in list: " + Shoe.numOfItems(shoeList1));
                    System.out.println("Total value of new shoes: $" + Shoe.totalNewValue(shoeList1));
                    System.out.println("Total value of used shoes: $" + Shoe.totalUsedValue(shoeList1));
                    System.out.println("Total value of all shoes: $" + Shoe.totalValue(shoeList1));
                    readStream.close();
                } catch (FileNotFoundException e1) {
                    System.out.println("File not found");
                } catch (IOException e2) {
                    System.out.println("IOException");
                } catch (ClassNotFoundException e3) {
                    System.out.println("Class not found");
                }
            }

            // if statement for printing array
            if (input == 5) {
                try {
                    ObjectInputStream readStream = new ObjectInputStream(new FileInputStream("data.dat"));
                    ArrayList<Shoe> shoeList2 = (ArrayList<Shoe>) readStream.readObject();
                    for (Shoe shoe : shoeList2) {
                        System.out.println(shoe.toString());
                    }
                    readStream.close();
                } catch (FileNotFoundException e1) {
                    System.out.println("File not found");
                } catch (IOException e2) {
                    System.out.println("IOException");
                } catch (ClassNotFoundException e3) {
                    System.out.println("Class not found");
                }
            }

            // ask if user would like to do another action. if yes, repeat do and if no, end
            // loop
            System.out.print("\nWould you like to do another action?(y/n) ");
            repeat = scan.next().toUpperCase().charAt(0);
            System.out.println();
        } while (repeat == 'Y');
        scan.close();
    }
}