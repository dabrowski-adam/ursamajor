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
public class Level4 implements LevelSetter {

    @Override
    public void loadLevel(Level currentLevel) {
        new Level.Loader().levelHeight(630.0f).apply(currentLevel);
        
        currentLevel.addTestingPlatform(new TestingPlatform(0.0f, 0.0f, 150.0f, "divisible by 4", currentLevel) {
            @Override
            public boolean checkIfCorrect(Collection<Integer> number) {
                for (Integer current : number) {
                    if (current % 4 == 0) {
                        return true;
                    }
                }
                return false;
            }
        });
        currentLevel.addNumberAt(120.0f, 20.0f, 93);

        currentLevel.addBar(new PassableBar(0.0f, 150.0f, 320.0f, "sum of digits > 10", currentLevel) {
            @Override
            public boolean checkIfCanBePassed(int number) {
                int sum = 0;
                while (number > 0) {
                    sum += number % 10;
                    number /= 10;
                }
                return sum > 10;
            }
        });

        currentLevel.addNumberAt(300.0f, 180.0f, 15);

        currentLevel.addBar(new PassableBar(0.0f, 270.0f, 320.0f, "divisible by 5", currentLevel) {
            @Override
            public boolean checkIfCanBePassed(int number) {
                return number % 5 == 0;
            }
        });

        currentLevel.addNumberAt(300.0f, 300.0f, 63);
        currentLevel.addObstacle(185, 300, 53, 120);
        currentLevel.addNumberAt(153, 300, 153);
        currentLevel.addObstacle(0, 300, 90, 120);

        currentLevel.addObstacle(0, 420, 90, 30);
        currentLevel.addBar(new PassableBar(90.0f, 420.0f, 230.0f, "even", currentLevel) {
            @Override
            public boolean checkIfCanBePassed(int number) {
                return number % 2 == 0;
            }
        });
        currentLevel.addNumberAt(90, 450.0f, 32);
    }

}
