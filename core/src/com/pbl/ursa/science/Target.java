package com.pbl.ursa.science;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

import java.util.Map;

class Target {
    Circle body;
    SGAssets asset;

    public static final float TARGETDEFAULTX = 80f;
    public static final float TARGETDEFAULTY = 45f;
    public static final float TARGETDEFAULTRADIUS = 5f;

    Target() {
        body = new Circle(TARGETDEFAULTX, TARGETDEFAULTY, TARGETDEFAULTRADIUS);
        asset = SGAssets.Target;
    }

    Target(float x, float y, float radius) {
        if(x-radius > ScienceGame.WORLDLEFTBOUNDARY
                && x+radius < ScienceGame.WORLDRIGHTBOUNDARY
                && y-radius > ScienceGame.WORLDDOWNBOUNDARY
                && y+radius < ScienceGame.WORLDUPBOUNDARY) {
            body = new Circle(x, y, radius);
        }
        else body = new Circle();
        asset = SGAssets.Target;
    }

    boolean isHit(Circle missile) {
        return body.overlaps(missile);
    }

    void render(SpriteBatch spriteBatch, Map<SGAssets, Texture> resources) {
        if (spriteBatch == null || !spriteBatch.isDrawing()) {
            return;
        }
        if (!resources.containsKey(asset)) {
            return;
        }

        spriteBatch.draw(
                resources.get(asset),
                body.x - body.radius,
                body.y - body.radius,
                2 * body.radius,
                2 * body.radius);
    }
}
