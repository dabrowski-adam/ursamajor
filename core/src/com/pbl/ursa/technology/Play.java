package com.pbl.ursa.technology;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Map;

public class Play {
    Rectangle bounds;

    public Play(float x, float y, final TechnologyGameScreen gameScreen) {
        bounds = new Rectangle(x, y, 35, 35);

        gameScreen.input.bindDown(bounds, new Callable() {
            @Override
            public void call(float x, float y) {
                if (gameScreen.board.isSimulating) {
                    gameScreen.board.stopSimulation();
                } else {
                    gameScreen.board.startSimulation();
                }
            }
        });
    }

    public void render(SpriteBatch spriteBatch, Map<Tool, Texture> resources) {
        if (spriteBatch == null || !spriteBatch.isDrawing()) {
            return;
        }

        spriteBatch.draw(
                resources.get(Tool.BeltRightLocked),
                bounds.x,
                bounds.y,
                bounds.width,
                bounds.height);
    }
}
