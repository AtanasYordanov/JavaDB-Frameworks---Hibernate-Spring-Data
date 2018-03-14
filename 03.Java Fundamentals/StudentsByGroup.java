import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class StudentsByGroup {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, List<String>> students = new LinkedHashMap<>();
        String input;
        while(!"END".equals(input = reader.readLine())){
            String[] tokens = input.split(" ");
            int group = Integer.parseInt(tokens[2]);
            students.putIfAbsent(group, new ArrayList<>());
            students.get(group).add(tokens[0] + " " + tokens[1]);
        }
        students.entrySet().stream()
                .filter(kvp -> kvp.getKey() == 2)
                .forEach(kvp -> {
                    kvp.getValue().stream()
                            .sorted(Comparator.comparing(s -> s.split(" ")[0]))
                            .forEach(System.out::println);
                });
    }
}
