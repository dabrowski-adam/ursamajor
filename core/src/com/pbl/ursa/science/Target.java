package com.pbl.ursa.science;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

import java.util.Map;

class Target {
    Circle body;
    SGAssets asset;

    public static final float TARGETDEFAULTX = ScienceGame.MAXALLOWEDX - ScienceGame.MINALLOWEDX;
    public static final float TARGETDEFAULTY = ScienceGame.MAXALLOWEDY - ScienceGame.MINALLOWEDY;
    public static final float TARGETDEFAULTRADIUS = 2f;

    Target() {
        body = new Circle(TARGETDEFAULTX, TARGETDEFAULTY, TARGETDEFAULTRADIUS);
        asset = SGAssets.Target;
    }

    Target(float x, float y, float radius) {
        if(x-radius > ScienceGame.MINALLOWEDX
                && x+radius < ScienceGame.MAXALLOWEDX
                && y-radius > ScienceGame.MINALLOWEDY
                && y+radius < ScienceGame.MAXALLOWEDY) {
            body = new Circle(x, y, radius);
            asset = SGAssets.Target;
        }
        else body = new Circle();
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
