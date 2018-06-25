package com.pbl.ursa.technology;

public class Levels {

    private Levels() { }

    public static void setLevel(Board board, Inventory inventory, int level) {
        board.cells[0][4].content = Tool.BeltRightLocked;
        board.cells[0][4].isDisabled = true;
        board.cells[3][4].content = Tool.Bin;
        board.cells[3][4].isDisabled = true;
        board.cells[2][2].content = Tool.BeltRight;

        inventory.insert(Tool.BeltRight);
        inventory.insert(Tool.BeltRight);
    }

}
