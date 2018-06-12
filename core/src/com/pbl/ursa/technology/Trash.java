package com.pbl.ursa.technology;

import com.badlogic.gdx.math.Vector2;

public class Trash {
    Tool type;
    int posX;
    int posY;
    int nextPosX;
    int nextPosY;
    boolean isMoved;
    boolean isRecycled;

    public Trash(Tool type, int x, int y) {
        this.type = type;
        this.posX = x;
        this.posY = y;
        isMoved = false;
        isRecycled = false;
    }
}