package com.vfive.game.graphisObj;

public class Inventory {

    public static final int SUM_SLOTS = 5;
    ItemInventory[] items;

    public Inventory() {
        items = new  ItemInventory[SUM_SLOTS];
    }

    public int getIndexFirstFreeSlot() {
        for (int i = 0; i < SUM_SLOTS; i++) {
            if (items[i] == null)
                return i;
        }
        return -1;
    }

    public void addItem(ItemInventory item) {
        int i = getIndexFirstFreeSlot();
        if (i != -1) {
            items[i] = item;
            item.index = i;
        }
    }

    public void removeItem(int index) {
        if (items[index] != null)
            items[index] = null;
    }

    public ItemInventory getItem(int index) {
        return items[index];
    }

    public boolean isEmpty(int index) {
        return items[index] == null;
    }

    public void clear() {
        for (int i = 0; i < SUM_SLOTS; i++) {
            removeItem(i);
        }
    }
}
