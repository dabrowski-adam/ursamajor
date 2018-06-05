/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbl.ursa.mathematics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 *
 * @author marcin7Cd
 */
public class TemporaryDigit extends Actor {

    Vector2 initalPosition;
    Sprite sprite;

    public TemporaryDigit(float initialX, float initialY, int Value, Vector2 dimensions, Digit destination,float delay) {
        sprite = new Sprite(new Texture(Gdx.files.internal("mathematics/digit" + Integer.toString(Value % 10)) + ".png"));
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        this.setPosition(initialX, initialY);
        sprite.setSize(dimensions.x, dimensions.y);
        goToDigit(destination, delay);
    }

    void goToDigit(Digit destination, float delay) {
        this.addAction(Actions.sequence(
                //Actions.delay(delay),
                Actions.moveBy((-Number.DIGIT_X) / 2, 50, delay/2, Interpolation.circle),
                Actions.moveBy((-Number.DIGIT_X) / 2, -20, delay/2, Interpolation.circle),
                Actions.removeActor()));
    }

    @Override
    protected void positionChanged() {
        sprite.setPosition(getX(), getY());
        super.positionChanged();
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch,parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
