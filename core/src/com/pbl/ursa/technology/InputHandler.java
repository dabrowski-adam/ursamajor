package com.pbl.ursa.technology;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.HashMap;
import java.util.Map;

public class InputHandler extends InputAdapter {
    Camera camera;
    Vector3 target;
    boolean isDragging;
    Map<Rectangle, Callable> touchDownBinder;
    Map<Rectangle, Callable> touchDraggedBinder;
    Map<Rectangle, Callable> touchUpBinder;



    public InputHandler(TechnologyGameScreen gameScreen) {
        super();

        this.camera = gameScreen.camera;

        target = new Vector3();
        isDragging = false;
        touchDownBinder = new HashMap<Rectangle, Callable>();
        touchDraggedBinder = new HashMap<Rectangle, Callable>();
        touchUpBinder = new HashMap<Rectangle, Callable>();
    }

    public void bindDown(Rectangle bounds, Callable function) {
        touchDownBinder.put(bounds, function);
    }

    public void bindDragged(Rectangle bounds, Callable function) {
        touchDraggedBinder.put(bounds, function);
    }

    public void bindUp(Rectangle bounds, Callable function) {
        touchUpBinder.put(bounds, function);
    }

    private void handleBinder(Map<Rectangle, Callable> binder, Vector3 coords) {
        for (Rectangle bounds : binder.keySet()) {
            if (bounds.contains(coords.x, coords.y)) {
                binder.get(bounds).call(coords.x, coords.y);
            }
        }
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) { return false; }

        camera.unproject(target.set(x, y, 0));
        isDragging = true;

        handleBinder(touchDownBinder, target);

        return true;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        if (!isDragging) { return false; }

        camera.unproject(target.set(x, y, 0));

        handleBinder(touchDraggedBinder, target);

        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) { return false; }

        camera.unproject(target.set(x, y, 0));
        isDragging = false;

        handleBinder(touchUpBinder, target);

        return true;
    }
}
