package collection;

/**
 * a class to describe the movie
 */

public class Movie {
    private String title;
    private String starring;
    private String director;
    private String duration;
    private String genre;
    private String classification;
    private String releaseDate;

    public Movie(String title){
        this.title = title;
    }

    public Movie(String title, String starring, String director, String duration, String genre, String classification, String releaseDate){
        this.title = title;
        this.starring = starring;
        this.director = director;
        this.duration = duration;
        this.genre = genre;
        this.classification = classification;
        this.releaseDate = releaseDate;
    }

    public String getTitle(){
        return title;
    }

    public String getStarring() {
        return starring;
    }

    public String getDirector() {
        return director;
    }

    public String getDuration() {
        return duration;
    }

    public String getGenre() {
        return genre;
    }

    public String getClassification() {
        return classification;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

}

