/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbl.ursa.mathematics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 *
 * @author marcin7Cd
 */
public class NumberContactListener implements ContactListener {

    private Level level;

    NumberContactListener(Level level) {
        this.level = level;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        Number firstNumber;
        Number secondNumber;
        if (fa.getBody().getUserData() != null && fa.getBody().getUserData().getClass() == Number.class) {
            firstNumber = (Number) fa.getBody().getUserData();
            if (fb.getBody().getUserData() != null && fb.getBody().getUserData().getClass() == Number.class) {
                secondNumber = (Number) fb.getBody().getUserData();
                if (fb.getBody().getWorldCenter().y > fa.getBody().getWorldCenter().y) {
                    firstNumber.numbersOnTop++;
                } else {
                    secondNumber.numbersOnTop++;
                }
                addNumbersIfPossible(firstNumber,secondNumber);
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        Number firstNumber;
        Number secondNumber;
        if (fa.getBody().getUserData() != null && fa.getBody().getUserData().getClass() == Number.class) {
            firstNumber = (Number) fa.getBody().getUserData();
            if (fb.getBody().getUserData() != null && fb.getBody().getUserData().getClass() == Number.class) {
                secondNumber = (Number) fb.getBody().getUserData();
                if (fb.getBody().getWorldCenter().y > fa.getBody().getWorldCenter().y) {
                    firstNumber.numbersOnTop--;
                } else {
                    secondNumber.numbersOnTop--;
                }
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
    // To addNumbers conditions below should be satisfied:
    // |- difference in x coordinate has to be small
    // |- number with more digits on the bottom
    
    void addNumbersIfPossible(Number a, Number b) { 
        if (!a.isOperatedOn && !b.isOperatedOn && Math.abs(a.getLogicalX() - b.getLogicalX()) < 10) { // 5 -temporary range
            Gdx.app.log("test","tt");
            if (a.realBody.getLinearVelocity().y < b.realBody.getLinearVelocity().y) {
                if (b.digits.size() >= a.digits.size()) {
                    level.appendAddOperation(b, a);
                }
            } else {
                if (a.digits.size() >= b.digits.size()) {
                    level.appendAddOperation(a, b);
                }
            }
        }

    }
}
