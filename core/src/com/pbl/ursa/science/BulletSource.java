package com.pbl.ursa.science;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

import java.util.Map;

class BulletSource {
    Circle body;
    SGAssets asset;

    BulletSource(float x, float y) {
        body = new Circle (x, y, Bullet.DEFAULTBULLETRADIUS);
        asset = SGAssets.Source;
    }

    void render(SpriteBatch spriteBatch, Map<SGAssets, Texture> resources) {
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
