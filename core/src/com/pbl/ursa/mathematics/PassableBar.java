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
import com.badlogic.gdx.graphics.g2d.TextureAtlas.TextureAtlasData.Region;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public abstract class PassableBar  {

    static final float width = 30.0f;

    Texture myTexture;
    Body realBody;
    Sprite sprite;
    String condition = "";
    Level currentLevel;

    public PassableBar(float position_x, float position_y, float lenght, String condition, Level level) {
        currentLevel = level;
        this.condition = condition;

        /////////////////////////////////////////////////
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set( (position_x + lenght/2) / Level.PPM, (position_y + width/2) / Level.PPM);
        bodyDef.type = BodyDef.BodyType.StaticBody;

        realBody = currentLevel.world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();

        shape.setAsBox(lenght / 2 / Level.PPM, width / 2 /Level.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.filter.categoryBits = -1;

        realBody.createFixture(fixtureDef);
        shape.dispose();
        realBody.setUserData(this);
        ////////////////////////////////////////
        myTexture = new Texture(Gdx.files.internal("mathematics/bar") + ".bmp");
        myTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
          
        sprite = new Sprite(myTexture,(int) (myTexture.getWidth()*(lenght/320.0f)),myTexture.getHeight());
        sprite.setPosition(position_x, position_y);
        sprite.setSize(lenght, width);

    }

    void setConditionString(String condition) {
        this.condition = condition;
    }

    void checkNumber(Number number) {
        Filter filter;
        filter = realBody.getFixtureList().get(0).getFilterData();

        if (checkIfCanBePassed(number.value)) {
            // exludes this number from collisions with it;
            filter.maskBits &= ~(0x0001 << number.collisionCategory);
            realBody.getFixtureList().get(0).setFilterData(filter);
                //realBody.getFixtureList().get(0).getFilterData().maskBits
                    //        &=  ~number.realBody.getFixtureList().get(0).getFilterData().categoryBits;
        } else {
            //include this number in collisions
            
            filter.maskBits |= 0x0001 << number.collisionCategory;
            realBody.getFixtureList().get(0).setFilterData(filter);
            //realBody.getFixtureList().get(0).getFilterData().maskBits |= number.realBody.getFixtureList().get(0).getFilterData().categoryBits;
        }
    }

    abstract public boolean checkIfCanBePassed(int number);

    public void update(float deltaTime) {

    }

    public void render(SpriteBatch spriteBatch) {
        sprite.draw(spriteBatch);
        currentLevel.font.getSpaceWidth();
        currentLevel.font.draw(spriteBatch, condition, sprite.getX() + sprite.getWidth() / 2 , sprite.getY() + width);
    }

    void dispose() {
        realBody.getWorld().destroyBody(realBody);
        myTexture.dispose();
    }
}
