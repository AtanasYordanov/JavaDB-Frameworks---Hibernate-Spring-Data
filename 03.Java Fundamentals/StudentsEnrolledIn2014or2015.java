import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class StudentsEnrolledIn2014or2015 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, List<Integer>> students = new LinkedHashMap<>();
        String input;
        while(!"END".equals(input = reader.readLine())){
            String[] tokens = input.split(" ");
            students.putIfAbsent(tokens[0], new ArrayList<>());
            for (int i = 1; i < tokens.length; i++) {
                students.get(tokens[0]).add(Integer.parseInt(tokens[i]));
            }
        }
        students.entrySet().stream()
                .filter(kvp -> kvp.getKey().endsWith("14") || kvp.getKey().endsWith("15"))
                .forEach(kvp -> System.out.println(kvp.getValue().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(" "))));
    }
}
