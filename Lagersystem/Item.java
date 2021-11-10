package Lagersystem;
abstract class Item implements Expirable, Comparable < Item > {
    private String name;



    private double price;

    Item(String name, double price) {

        this.name = name;

        this.price = price;

    }

    public String getName() {

        return name;

    }

    public double getPrice() {

        return price;

    }

    @Override

    public int compareTo(Item it) {

        if (it == null) return -1;

        double tmp = getPrice() - it.getPrice();

        if (tmp < 0) {

            return -1;

        } else if (tmp > 0) {
            return 1;
        } else {

            return 0;

        }

    }

}