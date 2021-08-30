package dunzo.assignment.store;

import dunzo.assignment.constants.Const;
import dunzo.assignment.model.Beverage;
import dunzo.assignment.model.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * Inventory store class which manages the quantity & availability of the ingredients in the coffee machine.
 */
public class InventoryStore {
    //Doing eager instantiation for making this class thread safe. The class is light weight, hence we can do eager instantiation.
    private static InventoryStore inventoryStore = new InventoryStore();
    //A dummy map to store the ingredient set as Java set method does not have an efficient get by key method.
    private Map<Ingredient, Ingredient> inventory = new HashMap();
    private List<Beverage> servedBeverages = new ArrayList();
    private List<Beverage> rejectedBeverages = new ArrayList();

    public static InventoryStore getInventoryStoreInstance() {
        return  inventoryStore;
    }

    public static void resetInventoryStoreInstance() {
        inventoryStore = new InventoryStore();
    }

    private InventoryStore() {

    }

    //Making adding of items to inventory thread safe.
    public synchronized void addItemsToInventoryStore(Ingredient ingredient) {
        if(inventory.containsKey(ingredient)) {
            ingredient.setQuantity(ingredient.getQuantity() + inventory.get(ingredient).getQuantity());
        }
        inventory.put(ingredient, ingredient);
    }

    public synchronized void processBeverage(Beverage beverage) {
        boolean serveBeverage = true;
        String rejectionReason = null;
        for (Ingredient ingredient :
                beverage.getIngredients()) {
            if(!inventory.containsKey(ingredient)) {
                serveBeverage = false;
                rejectionReason = ingredient.getIngredientName() + " " + Const.UNAVAILABLE_INGREDIENT_MESSAGE;
                break;
            }
            Ingredient availableIngredient = inventory.get(ingredient);
            if(availableIngredient.getQuantity() < ingredient.getQuantity()) {
                serveBeverage = false;
                rejectionReason = ingredient.getIngredientName() + " " + Const.INSUFFICIENT_QUANTITY_MESSAGE;
                break;
            }
        }
        if(serveBeverage == true) {
            serve(beverage);
        } else {
            reject(beverage, rejectionReason);
        }
    }

    private void reject(Beverage beverage, String rejectionReason) {
        rejectedBeverages.add(beverage);
        String message = beverage.getBeverageName() + " " + Const.REJECTION_MESSAGE + " " + rejectionReason;
        System.out.println(message);
    }

    private void serve(Beverage beverage) {
        servedBeverages.add(beverage);
        for (Ingredient ingredient :
                beverage.getIngredients()) {
            updateInventoryWithIngredient(ingredient);
        }
        String message = beverage.getBeverageName() + " " + Const.PREPARED_MESSAGE;
        System.out.println(message);
    }

    private void updateInventoryWithIngredient(Ingredient ingredient) {
        int currentQuantity = inventory.get(ingredient).getQuantity();
        int usedQuantity = ingredient.getQuantity();
        int availableQuantity = currentQuantity - usedQuantity;
        ingredient.setQuantity(availableQuantity);
        inventory.put(ingredient, ingredient);
    }

    public List<Beverage> getRejectedBeverages() {
        return rejectedBeverages;
    }

    public List<Beverage> getServedBeverages() {
        return servedBeverages;
    }
}
