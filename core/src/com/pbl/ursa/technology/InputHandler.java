package com.pbl.ursa.technology;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

public class InputHandler extends InputAdapter {
    Camera camera;
    Vector3 target;
    boolean isDragging;

    public InputHandler(TechnologyGameScreen gameScreen) {
        super();

        this.camera = gameScreen.camera;

        target = new Vector3();
        isDragging = false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) { return false; }

        camera.unproject(target.set(x, y, 0));
        isDragging = true;

        return true;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        if (!isDragging) { return false; }

        camera.unproject(target.set(x, y, 0));

        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) { return false; }

        camera.unproject(target.set(x, y, 0));
        isDragging = false;

        return true;
    }
}
