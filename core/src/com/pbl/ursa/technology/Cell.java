package com.pbl.ursa.technology;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Map;

public class Cell {
    Rectangle bounds;
    Tool content;

    public Cell(float x, float y, float width, float height) {
        bounds = new Rectangle(x, y, width, height);
        content = Tool.Empty;
    }

    public void render(SpriteBatch spriteBatch, Map<Tool, Texture> resources) {
        if (spriteBatch == null || !spriteBatch.isDrawing()) { return; }
        if (!resources.containsKey(content)) { return; }

        spriteBatch.draw(
                resources.get(content),
                bounds.x,
                bounds.y,
                bounds.width,
                bounds.height);
    }

}
