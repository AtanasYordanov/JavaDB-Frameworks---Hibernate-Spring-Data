import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class StudentsByEmailDomain {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, String> studentsEmails = new LinkedHashMap<>();
        String input;
        while(!"END".equals(input = reader.readLine())){
            String[] tokens = input.split(" ");
            String email = tokens[2];
            studentsEmails.put(tokens[0] + " " + tokens[1], email);
        }
        studentsEmails.entrySet().stream()
                .filter(kvp -> kvp.getValue().split("@")[1].equals("gmail.com"))
                .forEach(kvp -> System.out.println(kvp.getKey()));
    }
}
