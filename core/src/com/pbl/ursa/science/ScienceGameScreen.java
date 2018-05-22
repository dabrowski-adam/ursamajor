package com.pbl.ursa.science;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pbl.ursa.UrsaGame;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.Actor;

/* TO DO:
display: score, "velocity", "angle"
buttons for velocity, angle, fire
bullet destroy - better destroy*/

public class ScienceGameScreen implements Screen {

    private final static int TIMERPOSITIONX = 78;
    private final static int TIMERPOSITIONY = 67;
    private final static int SCOREPOSITIONX = 40;
    private final static int SCOREPOSITIONY = 67;
    private final static int VELOCITYPOSITIONX = 50;
    private final static int VELOCITYPOSITIONY = 31;
    private final static int ANGLEPOSITIONX = 95;
    private final static int ANGLEPOSITIONY = 31;

    final UrsaGame ursaGame;
    final SpriteBatch spriteBatch;
    final ScienceGame gameInstance;
    OrthographicCamera camera;
    Viewport viewport;
    private Map<SGAssets, Texture> minigameAssets;
    BitmapFont font;

    private Stage stage;
    private Button fireButton;
    private Button velocityUpButton;
    private Button velocityDownButton;
    private Button angleUpButton;
    private Button angleDownButton;

    public ScienceGameScreen(final UrsaGame game) {
        //PREPARATIONS
        ursaGame = game;
        spriteBatch = game.spriteBatch;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, ScienceGame.WORLDRIGHTBOUNDARY, ScienceGame.WORLDUPBOUNDARY);
        camera.rotate(-90);
        viewport = new FillViewport(ScienceGame.WORLDRIGHTBOUNDARY, ScienceGame.WORLDUPBOUNDARY, camera);
        viewport.apply();
        font = new BitmapFont();

        //LOADING GAME ASSETS
        minigameAssets = new HashMap<SGAssets, Texture>();
        minigameAssets.put(SGAssets.Bullet, new Texture(Gdx.files.internal("science/bullet.png")));
        minigameAssets.put(SGAssets.Source, new Texture(Gdx.files.internal("science/bulletSource.png")));
        minigameAssets.put(SGAssets.Target, new Texture(Gdx.files.internal("science/target.png")));
        minigameAssets.put(SGAssets.Background, new Texture(Gdx.files.internal("science/background.png")));
        gameInstance = new ScienceGame();

        //BUTTON SECTION
        stage = new Stage(viewport);
        fireButton = new Button(
                        new SpriteDrawable(
                            new Sprite(
                                new Texture(Gdx.files.internal("science/fireButton.png")))));
        fireButton.setSize(10, 10);
        fireButton.setPosition(76,21);

        velocityUpButton = new Button(
                        new SpriteDrawable(
                            new Sprite(
                                new Texture(Gdx.files.internal("science/plusButton.png")))));
        velocityUpButton.setSize(8, 8);
        velocityUpButton.setPosition(50,20);

        velocityDownButton = new Button(
                        new SpriteDrawable(
                            new Sprite(
                                new Texture(Gdx.files.internal("science/minusButton.png")))));
        velocityDownButton.setSize(8, 8);
        velocityDownButton.setPosition(61,20);

        angleUpButton = new Button(
                        new SpriteDrawable(
                            new Sprite(
                                new Texture(Gdx.files.internal("science/plusButton.png")))));
        angleUpButton.setSize(8, 8);
        angleUpButton.setPosition(94,20);

        angleDownButton = new Button(
                        new SpriteDrawable(
                            new Sprite(
                                new Texture(Gdx.files.internal("science/minusButton.png")))));
        angleDownButton.setSize(8, 8);
        angleDownButton.setPosition(105,20);


        stage.addActor(fireButton);
        stage.addActor(velocityUpButton);
        stage.addActor(velocityDownButton);
        stage.addActor(angleUpButton);
        stage.addActor(angleDownButton);

        fireButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!gameInstance.bulletExists()) gameInstance.deployBullet();
                else gameInstance.destroyBullet();
            }
        });

        velocityUpButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameInstance.changeVelocity(5.0f);
            }
        });

        velocityDownButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameInstance.changeVelocity(-5.0f);
            }
        });

        angleUpButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameInstance.changeAngle(5.0f);
            }
        });

        angleDownButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameInstance.changeAngle(-5.0f);
            }
        });


        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        camera.update();
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        renderBackground();
        gameInstance.render(spriteBatch, minigameAssets);
        drawTexts();
        spriteBatch.end();
        stage.draw();
        gameInstance.doMainGameLoop();
        //gameInstance.deployBullet();
        stage.act(Gdx.graphics.getDeltaTime());
        if (gameInstance.getTime() < 0 /*|| Gdx.input.isTouched()*/) {
            ursaGame.setScreen(ursaGame.mainMenuScreen);
            dispose();
        }
    }

    private void drawTexts() {
            font.getData().setScale(0.25f, 0.25f);
            font.setUseIntegerPositions(false);
            font.draw(spriteBatch, Integer.toString(gameInstance.getTime()), TIMERPOSITIONX, TIMERPOSITIONY);
            font.draw(spriteBatch, "Score: " + Integer.toString(gameInstance.getScore()), SCOREPOSITIONX, SCOREPOSITIONY);
            font.draw(spriteBatch, "Velocity: " + Integer.toString(gameInstance.getVelocity()), VELOCITYPOSITIONX, VELOCITYPOSITIONY);
            font.draw(spriteBatch, "Angle: " + Integer.toString(gameInstance.getAngle()), ANGLEPOSITIONX, ANGLEPOSITIONY);
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
