package com.pbl.ursa.technology;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.LinkedHashMap;
import java.util.Map;

public class InputHandler extends InputAdapter {
    Camera camera;
    Vector3 target;
    boolean isDisabled;
    boolean isDragging;
    Map<Rectangle, Callable> touchDownBinder;
    Map<Rectangle, Callable> touchDraggedBinder;
    Map<Rectangle, Callable> touchUpBinder;



    public InputHandler(TechnologyGameScreen gameScreen) {
        super();

        this.camera = gameScreen.camera;

        target = new Vector3();
        isDisabled = false;
        isDragging = false;
        touchDownBinder = new LinkedHashMap<Rectangle, Callable>();
        touchDraggedBinder = new LinkedHashMap<Rectangle, Callable>();
        touchUpBinder = new LinkedHashMap<Rectangle, Callable>();
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

    public void unbind(Rectangle bounds) {
        touchDownBinder.remove(bounds);
        touchUpBinder.remove(bounds);
        touchUpBinder.remove(bounds);
    }

    private void handleBinder(Map<Rectangle, Callable> binder, Vector3 coords) {
        if (isDisabled) { return; }

        for (Rectangle bounds : binder.keySet()) {
            if (bounds.contains(coords.x, coords.y)) {
                binder.get(bounds).call(coords.x, coords.y);
                return;
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
