package com.pbl.ursa;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.pbl.ursa.engineering.EngineeringGameScreen;
import com.pbl.ursa.technology.TechnologyGameScreen;
import com.pbl.ursa.mathematics.MathematicsGameScreen;


public class MainMenuScreen implements Screen {
    final UrsaGame game;
    final SpriteBatch spriteBatch;
    Skin skin;
    Stage stage;

    public MainMenuScreen(final UrsaGame game) {
        this.game = game;
        this.spriteBatch = game.spriteBatch;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        skin.add("default", new BitmapFont());

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.add(new Image(skin.newDrawable("white", Color.ROYAL))).size(96);
        table.row();

        final TextButton buttonS = new TextButton("Science", skin);
        buttonS.pad(4);
        table.add(buttonS).padTop(16);
        table.row();

        final TextButton buttonT = new TextButton("Technology", skin);
        buttonT.pad(4);
        table.add(buttonT).padTop(16);
        table.row();

        final TextButton buttonE = new TextButton("Engineering", skin);
        buttonE.pad(4);
        table.add(buttonE).padTop(16);
        table.row();

        final TextButton buttonM = new TextButton("Mathematics", skin);
        buttonM.pad(4);
        table.add(buttonM).padTop(16);
        table.row();

        buttonS.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buttonS.setText("Science!");
                game.setScreen(new ScienceGameScreen(game));
            }
        });

        buttonT.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buttonT.setText("Technology!");
                game.setScreen(new TechnologyGameScreen(game));
            }
        });

        buttonE.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buttonE.setText("Engineering!");
                game.setScreen(new EngineeringGameScreen(game));
            }
        });

        buttonM.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buttonM.setText("Mathematics!");
                game.setScreen(new MathematicsGameScreen(game));
            }
        });

        //table.setDebug(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.7f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
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
        stage.dispose();
        skin.dispose();
    }

}
