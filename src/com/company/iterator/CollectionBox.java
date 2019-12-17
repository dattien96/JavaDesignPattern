package com.company.iterator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CollectionBox {
    private List<Integer> list = new ArrayList<>();

    public MyIterator getIterator() {
        return new MyIterator(this);
    }

    public void add(int in) {
        list.add(in);
    }

    public class MyIterator {
        private CollectionBox box;
        private java.util.Iterator iterator;
        private int value;

        public MyIterator(CollectionBox collectionBox) {
            box = collectionBox;
        }

        public void first() {
            iterator = box.list.iterator();
            next();
        }

        public void next() {
            try {
                value = (Integer)iterator.next();
            } catch (NoSuchElementException ex) {
                value =  -1;
            }
        }

        public boolean isDone() {
            return value == -1;
        }

        public int currentValue() {
            return value;
        }
    }
}
