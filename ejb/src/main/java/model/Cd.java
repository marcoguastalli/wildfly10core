package model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Cd implements Serializable {

    private int id;
    private String title;
    private String artist;
    private int year;

    public Cd() {
        this.id = 0;
        this.title = "";
        this.artist = "";
        this.year = 0;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Cd rhs = (Cd) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.id, rhs.id)
                .append(this.title, rhs.title)
                .append(this.artist, rhs.artist)
                .append(this.year, rhs.year)
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).
                append(this.id).
                append(this.title).
                append(this.artist).
                append(this.year).
                toHashCode();
    }
}
