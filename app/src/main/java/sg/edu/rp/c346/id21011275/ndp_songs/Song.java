package sg.edu.rp.c346.id21011275.ndp_songs;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song(int id, String title, String singers, int year, int stars) {
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSingers() {
        return singers;
    }

    public int getYear() {
        return year;
    }

    public int getStars() { return stars; }

    public String getTvStars() {
        String stars = "";
        for (int i = 0; i <= getStars(); i++) {
            stars += "*";
        }
        return stars;
    }

    public String toString() {
        String stars = "";
        for (int i = 0; i <= getStars(); i++) {
            stars += "*";
        }
        String output = title + "\n" + singers + " - " + year + "\n" + stars;
        return output;
    }
}
