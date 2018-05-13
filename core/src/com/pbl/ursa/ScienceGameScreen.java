package com.pbl.ursa;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kotcrab.vis.runtime.scene.Scene;
import com.kotcrab.vis.runtime.scene.VisAssetManager;

public class ScienceGameScreen implements Screen {
    final UrsaGame game;
    //final SpriteBatch spriteBatch;
    final VisAssetManager manager;
    Scene scene;

    public ScienceGameScreen(final UrsaGame game) {
        this.game = game;
        //this.spriteBatch = game.spriteBatch;
        this.manager = game.manager;
        scene = manager.loadSceneNow("scene/science.scene");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0.5f, 0.75f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        scene.render();

        if (Gdx.input.isTouched()) {
            game.setScreen(game.mainMenuScreen);
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        scene.resize(width, height);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
