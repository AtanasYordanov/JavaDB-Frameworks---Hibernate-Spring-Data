package ShoppingSpree;

import java.util.ArrayList;

public class Person {
    private String name;
    private int money;
    private ArrayList<Product> bag;

    public Person(String name, int money) {
        this.name = name;
        this.money = money;
        this.bag = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Product> getBag() {
        return bag;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
