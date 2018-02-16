package com.krvang.lindved.minesweeper.bll;

/**
 * Created by Lindved on 08-02-2018.
 */

public interface IGameBoard {

    boolean isBomb(int row, int col);
    int getBombsAroundTile(int row, int col);
}
