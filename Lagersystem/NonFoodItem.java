package Lagersystem;

import java.util.ArrayList;

import java.util.Arrays;

class NonFoodItem extends Item {

    private ArrayList < String > materials;

    NonFoodItem(String name, double price, ArrayList < String > materials) {
        super(name, price);

        this.materials = materials;

    }

    NonFoodItem(String name, double price, String[] materials) {
        super(name, price);

        this.materials = new ArrayList < String > (Arrays.asList(materials));

    }

    public ArrayList < String > getMaterials() {

        return materials;

    }

    @Override

    public String toString() {

        String m = "[";

        for (int i = 0; i < materials.size(); i++) {
            m += (i == 0 ? "" : ",") + materials.get(i);

        }

        m += "]";

        return "NonFoodItem name='" + getName()

            +
            "' price='" + getPrice()

            +
            "' materials='" + m + "'";

    }

    public static void main(String[] args) {
        NonFoodItem[] items = new NonFoodItem[10];

        for (int i = 0; i < items.length; i++) {

            items[i] = new NonFoodItem("Item " + i, 12.3 * i,

                new String[] {
                    "butter",
                    "cream"
                });

        }


        for (NonFoodItem item: items) {

            System.out.println(item);

        }

    }

    @Override
    public boolean isExpired() {
        return false;
    }

}