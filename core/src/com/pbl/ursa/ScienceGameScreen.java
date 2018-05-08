package com.pbl.ursa;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScienceGameScreen implements Screen {
    final SpriteBatch spriteBatch;

    public ScienceGameScreen(final SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0.5f, 0.75f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
