import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class StudentsByAge {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> students = new LinkedHashMap<>();
        String input;
        while(!"END".equals(input = reader.readLine())){
            String[] tokens = input.split(" ");
            int age = Integer.parseInt(tokens[2]);
            students.put(tokens[0] + " " + tokens[1], age);
        }
        students.entrySet().stream()
                .filter(kvp -> kvp.getValue() >= 18  && kvp.getValue() <= 24)
                .forEach(kvp -> System.out.printf("%s %d%n", kvp.getKey(), kvp.getValue()));
    }
}
