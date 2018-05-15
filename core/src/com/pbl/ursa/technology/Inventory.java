package com.pbl.ursa.technology;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.HashMap;
import java.util.Map;

public class Inventory implements ToolHolder {
    Rectangle bounds;
    Map<Tool, Integer> tools;

    public Inventory(float x, float y) {
        tools = new HashMap<Tool, Integer>();
        bounds = new Rectangle(x, y, 320, 180);
    }

    public void render(SpriteBatch spriteBatch, Map<Tool, Texture> resources, BitmapFont font) {
        if (spriteBatch == null || !spriteBatch.isDrawing()) { return; }

        spriteBatch.draw(
                resources.get(Tool.BottomBackground),
                bounds.x,
                bounds.y,
                bounds.width,
                bounds.height);

        int i = 0;
        for (Tool key : tools.keySet()) {
            spriteBatch.draw(
                    resources.get(key),
                    35 + i * 65,
                    bounds.y + 35,
                    50,
                    50);
            font.draw(
                    spriteBatch,
                    tools.get(key).toString(),
                    35 + 40 + i * 65,
                    bounds.y + 35 + 40);
            i++;
        }
    }

    @Override
    public boolean insert(Tool tool) {
        tools.put(tool, tools.get(tool) + 1);

        return true;
    }
}
