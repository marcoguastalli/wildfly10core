package servlet.cds;

import base.http.Populatable;
import base.http.PopulateException;
import utils.Checks;

import java.io.Serializable;

public class CdsRequest implements Populatable, Serializable {
    private String title;
    private String artist;
    private int year;

    public CdsRequest() {
    }

    public void validate() throws PopulateException {
        try {
            Checks.checkNotNull(title, "title");
            Checks.checkNotNull(artist, "artist");
            Checks.checkPositive(year, "year");
        } catch (Exception e) {
            throw new PopulateException(e);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
