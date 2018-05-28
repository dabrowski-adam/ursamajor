/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbl.ursa.mathematics;

/**
 *
 * @author marcin7Cd
 */
public interface Dragable {
    
    void drop();
    boolean grab();
    void dragBy(float dx,float dy);
}
