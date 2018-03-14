package ShoppingSpree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Person> people = new LinkedHashMap<>();
        Map<String, Product> products = new LinkedHashMap<>();

        String[] tokens = reader.readLine().split(";");
        for (String token : tokens) {
            String[] nameAndMoney = token.split("=");
            String name = nameAndMoney[0];
            int money = Integer.parseInt(nameAndMoney[1]);
            if (name.equals("")) {
                System.out.println("Name cannot be empty");
                return;
            } else if (money < 0) {
                System.out.println("Money cannot be negative");
                return;
            }
            Person person = new Person(name, money);
            people.put(name, person);
        }
        tokens = reader.readLine().split(";");
        for (String token : tokens) {
            String[] productAndCost = token.split("=");
            String name = productAndCost[0];
            int cost = Integer.parseInt(productAndCost[1]);
            if (name.equals("")) {
                System.out.println("Name cannot be empty");
                return;
            } else if (cost < 0) {
                System.out.println("Cost cannot be negative");
                return;
            }
            Product product = new Product(name, cost);
            products.put(name, product);
        }

        String input;
        while (!"END".equals(input = reader.readLine())) {
            if (input == null){
                break;
            }
            tokens = input.split(" ");
            String person = tokens[0];
            String product = tokens[1];
            int money = people.get(person).getMoney();
            int cost = products.get(product).getCost();
            if (money < cost) {
                System.out.printf("%s can't afford %s%n", person, product);
            } else {
                System.out.printf("%s bought %s%n", person, product);
                people.get(person).getBag().add(products.get(product));
                people.get(person).setMoney(money - cost);
            }
        }
        for (Person person : people.values()) {
            System.out.printf("%s - ", person.getName());
            if (person.getBag().size() == 0) {
                System.out.println("Nothing bought");
            } else {
                for (int i = 0; i < person.getBag().size(); i++) {
                    if (i != 0){
                        System.out.print(", ");
                    }
                    System.out.print(person.getBag().get(i).getName());
                }
                System.out.println();
            }
        }
    }
}
