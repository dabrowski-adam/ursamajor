package com.pbl.ursa.technology;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Map;

public class Board {
    Rectangle bounds;
    Cell[][] cells;

    public Board(float x, float y, final TechnologyGameScreen gameScreen) {
        InputHandler input = gameScreen.input;

        bounds = new Rectangle(x, y, 250, 250);

        cells = new Cell[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                final Cell cell = new Cell(
                        bounds.x + i * 50,
                        bounds.y + j * 50,
                        50,
                        50);

                input.bindDown(cell.bounds, new Callable() {
                    @Override
                    public void call(float x, float y) {
                        Tool tool = cell.remove();
                        if (tool != null) {
                            gameScreen.draggedItem = tool;
                            gameScreen.itemOrigin = cell;
                        }
                    }
                });
                input.bindUp(cell.bounds, new Callable() {
                    @Override
                    public void call(float x, float y) {
                        if (cell.insert(gameScreen.draggedItem)) {
                            gameScreen.draggedItem = null;
                        }
                    }
                });

                cells[i][j] = cell;
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
