package com.pbl.ursa.engineering;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Mateusz Bujnowicz on 5/15/2018.
 */

public class InputClass implements InputProcessor {
    Camera camera;
    private Level current_level;
    private float previous_x;
    private float previous_y;

    InputClass (EngineeringGameScreen screen){
        camera = screen.camera;
        current_level = screen.levels.get(screen.current_level);
    }

    void updateLevel(EngineeringGameScreen screen){
        current_level = screen.levels.get(screen.current_level);
    }

    /// REMMEMBER ABOUT CAMERA UNPROJECT FROM
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        current_level.endLevel();
        Vector3  position = camera.unproject(new Vector3(screenX,screenY,0));
        previous_x = position.x;
        previous_y = position.y;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector3  position = camera.unproject(new Vector3(screenX,screenY,0));
        float difference_x;
        float difference_y;
        difference_x = position.x - previous_x;
        difference_y = position.y - previous_y;
        previous_x = position.x;
        previous_y = position.y;
        current_level.move(position.x,position.y,difference_x,difference_y);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
