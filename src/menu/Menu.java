package menu;

import collection.Member;
import collection.MemberCollection;
import collection.MovieCollection;

/**
 * the main menu
 */
public class Menu extends SuperMenu{

    private Member staff;
    private MemberCollection members;
    private MovieCollection libraryCollection;

    public Menu(MovieCollection libraryCollection, MemberCollection members){
        staff = new Member("staff", "today123", true);
        this.libraryCollection = libraryCollection;
        this.members = members;
    }

    /**
     * override the printMenu from SuperMenu
     */
    @Override
    public void printMenu(){
        System.out.print(
                "\nWelcome to the Community Library\n"
                        + "==========Main Menu==========\n"
                        + " 1. Staff Login\n"
                        + " 2. Member Login\n"
                        + " 0. Exit\n"
                        + "=============================\n\n"
        );
    }

    /**
     * override the handleMenu from SuperMenu
     */
    @Override
    public void handleMenu(){
        String menuSelect = readInput("Please make a selection (1-2 or 0 to exit): ");

        switch(menuSelect){
            case "0":
                System.exit(0);
                break;
            case "1":
                handleStaffLogin();
                break;
            case "2":
                handleMemberLogin();
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
     * handle staff login after getting username and password
     */
    public void handleStaffLogin(){
        System.out.println("Staff Login:");
        String username = readInput("Enter username: ");
        String password = readInput("Enter password: ");

        if (staff.login(username, password, true)){
            System.out.println("You successfully login!");
            StaffMenu staffMenu = new StaffMenu(staff, libraryCollection, members);
            staffMenu.printMenu();
            staffMenu.handleMenu();
        }else{
            System.out.println("Failed to login!");
        }
    }

    /**
     * handle member login after getting username and password
     */
    public void handleMemberLogin(){
        System.out.println("Member Login:");
        String username = readInput("Enter username(LastnameFirstname): ");
        String password = readInput("Enter password: ");

        Member member = members.find(username);

        if (member != null && member.login(username, password, false)){
            System.out.println("You successfully login!");
            MemberMenu memberMenu = new MemberMenu(member, libraryCollection, members);
            memberMenu.printMenu();
            memberMenu.handleMenu();
        }else{
            System.out.println("Failed to login!");
        }
    }
}
