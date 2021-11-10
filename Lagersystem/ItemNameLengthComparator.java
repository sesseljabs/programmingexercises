package Lagersystem;

import java.util.Comparator;

class ItemNameLengthComparator implements Comparator<Item> { @Override

    public int compare (Item i1, Item i2) {

        if (i2==null) return -1;

        if (i1==null) return 1;

        return i1.getName().length() - i2.getName().length();

    }

}
