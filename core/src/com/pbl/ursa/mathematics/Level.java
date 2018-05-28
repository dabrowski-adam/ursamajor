/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbl.ursa.mathematics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marcin7Cd
 */
public class Level {

    List<Number> numbers;

    Level() {
        numbers = new ArrayList();
    }

    Number selectNumberAt(float x, float y) {
        Rectangle r1 = new Rectangle();
        for (Number currentNumber : numbers) {
            r1.set(currentNumber.position.x, currentNumber.position.y,
                    currentNumber.dimension.x, currentNumber.dimension.y);
            //Gdx.app.log("xN", Float.toString(x-currentNumber.position.x - currentNumber.dimension.x));
            //Gdx.app.log("yN", Float.toString(y-currentNumber.position.y - currentNumber.dimension.y));
            //Gdx.app.log("wN", Float.toString(currentNumber.position.x-x));
            //Gdx.app.log("hN", Float.toString(currentNumber.position.y-y));
            
            if (r1.contains(x, y)) {
            
                return currentNumber;
            }
        }
        return null;
    }

    void addNumberAt(float x, float y, int value) {
        numbers.add(new Number(x, y, value));
    }

    void update(float dt) {
        for (Number currentNumber : numbers) {
            currentNumber.update(dt);
        }
    }

    void render(SpriteBatch spriteBatch) {
        for (Number currentNumber : numbers) {
            currentNumber.render(spriteBatch);
        }
    }
}
