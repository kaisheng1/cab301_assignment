package collection;

import java.util.ArrayList;
import java.util.List;

/**
 * a class to describe the member
 */

public class Member {
    private String username;
    private String password;
    private String address;
    private String contactNo;
    private boolean isStaff;
    private List<String> borrowing;


    public Member(String username, String password, boolean isStaff){
        this(username, password, "", "", isStaff);
    }

    public Member(String username, String password, String address, String contactNo, boolean isStaff){
        //need to check if the password is 4 digit integer if the member is not staff
        this.username = username;
        this.password = password;
        this.address = address;
        this.contactNo = contactNo;
        this.isStaff = isStaff;
        this.borrowing = new ArrayList<>();
    }

    public String getUsername(){
        return username;
    }

    public String getContactNo(){
        return contactNo;
    }

    /**
     * static method to check if the password is valid
     * @param password
     * @return true if valid
     */
    public static boolean validPassword(String password){
        if (password.length() != 4) return false;

        for (char c : password.toCharArray()){
            if (!Character.isDigit(c)) return false;
        }

        return true;
    }

    /**
     * check if it matches the user's username and password
     * @param username
     * @param password
     * @param staffLogin
     * @return true if it matches
     */
    public boolean login(String username, String password, boolean staffLogin){
        return this.username.equals(username) && this.password.equals(password) && (staffLogin == isStaff);
    }

    /**
     * add a movie dvd to the borrowing list
     * @param movieTitle
     * @return true if successfully added
     */
    public boolean addBorrowing(String movieTitle){
        return borrowing.add(movieTitle);
    }

    /**
     * remove a movie dvd from the borrowing list
     * @param movieTitle
     * @return true if successfully removed
     */
    public boolean removeBorrowing(String movieTitle){
        return borrowing.remove(movieTitle);
    }

    /**
     * check if the member has already borrowed the movie
     * @param movieTitle
     * @return true if the member has already borrowed the movie
     */
    public boolean hasBorrowed(String movieTitle){
        return borrowing.contains(movieTitle);
    }

    /**
     * print the borrowing list
     */
    public void displayBorrowing(){
        for (int i = 0; i < borrowing.size(); i++){
            System.out.println(borrowing.get(i));
        }
    }

}
