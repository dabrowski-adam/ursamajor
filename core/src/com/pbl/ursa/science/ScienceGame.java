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
    public static final float DEFAULTVELOCITY = 0f;
    public static final float DEFAULTBULLETSOURCEX = 1f;
    public static final float DEFAULTBULLETSOURCEY = 45f;

    ScienceGame() {
        score = new Score();
        timer = 5f;
        selectedAngle = DEFAULTANGLE;
        selectedVelocity = DEFAULTVELOCITY;
        bulletSource = new BulletSource(DEFAULTBULLETSOURCEX, DEFAULTBULLETSOURCEY);
    }

    void changeAngle(float angle) {
        if (selectedAngle + angle > 90 || selectedAngle + angle < 0) return;
        selectedAngle += angle;
    }

    void changeVelocity(float velocity) {
        if (selectedVelocity + velocity < 0 /* || selectedVelocity + velocity > iluś tam*/) return;
        selectedVelocity += velocity;
    }

    void deployBullet() {
        if (bullet == null) bullet = new Bullet(bulletSource.body.x, bulletSource.body.y, Bullet.DEFAULTBULLETRADIUS, selectedAngle, selectedVelocity);
    }

    boolean bulletExists() {
        return bullet!=null;
    }

    void destroyBullet() {
       bullet = null;
    }

    void deployTarget() {
        if (target == null) target = new Target(
                MathUtils.random(WORLDLEFTBOUNDARY + Target.TARGETDEFAULTRADIUS,
                                WORLDRIGHTBOUNDARY - Target.TARGETDEFAULTRADIUS),
                MathUtils.random(WORLDDOWNBOUNDARY + Target.TARGETDEFAULTRADIUS,
                                WORLDUPBOUNDARY - Target.TARGETDEFAULTRADIUS),
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
                bullet.resetTIA();
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
