package com.pbl.ursa.technology;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pbl.ursa.UrsaGame;

import java.util.HashMap;
import java.util.Map;

public class TechnologyGameScreen implements Screen {
    final UrsaGame game;
    final SpriteBatch spriteBatch;
    OrthographicCamera camera;
    Viewport viewport;
    Map<Tool, Texture> assets;
    InputHandler input;

    Board board;
    Inventory inventory;

    Tool draggedItem;
    ToolHolder itemOrigin;

    public TechnologyGameScreen(final UrsaGame game) {
        // Dependencies
        this.game = game;
        this.spriteBatch = game.spriteBatch;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 320, 480);
        viewport = new FillViewport(320, 480, camera);

        input = new InputHandler(this);
        Gdx.input.setInputProcessor(input);

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
        board = new Board(35, 35, this);
        board.cells[0][4].content = Tool.BeltRightLocked;
        board.cells[0][4].isDisabled = true;
        board.cells[3][4].content = Tool.Bin;
        board.cells[3][4].isDisabled = true;
        board.cells[2][2].content = Tool.BeltRight;

        inventory = new Inventory(0, 320, this);
        inventory.insert(Tool.BeltRight);
        inventory.insert(Tool.BeltRight);

        // Has to be the last input event
        input.bindUp(new Rectangle(0, 0, 320, 480), new Callable() {
            @Override
            public void call(float x, float y) {
                itemOrigin.insert(draggedItem);
                draggedItem = null;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        board.render(spriteBatch, assets);
        inventory.render(spriteBatch, assets, game.font);
        if (draggedItem != null) {
            spriteBatch.draw(assets.get(draggedItem),
                    input.target.x - 25,
                    input.target.y - 25,
                    50, 50);
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
        for (Tool key : assets.keySet()) {
            assets.get(key).dispose();
        }
    }
}
