package collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieCollectionTest {
    public MovieCollection movies;

    @BeforeEach
    public void init(){
        movies = new MovieCollection();
    }

    @Test
    public void add(){
        movies.addCopies(new Movie("fahan"), 23);
        movies.addCopies(new Movie("fahan"), 12);
        movies.addCopies(new Movie("tom"), 12);
        movies.addCopies(new Movie("hanson"), 23);

        MovieCollection.Node root = movies.getNode();

        assertEquals(root.movie.getTitle(), "fahan");
        assertEquals(root.right.movie.getTitle(), "tom");
        assertEquals(root.right.left.movie.getTitle(), "hanson");

        assertEquals(root.copies, 23 + 12);
        assertEquals(root.right.copies, 12);
        assertEquals(root.right.left.copies, 23);
    }

    @Test
    public void rent(){
        movies.addCopies(new Movie("fahan"), 23);
        movies.addCopies(new Movie("fahan"), 12);
        movies.addCopies(new Movie("tom"), 12);
        movies.addCopies(new Movie("hanson"), 23);

        assertDoesNotThrow(() -> movies.rent("fahan", 2));
        assertDoesNotThrow(() -> movies.rent("fahan", 3));
        assertDoesNotThrow(() -> movies.rent("tom", 1));

        MovieCollection.Node root = movies.getNode();

        assertEquals(root.copies, 23 + 12 - 2 - 3);
        assertEquals(root.rented, 2 + 3);
        assertEquals(root.right.rented, 1);
        assertEquals(root.right.left.rented, 0);
    }

    @Test
    public void displayTopTenRented(){

        for (int i = 0; i < 15; i++){
            movies.addCopies(new Movie("movie" + i), 5);
        }

        assertDoesNotThrow(() -> movies.rent("movie0", 2));
        assertDoesNotThrow(() -> movies.rent("movie1", 3));
        assertDoesNotThrow(() -> movies.rent("movie2", 1));

        movies.displayTopTenRented();
    }


}