package LagersystemCopy.presentation;

import LagersystemCopy.domain.Inventory;
import LagersystemCopy.domain.Item;
import LagersystemCopy.domain.NonFoodItem;
public class AddCLI {
    public static void main(String[] args) {

        Item i1 = new NonFoodItem("USB_Charger", 17.45,
            new String[] {
                "plastic",
                "stuff"
            });
        Inventory inventory = new Inventory();
        inventory.load("inventory.txt");
        inventory.addItem(i1);
        inventory.store("inventory.txt");
    }
}