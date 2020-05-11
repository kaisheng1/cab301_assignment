package main;

import collection.MemberCollection;
import collection.MovieCollection;
import menu.Menu;

public class Main {
    public static void main(String[] args){
        //setup
        MovieCollection libraryCollection = new MovieCollection();
        MemberCollection members = new MemberCollection();

        //start the menu
        Menu menu = new Menu(libraryCollection, members);
        menu.printMenu();
        menu.handleMenu();
    }
}
