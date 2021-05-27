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
                    return new int[][]{{0}, {3,39}, {3}, {6,0}, {0,0,1}, {1,1,2}};
                case 1:
                    return new int[][]{{1}, {3,37}, {3}, {6,0}, {0,0,1}, {1,0,2}};
                case 2:
                    return new int[][]{{2}, {3,39}, {3}, {6,0}, {1,0,1}, {0,1,2}};
                case 3:
                    return new int[][]{{3}, {3,37}, {3}, {6,0}, {1,0,1}, {0,0,2}};
                case 4:
                    return new int[][]{{4}, {1,4,31}, {2}, {2,0,1}, {0,2,1}};
                case 5:
                    return new int[][]{{5}, {3,5,37}, {3}, {6,0}, {0,2,1}, {3,3,0}};
                case 6:
                    return new int[][]{{6}, {1,4,31}, {2}, {3,0,1}, {1,2,1}};
                case 7:
                    return new int[][]{{7}, {3,5,37}, {3}, {6,0}, {1,2,1}, {2,3,0}};
                case 8:                                                 //3         4       5
                    return new int[][]{{8}, {1,3,5,44}, {4}, {3,0,1}, {1,1,2}, {0,0,4}, {4,3,5}};
                case 9:                                                 //3         4       5
                    return new int[][]{{9}, {1,3,5,44}, {4}, {1,0,1}, {3,1,2}, {2,0,4}, {5,3,5}};
                case 10:                                                 //3         4
                    return new int[][]{{10}, {1,3,5,38}, {3}, {0,0,1}, {1,1,2}, {5,3,4}};
                case 11:                                                 //3         4
                    return new int[][]{{11}, {1,3,5,38}, {3}, {2,0,1}, {3,1,2}, {4,3,4}};
                default:
                    return new int[][]{};
                    }
            case 2: switch (key) {
                //circuit_no(0), active_grid(1), gates_count(2), active_gates(length-1)
                case 0:
                    return new int[][]{{12}, {1,4,31}, {2}, {7,0,1}, {7,1,2}, {16,24}};
                case 1:
                    return new int[][]{{13}, {3,5,37}, {3}, {6,0}, {7,2,1}, {7,3,0}, {25,30}};
                case 2:
                    return new int[][]{{14}, {1,3,5,44}, {4}, {7,0,1}, {7,1,2}, {7,0,4}, {7,3,5}, {18,23,24,37}};
                case 3:
                    return new int[][]{{15}, {1,3,5,38}, {3}, {7,0,1}, {7,1,2}, {7,3,4}, {16,18,31}};

            }
            default:
                return new int[][]{};
        }
    }
}
