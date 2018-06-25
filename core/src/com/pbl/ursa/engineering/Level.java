package com.pbl.ursa.engineering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Locale;

import sun.rmi.runtime.Log;

/**
 * Created by Mateusz Bujnowicz on 5/15/2018.
 */

public class Level {
    ArrayList<Part> parts;
    private final Sprite done;
    private final Texture mystery;
    private float shape_x;
    private float shape_y;
    private boolean firstTime=true;
    private float alpha=0.0f;
    private float deltaTime;
    private BitmapFont text;
    private BitmapFont text_fact;
    private boolean endGame=false;
    private final String name;
    private final String fact;
    private Sound done_sound;
    private boolean finish;

    public Level(String done, String mystery, String name, String fact) {
        Texture done_texture = new Texture(Gdx.files.internal("engineering/"+done));
        done_texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.done = new Sprite(done_texture);
        this.done.setAlpha(alpha);
        this.mystery = new Texture(Gdx.files.internal("engineering/"+mystery));
        this.mystery.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        shape_x = 160-this.done.getWidth()/2;
        shape_y = 240;
        this.done.setPosition(shape_x,shape_y);
        parts = new ArrayList<Part>();
        text = new BitmapFont();
        text.getData().setScale(3);
        text.setColor(Color.BLACK);
        text.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        text_fact = new BitmapFont();
        text_fact.getData().setScale(1);
        text_fact.setColor(Color.BLACK);
        text_fact.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.name=name;
        this.fact=fact;
        done_sound = Gdx.audio.newSound(Gdx.files.internal("engineering/tada.mp3"));
    }

    private void throwParts(){
        int amount = parts.size();
        float distance = 320/amount;
        for(int i=0;i<amount;i++){
            parts.get(i).coor_y=100;
            parts.get(i).coor_x=i*distance;
        }
    }

    boolean isFinished(){
        for(Part each: parts){
            if(!each.isPlaced()) return false;
        }
        return true;
    }

    Part detectPart(float x, float y){
        for(Part each: parts){
            if(each.isPlaced()) continue;
            if(     x > each.coor_x &&
                    x < each.coor_x + each.width &&
                    y > each.coor_y &&
                    y < each.coor_y + each.height){
                return each;
            }
        }
        return null;
    }

    void move(float difference_x, float difference_y, Part current_shape){
        current_shape.coor_y += difference_y;
        current_shape.coor_x += difference_x;
        Gdx.app.log("position x",Float.toString(current_shape.coor_x));
        Gdx.app.log("position y",Float.toString(current_shape.coor_y));
    }

    void endLevel(){
        if(endGame){
            finish=true;
        }
    }

    boolean fadeIn(Sprite sprite){
        if(alpha>=1){
            sprite.setAlpha(1);
            return true;
        }
        else {
            if(alpha==0) done_sound.play();
            deltaTime = Gdx.graphics.getDeltaTime();
            alpha = alpha + deltaTime;
            sprite.setAlpha(alpha);
            return false;
        }
    }

    boolean render(SpriteBatch spriteBatch){
        if (spriteBatch == null || !spriteBatch.isDrawing()) { return false; }
        if(endGame){
            done.draw(spriteBatch);
            text.draw(spriteBatch,name,50,200);
            text_fact.draw(spriteBatch,fact,5,150);
            if(finish) return true;
            return false;
        }
        if(firstTime){
            firstTime=false;
            throwParts();
        }
        spriteBatch.draw(mystery,shape_x,shape_y);

        for(Part each: parts){
            each.render(spriteBatch);
        }
        if(isFinished()){
            if(fadeIn(done)) {
                endGame = true;
            }
            done.draw(spriteBatch);
        }
        return false;
    }
}
