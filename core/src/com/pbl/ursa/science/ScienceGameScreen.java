package com.pbl.ursa.science;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pbl.ursa.UrsaGame;

import java.util.HashMap;
import java.util.Map;

public class ScienceGameScreen implements Screen {
    final UrsaGame ursaGame;
    final SpriteBatch spriteBatch;
    final ScienceGame gameInstance;
    OrthographicCamera camera;
    Viewport viewport;
    private Map<SGAssets, Texture> minigameAssets;

    public ScienceGameScreen(final UrsaGame game) {
        //PREPARATIONS
        ursaGame = game;
        spriteBatch = game.spriteBatch;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(ScienceGame.WORLDRIGHTBOUNDARY, ScienceGame.WORLDUPBOUNDARY, camera);
        viewport.apply();
        camera.position.set(camera.viewportHeight/2f,camera.viewportWidth/2f, 0);
        camera.rotate(90);

        //LOADING GAME ASSETS
        minigameAssets = new HashMap<SGAssets, Texture>();
        minigameAssets.put(SGAssets.Bullet, new Texture(Gdx.files.internal("science/bullet.png")));
        minigameAssets.put(SGAssets.Source, new Texture(Gdx.files.internal("science/bulletSource.png")));
        minigameAssets.put(SGAssets.Target, new Texture(Gdx.files.internal("science/target.png")));
        minigameAssets.put(SGAssets.Background, new Texture(Gdx.files.internal("science/background.png")));
        gameInstance = new ScienceGame();
    }

    @Override
    public void render(float delta) {
        camera.update();
        Gdx.gl.glClearColor(1, 0.5f, 0.75f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //gameInstance.deployBullet();
        gameInstance.doMainGameLoop();
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        renderBackground();
        gameInstance.render(spriteBatch, minigameAssets);
        spriteBatch.end();
        //SOMEWHERE HERE TIME NEEDS TO BE DISPLAYED gameInstance.getTime();
        if (gameInstance.getTime() < 0 || Gdx.input.isTouched()) {
            ursaGame.setScreen(ursaGame.mainMenuScreen);
            dispose();
        }
    }

    private void renderBackground() {
        if (spriteBatch == null || !spriteBatch.isDrawing()) { return; }
        if (!minigameAssets.containsKey(SGAssets.Background)) { return; }

        spriteBatch.draw(
                minigameAssets.get(SGAssets.Background),
                ScienceGame.WORLDLEFTBOUNDARY,
                ScienceGame.WORLDDOWNBOUNDARY,
                ScienceGame.WORLDRIGHTBOUNDARY,
                ScienceGame.WORLDUPBOUNDARY);
    }

    @Override
    public void resize(int width, int height){
        viewport.update(width, height,true);
        camera.position.set(camera.viewportWidth/2f,camera.viewportHeight/2f,0);
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
        for (SGAssets key : minigameAssets.keySet()) {
            minigameAssets.get(key).dispose();
        }
    }
}
