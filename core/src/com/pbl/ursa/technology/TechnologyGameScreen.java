package com.pbl.ursa.technology;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pbl.ursa.UrsaGame;

import java.util.HashMap;
import java.util.Map;

public class TechnologyGameScreen implements Screen {
    private final UrsaGame game;
    private final SpriteBatch spriteBatch;
    private OrthographicCamera camera;
    private Map<Tool, Texture> assets;
    private Board board;
    private Map<Tool, Integer> inventory;

    public TechnologyGameScreen(final UrsaGame game) {
        // Dependencies
        this.game = game;
        this.spriteBatch = game.spriteBatch;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 320, 480);

        // Assets
        assets = new HashMap<Tool, Texture>();
        assets.put(Tool.BeltRight, new Texture(Gdx.files.internal("technology/belt.png")));
        assets.put(Tool.BeltRightLocked, new Texture(Gdx.files.internal("technology/belt_blocked.png")));
        assets.put(Tool.Bottle, new Texture(Gdx.files.internal("technology/bottle.png")));
        assets.put(Tool.Bin, new Texture(Gdx.files.internal("technology/bin.png")));
        assets.put(Tool.Empty, new Texture(Gdx.files.internal("technology/cell.png")));

        Pixmap pixmap = new Pixmap(320, 180, Pixmap.Format.RGB888);
        pixmap.setColor(0.2f, 0.4f, 0.8f, 1);
        pixmap.fillRectangle(0, 0, 320, 180);
        assets.put(Tool.BottomBackground, new Texture(pixmap));
        pixmap.dispose();

        // State
        board = new Board(35, 35);
        board.cells[0][4].content = Tool.BeltRightLocked;
        board.cells[3][4].content = Tool.Bin;

        inventory = new HashMap<Tool, Integer>();
        inventory.put(Tool.BeltRight, 2);

    }

    private void renderInventory(SpriteBatch spriteBatch) {
        if (spriteBatch == null || !spriteBatch.isDrawing()) { return; }

        spriteBatch.draw(assets.get(Tool.BottomBackground), 0, 320);
        for (Tool key : inventory.keySet()) {
            spriteBatch.draw(assets.get(key), 35, 320 + 35, 50, 50);
            game.font.draw(spriteBatch,
                    inventory.get(key).toString(),
                    35 + 40, 320 + 35 + 40);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        board.render(spriteBatch, assets);
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
        for (Tool key : assets.keySet()) {
            assets.get(key).dispose();
        }
    }
}
