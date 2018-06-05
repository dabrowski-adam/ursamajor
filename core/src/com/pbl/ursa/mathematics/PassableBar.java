/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbl.ursa.mathematics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class PassableBar extends AbstractGameObject {

    Texture myTexture;
    Body realBody;
    String condition;
    
    
    PassableBar(World world) {
        myTexture = new Texture(Gdx.files.internal("bar") + ".bmp");
        myTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }
    
    void setConditionString(String condition){
        this.condition = condition;
    }
    
    abstract boolean checkIfCanBePassed(Number number);

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.draw(myTexture, position.x, position.y, 0.0f, 0.0f, dimension.x, dimension.y, 1.0f, 1.0f, 0.0f,
                0, 0, myTexture.getWidth(), myTexture.getHeight(), false, false);
    }

}
