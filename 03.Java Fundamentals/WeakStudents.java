import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class WeakStudents {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, List<Integer>> studentsMarks = new LinkedHashMap<>();
        String input;
        while (!"END".equals(input = reader.readLine())) {
            String[] tokens = input.split(" ");
            String fullName = tokens[0] + " " + tokens[1];
            studentsMarks.putIfAbsent(fullName, new ArrayList<>());
            for (int i = 2; i < tokens.length; i++) {
                studentsMarks.get(fullName).add(Integer.parseInt(tokens[i]));
            }
        }
        studentsMarks.entrySet().stream()
                .filter(kvp -> kvp.getValue().stream()
                        .filter(m -> m <= 3)
                        .collect(Collectors.toList()).size() >= 2)
                .forEach(kvp -> System.out.println(kvp.getKey()));
    }
}
