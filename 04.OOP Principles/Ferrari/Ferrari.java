package Ferrari;

public class Ferrari implements Car {
    private String driver;
    private String model = "488-Spider";

    public Ferrari(String driver) {
        this.driver = driver;
    }

    public String getModel(){
        return this.model;
    }

    @Override
    public void useBrakes() {
        System.out.print("Brakes!");
    }

    @Override
    public void pushGas() {
        System.out.print("Zadu6avam sA!");
    }

    @Override
    public String getDriver() {
        return this.driver;
    }
}
