/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbl.ursa.mathematics;

/**
 *
 * @author marcin7Cd
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import sun.rmi.runtime.Log;

public class Digit extends AbstractGameObject {

    float positionX;
    float positionY;
    float scaleX;
    float scaleY;
    float width;
    float height;
    int value;

    private final Texture myTexture;

    Digit(float initialX, float initialY, int Value) {
        position.set(initialX, initialY);
        dimension.set(40.0f, 60.f);
        myTexture = new Texture(Gdx.files.internal("mathematics/digit" + Integer.toString(Value % 10)) + ".png");
        myTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    float getX() {
        return position.x;
    }

    float getY() {
        return position.y;
    }

    float getHeight() {
        return dimension.y;
    }

    float getWidth() {
        return dimension.x;
    }

    void moveBy(float dx, float dy) {
        position.x += dx;
        position.y += dy;
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.draw(myTexture, position.x, position.y, 0.0f, 0.0f, dimension.x, dimension.y, 1.0f, 1.0f, 0.0f,
                0, 0, myTexture.getWidth(), myTexture.getHeight(), false, false);
    }

}
