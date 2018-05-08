package com.pbl.ursa;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TechnologyGameScreen implements Screen {
    final UrsaGame game;
    final SpriteBatch spriteBatch;

    public TechnologyGameScreen(final UrsaGame game) {
        this.game = game;
        this.spriteBatch = game.spriteBatch;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.75f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isTouched()) {
            game.setScreen(game.mainMenuScreen);
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

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
