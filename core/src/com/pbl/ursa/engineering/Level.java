package com.pbl.ursa.engineering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Locale;

import sun.rmi.runtime.Log;

/**
 * Created by Mateusz Bujnowicz on 5/15/2018.
 */

public class Level {
    ArrayList<Part> parts;
    private final Texture done;
    private final Texture mystery;
    private float shape_x;
    private float shape_y;
    private boolean firstTime=true;

    public Level(String done, String mystery) {
        this.done = new Texture(Gdx.files.internal("engineering/"+done));
        this.mystery = new Texture(Gdx.files.internal("engineering/"+mystery));
        shape_x = 160-this.done.getWidth()/2;
        shape_y = 240;
        parts = new ArrayList<Part>();
    }

    private void throwParts(){
        int amount = parts.size();
        float distance = 270/amount;
        for(int i=0;i<amount;i++){
            parts.get(i).coor_y=10;
            parts.get(i).coor_x=50+i*distance;
        }
    }

    boolean isFinished(){
        for(Part each: parts){
            if(!each.isPlaced()) return false;
        }
        return true;
    }

    void move(float x, float y, float difference_x, float difference_y){
        for(Part each: parts){
            if(each.isPlaced()) continue;
            if(     x > each.coor_x &&
                    x < each.coor_x + each.width &&
                    y > each.coor_y &&
                    y < each.coor_y + each.height){

                each.coor_y += difference_y;
                each.coor_x += difference_x;
                Gdx.app.log("position x",Float.toString(each.coor_x));
                Gdx.app.log("position y",Float.toString(each.coor_y));
                break;
            }
        }
    }

    void render(SpriteBatch spriteBatch){
        if (spriteBatch == null || !spriteBatch.isDrawing()) { return; }
        if(firstTime){
            firstTime=false;
            throwParts();
        }
        if(isFinished()){
            spriteBatch.draw(done,shape_x,shape_y);
        }
        else {
            spriteBatch.draw(mystery,shape_x,shape_y);
            for(Part each: parts){
                each.render(spriteBatch);
            }
        }
    }
}
