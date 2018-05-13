package com.pbl.ursa;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kotcrab.vis.runtime.scene.VisAssetManager;

public class UrsaGame extends Game {
    public SpriteBatch spriteBatch;
    public VisAssetManager manager;
    public BitmapFont font;
    public MainMenuScreen mainMenuScreen;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        manager = new VisAssetManager(spriteBatch);
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
        manager.dispose();
        font.dispose();
        mainMenuScreen.dispose();
    }
}
