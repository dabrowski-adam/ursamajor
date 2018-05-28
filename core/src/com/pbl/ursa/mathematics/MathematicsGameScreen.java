/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbl.ursa.mathematics;

/**
 *
 * @author marcin7Cd
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pbl.ursa.UrsaGame;

public class MathematicsGameScreen implements Screen {
    final UrsaGame game;
    final SpriteBatch spriteBatch;
    OrthographicCamera camera;
    Viewport viewport;
    Digit testDigit;
    Number testNumber;
    public Level currentLevel;
    InputManager input;
    
    
    public MathematicsGameScreen(final UrsaGame game) {
        this.game = game;
        this.spriteBatch = game.spriteBatch;
        currentLevel = new Level();
        currentLevel.addNumberAt(100,200,345);
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 320, 480);
        viewport = new StretchViewport(320, 480, camera);
        
        input = new InputManager(this);
        Gdx.input.setInputProcessor(input);
        
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0.5f, 1.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        currentLevel.update(delta);
        
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        currentLevel.render(spriteBatch);
        /*if (Gdx.input.isTouched()) {
            game.setScreen(game.mainMenuScreen);
            dispose();
        }*/
        spriteBatch.end();
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
