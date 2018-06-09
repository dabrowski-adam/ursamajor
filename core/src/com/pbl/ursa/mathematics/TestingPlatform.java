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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marcin7Cd
 */
public abstract class TestingPlatform {

    static final float width = 20.0f;
    Level currentLevel;
    Body realBody;
    Sprite sprite;
    Texture myTexture;
    String condition="";
    TextureRegion correctRegion;
    TextureRegion wrongRegion;
    TextureRegion neutralRegion;
    boolean ifCorrect;
    Map<Number, Integer> numbers;

    public TestingPlatform(float x, float y, float lenght, String condition, Level currentLevel) {
        //    numbersOnTop = new ArrayList();
        this.currentLevel = currentLevel;
        this.condition = condition;
        numbers = new HashMap();
        ifCorrect = false;
        BodyFactory bodyFactory = BodyFactory.getInstance(currentLevel.world);
        realBody = bodyFactory.makeBoxPolyBody(x / Level.PPM, y / Level.PPM, lenght / Level.PPM, width / Level.PPM, 0, BodyDef.BodyType.StaticBody);

        myTexture = new Texture(Gdx.files.internal("mathematics/testing_bar") + ".bmp");
        correctRegion = new TextureRegion(myTexture, 0, 0, 320, 20);
        wrongRegion = new TextureRegion(myTexture, 0, 20, 320, 20);
        neutralRegion = new TextureRegion(myTexture, 0, 20 * 2, 320, 20);
        sprite = new Sprite(neutralRegion);

        realBody.setUserData(this);
        sprite.setPosition(x, y);
        sprite.setSize(lenght, width);
    }

    void putOn(Number number) {
        Gdx.app.log("putOn:", "OK");
        numbers.put(number, new Integer(number.value));
        checkIfCorrect();
    }

    void pickUp(Number number) {
        Gdx.app.log("pickUp:", "OK");
        numbers.remove(number);
        if (numbers.isEmpty()) {
            sprite.setRegion(neutralRegion);
        } else {
            checkIfCorrect();
        }
    }

    boolean checkIfCorrect() {
        ifCorrect = checkIfCorrect(numbers.values());
        if (ifCorrect) {
            sprite.setRegion(correctRegion);
            return true;
        } else {
            sprite.setRegion(wrongRegion);
            return false;
        }
    }

    boolean getCorrectness() {
        return ifCorrect;
    }

    public abstract boolean checkIfCorrect(Collection<Integer> number);

    void dispose() {
        numbers.clear();
        realBody.getWorld().destroyBody(realBody);
        myTexture.dispose();
    }

    void render(SpriteBatch batch) {
        sprite.draw(batch);
        currentLevel.font.draw(batch, condition, sprite.getX() + sprite.getWidth() / 2, sprite.getY() + width);

    }

    void update(float dt) {

    }

}
