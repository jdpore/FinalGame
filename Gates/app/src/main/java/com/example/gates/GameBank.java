package com.example.gates;

import java.util.ArrayList;

public class GameBank {

    public int[][] getProblem(int difficulty, int key) {
        switch(difficulty) {
            case 0: switch(key) {
                case 0: return new int[][]{{0}, {2, 4}, {0}, {0}};
            }
            default:
                return new int[][] {};
        }
    }
}
