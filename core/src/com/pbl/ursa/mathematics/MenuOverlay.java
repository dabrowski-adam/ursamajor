/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbl.ursa.mathematics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pbl.ursa.mathematics.levels.LevelLoader;

/**
 *
 * @author marcin7Cd
 */
public class MenuOverlay {

    final static float Width = 70.0f;

    Texture backButtonTexture;
    Texture restartButtonTexture;
    Texture menuBar;
    ImageButton backButton;
    ImageButton restartButton;
    Image menuBarImg;
    Level currentLevel;
    int currentLevelNumber;
    LevelLoader levelLoader;
    Stage stage;
    OrthographicCamera camera;
    Viewport viewport;

    MenuOverlay(Level currentLevel, LevelLoader levelLoader) {
        stage = new Stage();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 320, 480);
        viewport = new StretchViewport(320, 480, camera);
        stage = new Stage(viewport);

        this.levelLoader = levelLoader;
        this.currentLevel = currentLevel;
        currentLevelNumber = 0;
        restartButtonTexture = new Texture(Gdx.files.internal("mathematics/buttonMenu") + ".bmp");
        backButtonTexture = new Texture(Gdx.files.internal("mathematics/buttonRestart") + ".bmp");
        menuBar = new Texture(Gdx.files.internal("mathematics/menu_bar") + ".bmp");
        menuBarImg = new Image(menuBar);
        restartButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(backButtonTexture)));

        restartButton.setSize(50.0f, 50.0f);
        restartButton.setPosition(10.0f, 10.0f);
        //restartButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(restartButtonTexture)));
        restartButton.addListener(new RestartEventListener(this));
        menuBarImg.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                return;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        stage.addActor(menuBarImg);
        stage.addActor(restartButton);

        //currentLevel.stage.addActor(restartButton);
    }

    void ReeloadLevel() {
        currentLevel.flushLevel();
        levelLoader.loadLevel(currentLevelNumber, currentLevel);
    }

    void dispose() {
        restartButtonTexture.dispose();
        backButtonTexture.dispose();
        menuBar.dispose();
    }

    void act(float dt) {
        stage.act();
    }

    void render(SpriteBatch batch) {

        //stage.draw();
    }

    class RestartEventListener extends InputListener {

        MenuOverlay menu;

        RestartEventListener(MenuOverlay menu) {
            this.menu = menu;
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            return;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            menu.ReeloadLevel();
            return true;
        }

    }
}
