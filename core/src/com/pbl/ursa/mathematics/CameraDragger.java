/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbl.ursa.mathematics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author marcin7Cd
 */
public class CameraDragger implements Dragable {

    Camera camera;
    Level currentLevel;
    Vector2 grabPosition; // in terms of scrren position
    Vector2 prevPosition; // in terms of scrren position
    float minXcam;
    float maxXcam;
    float minYcam;
    float maxYcam;

    CameraDragger(Camera camera, Level currentLevel) {
        this.camera = camera;
        this.currentLevel = currentLevel;
        grabPosition = new Vector2();//3();
        prevPosition = new Vector2();//3();
        minXcam = camera.viewportWidth / 2;
        maxXcam = currentLevel.levelWidth - minXcam;
        minYcam = camera.viewportHeight / 2 - MenuOverlay.Width;
        maxYcam = currentLevel.levelHeight - minYcam - MenuOverlay.Width;
    }
    
    void update(){
        minXcam = camera.viewportWidth / 2;
        maxXcam = currentLevel.levelWidth - minXcam;
        minYcam = camera.viewportHeight / 2 - MenuOverlay.Width;
        maxYcam = currentLevel.levelHeight - minYcam - MenuOverlay.Width;
    }

    @Override
    public void drop() {

    }

    @Override
    public boolean grab(Vector2 position) {
        grabPosition = position;
        //grabPosition = camera.unproject(new Vector3(position.x, position.y, 0.0f));
        prevPosition = grabPosition;
        return true;
    }

    @Override
    public void dragTo(Vector2 position) {

        //Vector3 position3 = camera.unproject(new Vector3(position.x, position.y, 0.0f));
        //Gdx.app.log("camera(x,y):", "(" + Float.toString(camera.position.x) + "," + Float.toString(camera.position.y) + ")");
        Vector2 temp = prevPosition.cpy().sub(position);
        temp.x = MathUtils.clamp(camera.position.x+temp.x, minXcam, maxXcam)-camera.position.x;
        temp.y = MathUtils.clamp(camera.position.y+temp.y, minYcam, maxYcam)-camera.position.y;
        
        camera.translate(temp.x, temp.y, 0);
        prevPosition = position;
        prevPosition.add(temp);
    }

    @Override
    public boolean doesWantScreenCoordinates() {
        return true;
    }

}
