package com.pbl.ursa;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UrsaGame extends Game {
    public SpriteBatch spriteBatch;
    public BitmapFont font;
    public MainMenuScreen mainMenuScreen;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        font = new BitmapFont(true);
        font.setColor(0.1f, 0.1f, 0.1f, 1);
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
        font.dispose();
        mainMenuScreen.dispose();
    }
}
