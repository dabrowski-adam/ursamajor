/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbl.ursa.mathematics.levels;

import com.pbl.ursa.mathematics.Level;
import com.pbl.ursa.mathematics.PassableBar;
import com.pbl.ursa.mathematics.TestingPlatform;
import java.util.Collection;

/**
 *
 * @author marcin7Cd
 */
public class Level1 implements LevelSetter {

    @Override
    public void loadLevel(Level currentLevel) {
        
        new Level.Loader().apply(currentLevel);
        currentLevel.addNumberAt(150, 150, 999);
        currentLevel.addNumberAt(150, 330, 1);
        currentLevel.addNumberAt(270, 150, 50);
        currentLevel.addBar(new PassableBar(0.0f,120.0f,320.0f,"Sum of digits = 6",currentLevel){
            @Override
            public boolean checkIfCanBePassed(int number) {
                int sum = 0;
                int temp = number;
                while (temp > 0) {
                    sum += temp % 10;
                    temp /= 10;
                }
                if (sum == 6) {
                    return true;
                }
                return false;    
            }
        });
        
        currentLevel.addTestingPlatform(new TestingPlatform(0.0f,0.0f,320.0f,"",currentLevel){
            @Override
            public boolean checkIfCorrect(Collection<Integer> number) {
            return !number.isEmpty();
            }
        });
    }

}
