/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbl.ursa.mathematics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

import sun.rmi.runtime.Log;

/**
 *
 * @author marcin7Cd
 */
public class Number extends AbstractGameObject implements Dragable {

    List<Digit> digits;
    Vector2 jerk;
    static Vector2 initalAcceleration= new Vector2(0.0f,-300.0f);
    
    Number(float PositionX, float PositionY, int value) {
        position.set(PositionX, PositionY);
        acceleration.set(0.0f, -300.0f);
        jerk = new Vector2(0.0f,-200.0f);
        terminalVelocity.set(0.0f, 600.0f);
        dimension.set(0.0f, 0.0f);
        dragable = true;
        isGrabbed = false;
        digits = new ArrayList();

        Digit currentDigit;
        if (value > 0) {
            while (value > 0) {
                currentDigit = new Digit(position.x, position.y, value);
                digits.add(currentDigit);
                position.x -= currentDigit.getWidth();
                dimension.x += currentDigit.getWidth();
                dimension.y = Math.max(currentDigit.getHeight(), dimension.y);
                value /= 10;
            }

        }
    }

    @Override
    public void update(float dt) {
        if (position.y > 0.0f) {
            if (!isGrabbed) {
                acceleration.x = acceleration.x + jerk.x*dt;
                acceleration.y = acceleration.y + jerk.y*dt;
                updateMotionY(dt);
                moveBy(velocity.x * dt, velocity.y * dt);
            }
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        for (Digit currentDigit : digits) {
            currentDigit.render(spriteBatch);
        }
    }

    boolean dragable;
    boolean isGrabbed;

    @Override
    public boolean grab() {
        isGrabbed = true;
        velocity.set(0.0f,0.0f);
        acceleration.set(Number.initalAcceleration.x,Number.initalAcceleration.y);
        return dragable;
    }

    @Override
    public void dragBy(float dx, float dy) {
        if (dragable) {
            moveBy(dx, dy);
        }
    }

    @Override
    public void drop() {
        isGrabbed = false;
    }

    void setPosition(float PositionX, float PositionY) {
        float dx = PositionX - position.x;
        float dy = PositionY - position.y;
        for (Digit currentDigit : digits) {
            if (currentDigit != null) {
                currentDigit.moveBy(dx, dy);
            }
        }
        position.x = PositionX;
        position.y = PositionY;
    }

    void moveBy(float dx, float dy) {
        for (Digit currentDigit : digits) {
            if (currentDigit != null) {
                currentDigit.moveBy(dx, dy);
            }
        }
        position.x += dx;
        position.y += dy;
    }

}
