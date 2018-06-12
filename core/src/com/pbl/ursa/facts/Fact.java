package com.pbl.ursa.facts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Mateusz Bujnowicz on 6/12/2018.
 */

public class Fact {
     final String text;
     private final String start;
     private BitmapFont text_fact;
     private BitmapFont start_font;
     private Texture image;

    Fact(String photo, String text){
        this.text=text;
        start="Did you know?";
        text_fact = new BitmapFont();
        text_fact.getData().setScale(1);
        text_fact.setColor(Color.BLACK);
        text_fact.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        start_font = new BitmapFont();
        start_font.getData().setScale(1.7f);
        start_font.setColor(Color.BLACK);
        start_font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        image = new Texture(Gdx.files.internal("facts/"+photo));
    }

    void render(SpriteBatch spriteBatch){
        if (spriteBatch == null || !spriteBatch.isDrawing()) { return; }
        spriteBatch.draw(image,10,270,300,200);
        start_font.draw(spriteBatch,start,80,260);
        text_fact.draw(spriteBatch,text,5,230);
    }
}
