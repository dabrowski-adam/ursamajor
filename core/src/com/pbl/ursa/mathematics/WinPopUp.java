/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbl.ursa.mathematics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 *
 * @author marcin7Cd
 */
public class WinPopUp {
    MenuOverlay menu;
    Texture winMessageTexture;
    Texture nextLevelTexture;
    Level currentLevel;
    Image winMessage;
    ImageButton nextLevelButton;
    
    WinPopUp(Level currentLevel, MenuOverlay menu){
        this.currentLevel = currentLevel;
        this.menu = menu;
        winMessageTexture = new Texture(Gdx.files.internal("mathematics/win_message")+".bmp");
        nextLevelTexture = new Texture(Gdx.files.internal("mathematics/win_nextLevel")+".bmp");
        
        
        nextLevelButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(nextLevelTexture)));
        nextLevelButton.setPosition((320 - nextLevelTexture.getWidth())/2, 140);
        nextLevelButton.addListener(new NextLevelEventListener(menu));
        
        winMessage = new Image(winMessageTexture);
        winMessage.setPosition((320 - winMessageTexture.getWidth())/2, 200);
        
        menu.stage.addActor(nextLevelButton);
        menu.stage.addActor(winMessage);
    }
    
    void dispose(){
        winMessage.remove();
        nextLevelButton.remove();
        winMessageTexture.dispose();
        nextLevelTexture.dispose();
    }
    
    class NextLevelEventListener extends InputListener {

        MenuOverlay menu;

        NextLevelEventListener(MenuOverlay menu) {
            this.menu = menu;
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            return;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            menu.loadNextLevel();
            return true;
        }

    }
}
