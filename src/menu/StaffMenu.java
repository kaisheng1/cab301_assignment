package menu;

import collection.*;

/**
 * menu for staff
 */
public class StaffMenu extends SuperMenu{
    private Member staff;
    private MemberCollection members;
    private MovieCollection libraryCollection;

    public StaffMenu(Member staff, MovieCollection libraryCollection,  MemberCollection members){
        this.staff = staff;
        this.libraryCollection = libraryCollection;
        this.members = members;
    }

    /**
     * override the printMenu from SuperMenu
     */
    @Override
    public void printMenu(){
        System.out.print(
                "\n==========Staff Menu==========\n"
                + " 1. Add a new movie DVD\n"
                + " 2. Remove a movie DVD\n"
                + " 3. Register a new member\n"
                + " 4. Find a registered member's phone number\n"
                + " 0. Return to main menu\n"
                + "=============================\n\n"
        );
    }

    /**
     * override the handleMenu from SuperMenu
     */
    @Override
    public void handleMenu(){
        String menuSelect = readInput("Please make a selection (1-4 or 0 to return to main menu): ");

        switch(menuSelect){
            case "0":
                Menu menu = new Menu(libraryCollection, members);
                menu.printMenu();
                menu.handleMenu();
                break;
            case "1":
                addToLibrary();
                break;
            case "2":
                removeFromLibrary();
                break;
            case "3":
                registerMember();
                break;
            case "4" :
                findMemberContact();
                break;
            default:
                printMenu();
                handleMenu();
                return;
        }

        printMenu();
        handleMenu();
    }

    /**
     * add certain copies of movies to the library,
     * will define movie details if it has not been added yet
     */
    public void addToLibrary(){
        System.out.println("Adding movie...");
        String movieTitle = readInput("Enter the movie title: ");

        Movie found = libraryCollection.find(movieTitle);
        int copies;

        if (found != null){
            copies = readInt("Enter the number of copies available: ");
            libraryCollection.addCopies(found, copies);
        }else {
            String starring = readInput("Starring: ");
            String director = readInput("Director: ");
            String duration = readInput("Duration: ");
            String genre = selectOption("genre", new String[] {
                    "Drama",
                    "Adventure",
                    "Family",
                    "Action",
                    "Sci-Fi",
                    "Comedy",
                    "Thriller",
                    "Other"
            });
            String classification = selectOption("classification", new String[] {
                    "General (G)",
                    "Parental Guidance (PG)",
                    "Mature (M15+)",
                    "Mature Accompanied (MA15+)",
            });
            String releaseDate = readInput("Release Date: ");
            copies = readInt("Enter the number of copies available: ");
            libraryCollection.addCopies(new Movie(movieTitle, starring, director, duration, genre, classification, releaseDate), copies);
        }
        System.out.println("Added " + copies + " copies of " + movieTitle);
    }

    /**
     * remove the movie from the library
     */
    public void removeFromLibrary(){
        System.out.println("Removing movie...");
        String movieTitle = readInput("Enter movie title: ");
        libraryCollection.removeMovie(movieTitle);
        System.out.println(movieTitle + " was removed");
    }

    /**
     * register a new member
     */
    public void registerMember(){
        String firstName = readInput("Enter member's first name: ");
        String lastName = readInput("Enter member's last name: ");
        String username = lastName + firstName; //got the username formatted
        String address = readInput("Enter member's address: ");
        String contactNo = readInput("Enter member's phone number: ");

        String password = "";
        while (!Member.validPassword(password)){ //keep looping if it's not 4-digit integer
            password = readInput("Enter member's password(4-digit integer):");
        }

        if (members.add(new Member(username, password, address, contactNo, false))){
            System.out.println("Successfully added " + firstName + " " + lastName);
        }else{
            System.out.println(firstName + " " + lastName + " has already registered");
        }

    }

    /**
     * find the member contact no with the full name
     */
    public void findMemberContact(){
        String firstName = readInput("Enter member's first name: ");
        String lastName = readInput("Enter member's last name: ");
        String username = lastName + firstName; //got the username formatted

        Member member = members.find(username);

        if (member != null){
            String contact = member.getContactNo().isEmpty() ? "N/A" : member.getContactNo();
            System.out.println(firstName + " " + lastName + " phone number is: " + contact);
        }else{
            System.out.println("The member is not found");
        }
    }
}
