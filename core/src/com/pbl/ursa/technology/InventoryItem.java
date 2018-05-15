package com.pbl.ursa.technology;

import com.badlogic.gdx.math.Rectangle;

public class InventoryItem {
    Rectangle bounds;
    Tool item;
    int count;

    public InventoryItem(float x, float y, Tool item) {
        bounds = new Rectangle(x, y, 50, 50);
        this.item = item;
        count = 1;
    }

    public Tool remove() {
        if (count > 0) {
            count = count - 1;
            return item;
        }

        return null;
    }

}
