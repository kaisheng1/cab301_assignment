package collection;

import java.util.Comparator;

/**
 * Implemented the data structure as Binary Search Tree (BST)
 */

public class MovieCollection {
    /**
     * a helper class
     */
    class Node{
        Movie movie;
        int copies, rented;
        Node left, right = null;

        public Node(Movie movie){
            this(movie, 1, 0);
        }

        public Node(Movie movie, int copies){
            this(movie, copies, 0);
        }

        public Node(Movie movie, int copies, int rented){
            this.movie = movie;
            this.copies = copies;
            this.rented = rented;
        }
    }

    private Node root;
    private int printed;

    public MovieCollection(){
        this.root = null;
    }

    /**
     *
     * @return the root
     */
    public Node getNode() {return root;}

    /**
     * compare both nodes
     * @param n1
     * @param n2
     * @return the integer as how much the first node is greater than the second node
     */
    public int compare(Node n1, Node n2) {return n1.movie.getTitle().compareTo(n2.movie.getTitle());}

    /**
     *
     * @param m1
     * @param m2
     * @return true if their titles are the same
     */
    public boolean isSameTitle(Movie m1, Movie m2) {return m1.getTitle().equals(m2.getTitle());}

    /**
     *
     * @param current
     * @return the smallest node as movie in the root
     */
    public Movie findSmallest(Node current){
        return current.left == null ? current.movie : findSmallest(current.left);
    }

    /**
     *
     * @param movieTitle
     * @return the movie if found, otherwise null
     */
    public Movie find(String movieTitle){
        return find(root, new Movie(movieTitle));
    }

    /**
     *
     * @param current
     * @param movie
     * @return the movie if found, otherwise null
     */
    public Movie find(Node current, Movie movie){
        if (current == null) return null;

        int result = compare(new Node(movie), current);

        if (result == 0){
            return current.movie;
        }else if (result < 0){
            return find(current.left, movie);
        }else {
            return find(current.right, movie);
        }
    }

    /**
     * add one copy of the movie
     * @param movie
     */
    public void add(Movie movie){
        addCopies(movie, 1);
    }

    /**
     * add more than one copy of the movie
     * @param movie
     * @param copies
     */
    public void addCopies(Movie movie, int copies){
        root = addCopies(root, movie, copies);
    }

    /**
     * add more than one copy of the movie
     * @param current
     * @param movie
     * @param copies
     * @return the node after the addition
     */
    public Node addCopies(Node current, Movie movie, int copies){
        if (current == null){
            return new Node(movie, copies);
        }

        int result = compare(new Node(movie, copies), current);

        if(result < 0){
            current.left = addCopies(current.left, movie, copies);
        }else if (result > 0){
            current.right = addCopies(current.right, movie, copies);
        }else {
            if (isSameTitle(movie, current.movie)){
                current.copies += copies;
            }else{
                current.left = addCopies(current.left, movie, copies);
            }
        }

        return current;
    }

    /**
     * add the node which contains the movie, copies and rented number with custom comparator
     * @param current
     * @param toAdd
     * @param comparator
     * @return the node after the addition
     */
    public Node addWithComparator(Node current, Node toAdd, Comparator comparator){
        if (current == null){
            return new Node(toAdd.movie, toAdd.copies, toAdd.rented);
        }

        int result = comparator.compare(toAdd, current);

        if(result < 0){
            current.left = addWithComparator(current.left, toAdd, comparator);
        }else if (result > 0){
            current.right = addWithComparator(current.right, toAdd, comparator);
        }else {
            if (isSameTitle(toAdd.movie, current.movie)){   //prevent addition if the comparator is to compare the copies/rented
                current.copies += toAdd.copies;
                current.rented += toAdd.rented;
            }else{
                current.left = addWithComparator(current.left, toAdd, comparator);
            }
        }

        return current;
    }

    /**
     * rent certain number of copies from the collection
     * @param movieTitle
     * @param amount
     * @throws Exception
     */
    public void rent(String movieTitle, int amount) throws Exception{
        rent(new Movie(movieTitle), amount);
    }

    /**
     * rent certain number of copies from the collection
     * @param movie
     * @param amount
     * @throws Exception
     */
    public void rent(Movie movie, int amount) throws Exception{
        root = rent(root, movie, amount);
    }

    /**
     * rent certain number of copies from the collection
     * @param current
     * @param movie
     * @param amount
     * @return the node after rented
     * @throws Exception
     */
    public Node rent(Node current, Movie movie, int amount) throws Exception {
        if (current == null){
            return null;
        }

        int result = compare(new Node(movie), current);

        if(result < 0){
            current.left = rent(current.left, movie, amount);
        }else if (result > 0){
            current.right = rent(current.right, movie, amount);
        }else {
            if (isSameTitle(movie, current.movie)){
                int newAmount = current.copies - amount;

                if (newAmount >= 0) {
                    current.copies = newAmount;
                    current.rented += amount;
                } else throw new Exception("There is no enough copies available");
            }else {
                current.left = rent(current.left, movie, amount);
            }

        }

        return current;
    }

    /**
     * completely remove the movie from the collection
     * @param movieTitle
     */
    public void removeMovie(String movieTitle){
        root = removeMovie(root, new Movie(movieTitle));
    }

    /**
     * completely remove the movie from the collection
     * @param current
     * @param movie
     * @return the node after removed
     */
    public Node removeMovie(Node current, Movie movie){
        if (current == null){
            return null;
        }

        int result = compare(new Node(movie), current);

        if (result == 0){
            if (current.left == null && current.right == null){
                return null;
            }else if (current.left == null){
                return current.right;
            }else if (current.right == null){
                return current.left;
            }else {
                Movie smallest = findSmallest(current.right);
                current.movie = smallest;
                current.right = removeMovie(current.right, smallest);
                return current;
            }
        }else if (result < 0){
            current.left = removeMovie(current.left, movie);
        }else {
            current.right = removeMovie(current.right, movie);
        }

        return current;
    }

    /**
     * inorder traversal of BST
     */
    public void inorder(){
        inorder(root);
    }

    /**
     * inorder traversal of BST that prints the value of node
     * @param current
     */
    public void inorder(Node current){
        if (current == null) return;

        inorder(current.left);

        System.out.println("Title: " + current.movie.getTitle());
        System.out.println("Starring: " + current.movie.getStarring());
        System.out.println("Director: " + current.movie.getDirector());
        System.out.println("Genre: " + current.movie.getGenre());
        System.out.println("Classification: " + current.movie.getClassification());
        System.out.println("Duration: " + current.movie.getDuration());
        System.out.println("Release Date: " + current.movie.getReleaseDate());
        System.out.println("Copies of available: " + current.copies);
        System.out.println("Times rented: " + current.rented);
        System.out.println();

        inorder(current.right);
    }

    /**
     * inorder traversal with the limit and print the rented number of the movie
     * @param current
     * @param limit
     */
    public void inorderRented(Node current, int limit){
        if (current == null) return;

        inorderRented(current.left, limit);
        if (printed >= limit) return;
        System.out.println(current.movie.getTitle() + " borrowed " + current.rented + " times.");
        if (++printed >= limit) return;
        inorderRented(current.right, limit);
    }

    /**
     * sort the node with custom comparator
     * @param acc
     * @param toSort
     * @param comparator
     * @return the node after sort
     */
    public Node sort(Node acc, Node toSort, Comparator comparator){
        if (toSort == null) return acc;

        Node afterSort = addWithComparator(acc, toSort, comparator);
        afterSort = sort(afterSort, toSort.left, comparator);
        afterSort = sort(afterSort, toSort.right, comparator);

        return afterSort;
    }

    /**
     * print top ten rented movies
     */
    public void displayTopTenRented(){
        Node afterSort = sort(null, root, (Comparator<Node>) (n1, n2) -> n2.rented - n1.rented);

        printed = 0;
        inorderRented(afterSort, 10);
    }

}
