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
public class Level2 implements LevelSetter {

    @Override
    public void loadLevel(Level currentLevel) {
        new Level.Loader().apply(currentLevel);
        currentLevel.addNumberAt(120.0f, 210.0f, 927);
        currentLevel.addNumberAt(210.0f, 210.0f, 83);
        currentLevel.addNumberAt(300.0f, 210.0f, 74);
        currentLevel.addBar(new PassableBar(0.0f,180.0f,320.0f,"sum od digits = 2",currentLevel) {
            @Override
            public boolean checkIfCanBePassed(int number) {
                int sum = 0;
                while(number>0){
                    sum+=number%10;
                    number/=10;
                }
                if(sum == 2 ){
                return true;
                }
                return false;
            }
        });
        
        currentLevel.addTestingPlatform(new TestingPlatform(0.0f,0.0f,320.0f,"x<1010",currentLevel){
            @Override
            public boolean checkIfCorrect(Collection<Integer> numbers) {
                for(Integer current : numbers){
                    if(current <1010){
                    return true;
                    }
                }
                return false;
            }
        });
    }
    
}
