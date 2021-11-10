package Lagersystem;
import java.util.Date;

class FoodItem extends Item {

    private Date expires;

    FoodItem(String name, double price, Date expires) throws ExpiredItemAddedException {
        super(name, price);

        if (expires.compareTo(new Date()) < 0) {

            throw new ExpiredItemAddedException();

        }

        this.expires = expires;

    }

    public Date getExpires() {

        return expires;

    }

    @Override

    public String toString() {

        return "FoodItem name='" + getName()

            +
            "' price='" + getPrice()

            +
            "' expires='" + getExpires() + "'";

    }

    @Override

    public boolean isExpired() {

        return expires.compareTo(new Date()) < 0;

    }

    public static void main(String[] args) {

        FoodItem[] items = new FoodItem[10];

        for (int i = 0; i < items.length; i++) {

            try {

                items[i] = new FoodItem("Item " + i, 12.3 * i,

                    new Date((50 + i) * 365 * 1000 * 60 * 60 * 24));

            } catch (ExpiredItemAddedException e) {
                items[i] = null;
                System.out.println(e.getMessage());

            }

        }


        for (FoodItem item: items) {

            System.out.println(item);

        }

    }

}