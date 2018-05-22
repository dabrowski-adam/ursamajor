package com.pbl.ursa.science;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

import java.util.Map;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;

class Bullet {
    Circle body;
    SGAssets asset;
    private static final int MINALLOWEDX = 35;
    private static final int MAXALLOWEDX = 125;
    private static final int MINALLOWEDY = 34;
    private static final int MAXALLOWEDY = 70;
    private float timeInAir;
    private float initialVelocity;
    private double angle;
    private static float GRAVITYACCELERATION = 12.5f;
    private float initialX;
    private float initialY;

    public static final float DEFAULTBULLETRADIUS = 1f;

    Bullet(float x, float y, float radius, double angle, float velocity) {
        body = new Circle (x, y, radius);
        initialX = x;
        initialY = y;
        this.angle = angle*PI/180;
        this.initialVelocity = velocity;
        asset = SGAssets.Bullet;
        timeInAir = 0;
    }

    void updatePosition(float time) {
        timeInAir += time;
        body.setPosition(
                initialX + initialVelocity * timeInAir * (float)cos(angle),
                initialY + initialVelocity * timeInAir * (float)sin(angle) - GRAVITYACCELERATION * (float)pow(timeInAir, 2));
    }

    Circle getBody() {
        return body;
    }

    void resetTIA() {timeInAir = 0; }

    boolean isWithinBoundaries() {
        if (body.x < MINALLOWEDX ||
                body.x > MAXALLOWEDX ||
                body.y < MINALLOWEDY ||
                body.y > MAXALLOWEDY) return false;
        return true;
    }

    void render(SpriteBatch spriteBatch, Map<SGAssets, Texture> resources) {
        updatePosition(Gdx.graphics.getDeltaTime());
        if (spriteBatch == null || !spriteBatch.isDrawing()) { return; }
        if (!resources.containsKey(asset)) { return; }

        spriteBatch.draw(
                resources.get(asset),
                body.x - body.radius,
                body.y - body.radius,
                2*body.radius,
                2*body.radius);
    }
}

