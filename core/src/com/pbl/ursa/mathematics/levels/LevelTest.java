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
 */public class LevelTest implements LevelSetter {

    @Override
    public void loadLevel(Level currentLevel) {
        new Level.Loader().levelHeight(480.0f*1.5f).apply(currentLevel);
        currentLevel.addNumberAt(100, 400, 159);
        currentLevel.addNumberAt(80, 300, 45);
        currentLevel.addObstacle(100, 230, 50, 100);
        currentLevel.addBar(new PassableBar(0.0f, 50.0f, 160.0f, "sum of digits >= 10", currentLevel) {
            @Override
            public boolean checkIfCanBePassed(int number) {
                int sum = 0;
                int temp = number;
                while (temp > 0) {
                    sum += temp % 10;
                    temp /= 10;
                }
                if (sum >= 10) {
                    return true;
                }
                return false;
            }
        }
        );
        currentLevel.addBar(new PassableBar(0.0f, 200.0f, 320.0f, "x>200 and x<250", currentLevel) {
            @Override
            public boolean checkIfCanBePassed(int number) {
                if (number > 200 && number < 250) {
                    return true;
                }
                return false;
            }
        }
        );

        currentLevel.addTestingPlatform(new TestingPlatform(200.0f, 400.0f, 120.0f,"x>100", currentLevel) {
            @Override
            public boolean checkIfCorrect(Collection<Integer> number) {
                for (Integer x : number) {
                    if (x > 100) {
                        return true;
                    }
                }
                return false;
            }
        });

    }

}

