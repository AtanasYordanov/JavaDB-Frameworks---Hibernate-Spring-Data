package BookLibrary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Library library = new Library("My BookLibrary.Library", new ArrayList<>());
        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split("\\s+");
            String author = input[1];
            double price = Double.parseDouble(input[5]);

            Book book = new Book(author, price);
            library.getAuthorsAndBooks().putIfAbsent(author, new ArrayList<>());
            library.getAuthorsAndBooks().get(author).add(book);
        }
        Map<Double, TreeSet<String>> tempMap = new TreeMap<>(Collections.reverseOrder());
        for (Map.Entry<String, ArrayList<Book>> pair : library.getAuthorsAndBooks().entrySet()) {
            double totalPrice = 0.0;
            for (Book book : pair.getValue()) {
                totalPrice += book.getPrice();
            }
            tempMap.putIfAbsent(totalPrice, new TreeSet<>());
            tempMap.get(totalPrice).add(pair.getKey());
        }
        for (Map.Entry<Double, TreeSet<String>> pair : tempMap.entrySet()) {
            for (String str : pair.getValue()) {
                System.out.printf("%s -> %.2f%n", str, pair.getKey());
            }
        }
    }
}
