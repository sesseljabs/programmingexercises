package LagersystemCopy.domain;

import java.util.Date;
import java.text.SimpleDateFormat;
public class FoodItem extends Item {
    private Date expires;
    static FoodItem unmarshal(String[] args) {
        if (args.length != 4) return null;
        String name = args[1];
        double price = Double.parseDouble(args[2]);
        Date date;
        try {
            date = (new SimpleDateFormat()).parse(args[3]);
        } catch (java.text.ParseException e) {
            return null;
        }
        try {
            return new FoodItem(name, price, date);
        } catch (ExpiredItemAddedException e) {
            return null;
        }
    }
    FoodItem(String name, double price, Date expires) throws ExpiredItemAddedException {
        super(name, price);
        this.expires = expires;
        if (isExpired()) {
            throw new ExpiredItemAddedException();
        }
    }
    public Date getExpires() {
        return expires;
    }
    @Override
    public String toString() {
        return "FoodItem name='" + getName() +
            "' price='" + getPrice() +
            "' expires='" + getExpires() + "'";
    }
    @Override
    public boolean isExpired() {
        return expires.compareTo(new Date()) < 0;
    }
    @Override
    public String getType() {
        return "FoodItem";
    }
    @Override
    public String[] getState() {
        return new String[] {
            getName(), "" + getPrice(), "" + expires
        };
    }
    public static void main(String[] args) {
        FoodItem[] items = new FoodItem[10];
        
        for (int i = 0; i < items.length; i++) {
            try {
                items[i] = new FoodItem("Item " + i, 12.3 * i,
                    new Date(i * 30  * 365 * 1000 * 60 * 60 * 24));
            } catch (ExpiredItemAddedException e) {
                items[i] = null;
            }
        }
        for (FoodItem item: items) {
            System.out.println(item);
        }
    }
}