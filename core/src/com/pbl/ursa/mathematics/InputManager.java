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
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 *
 * @author marcin7Cd
 */
public class InputManager implements InputProcessor {

    Camera camera;
    private Level currentLevel;
    private Stage overlayStage;
    private Dragable currentDragable;

    InputManager(MathematicsGameScreen screen) {
        camera = screen.currentLevel.camera;
        currentLevel = screen.currentLevel;
        overlayStage = screen.menu.stage;
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
        if (!overlayStage.touchDown(screenX, screenY, pointer, button)) {
            Vector3 position = camera.unproject(new Vector3(screenX, screenY, 0));
            currentDragable = currentLevel.selectNumberAt(position.x, position.y);
            if (currentDragable == null) {
                currentDragable = currentLevel.camDrag;
            }
            if (currentDragable.doesWantScreenCoordinates()) {
                if (currentDragable != null && !currentDragable.grab(new Vector2(position.x, position.y))) {
                    currentDragable = null;
                }
            } else {
                if (currentDragable != null && !currentDragable.grab(new Vector2(screenX, screenY))) {
                    currentDragable = null;
                }
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        overlayStage.touchUp(screenX, screenY, pointer, button);
        if (screenY > MenuOverlay.Width) {
            if (currentDragable != null) {
                currentDragable.drop();
                currentDragable = null;
            }
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (screenY > MenuOverlay.Width) {
            if (currentDragable != null) {
                if (currentDragable.doesWantScreenCoordinates()) {
                    Vector3 position = camera.unproject(new Vector3(screenX, screenY, 0));
                    currentDragable.dragTo(new Vector2(position.x, position.y));
                } else {
                    currentDragable.dragTo(new Vector2(screenX, screenY));
                }
            }
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
