package book.kp26.data;

import java.util.Objects;

public class Book {
    private int id;
    private String name;
    private String author;
    private int year;
    private String note;

    public Book() {
    }

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book(String name, String author, int year, String note) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.note = note;
    }
    public Book(int id, String name, String author, int year, String note) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.note = note;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && year == book.year && Objects.equals(name, book.name) && Objects.equals(author, book.author) && Objects.equals(note, book.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, year, note);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + name + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + year +
                ", note='" + note + '\'' +
                '}';
    }
}
