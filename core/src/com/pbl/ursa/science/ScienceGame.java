package com.pbl.ursa.science;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.util.Map;

class ScienceGame {
    private Target target;
    private Bullet bullet;
    private final BulletSource bulletSource;
    private final Score score;
    float timer;
    private float selectedAngle;
    private float selectedVelocity;

    public static final float WORLDLEFTBOUNDARY = 0f;
    public static final float WORLDDOWNBOUNDARY = 0f;
    public static final float WORLDRIGHTBOUNDARY = 160f;
    public static final float WORLDUPBOUNDARY = 90f;
    public static final float DEFAULTANGLE = 45f;
    public static final float DEFAULTVELOCITY = 25f;
    public static final float DEFAULTBULLETSOURCEX = 40f;
    public static final float DEFAULTBULLETSOURCEY = 40f;
    public static final float MINALLOWEDX = 40f;
    public static final float MAXALLOWEDX = 120f;
    public static final float MINALLOWEDY = 40f;
    public static final float MAXALLOWEDY = 65f;

    ScienceGame() {
        score = new Score();
        timer = 60f;
        selectedAngle = DEFAULTANGLE;
        selectedVelocity = DEFAULTVELOCITY;
        bulletSource = new BulletSource(DEFAULTBULLETSOURCEX, DEFAULTBULLETSOURCEY);
    }

    void changeAngle(float angle) {
        if (selectedAngle + angle > 90 || selectedAngle + angle < 0) return;
        selectedAngle += angle;
    }

    int getAngle() { return (int)selectedAngle; }

    void changeVelocity(float velocity) {
        if (selectedVelocity + velocity < 0  || selectedVelocity + velocity > 50) return;
        selectedVelocity += velocity;
    }

    int getVelocity() { return (int)selectedVelocity; }

    void deployBullet() {
        if (bullet == null) bullet = new Bullet(DEFAULTBULLETSOURCEX, DEFAULTBULLETSOURCEY, Bullet.DEFAULTBULLETRADIUS, (double)selectedAngle, selectedVelocity);
    }

    boolean bulletExists() {
        return bullet!=null;
    }

    void destroyBullet() {
       bullet = null;
    }

    void deployTarget() {
        if (target == null) target = new Target(
                MathUtils.random(MINALLOWEDX + Target.TARGETDEFAULTRADIUS,
                                MAXALLOWEDX - Target.TARGETDEFAULTRADIUS),
                MathUtils.random(MINALLOWEDY + Target.TARGETDEFAULTRADIUS,
                                MAXALLOWEDY - Target.TARGETDEFAULTRADIUS),
                Target.TARGETDEFAULTRADIUS);
    }

    boolean targetExists() {
        return target!=null;
    }

    void destroyTarget() {
        target = null;
    }

    int getTime() {
        return (int)timer;
    }

    void updateTimer(float byValue) {
        timer -= byValue;
    }

    int getScore() { return score.getScore(); }

    void render(SpriteBatch spriteBatch, Map<SGAssets, Texture> resources) {
        if (spriteBatch == null || !spriteBatch.isDrawing()) { return; }
        if (target != null) target.render(spriteBatch, resources);
        if (bullet != null) bullet.render(spriteBatch, resources);
        if (bulletSource != null) bulletSource.render(spriteBatch, resources);
    }

    void doMainGameLoop() {
        updateTimer(Gdx.graphics.getDeltaTime());
        if (bulletExists()) {
            if (target.isHit(bullet.getBody())) {
                destroyBullet();
                destroyTarget();
                score.IncreaseValue(1);
            }
            else if (!bullet.isWithinBoundaries()) {
                destroyBullet();
            }
        }
        if (!targetExists()) {
            deployTarget();
        }
    }
}
