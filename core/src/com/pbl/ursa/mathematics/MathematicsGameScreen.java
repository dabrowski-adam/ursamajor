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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pbl.ursa.UrsaGame;
import com.pbl.ursa.mathematics.levels.Level1;
import com.pbl.ursa.mathematics.levels.LevelLoader;
import com.pbl.ursa.mathematics.levels.LevelSetter;

public class MathematicsGameScreen implements Screen {

    final UrsaGame game;
    final SpriteBatch spriteBatch;
    //OrthographicCamera camera;
    //Viewport viewport;
    Digit testDigit;
    Number testNumber;
    public Level currentLevel;
    InputManager input;
    float time;
    int epoch;
    LevelLoader levelLoader;
    MenuOverlay menu;
    
    
    public MathematicsGameScreen(final UrsaGame game) {
        this.game = game;
        this.spriteBatch = game.spriteBatch;
        currentLevel = new Level();
        time = 0;
        epoch = 0;
        levelLoader = LevelLoader.getInstance();
        menu = new MenuOverlay(currentLevel,levelLoader);
        
        
        levelLoader.loadLevel(0,currentLevel);
        

        input = new InputManager(this);
        Gdx.input.setInputProcessor(input);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0.5f, 1.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        time += delta;
        currentLevel.update(delta);

        spriteBatch.setProjectionMatrix(currentLevel.getCamera().combined);
        spriteBatch.begin();
        
        currentLevel.render(spriteBatch); 
        menu.render(spriteBatch);
        spriteBatch.end();
        
        currentLevel.stage.draw();
        menu.stage.draw();
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
