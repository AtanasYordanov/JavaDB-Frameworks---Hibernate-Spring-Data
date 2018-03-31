package app;

import app.entities.Author;
import app.entities.Book;
import app.entities.Category;
import app.dto.ReducedBook;
import app.enums.AgeRestriction;
import app.enums.EditionType;
import app.services.api.AuthorService;
import app.services.api.BookService;
import app.services.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private BookService bookService;
    private AuthorService authorService;
    private CategoryService categoryService;


    @Autowired
    public ConsoleRunner(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        //this.seedDatabase();

        //Problem 1
        //this.printBooksByAgeRestriction();

        //Problem 2
        //this.printGoldBooksWithCopiesLessThan5000();

        //Problem 3
        //this.printBooksWithPriceLessThan5AndMoreThan40();

        //Problem 4
        //this.printBooksNotReleasedInYear();

        //Problem 5
        //this.printBooksReleasedBefore();

        //Problem 6
        //this.printAuthorsWithFirstNameEndingWith();

        //Problem 7
        //this.printBooksWithTitleContaining();

        //Problem 8
        //this.printBooksWithAuthorNameStartingWith();

        //Problem 9
        //this.printBooksWithTitleLongerThan();

        //Problem 10
        //this.printBookCopiesPerAuthor();

        //Problem 11
        //this.printBookWithTitle();

        //Problem 12
        //this.increaseCopiesForBooksReleasedAfter();

        //Problem 13
        //this.removeBooksWithCopiesLessThan();

        //Problem 14
        //this.getAuthorBookCount();
    }

    private void getAuthorBookCount() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = reader.readLine().split("\\s+");
        int count = this.authorService.getAuthorBooksCountProcedure(tokens[0], tokens[1]);
        System.out.println(count);
    }

    private void removeBooksWithCopiesLessThan() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(reader.readLine());
        int booksDeleted = this.bookService.removeBooksWithCopiesLessThan(number);
        System.out.printf("%d books were deleted%n", booksDeleted);
    }

    private void increaseCopiesForBooksReleasedAfter() throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        Date date = sdf.parse(reader.readLine());
        int count = Integer.parseInt(reader.readLine());
        int copiesAdded = this.bookService.increaseCopiesForBooksReleasedAfter(date, count);
        System.out.println(copiesAdded*count);
    }

    private void printBookWithTitle() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String title = reader.readLine();
        ReducedBook book = this.bookService.findBookByTitle(title);
        System.out.printf("%s %s %s %.2f%n", book.getTitle(), book.getEditionType(), book.getAgeRestriction(), book.getPrice());
    }

    private void printBookCopiesPerAuthor() {
        List<Object[]> resultSet = this.authorService.getBookCopiesPerAuthor();
        for (Object[] tokens : resultSet) {
            System.out.printf("%s - %s%n", tokens[0], tokens[1]);
        }
    }

    private void printBooksWithTitleLongerThan() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(reader.readLine());
        int count = this.bookService.findBooksWithTitleLongerThan(length);
        System.out.println(count);
    }

    private void printBooksWithAuthorNameStartingWith() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String pattern = reader.readLine();
        List<Book> books = this.bookService.findBooksWithAuthorNameStartingWith(pattern);
        for (Book book : books) {
            System.out.printf("%s (%s %s)%n", book.getTitle(), book.getAuthor().getFirstName(), book.getAuthor().getLastName());
        }
    }

    private void printBooksWithTitleContaining() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String pattern = reader.readLine();
        List<Book> books = this.bookService.findBooksWithTitleContaining(pattern);
        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }

    private void printAuthorsWithFirstNameEndingWith() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String pattern = reader.readLine();
        List<Author> authors = this.authorService.findAllWithFirstNameEndingWith(pattern);
        for (Author author : authors) {
            System.out.println(author.getFirstName()+ " " + author.getLastName());
        }
    }

    private void printBooksReleasedBefore() throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = sdf.parse(reader.readLine());
        List<Book> books = this.bookService.findBooksReleasedBefore(date);
        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }

    private void printBooksNotReleasedInYear() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int year = Integer.parseInt(reader.readLine());
        List<Book> books = this.bookService.findBooksNotReleasedInYear(year);
        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }

    private void printBooksWithPriceLessThan5AndMoreThan40() {
        List<Book> books = this.bookService.findBooksWithPriceLowerThan5AndMoreThan40();
        for (Book book : books) {
            System.out.printf("%s - $%.2f%n", book.getTitle(), book.getPrice());
        }
    }

    private void printGoldBooksWithCopiesLessThan5000() {
        List<Book> books = this.bookService.findGoldBooksWithCopiesLessThan5000();
        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }

    private void printBooksByAgeRestriction() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        AgeRestriction ageRestriction = AgeRestriction.valueOf(reader.readLine().toUpperCase());
        List<Book> books = this.bookService.findBooksByAgeRestriction(ageRestriction);
        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }

    private void seedDatabase() throws IOException, ParseException {

        List<Author> authors = new ArrayList<>();

        BufferedReader authorsReader = new BufferedReader(new FileReader("src/main/resources/files/authors.txt"));
        String line = authorsReader.readLine();
        while ((line = authorsReader.readLine()) != null) {
            String[] data = line.split("\\s+");
            String firstName = data[0];
            String lastName = data[1];

            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);

            authors.add(author);

            authorService.save(author);
        }

        List<Category> allCategories = new ArrayList<>();
        BufferedReader categoriesReader = new BufferedReader(new FileReader("src/main/resources/files/categories.txt"));
        while ((line = categoriesReader.readLine()) != null) {
            Category category = new Category();
            category.setName(line);
            allCategories.add(category);
            categoryService.save(category);
        }

        BufferedReader booksReader = new BufferedReader(new FileReader("src/main/resources/files/books.txt"));
        line = booksReader.readLine();
        Random random = new Random();
        while ((line = booksReader.readLine()) != null){
            String[] data = line.split("\\s+");
            int authorIndex = random.nextInt(authors.size());
            Author author = authors.get(authorIndex);
            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            Date releaseDate = formatter.parse(data[1]);
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }
            titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
            String title = titleBuilder.toString();

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);

            author.addBook(book);
            authorService.save(author);

            Set<Category> categories = new HashSet<>();
            int categoriesCount = random.nextInt(4);
            for (int i = 0; i < categoriesCount; i++) {
                int categoryIndex = random.nextInt(allCategories.size());
                categories.add(allCategories.get(categoryIndex));
            }
            book.setCategories(categories);

            bookService.save(book);
        }
    }
}
