package Ferrari;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String driverName = reader.readLine();
        Car car = new Ferrari(driverName);
        System.out.print(car.getModel() + "/");
        car.useBrakes();
        System.out.print("/");
        car.pushGas();
        System.out.print("/");
        System.out.println(car.getDriver());
    }
}
