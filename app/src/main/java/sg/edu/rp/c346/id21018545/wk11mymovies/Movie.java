package sg.edu.rp.c346.id21018545.wk11mymovies;
import android.graphics.Color;

import java.io.Serializable;

import androidx.annotation.NonNull;

public class Movie implements Serializable {

    private int id;
    private String title;
    private String genre;
    private int yearReleased;
    private int stars;
    private String ratings;

    public Movie(int id, String title, String genre, int yearReleased, int stars, String ratings) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.yearReleased = yearReleased;
        this.stars = stars;
        this.ratings = ratings;
    }

    public int getId() {
        return id;
    }

    public Movie setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Movie setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public Movie setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    public Movie setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
        return this;
    }

    public int getStars() {
        return stars;
    }

    public Movie setStars(int stars) {
        this.stars = stars;
        return this;
    }
    public String getRatings() {
        return ratings;
    }

    public Movie setRatings(String ratings) {
        this.ratings = ratings;
        return this;
    }
    @NonNull
    @Override
    public String toString() {
        String starsString = "";
        for (int i = 0; i < stars; i++) {
            starsString += "*";
        }


        return title + "\n" + yearReleased + " " + starsString + "\n" + genre + "\n" + ratings;

    }

}
