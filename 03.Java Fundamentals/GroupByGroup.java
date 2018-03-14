import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class GroupByGroup {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Person> people = new ArrayList<>();
        String input;
        while (!"END".equals(input = reader.readLine())){
            String[] tokens = input.split(" ");
            String name = tokens[0] + " " + tokens[1];
            int group = Integer.parseInt(tokens[2]);
            Person person = new Person(name, group);
            people.add(person);
        }
        people.stream()
                .collect(Collectors.groupingBy(Person::getGroup))
                .forEach((key, value) -> {
                    System.out.print(key + " - ");
                    System.out.println(value.stream().map(Object::toString)
                            .collect(Collectors.joining(", ")));
                });
    }
}
class Person{
    private String name;
    private int group;

    public Person(String name, int group) {
        this.name = name;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public int getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
