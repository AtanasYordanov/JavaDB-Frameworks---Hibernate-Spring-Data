package Animals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String type;
        while (!"Beast!".equals(type = reader.readLine())) {
            String[] tokens = reader.readLine().split(" ");
            try {
                Animal animal = getAnimal(type, tokens);
                System.out.println(animal);
                System.out.println(animal.produceSound());
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static Animal getAnimal(String type, String[] tokens) {
        switch (type) {
            case "Cat":
                return new Cat(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
            case "Dog":
                return new Dog(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
            case "Frog":
                return new Frog(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
            case "Kitten":
                return new Kitten(tokens[0], Integer.parseInt(tokens[1]));
            case "Tomcat":
                return new Tomcat(tokens[0], Integer.parseInt(tokens[1]));
            default:
                throw new IllegalArgumentException("Invalid input!");
        }
    }
}
