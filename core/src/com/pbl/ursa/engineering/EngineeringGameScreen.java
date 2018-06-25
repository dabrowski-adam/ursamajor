package com.pbl.ursa.engineering;

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
        viewport = new StretchViewport(320, 480, camera);




        // Assets
        background = new Texture(Gdx.files.internal("engineering/paper.jpg"));
        background.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);

        Level level_1;
        level_1 = new Level("engine_done.png","engine_mystery.png","engine:","The power of a jet engine is equivalent to that \n of 28 Formula-1 racing cars");
        level_1.parts.add(new Part("engine_part1.png",118,254));
        level_1.parts.add(new Part("engine_part2.png",159,259));
        level_1.parts.add(new Part("engine_part3.png",127,239));

        Level level_2 = new Level("wing_done.png","wing_mystery.png","wing:",       "Planes have wings that feature an airfoil \n(aerofoil) shape, this is important as it\n helps overcome the effect of gravity \npulling down on the plane");
        level_2.parts.add(new Part("wing_part1.png",256,324));
        level_2.parts.add(new Part("wing_part2.png",148,293));
        level_2.parts.add(new Part("wing_part3.png",54,271));
        level_2.parts.add(new Part("wing_part4.png",12,240));
        level_2.parts.add(new Part("wing_part5.png",10,254));

        Level level_3 = new Level("steer_done.png","steer_mystery.png","stabilizer:","An aircraft stabilizer is an aerodynamic " + "\n"+
                                                                                                               "surface, typically including one or more " +"\n"+
                                                                                                               "movable control surfaces, that provides " +"\n"+
                                                                                                               "longitudinal (pitch) and/or directional (yaw) " +"\n"+
                                                                                                               "stability and control.");
        level_3.parts.add(new Part("steer_part1.png",162,283));
        level_3.parts.add(new Part("steer_part2.png",132,323));
        level_3.parts.add(new Part("steer_part3.png",60,310));
        level_3.parts.add(new Part("steer_part4.png",112,277));
        level_3.parts.add(new Part("steer_part5.png",59,240));



        levels = new ArrayList<Level>();
        levels.add(level_1);
        levels.add(level_2);
        levels.add(level_3);

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
            if(levels.size()!=current_level)input.updateLevel(this);
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
