package Mankind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Human> humans = new ArrayList<>();
        String[] tokens = reader.readLine().split(" ");
        try {
            Student student = new Student(tokens[0], tokens[1], tokens[2]);
            humans.add(student);
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            return;
        }
        tokens = reader.readLine().split(" ");
        double salary = Double.parseDouble(tokens[2]);
        double workHours = Double.parseDouble(tokens[3]);
        try {
            Worker worker = new Worker(tokens[0], tokens[1], salary, workHours);
            humans.add(worker);
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            return;
        }
        for (Human human : humans) {
            System.out.println(human);
        }
    }
}
