package com.pbl.ursa;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UrsaGame extends Game {
    SpriteBatch spriteBatch;
    MainMenuScreen mainMenuScreen;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        mainMenuScreen = new MainMenuScreen(this);
        this.setScreen(mainMenuScreen);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        mainMenuScreen.dispose();
    }
}
