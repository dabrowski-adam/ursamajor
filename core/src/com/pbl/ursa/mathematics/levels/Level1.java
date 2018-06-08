/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbl.ursa.mathematics.levels;

import com.pbl.ursa.mathematics.Level;
import com.pbl.ursa.mathematics.PassableBar;

/**
 *
 * @author marcin7Cd
 */
public class Level1 implements LevelSetter {

    @Override
    public void loadLevel(Level currentLevel) {
        currentLevel.addNumberAt(100, 400, 159);
        currentLevel.addNumberAt(80, 300, 45);
        currentLevel.addNumberAt(200, 300, 67);

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
    }

}
