package com.pbl.ursa.technology;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Map;

public class Cell implements ToolHolder {
    Rectangle bounds;
    Tool content;
    boolean isDisabled;

    public Cell(float x, float y, float width, float height) {
        bounds = new Rectangle(x, y, width, height);
        content = Tool.Empty;
        isDisabled = false;
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

    public Tool remove() {
        if (isDisabled || content == Tool.Empty) { return null; }

        Tool removed = content;
        content = Tool.Empty;
        return removed;
    }

    @Override
    public boolean insert(Tool tool) {
        if (tool == null || content != Tool.Empty) { return false; }

        content = tool;
        return true;
    }

    public void rotate() {
        switch (content) {
            case BeltRight:
                content = Tool.BeltUp;
                break;
            case BeltDown:
                content = Tool.BeltRight;
                break;
            case BeltLeft:
                content = Tool.BeltDown;
                break;
            case BeltUp:
                content = Tool.BeltLeft;
                break;
            default:
                break;

        }
    }

    public void clear() {
        content = Tool.Empty;
        isDisabled = false;
    }
}
