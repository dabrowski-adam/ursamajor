package com.pbl.ursa.facts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pbl.ursa.UrsaGame;
import com.pbl.ursa.engineering.InputClass;
import com.pbl.ursa.engineering.Level;
import com.pbl.ursa.engineering.Part;

import java.util.ArrayList;

/**
 * Created by Mateusz Bujnowicz on 6/12/2018.
 */

public class FunFacts implements Screen {

    final UrsaGame game;
    final SpriteBatch spriteBatch;

    OrthographicCamera camera;
    Viewport viewport;
    Texture background;

    int currentFact=0;


    ArrayList<Fact> facts;

    public FunFacts(final UrsaGame game) {
        this.game = game;
        this.spriteBatch = game.spriteBatch;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 320, 480);
        viewport = new StretchViewport(320, 480, camera);

        facts = new ArrayList<Fact>();

        // Assets
        background = new Texture(Gdx.files.internal("facts/background.jpg"));
        background.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);

        facts.add(new Fact("1.jpg","There are more trees on Earth than stars in our " + "\n" +
                                               "galaxy. NASA experts believe there could be " +"\n" +
                                               "anywhere from 100 billion to 400 billion stars " +"\n" +
                                               "in the Milky Way galaxy, while the number of " +"\n" +
                                               "trees around the world is much higher: 3.04 " +"\n" +
                                                "trillion"));
        facts.add(new Fact("2.jpg","As a gas, oxygen is odorless and colorless. In " +"\n" +
                                               "its liquid and solid forms, however, it looks " +"\n" +
                "pale blue."));
        facts.add(new Fact("3.jpg", "When you burp on Earth, gravity keeps down the " +"\n" +
                                                "solids and liquid from the food you just ate, " +"\n" +
                                                 "so only the gas escapes from your mouth. In " +"\n" +
                                                "the absence of gravity, the gas cannot separate " +"\n" +
                                                "from the liquids and solids, so burping " +"\n" +
                                                 "essentially turns into puking. "));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background,0,0,320,480);

        if(facts.size()==currentFact){
            game.setScreen(game.mainMenuScreen);
            dispose();
        }
        else {
            facts.get(currentFact).render(spriteBatch);
            if(Gdx.input.justTouched()){
                currentFact++;
            }
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
        currentFact=0;
    }

}
