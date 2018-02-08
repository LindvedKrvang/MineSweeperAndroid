package com.krvang.lindved.minesweeper.bll;

/**
 * Created by Lindved on 08-02-2018.
 */

public class GameBoard implements IGameBoard {

    private boolean[][] mBombMatrix;

    public GameBoard(int rows, int cols){
        mBombMatrix = new boolean[rows][cols];
    }
}
