package app.runners;

import app.entities.Author;
import app.entities.Book;
import app.entities.Category;
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
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ConsoleRunner implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void run(String... args) throws Exception {
        //this.seedDatabase();
        //this.printAllBooksAfter2000();
        //this.printAuthorsWithBooksReleaseDateBefore1990();
        //this.printAuthorsByBookCount();
        this.printAllBooksByGeorgePowell();
    }

    private void printAllBooksByGeorgePowell() {
        for (Book book : bookService.findBooksByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateAndTitle()) {
            System.out.println(book.getTitle());
        }
    }

    private void printAuthorsByBookCount() {
        for (Author author : authorService.findAllByOrderByBooksDesc()) {
            System.out.println(author.getFirstName() + " " + author.getLastName());
        }
    }

    private void printAuthorsWithBooksReleaseDateBefore1990() {
        for (Author author : authorService.findAuthorsByBooksReleaseDateBefore1990()) {
            System.out.println(author.getFirstName() + " " + author.getLastName());
        }
    }

    private void printAllBooksAfter2000(){
        for (String title : bookService.findAllBooksAfter2000()) {
            System.out.println(title);
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
