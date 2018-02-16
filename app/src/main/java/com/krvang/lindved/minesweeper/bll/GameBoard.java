package com.krvang.lindved.minesweeper.bll;

import java.util.Random;

/**
 * Created by Lindved on 08-02-2018.
 */

public class GameBoard implements IGameBoard {

    private final int BOMB_DISTRIBUTION_FACTOR = 2;

    private int mRows, mCols, mAmountOfBombs, mAvailableTiles;
    private boolean[][] mBombMatrix;


    public GameBoard(int rows, int cols, int amountOfBombs){
        mBombMatrix = new boolean[rows][cols];
        mRows = rows;
        mCols = cols;
        mAmountOfBombs = amountOfBombs;
        mAvailableTiles = rows * cols;

        populateBombs();
    }

    private void populateBombs(){
        Random rand = new Random();
        int bombsPlaced = 0;
        for(int i = 0; i < mRows; i++){
            for(int j = 0; j < mCols; j++){
                if((mAmountOfBombs - bombsPlaced) == mAvailableTiles){
                    mBombMatrix[i][j] = true;
                    bombsPlaced++;
                }else if(rand.nextInt(BOMB_DISTRIBUTION_FACTOR) == 0){
                    mBombMatrix[i][j] = true;
                    bombsPlaced++;
                }else{
                    mBombMatrix[i][j] = false;
                }
            }
        }
    }

    public boolean isBomb(int row, int col){
        return mBombMatrix[row][col];
    }
}
