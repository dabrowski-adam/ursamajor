/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbl.ursa.mathematics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author marcin7Cd
 */
public class AddOperation {

    static final float turnDuration = 1.0f;
    static final float maximalOffset = 20.0f;

    Number movingNumber;
    Number stationaryNumber;
    Level currentLevel;

    int currentTurn = 0;
    float timeToTurn = 0.0f;
    Group temporaryDigits;
    int carries[];
    boolean isFinished = false;
    boolean isToBeDestroyed = false;

    AddOperation(Number stationaryNumber, Number movingNumber, Level currentLevel) {
        this.currentLevel = currentLevel;
        this.stationaryNumber = stationaryNumber;
        this.movingNumber = movingNumber;
        stationaryNumber.addZeroDigit();
        carries = new int[stationaryNumber.digits.size()];
        temporaryDigits = new Group();
        currentLevel.stage.addActor(temporaryDigits);
        currentLevel.numbers.remove(movingNumber);
        goToNextTurn();
    }

    void render(Batch spriteBatch) {
        temporaryDigits.draw(spriteBatch, 1.0f);
    }

    void update(float dt) {
        if (!isFinished) {
            temporaryDigits.addAction(Actions.moveTo(stationaryNumber.getLogicalX(), stationaryNumber.getLogicalY()));
            timeToTurn += dt;
            if (timeToTurn > turnDuration) {
                currentTurn++;
                timeToTurn -= turnDuration;
                goToNextTurn();
            }
        } else {
            if (!isToBeDestroyed) {
                if (!temporaryDigits.hasActions()) {
                    isToBeDestroyed = true;
                    recreateNumber();
                    remove();
                }
            } else {
                remove();
            }
        }
        temporaryDigits.act(dt);
    }

    void recreateNumber() {
        int sum = stationaryNumber.value + movingNumber.value;
        currentLevel.replaceNumber(stationaryNumber, sum);
    }

    void goToNextTurn() {
        int temp = 0;
        Digit a;
        Digit b;
        if (currentTurn == 0) {
            for (int i = 0; i < stationaryNumber.digits.size() - 1 && i < movingNumber.digits.size(); i++) {
                a = stationaryNumber.digits.get(i);
                b = movingNumber.digits.get(i);
                temp = a.value + b.value;
                a.changeDigitTo(temp);
                if (temp >= 10) {
                    temporaryDigits.addActor(new TemporaryDigit(a.position.x + a.dimension.x/2, a.position.y + a.dimension.y/2,
                            temp / 10, new Vector2(20.0f, 30.0f), stationaryNumber.digits.get(i + 1), turnDuration));
                    //temporaryDigits.addActor(new TemporaryDigit(a.sprite.getX()+ a.dimension.x/2 + 20.0f, a.sprite.getY() + a.dimension.y / 2 - 15.0f,
                    //        temp / 10, new Vector2(20.0f, 30.0f), stationaryNumber.digits.get(i + 1), turnDuration));
                    carries[i + 1] = temp / 10 ;
                }
            }
        } else {
            for (int i = stationaryNumber.digits.size()-1; i >=0; i--) {
                a = stationaryNumber.digits.get(i);
                temp = a.value + carries[i];
                Gdx.app.log("temp", Integer.toString(carries[i])+" "+Integer.toString(temp));
                carries[i] = 0;
                a.changeDigitTo(temp);
                if (temp >= 10) {
                    if(i == stationaryNumber.digits.size()) {continue;}
                    temporaryDigits.addActor(new TemporaryDigit(a.position.x + a.dimension.x/2, a.position.y + a.dimension.y/2,
                            temp / 10, new Vector2(20.0f, 30.0f), stationaryNumber.digits.get(i + 1), turnDuration));
                    //temporaryDigits.addActor(new TemporaryDigit(a.sprite.getX()+ a.dimension.x/2 + 20.0f, a.sprite.getY() + a.dimension.y / 2 - 15.0f,
                    //        temp / 10, new Vector2(20.0f, 30.0f), stationaryNumber.digits.get(i + 1), turnDuration));
                    carries[i + 1] = temp / 10;
                }
            }
            Gdx.app.log("-----", "xx");
                
        }
        if (checkIfStop()) {
            finishOperation();
        }
    }

    boolean checkIfStop() {
        for (int i = 0; i < carries.length; i++) {
            if (carries[i] != 0) {
                return false;
            }
        }
        return true;
    }

    void remove() {
        currentLevel.removeNumber(movingNumber);
        currentLevel.removeOperation(this);
    }

    void finishOperation() {
        isFinished = true;
    }
}
