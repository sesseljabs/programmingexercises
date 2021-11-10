package Lagersystem;

import java.util.ArrayList;

import java.util.Date;

import java.util.Collections;

class Inventory {





    private ArrayList < Item > items;

    Inventory(ArrayList < Item > items) {

        this.items = items;

    }

    Inventory() {

        this(new ArrayList < Item > ());

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

            if (item == null) continue;

            total += item.getPrice();

        }

        return total;

    }

    public void printInventory() {

        System.out.println("Inventory:");

        for (Item item: items) {

            if (item == null) continue;

            System.out.println(" - " + item);

        }

    }

    public static void printStatus(Inventory inventory) {

        inventory.printInventory();

        System.out.println("Total: " + inventory.getInventory());

        System.out.println("");

    }

    public void removeExpiredFoods() {

        ArrayList < Item > copy = new ArrayList < Item > ();

        for (int i = 0; i < items.size(); i++) {

            Item item = items.get(i);

            if (item == null) continue;

            try {





                boolean expired = item.isExpired();

                if (expired) {

                    items.remove(i);

                }

            } catch (UnsupportedOperationException e) {

            }

        }

        items = copy;

    }

    private static FoodItem safeFoodItem(String name, double price, Date expires) {
        try {

            return new FoodItem(name, price, expires);

        } catch (ExpiredItemAddedException e) {

            return null;

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

    public static void main(String args[]) {
        Inventory inventory = new Inventory();
        Item i1 = safeFoodItem("chocolate", 19.95, new Date(53  * 365 * 1000 * 60 * 60 * 24));
        Item i2 = safeFoodItem("coffee", 24.95, new Date(54  * 365 * 1000 * 60 * 60 * 24));
        Item i3 = safeFoodItem("Milk", 12.95, new Date(12 * 1000 * 60 * 60 * 24));
        Item i4 = new NonFoodItem("USB Charger", 17.45,
            new String[] {
                "plastic",
                "stuff"
            });
        Item[] items = new Item[] {
            i1,
            i2,
            i3,
            i4
        };

        printStatus(inventory);

        for (Item item: items) {

            if (item == null) continue;

            inventory.addItem(item);

            printStatus(inventory);

        }

        System.out.println("Sorting by name length:");

        for (Item i: inventory.sortedByNameLength()) {

            if (i == null) continue;

            System.out.println(" - " + i.getName() + " (" + i.getPrice() + ")");







        }

        System.out.println("");

        printStatus(inventory);

        System.out.println("Sorting by price:");

        for (Item i: inventory.sortedByPrice()) {

            if (i == null) continue;

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