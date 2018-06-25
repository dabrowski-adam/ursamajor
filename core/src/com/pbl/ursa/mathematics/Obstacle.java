/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbl.ursa.mathematics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author marcin7Cd
 */
public class Obstacle  {
    
    Body realBody;
    Sprite sprite;
    Texture myTexture;
    
    Obstacle(float x,float y, float w, float h, World world){
        BodyFactory bodyFactory = BodyFactory.getInstance(world);
        realBody = bodyFactory.makeBoxPolyBody(x/Level.PPM, y/Level.PPM, w/Level.PPM, h/Level.PPM, 0, BodyDef.BodyType.StaticBody);   
        myTexture = new Texture(Gdx.files.internal("mathematics/obstacle")+".bmp");
        sprite = new Sprite(myTexture);
        sprite.setBounds(x, y, w, h);
        sprite.setPosition(x, y);
    }
    
    void render(SpriteBatch batch){
        //sprite.setPosition(realBody.getWorldCenter().x*Level.PPM - sprite.getWidth()/2, realBody.getWorldCenter().y*Level.PPM - sprite.getHeight()/2);
        sprite.draw(batch);
    }
    
    void update(float dt){
        
    }
    
    void dispose(){
        realBody.getWorld().destroyBody(realBody);
        myTexture.dispose();
    }
    
}
