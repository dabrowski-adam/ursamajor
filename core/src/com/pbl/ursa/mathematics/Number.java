/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbl.ursa.mathematics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import java.util.ArrayList;
import java.util.List;

import sun.rmi.runtime.Log;

/**
 *
 * @author marcin7Cd
 */
public class Number implements Dragable  {

    List<Digit> digits;
    Vector2 destination; //used to follow cursor
    Vector2 relativeGrabPosition;
    Vector2 dimension;

    static final float DIGIT_X = 30.0f;
    static final float DIGIT_Y = 60.0f;

    boolean isOnGround;
    int numbersOnTop;

    boolean isOperatedOn;

    Body realBody;
    int value;
    int collisionCategory;

    Number(float PositionX, float PositionY, int value,int collisionCategory, World world) {
        this.collisionCategory = collisionCategory;
        
        Vector2 relativePosition = new Vector2(0.0f, 0.0f);
        dimension = new Vector2(0.0f, 0.0f);
        destination = new Vector2();
        relativeGrabPosition = new Vector2();
        dragable = true;
        isGrabbed = false;
        isOperatedOn = false;
        numbersOnTop = 0;
        this.value = value;
        digits = new ArrayList();

        Digit currentDigit;
        if (value > 0) {
            while (value > 0) {
                relativePosition.x -= DIGIT_X;
                currentDigit = new Digit(relativePosition.x, relativePosition.y, value, this, DIGIT_X, DIGIT_Y);
                digits.add(currentDigit);
                dimension.x += currentDigit.getWidth();
                dimension.y = Math.max(currentDigit.getHeight(), dimension.y);
                value /= 10;
            }
            //position.x += digits.get(0).getWidth();
        }
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((PositionX + relativePosition.x + dimension.x / 2) / Level.PPM, (PositionY + relativePosition.y + dimension.y / 2) / Level.PPM);
        bodyDef.fixedRotation = true;
        // add it to the world
        realBody = world.createBody(bodyDef);

        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        //Vector2 center = new Vector2(dimension.x / 2 / Level.PPM, dimension.y / 2 / Level.PPM);
        //Vector2 center = new Vector2(0.0f,0.0f);
        shape.setAsBox(dimension.x / 2 / Level.PPM, dimension.y / 2 / Level.PPM);//, center, 0.0f);
        // set the properties of the object ( shape, weight, restitution(bouncyness)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.filter.categoryBits = (short) (0x0001 << collisionCategory);
        fixtureDef.filter.maskBits = (short)(-1);
        
        // create the physical object in our body)
        realBody.createFixture(fixtureDef);
        shape.dispose();
        realBody.setUserData(this);
    }

    void addZeroDigit() {
        Digit lastDigit = digits.get(digits.size() - 1);
        digits.add(new Digit(lastDigit.position.x - lastDigit.getWidth(), lastDigit.position.y, 0, this, DIGIT_X, DIGIT_Y));
    }

    float getLogicalX() {
        return realBody.getPosition().x * Level.PPM + dimension.x / 2;
    }

    float getLogicalY() {
        return realBody.getPosition().y * Level.PPM - dimension.y / 2;
    }

    public void update(float dt) {
        if (isGrabbed) {
            if (numbersOnTop == 0) {
                Vector2 temp = new Vector2();
                temp.set(destination);
                temp.scl(1.0f / Level.PPM);
                temp.sub(realBody.getWorldCenter());
                temp.sub(relativeGrabPosition);
                temp.scl(temp.len());
                if (temp.len() > 0.5f) {
                    realBody.setLinearVelocity(temp);
                } else {
                    realBody.setLinearVelocity(0.0f, 0.0f);
                }
            } else {
                isGrabbed = false;
            }
        }
    }

    void dispose() {
        realBody.getWorld().destroyBody(realBody);
        for (Digit currentDigit : digits) {
            currentDigit.dispose();
        }
    }

    public void render(SpriteBatch spriteBatch) {
        for (Digit currentDigit : digits) {
            currentDigit.render(spriteBatch);
        }
    }

    boolean dragable;
    boolean isGrabbed;

    @Override
    public boolean grab(Vector2 position) {
        isGrabbed = true;
        relativeGrabPosition.set(realBody.getLocalPoint(position.cpy().scl(1.0f / Level.PPM)));
        destination.set(realBody.getWorldCenter().cpy().add(relativeGrabPosition));
        return dragable && numbersOnTop == 0;
    }

    @Override
    public void dragTo(Vector2 position) {
        if (isGrabbed) {
            destination.set(position);
        }
    }

    @Override
    public void drop() {
        isGrabbed = false;
    }

    @Override
    public boolean doesWantScreenCoordinates() {
        return true;
    }    
    
    void attachToOperation() {
        isOperatedOn = true;
    }

    void deatachFromOperation() {
        isOperatedOn = false;
    }


}

