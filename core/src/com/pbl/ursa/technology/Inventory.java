package com.pbl.ursa.technology;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.pbl.ursa.UrsaGame;

import java.util.*;

public class Inventory implements ToolHolder {
    final TechnologyGameScreen gameScreen;
    Rectangle bounds;
    List<InventoryItem> tools;

    public Inventory(float x, float y, final TechnologyGameScreen gameScreen) {
        this.gameScreen = gameScreen;

        bounds = new Rectangle(x, y, 320, 180);
        tools = new ArrayList<InventoryItem>();

        final Inventory itemOrigin = this;
        gameScreen.input.bindUp(bounds, new Callable() {
            @Override
            public void call(float x, float y) {
                if (itemOrigin.insert(gameScreen.draggedItem)) {
                    gameScreen.draggedItem = null;
                } else {
                    gameScreen.resetDragged();
                }
            }
        });


        // Exit
        /*final UrsaGame game = gameScreen.game;
        gameScreen.input.bindDown(bounds, new Callable() {
            @Override
            public void call(float x, float y) {
                game.setScreen(game.mainMenuScreen);
                gameScreen.dispose();
            }
        });*/
    }

    public void render(SpriteBatch spriteBatch, Map<Tool, Texture> resources, BitmapFont font) {
        if (spriteBatch == null || !spriteBatch.isDrawing()) { return; }

        spriteBatch.draw(
                resources.get(Tool.BottomBackground),
                bounds.x,
                bounds.y,
                bounds.width,
                bounds.height);

        Iterator<InventoryItem> i = tools.iterator();
        while (i.hasNext()) {
            InventoryItem invItem = i.next();

            if (invItem.count == 0) {
                gameScreen.input.unbind(invItem.bounds);
                i.remove();
                continue;
            }

            spriteBatch.draw(
                    resources.get(invItem.item),
                    invItem.bounds.x,
                    invItem.bounds.y,
                    invItem.bounds.width,
                    invItem.bounds.height
            );
            if (invItem.count > 1) {
                font.draw(
                        spriteBatch,
                        String.valueOf(invItem.count),
                        invItem.bounds.x + 40,
                        invItem.bounds.y + 40
                );
            }
        }
    }

    private void add(Tool tool) {
        if (tool == null || tool == Tool.Empty) { return; }

        for (InventoryItem invItem : tools) {
            if (invItem.item == tool) {
                invItem.count = invItem.count + 1;
                return;
            }
        }

        int size = tools.size();
        final InventoryItem newInvItem = new InventoryItem(
                35 + size * 65,
                35 + bounds.y,
                tool);

        final Inventory itemOrigin = this;
        gameScreen.input.bindDown(newInvItem.bounds, new Callable() {
            @Override
            public void call(float x, float y) {
                Tool tool = newInvItem.remove();
                if (tool != null) {
                    gameScreen.draggedItem = tool;
                    gameScreen.itemOrigin = itemOrigin;
                }
            }
        });

        tools.add(newInvItem);
    }

    @Override
    public boolean insert(Tool tool) {
        add(tool);

        return true;
    }
}
