package BoderControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> birthdays = new ArrayList<>();
        String input;
        while (!"End".equals(input = reader.readLine())) {
            String[] tokens = input.split(" ");
            if (tokens[0].equals("Pet")) {
                String birthday = tokens[2];
                birthdays.add(birthday);
            } else if (tokens[0].equals("Citizen")){
                String birthday = tokens[4];
                birthdays.add(birthday);
            }
        }
        String year = reader.readLine();
        for (String id : birthdays) {
            if (id.endsWith(year)) {
                System.out.println(id);
            }
        }
    }
}
