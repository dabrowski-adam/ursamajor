package com.pbl.ursa.technology;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pbl.ursa.UrsaGame;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TechnologyGameScreen implements Screen {
    private final UrsaGame game;
    private final SpriteBatch spriteBatch;
    private OrthographicCamera camera;
    private Texture belt;
    private Texture belt_blocked;
    private Texture bottle;
    private Texture bin;
    private Texture cell;
    private Texture bottomBackground;
    private Cell[][] board;
    private Map<Tool, Integer> inventory;

    public TechnologyGameScreen(final UrsaGame game) {
        // Dependencies
        this.game = game;
        this.spriteBatch = game.spriteBatch;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 320, 480);

        // Assets
        belt = new Texture(Gdx.files.internal("technology/belt.png"));
        belt_blocked = new Texture(Gdx.files.internal("technology/belt_blocked.png"));
        bottle = new Texture(Gdx.files.internal("technology/bottle.png"));
        bin = new Texture(Gdx.files.internal("technology/bin.png"));
        cell = new Texture(Gdx.files.internal("technology/cell.png"));

        Pixmap pixmap = new Pixmap(320, 180, Pixmap.Format.RGB888);
        pixmap.setColor(0.2f, 0.4f, 0.8f, 1);
        pixmap.fillRectangle(0, 0, 320, 180);
        bottomBackground = new Texture(pixmap);
        pixmap.dispose();

        // State
        board = new Cell[5][5];
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                board[y][x] = new Cell();
            }
        }
        board[4][0].content = Tool.BeltRightLocked;
        board[4][3].content = Tool.Bin;

        inventory = new HashMap<Tool, Integer>();
        inventory.put(Tool.BeltRight, 2);

    }

    private void renderGrid(SpriteBatch spriteBatch) {
        if (!spriteBatch.isDrawing()) { return; }

        Tool content;
        Texture sprite;
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                content = board[y][x].content;
                if (content == Tool.Bin) {
                    sprite = bin;
                } else if (content == Tool.BeltRightLocked) {
                    sprite = belt_blocked;
                } else {
                    sprite = cell;
                }
                spriteBatch.draw(sprite, 35 + x * 50, 35 + y * 50, 50, 50);
            }
        }
    }

    private void renderInventory(SpriteBatch spriteBatch) {
        if (!spriteBatch.isDrawing()) { return; }

        spriteBatch.draw(bottomBackground, 0, 320);
        for (Tool key : inventory.keySet()) {
            if (key == Tool.BeltRight) {
                spriteBatch.draw(belt, 35, 320 + 35, 50, 50);
                game.font.draw(spriteBatch,
                        inventory.get(key).toString(),
                        35 + 40, 320 + 35 + 40);
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        renderGrid(spriteBatch);
        renderInventory(spriteBatch);
        spriteBatch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(game.mainMenuScreen);
            dispose();
        }
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
        belt.dispose();
        belt_blocked.dispose();
        bottle.dispose();
        bin.dispose();
        cell.dispose();
    }
}
