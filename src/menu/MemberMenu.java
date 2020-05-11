package menu;

import collection.*;

/**
 * menu for member
 */
public class MemberMenu extends SuperMenu{
    private Member member;
    private MemberCollection members;
    private MovieCollection libraryCollection;

    public MemberMenu(Member member, MovieCollection libraryCollection, MemberCollection members){
        this.member = member;
        this.libraryCollection = libraryCollection;
        this.members = members;
    }

    /**
     * override the printMenu from SuperMenu
     */
    @Override
    public void printMenu(){
        System.out.print(
                "\n==========Member Menu==========\n"
                        + " 1. Display all movies\n"
                        + " 2. Borrow a movie DVD\n"
                        + " 3. Return a movie DVD\n"
                        + " 4. List current borrowed movie DVDs\n"
                        + " 5. Display top 10 most popular movies\n"
                        + " 0. Return to main menu\n"
                        + "===============================\n\n"
        );
    }
    /**
     * override the handleMenu from SuperMenu
     */
    @Override
    public void handleMenu(){
        String menuSelect = readInput("Please make a selection (1-5 or 0 to return to main menu): ");

        switch(menuSelect){
            case "0":
                Menu menu = new Menu(libraryCollection, members);
                menu.printMenu();
                menu.handleMenu();
                break;
            case "1":
                displayLibrary();
                break;
            case "2":
                borrow();
                break;
            case "3":
                returnDVD();
                break;
            case "4":
                displayCurrentBorrowing();
                break;
            case "5":
                displayTopTenRented();
            default:
                printMenu();
                handleMenu();
                return;
        }

        printMenu();
        handleMenu();
    }

    /**
     * display all the library collections in alphabetical order
     */
    private void displayLibrary() {
        System.out.println("\nAll library's collections:");
        libraryCollection.inorder();
        System.out.println();
    }

    /**
     * borrow a movie from library
     */
    private void borrow() {
        String movieTitle = readInput("Enter the movie title: ");
        Movie movieToBorrow = libraryCollection.find(movieTitle);

        try{
            if (movieToBorrow == null) throw new Exception("The movie doesn't exist");
            if (member.hasBorrowed(movieTitle)) throw new Exception("You've already borrowed " + movieTitle);

            libraryCollection.rent(movieToBorrow, 1);
            member.addBorrowing(movieTitle);
            System.out.println("You borrowed " + movieTitle);

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    /**
     * return the movie to library
     */
    private void returnDVD() {
        String movieTitle = readInput("Enter the movie title: ");

        if (member.removeBorrowing(movieTitle)){
            libraryCollection.addCopies(new Movie(movieTitle), 1);
            System.out.println("You returned " + movieTitle);
        }else{
            System.out.println("You haven't got " + movieTitle);
        }

    }

    /**
     * display all movies that are currently borrowed by the member
     */
    private void displayCurrentBorrowing() {
        System.out.println("\nYou are currently borrowing:");
        member.displayBorrowing();
        System.out.println();
    }

    /**
     * display top ten rented movies of the library
     */
    private void displayTopTenRented() {
        System.out.println("\nTop 10 most popular movie DVDs:");
        libraryCollection.displayTopTenRented();
        System.out.println();
    }

}
