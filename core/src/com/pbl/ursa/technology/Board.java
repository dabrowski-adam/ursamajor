package com.pbl.ursa.technology;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Map;

public class Board {
    Rectangle bounds;
    Cell[][] cells;

    public Board(float x, float y) {
        bounds = new Rectangle(x, y, 250, 250);

        cells = new Cell[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                cells[i][j] = new Cell(
                        bounds.x + i * 50,
                        bounds.y + j * 50,
                        50,
                        50);
            }
        }
    }

    public void render(SpriteBatch spriteBatch, Map<Tool, Texture> resources) {
        if (spriteBatch == null || !spriteBatch.isDrawing()) { return; }

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                cells[y][x].render(spriteBatch, resources);
            }
        }
    }
}
