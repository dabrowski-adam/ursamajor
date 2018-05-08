package com.pbl.ursa;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UrsaGame extends Game {
    SpriteBatch spriteBatch;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        this.setScreen(new MainMenuScreen(this, spriteBatch));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }
}
