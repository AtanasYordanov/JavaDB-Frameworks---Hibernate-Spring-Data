package BeerCounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while(!"End".equals(input = reader.readLine())){
            if (input == null || input.equals("")){
                break;
            }
            String[] tokens = input.split(" ");
            BeerCounter.buyBeer(Integer.parseInt(tokens[0]));
            BeerCounter.drinkBeer(Integer.parseInt(tokens[1]));
        }
        System.out.print(BeerCounter.getBeersInStock() + " ");
        System.out.println(BeerCounter.getBeersDrunkCount());
    }
}
