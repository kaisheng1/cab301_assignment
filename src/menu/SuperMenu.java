package menu;

import java.util.Scanner;

/**
 * a superclass for the menu
 */

public abstract class SuperMenu {
    protected Scanner scanner = new Scanner(System.in);

    /**
     * a method to override
     */
    public void printMenu(){ }

    /**
     * a method to override
     */
    public void handleMenu(){}

    /**
     * read user input as string
     * @param message
     * @return input
     */
    public String readInput(String message){
        System.out.print(message);
        return scanner.nextLine();
    }

    /**
     * read user input as integer
     * @param message
     * @return integer
     */
    public int readInt(String message) {
        String result = readInput(message);

        try{
            return Integer.parseInt(result);
        }catch(NumberFormatException ex){
            return readInt(message);
        }
    }

    /**
     * select option from options
     * @param name
     * @param options
     * @return selected option
     */
    public String selectOption(String name, String[] options){
        String message = "Select the " + name;

        System.out.println();
        System.out.println(message);
        for (int i = 0; i < options.length; i++){
            String number = (i+1) + ". ";
            System.out.println(number + options[i]);
        }

        int choice;
        choice = readInt("Please make a selection "+ "(1-" + options.length + "): ");
        while (choice < 1 || choice > options.length){
            choice = readInt("Please make a selection "+ "(1-" + options.length + "): ");
        }
        return options[choice-1];
    }
    
}
