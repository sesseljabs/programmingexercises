package LagersystemCopy.domain;

import java.util.ArrayList;
import java.util.Arrays;
public class NonFoodItem extends Item {
    private ArrayList < String > materials;
    public static NonFoodItem unmarshal(String[] args) {
        if (args.length != 4) return null;
        String name = args[1];
        double price = Double.parseDouble(args[2]);
        String[] materials = args[3].split(",");
        return new NonFoodItem(name, price, materials);
    }
    public NonFoodItem(String name, double price, ArrayList < String > materials) {
        super(name, price);
        this.materials = materials;
    }
    public NonFoodItem(String name, double price, String[] materials) {
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
        return "NonFoodItem name='" + getName() +
            "' price='" + getPrice() +
            "' materials='" + m + "'";
    }
    @Override
    public String getType() {
        return "NonFoodItem";
    }
    @Override
    public String[] getState() {
        return new String[] {
            getName(), "" + getPrice(), String.join(",", getMaterials())
        };
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
}