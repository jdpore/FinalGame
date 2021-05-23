package com.example.gates;

import java.util.ArrayList;

public class GameBank {

    public int[][] getProblem(int type, int key) {
        switch (type) {
            case 1: switch (key) {
                        case 0: //novice 0-3, intermediate 4-7, advance 8-11
                            return new int[][]{{0}, {3, 39}, {1, 4}, {1, 0}, {0, 1}, {2}};
                        default:
                            return new int[][]{};
                    }
            default:
                return new int[][]{};
        }
    }
}
