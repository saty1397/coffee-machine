package dunzo.assignment.store;

import dunzo.assignment.model.Beverage;

import java.util.ArrayList;
import java.util.List;

/***
 * A list of beverages that has been requested to be prepared.
 */
public class BeverageList {
    private static BeverageList beverageList = new BeverageList();
    private List<Beverage> beverages = new ArrayList<>();

    public static BeverageList getBeverageListInstance() {
        return beverageList;
    }

    private BeverageList() {
    };

    //Making the addBeverage function synchronised to make it thread safe.
    public synchronized void addBeverage(Beverage beverage) {
        beverages.add(beverage);
    }

    public List<Beverage> getBeverages() {
        return beverages;
    }
}
