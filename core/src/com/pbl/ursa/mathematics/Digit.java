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
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import sun.rmi.runtime.Log;

public class Digit extends AbstractGameObject {

    //static Texture allDigitTextures[] = new Texture[10];
    Sprite sprite;
    int value;
    Number origin;

    private Texture myTexture;

    Digit(float initialX, float initialY, int value, Number origin, float dimensionsX, float dimensionsY) {
        position.set(initialX, initialY);
        dimension.set(dimensionsX, dimensionsY);
        myTexture = new Texture(Gdx.files.internal("mathematics/digit" + Integer.toString(value % 10)) + ".png");
        myTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.origin = origin;
        this.value = (value % 10);
        sprite = new Sprite(myTexture);
        sprite.setSize(dimensionsX, dimensionsY);
    }

    void changeDigitTo(int Value) {
        if (value != (Value % 10) ) {
            myTexture.dispose();
            myTexture = new Texture(Gdx.files.internal("mathematics/digit" + Integer.toString(Value % 10)) + ".png");
            myTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            this.value = Value % 10;
            sprite.setTexture(myTexture);
        }
    }

    void remove() {
        origin = null;
        myTexture.dispose();
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

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        sprite.setPosition(origin.getLogicalX() + position.x,
                origin.getLogicalY() + position.y);
        sprite.draw(spriteBatch);
    }

}
