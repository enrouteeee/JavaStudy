package chapter11;

import java.util.Comparator;

public class KeyComparator implements Comparator<Key> {
    @Override
    public int compare(Key k1, Key k2) {
        if(k1.getNumber() < k2.getNumber()) return -1;
        else if(k1.getNumber() == k2.getNumber()) return 0;
        else return 1;
    }
}
