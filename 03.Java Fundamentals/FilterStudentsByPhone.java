import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class FilterStudentsByPhone {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, String> studentsPhones = new LinkedHashMap<>();
        String input;
        while(!"END".equals(input = reader.readLine())){
            String[] tokens = input.split(" ");
            String phone = tokens[2];
            studentsPhones.put(tokens[0] + " " + tokens[1], phone);
        }
        studentsPhones.entrySet().stream()
                .filter(kvp -> kvp.getValue().startsWith("02") ||
                        kvp.getValue().startsWith("+3592"))
                .forEach(kvp -> System.out.println(kvp.getKey()));
    }
}
