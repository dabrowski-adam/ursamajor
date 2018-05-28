/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbl.ursa.mathematics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author marcin7Cd
 */
public interface GameObject {
    
    void update(float delta);
    void render(SpriteBatch spritebatch);
    
}
