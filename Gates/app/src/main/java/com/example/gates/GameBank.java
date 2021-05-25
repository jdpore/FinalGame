package com.example.gates;

import java.util.ArrayList;

public class GameBank {

    public int[][] getProblem(int type, int key) {
        switch (type) {
            case 0:
            case 1:
                switch (key) {
                //circuit_no(0), active_grid(1), grid_type(2), gates_count(3)
                case 0: //novice 0-3, intermediate 4-7, advance 8-11
                    return new int[][]{{0}, {3,39}, {1,4}, {3}, {6,0}, {0,0,1}, {1,2,1}};
                case 1:
                    return new int[][]{{1}, {3,37}, {1,4}, {3}, {6,0}, {0,0,1}, {1,2,0}};
                case 2:
                    return new int[][]{{2}, {3,39}, {1,4}, {3}, {6,0}, {1,0,1}, {0,2,1}};
                case 3:
                    return new int[][]{{3}, {3,37}, {1,4}, {3}, {6,0}, {1,0,1}, {0,2,0}};
                case 4:
                    return new int[][]{{4}, {1,4,31}, {1,2,4}, {2}, {2,0,1}, {0,2,1}};
                case 5:
                    return new int[][]{{5}, {3,5,37}, {1,2,4}, {3}, {6,0}, {0,2,1}, {3,3,0}};
                case 6:
                    return new int[][]{{6}, {1,4,31}, {1,2,4}, {2}, {3,0,1}, {1,2,1}};
                case 7:
                    return new int[][]{{7}, {3,5,37}, {1,2,4}, {3}, {6,0}, {1,2,1}, {2,3,0}};
                case 8:                                                 //3         4       5
                    return new int[][]{{8}, {1,3,5,44}, {1,2,3,4}, {4}, {3,0,1}, {1,1,2}, {0,0,4}, {4,3,5}};
                case 9:                                                 //3         4       5
                    return new int[][]{{9}, {1,3,5,44}, {1,2,3,4}, {4}, {1,0,1}, {3,1,2}, {2,0,4}, {5,3,5}};
                case 10:                                                 //3         4
                    return new int[][]{{10}, {1,3,5,38}, {1,2,3,4}, {3}, {0,0,1}, {1,1,2}, {5,3,4}};
                case 11:                                                 //3         4
                    return new int[][]{{11}, {1,3,5,38}, {1,2,3,4}, {3}, {2,0,1}, {3,1,2}, {4,3,4}};
                default:
                    return new int[][]{};
                    }
            case 2: switch (key) {
                case 1:
                    return new int[][]{{1}, {1, 4, 16, 24, 31}, {1, 2, 5, 5, 4}, {1, 0, 0, 0, 1},
                            {0, 0, 1, 1, 0}, {2}};
            }
            default:
                return new int[][]{};
        }
    }
}
