

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

public class CustomerService {

    private static Shop shop = new Shop(8);

    public static void main(String[] args) throws FileNotFoundException{
        char command;
        int acct = 0;
        int acct0 = 0;
        Scanner input = new Scanner(System.in);
        printMenu();
        do {

            System.out.println("\nPlease enter a command or type ?");
            command = input.nextLine().toLowerCase().charAt(0);

            switch (command) {
                case 'a':
                    int petType = -1;
                    while (petType < 1 || petType > 5) {
                        System.out.print("\nEnter one of the following pet types to donate. \n" +
                                "1 for Dogs, 2 for Cats, 3 for Reptiles, or 4 for Fish: ");
                        petType = Integer.parseInt(input.nextLine());
                    }
                    System.out.print("Enter Pet name: ");
                    String name = input.nextLine();
                    Animals a = null;
                    if (petType == 1) {
                        a = new Dogs(name);

                    }
                    else if (petType == 2){
                        a = new Cats(name);
                    }
                    else if (petType == 3){
                    a = new Reptiles(name);
                    }
                    else if (petType == 4){
                    a = new Fishies(name);
                    }
                    else {
                        System.out.println("Pet type not specified.");
                    }

                    if (shop.add(a)) System.out.print("\nPet successfully Donated.\n");
                    else System.out.print("Pet not donated. No Clones Please\n");
                    break;

                case 'b': // Adopt a pet
                    System.out.print("\nEnter a pets  number to adopt: ");
                    acct = Integer.parseInt(input.nextLine());
                    if (shop.remove(shop.find(acct)))
                        System.out.print("\nPet Successfully Adopted!\n");
                    else System.out.print("Pet was not adopted properly.\n");
                    break;

                case 'c': // display the Animals
                    System.out.println(shop.toString());
                    break;
                case 'd': // count the Animals
                    System.out.println("\nThere are " + shop.getCount() + " Animals in the shop.");
                    break;
                case 'e': // sort the Animals
                    shop.sort();
                    System.out.println("Animals Organized.");
                    break;
                case 'f':
                    System.out.println("Enter file name: ");
                    String fileImport = input.nextLine();
                    try {
                        System.out.println("Successful accounts Imported" + readAccounts(fileImport));
                    }
                    catch (FileNotFoundException e){
                        System.err.println("Invalid File Name");
                    }
                    break;
                case 'g':
                    System.out.println("Enter a file name: ");
                    String fileExport = input.nextLine();
                    try{
                        System.out.println("Accounts successfully exported" + shop.writeAccounts(fileExport));
                    }catch (FileNotFoundException e) {
                        System.err.println("Invalid file name");
                    }
                    break;

                case '?':
                    printMenu();
                    break;
                case 'q':
                    System.out.println("Thank you for shopping at the Vivacious Vivariums Pet Shop!");
                    break;
                default:
                    System.out.println("Invalid Input");

            }

        } while (command != 'q');

        input.close();
    }


    // this method prints out the menu to a user
    // we put it here to keep the main method clean.
    public static void printMenu() {
        System.out.print("\nCustomer Service Options\n" + "-----------------------------------\n"
                + "a: Donate a pet to the shop\n" + "b: Adopt a pet from the shop\n"
                + "c: Display the pets in the shop\n" + "d: Count the pets in the shop\n"
                + "e: Sort the pets in the shop\n"
                + "f: Import Shop File\n" + "g: Export Shop File\n"
                + "?: Redisplay the menu\n" + "q: quit the pet shop program\n\n");

    } // end of the printMenu method
    public static Animals parseBankAccount(String data) throws ShopFileFormatException, ParseException {
        String[] tokens = data.split("\t");
        if (tokens.length != 6) throw new ShopFileFormatException("Account data format invalid");
        if (tokens[0].equals("Animals")) {
            return new Dogs(tokens);
        } else {
            return new Cats(tokens);
        }
    }
    public static int readAccounts(String filename) throws FileNotFoundException {
        int count = 0;
        File file = new File(filename);
        Scanner readMe = new Scanner(file);
        while (readMe.hasNextLine()){
            String data = readMe.nextLine();
            try{
                shop.add(parseBankAccount(data));
                count++;
            } catch (ShopFileFormatException | ParseException e){
                System.err.println("Bad Line Skipped");
            }
        }
        readMe.close();
        return count;
    }


}

