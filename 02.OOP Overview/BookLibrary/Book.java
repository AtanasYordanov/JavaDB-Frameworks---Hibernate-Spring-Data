package BookLibrary;

public class Book {
    private String author;
    private double price;

    Book(String author, double price) {
        this.author = author;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }
}
