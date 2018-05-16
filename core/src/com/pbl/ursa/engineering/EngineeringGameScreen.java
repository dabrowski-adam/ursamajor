package com.pbl.ursa.engineering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pbl.ursa.UrsaGame;
import com.pbl.ursa.technology.Tool;

import java.util.HashMap;
import java.util.Map;

public class EngineeringGameScreen implements Screen {
    final UrsaGame game;
    final SpriteBatch spriteBatch;

    OrthographicCamera camera;
    Viewport viewport;
    Map<Tool, Texture> assets;
    InputClass input;

    Level level_1;

    public EngineeringGameScreen(final UrsaGame game) {
        this.game = game;
        this.spriteBatch = game.spriteBatch;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 320, 480);
        viewport = new FillViewport(320, 480, camera);




        // Assets
        assets = new HashMap<Tool, Texture>();
        assets.put(Tool.BeltRight, new Texture(Gdx.files.internal("technology/belt.png")));

        level_1 = new Level("engine_done.png","engine_mystery.png");
        level_1.parts.add(new Part("engine_part1.png",118,254));
        level_1.parts.add(new Part("engine_part2.png",159,259));
        level_1.parts.add(new Part("engine_part3.png",127,239));

        input = new InputClass(this);
        Gdx.input.setInputProcessor(input);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.75f, 1, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        level_1.render(spriteBatch);



        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {

        viewport.update(width, height, true);

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
        for (Tool key : assets.keySet()) {
            assets.get(key).dispose();
        }
    }
}
