package dunzo.assignment.model;

import dunzo.assignment.store.InventoryStore;

import java.util.List;

/***
 * Model class for Beverage.
 */
public class Beverage implements Runnable {
    private String beverageName;
    private List<Ingredient> ingredients;

    public Beverage(String beverageName, List<Ingredient> ingredients) {
        this.beverageName = beverageName;
        this.ingredients = ingredients;
    }

    public String getBeverageName() {
        return beverageName;
    }

    public void setBeverageName(String beverageName) {
        this.beverageName = beverageName;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public void run() {
        InventoryStore.getInventoryStoreInstance().processBeverage(this);
    }
}
