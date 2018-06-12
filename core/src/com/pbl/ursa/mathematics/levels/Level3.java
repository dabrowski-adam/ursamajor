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
public class Level3 implements LevelSetter {

    @Override
    public void loadLevel(Level currentLevel) {
        new Level.Loader().levelHeight(560.0f).apply(currentLevel);
        
        currentLevel.addObstacle(0, 560 , 320, 30);
        
        currentLevel.addTestingPlatform(new TestingPlatform(0.0f,0.0f,320.0f,"x<500",currentLevel){
            @Override
            public boolean checkIfCorrect(Collection<Integer> numbers) {
                for(Integer current : numbers){
                    if(current>500){
                    return true;
                    }
                }
                return false;
            }
        });
        
        currentLevel.addBar(new PassableBar(0.0f,120.0f,120.0f,"x>300",currentLevel){ /// height 120
            @Override
            public boolean checkIfCanBePassed(int number) {
                return number>300;
            }
        });
        currentLevel.addObstacle(120,120,200, 30);   
        currentLevel.addNumberAt(90.0f, 150.0f, 83); ////// height 150
        currentLevel.addNumberAt(180.0f,150.0f,82);
        currentLevel.addNumberAt(270.0f, 150.0f, 91);
        
        currentLevel.addBar(new PassableBar(0.0f,330.0f,110.0f,"divisible by 4",currentLevel){ //// height 330
            @Override
            public boolean checkIfCanBePassed(int number) {
                return number %4 == 0;
            }
        });
        currentLevel.addObstacle(110, 330,90, 30);
        currentLevel.addBar(new PassableBar(200.0f,330.0f,120.0f,"sum of digits even",currentLevel){
            @Override
            public boolean checkIfCanBePassed(int number) {
                int sum =0;
                while(number>0){
                    sum+= number%10;
                    number/= 10;
                }
                return (sum%2)==0;
            }
        });
        currentLevel.addNumberAt(109, 360, 249); ////height 360
        
        
    }
    
}
