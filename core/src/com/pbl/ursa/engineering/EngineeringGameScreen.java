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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EngineeringGameScreen implements Screen {
    final UrsaGame game;
    final SpriteBatch spriteBatch;

    OrthographicCamera camera;
    Viewport viewport;
    InputClass input;
    Texture background;

    int current_level=0;

    ArrayList<Level> levels;

    public EngineeringGameScreen(final UrsaGame game) {
        this.game = game;
        this.spriteBatch = game.spriteBatch;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 320, 480);
        viewport = new FillViewport(320, 480, camera);




        // Assets
        background = new Texture(Gdx.files.internal("engineering/paper.jpg"));
        background.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);

        Level level_1;
        level_1 = new Level("engine_done.png","engine_mystery.png","engine");
        level_1.parts.add(new Part("engine_part1.png",118,254));
        level_1.parts.add(new Part("engine_part2.png",159,259));
        level_1.parts.add(new Part("engine_part3.png",127,239));

        Level level_2 = new Level("wing_done.png","wing_mystery.png","wing");
        level_2.parts.add(new Part("wing_part1.png",-100,-100));
        level_2.parts.add(new Part("wing_part2.png",-100,-100));
        level_2.parts.add(new Part("wing_part3.png",-100,-100));
        level_2.parts.add(new Part("wing_part4.png",-100,-100));
        level_2.parts.add(new Part("wing_part5.png",-100,-100));



        levels = new ArrayList<Level>();
        levels.add(level_1);
        levels.add(level_2);

        input = new InputClass(this);
        Gdx.input.setInputProcessor(input);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background,0,0,320,480);

        if(levels.size()==current_level){
            game.setScreen(game.mainMenuScreen);
            dispose();
            }
        else if(levels.get(current_level).render(spriteBatch)) {
            current_level++;
            input.updateLevel(this);
        }
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
        current_level=0;
    }
}
