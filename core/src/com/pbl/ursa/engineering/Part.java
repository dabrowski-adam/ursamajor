package com.pbl.ursa.engineering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import sun.rmi.runtime.Log;

/**
 * Created by Mateusz Bujnowicz on 5/15/2018.
 */

public class Part {
    private static final int MARGIN = 6;

    float coor_x;
    float coor_y;
    final float width;
    final float height;
    private boolean isPlaced=false;
    private float desired_x; // JAK NA RAZIE - DO USTAWIENIA
    private float desired_y;
    private final Sound attatched_sound;

    private final Texture myTexture;

    public Part(String textureName, float x, float y) {
        myTexture = new Texture(Gdx.files.internal("engineering/"+textureName));
        myTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        width = myTexture.getWidth();
        height = myTexture.getHeight();
        desired_y = y;
        desired_x = x;
        attatched_sound = Gdx.audio.newSound(Gdx.files.internal("engineering/bad.mp3"));
    }


    boolean isPlaced(){
        if(isPlaced) return true;
        if(coor_x < desired_x + MARGIN &&
                coor_x > desired_x - MARGIN &&
                coor_y < desired_y + MARGIN &&
                coor_y > desired_y - MARGIN){
            isPlaced=true;
            coor_x=desired_x;
            coor_y=desired_y;
            attatched_sound.play();

            return true;
        }
        return false;
    }

    void render(SpriteBatch spriteBatch){
        spriteBatch.draw(myTexture,coor_x,coor_y);
    }

}
