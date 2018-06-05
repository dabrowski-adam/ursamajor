/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbl.ursa.mathematics;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author marcin7Cd
 */
public interface Dragable {
    
    void drop();
    boolean grab(Vector2 position);
    void dragTo(Vector2 position);
}
