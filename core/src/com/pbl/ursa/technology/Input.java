package com.pbl.ursa.technology;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;

public class Input extends InputAdapter {
    private Camera camera;


    public Input(Camera camera) {
        super();
        this.camera = camera;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        return false;
    }
}
