package LagersystemCopy.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.util.List;
import LagersystemCopy.data.FileBackend;
public class Inventory {
    private ArrayList < Item > items;
    public Inventory(ArrayList < Item > items) {
        this.items = items;
    }
    public Inventory() {
        this(new ArrayList < Item > ());
    }
    public boolean load(String filename) {
        FileBackend fb = new FileBackend(filename);
        if (fb == null) return false;
        System.out.println("a");
        List < String > entries = fb.load();
        if (entries == null) return false;
        System.out.println("b");
        for (String entry: entries) {
            Item item = Item.unmarshal(entry);
            System.out.println("c " + item);
            if (item != null) addItem(item);

        }
        return true;
    }
    public boolean store(String filename) {
        FileBackend fb = new FileBackend(filename);
        if (fb == null) return false;
        List < String > entries = new ArrayList < String > ();
        for (Item item: items) {
            entries.add(item.marshal());
        }
        return fb.store(entries);
    }
    public void addItem(Item item) {
        if (!items.contains(item)) {
            items.add(item);
        }
    }
    public void removeItem(Item item) {
        items.remove(item);
    }
    public double getInventory() {
        double total = 0.0;
        for (Item item: items) {
            total += item.getPrice();
        }
        return total;
    }
    public void printInventory() {
        System.out.println("Inventory:");
        for (Item item: items) {
            System.out.println(" - " + item);
        }
    }
    public static void printStatus(Inventory inventory) {
        inventory.printInventory();
        System.out.println("Total: " + inventory.getInventory());
        System.out.println("");
    }

    public void removeExpiredFoods() {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            try {
                boolean expired = item.isExpired();
                if (expired) {
                    items.remove(i);
                }
            } catch (UnsupportedOperationException e) {}
        }
    }
    public ArrayList < Item > sortedByNameLength() {
        ArrayList < Item > result = new ArrayList < Item > (items);
        Collections.sort(result, new ItemNameLengthComparator());
        return result;
    }
    public ArrayList < Item > sortedByPrice() {
        ArrayList < Item > result = new ArrayList < Item > (items);
        Collections.sort(result);
        return result;
    }
    public static FoodItem safeFoodItem(String name, double price, Date date) {
        try {
            return new FoodItem(name, price, date);
        } catch (ExpiredItemAddedException e) {
            return null;
        }
    }
    public static void main(String args[]) {
        Inventory inventory = new Inventory();
        Item i1 = safeFoodItem("chocolate", 19.95, new Date(52 * 365 * 1000 * 60 * 60 * 24));
        Item i2 = safeFoodItem("coffee", 24.95, new Date(53 * 365 * 1000 * 60 * 60 * 24));
        Item i3 = safeFoodItem("Milk", 12.95, new Date(12 * 1000 * 60 * 60 * 24));
        Item i4 = safeFoodItem("Egg", 4.95, new Date(0 * 1000 * 60 * 60 * 24));
        Item i5 = new NonFoodItem("USB Charger", 17.45,
            new String[] {
                "plastic",
                "stuff"
            });
        Item[] items = new Item[] {
            i1,
            i2,
            i3,
            i4,
            i5
        };
        printStatus(inventory);
        for (Item item: items) {
            // guard: don't add null values
            if (item == null) continue;
            inventory.addItem(item);
            System.out.println("Adding " + item);

            printStatus(inventory);
        }
        System.out.println("Sorting by name length:");
        for (Item i: inventory.sortedByNameLength()) {
            System.out.println(" - " + i.getName() + " (" + i.getPrice() + ")");
        }
        System.out.println("");
        printStatus(inventory);
        System.out.println("Sorting by price:");
        for (Item i: inventory.sortedByPrice()) {
            System.out.println(" - " + i.getName() + " (" + i.getPrice() + ")");
        }
        System.out.println("");
        printStatus(inventory);
        inventory.removeItem(i1);
        printStatus(inventory);
        inventory.removeExpiredFoods();
        printStatus(inventory);
    }
}