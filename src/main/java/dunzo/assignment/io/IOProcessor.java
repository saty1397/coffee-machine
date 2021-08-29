package dunzo.assignment.io;

import dunzo.assignment.constants.Const;
import dunzo.assignment.model.Beverage;
import dunzo.assignment.store.BeverageList;
import dunzo.assignment.model.Ingredient;
import dunzo.assignment.model.Machine;
import dunzo.assignment.store.InventoryStore;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

/***
 * This class is used to process the Json input from file. Eventually this class converts the given json object into Java objects and makes them available for use throughout the project.
 */
public class IOProcessor {
    public static void JSONProcessor(String jsonInput) throws AssertionError {
        JSONObject data = new JSONObject(jsonInput);
        JSONObject machineData = data.getJSONObject(Const.MACHINE);

        //Extract the outlets count.
        JSONObject outlets = machineData.getJSONObject(Const.OUTLETS);
        int outletCount = outlets.getInt(Const.COUNT);
        Machine.initMachineInstance(outletCount);

        //Extract ingredients and add it to the ingredient store.
        JSONObject items = machineData.getJSONObject(Const.TOTAL_ITEMS_QUANTITY);
        items.keySet().stream().forEach(item -> {
            int quantity = items.getInt(item);
            Ingredient ingredient = new Ingredient(item, quantity);
            InventoryStore inventoryStore = InventoryStore.getInventoryStoreInstance();
            inventoryStore.addItemsToInventoryStore(ingredient);
        });

        //Extract the beverages list and add it to the global beverage store.
        JSONObject beverages = machineData.getJSONObject(Const.BEVERAGES);
        beverages.keySet().stream().forEach(beverageName -> {
            JSONObject ingredientsInput = beverages.getJSONObject(beverageName);
            List<Ingredient> ingredients = ingredientsInput.keySet().stream().map(ingredientInput -> {
                int quantity = ingredientsInput.getInt(ingredientInput);
                Ingredient ingredient = new Ingredient(ingredientInput, quantity);
                return ingredient;
            }).collect(Collectors.toList());
            Beverage beverage = new Beverage(beverageName, ingredients);
            BeverageList beverageList = BeverageList.getBeverageListInstance();
            beverageList.addBeverage(beverage);
        });
    }
}
