package BeerCounter;

public class BeerCounter {
    private static int beersInStock = 0;
    private static int beersDrunkCount = 0;

    public static void buyBeer(int bottlesCount){
        beersInStock += bottlesCount;
    }

    public static void drinkBeer(int bottlesCount){
        beersDrunkCount += bottlesCount;
        beersInStock -= bottlesCount;
    }

    public static int getBeersInStock() {
        return beersInStock;
    }

    public static int getBeersDrunkCount() {
        return beersDrunkCount;
    }
}
