package model;

import java.io.Serializable;

public class Book implements Serializable {
    private final int year;
    private final String author;
    private final String title;
    private final String isbn;
    private final String description;
    private final double price;

    public static class BookBuilder {
        // Required parameters
        private final String author;
        private final String title;
        // Optional parameters - initialized to default values
        private int year = 1975;
        private String isbn = "";
        private String description = "";
        private double price = 0;

        public BookBuilder(String author, String title) {
            this.author = author;
            this.title = title;
        }

        public BookBuilder year(int year) {
            this.year = year;
            return this;
        }

        public BookBuilder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public BookBuilder description(String description) {
            this.description = description;
            return this;
        }

        public BookBuilder price(int price) {
            this.price = price;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }

    private Book(BookBuilder builder) {
        year = builder.year;
        author = builder.author;
        title = builder.title;
        isbn = builder.isbn;
        description = builder.description;
        price = builder.price;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String generateKey() {
        return getAuthor() + getTitle();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (Double.compare(book.price, price) != 0) return false;
        if (year != book.year) return false;
        if (!author.equals(book.author)) return false;
        if (!description.equals(book.description)) return false;
        if (!isbn.equals(book.isbn)) return false;
        if (!title.equals(book.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = year;
        result = 31 * result + author.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + isbn.hashCode();
        result = 31 * result + description.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Book").append(author);
        stringBuilder.append("\n author: ").append(author);
        stringBuilder.append("\n title: ").append(title);
        return stringBuilder.toString();
    }
}
