/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbl.ursa.mathematics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author marcin7Cd
 */
public class InputManager implements InputProcessor {

    Camera camera;
    private Level currentLevel;
    private Vector2 previousPosition;
    private Number currentNumber;

    InputManager(MathematicsGameScreen screen) {
        camera = screen.currentLevel.camera;
        currentLevel = screen.currentLevel;
        previousPosition = new Vector2();
    }

    void updateLevel(MathematicsGameScreen screen) {
        if (currentLevel == null) {
            return;
        }
        currentLevel = screen.currentLevel;
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
        Vector3 position = camera.unproject(new Vector3(screenX, screenY, 0));
        currentNumber = currentLevel.selectNumberAt(position.x, position.y);
        //previousPosition.x = position.x;
        //previousPosition.y = position.y;
        if (currentNumber != null && !currentNumber.grab(new Vector2(position.x,position.y))) {
            currentNumber = null;
        }
         
        if (currentNumber != null) {
         
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (currentNumber != null) {
            currentNumber.drop();
            currentNumber = null;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        Vector3 position = camera.unproject(new Vector3(screenX, screenY, 0));
        //Vector2 pos = new Vector2(position.x,position.y);
        if (currentNumber != null) {
            currentNumber.dragTo(new Vector2(position.x, position.y));
            //currentNumber.realBody.applyForceToCenter(pos.sub(currentNumber.realBody.getWorldCenter()).scl(2.0f), true);
            //Gdx.app.log("xxx",Float.toString(pos.sub(currentNumber.realBody.getWorldCenter()).scl(2.0f).x));
            //Gdx.app.log("yyy",Float.toString(pos.sub(currentNumber.realBody.getWorldCenter()).scl(2.0f).y));
            
        }
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
