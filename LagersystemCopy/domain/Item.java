package LagersystemCopy.domain;

public abstract class Item implements Expirable, Comparable < Item > {
    private String name;
    private double price;
    Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
    // abstract Item (String[] args);
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    @Override
    public boolean isExpired() {
        throw new UnsupportedOperationException("Item does not support this operation.");
    }
    @Override
    public int compareTo(Item it) {
        double tmp = getPrice() - it.getPrice();
        if (tmp < 0) {
            return -1;
        } else if (tmp > 0) {
            return 1;
        } else {
            return 0;
        }
    }
    abstract public String getType();
    abstract public String[] getState();
    public final String marshal() {
        String type = getType();
        String[] params = getState();
        return type + " " + String.join(" ", params);
    }
    static Item unmarshal(String string) {
        String[] args = string.split(" ");
        String type = args[0];
        System.out.println("b1 " + type);
        switch (type) {
            case "FoodItem":
                return FoodItem.unmarshal(args);
            case "NonFoodItem":
                System.out.println("b2a " + args.length);
                return NonFoodItem.unmarshal(args);
            default:
                System.out.println("b2b " + args.length);
                return null;
        }
    }
}