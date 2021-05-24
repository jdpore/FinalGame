package com.example.gates;

import java.util.ArrayList;

public class GameBank {

    public int[][] getProblem(int type, int key) {
        switch (type) {
            case 1: switch (key) {
                //circuit_no(0), active_grid(1), grid_type(2), type_state(3), grid_enabled(4), answer_key(5)
                case 0: //novice 0-3, intermediate 4-7, advance 8-11
                    return new int[][]{{0}, {3, 39}, {1, 4}, {1, 2}, {0, 1}, {3}};
                case 1:
                    return new int[][]{{1}, {3, 37}, {1, 4}, {1, 2}, {0, 1}, {1}};
                case 2:
                    return new int[][]{{2}, {3, 39}, {1, 4}, {1, 2}, {0, 1}, {0}};
                case 3:
                    return new int[][]{{3}, {3, 37}, {1, 4}, {1, 2}, {0, 1}, {1}};
                default:
                    return new int[][]{};
                    }
            case 3: switch (key) {
                case 1:
                    return new int[][]{{1}, {1, 4, 16, 24, 31}, {1, 2, 5, 5, 4}, {1, 0, 0, 0, 1},
                            {0, 0, 1, 1, 0}, {2}};
            }
            default:
                return new int[][]{};
        }
    }
}
