import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;

public class StudentsByFirstAndLastName {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> students = new ArrayList<>();
        String input;
        while(!"END".equals(input = reader.readLine())){
            students.add(input);
        }
        students.stream()
                .sorted((s1, s2) -> {
            int comp = s1.split(" ")[1].compareTo(s2.split(" ")[1]);
            if (comp == 0){
                comp = s2.split(" ")[0].compareTo(s1.split(" ")[0]);
            }
            return comp;
                })
                .forEach(System.out::println);
    }
}
