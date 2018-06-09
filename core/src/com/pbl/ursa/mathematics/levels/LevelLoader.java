/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbl.ursa.mathematics.levels;

import com.pbl.ursa.mathematics.Level;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marcin7Cd
 */
public class LevelLoader {

    static LevelLoader thisInstance;
    List<LevelSetter> levels;

    private LevelLoader() {
        levels = new ArrayList();
        levels.add(new Level1());
        levels.add(new Level2());
    }

    public static LevelLoader getInstance() {
        if (thisInstance == null) {
            thisInstance = new LevelLoader();
        }
        return thisInstance;
    }

    LevelSetter getLevel(int i) {
        if (i >= 0 && i < levels.size()) {
            return levels.get(i);
        } else {
            return null;
        }
    }
    public void loadLevel(int i,Level currentLevel){
        if (i >= 0 && i < levels.size()) {
            levels.get(i).loadLevel(currentLevel);
        }
    }
    
    public int getNumberOfLevel(){
        return levels.size();
    }
    
    
}
