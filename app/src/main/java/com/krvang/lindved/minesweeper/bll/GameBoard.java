package com.krvang.lindved.minesweeper.bll;

import java.util.Random;

/**
 * Created by Lindved on 08-02-2018.
 */

public class GameBoard implements IGameBoard {

    private final int BOMB_DISTRIBUTION_FACTOR = 4;
    private final int AMOUNT_OF_BOMBS = 25;

    private int mRows, mCols, mAvailableTiles;
    private boolean[][] mBombMatrix;


    public GameBoard(int rows, int cols){
        mBombMatrix = new boolean[rows][cols];
        mRows = rows;
        mCols = cols;
        mAvailableTiles = rows * cols;

        populateBombs();
    }

    private void populateBombs(){
        Random rand = new Random();
        int bombsPlaced = 0;
        int tilesPlaced = 0;
        for(int i = 0; i < mRows; i++){
            for(int j = 0; j < mCols; j++){
                if(rand.nextInt(BOMB_DISTRIBUTION_FACTOR) == 0 && bombsPlaced != AMOUNT_OF_BOMBS){
                    mBombMatrix[i][j] = true;
                    bombsPlaced++;
                    tilesPlaced++;
                }else if(mAvailableTiles - tilesPlaced == AMOUNT_OF_BOMBS - bombsPlaced){
                    mBombMatrix[i][j] = true;
                    bombsPlaced++;
                    tilesPlaced++;
                }else{
                    mBombMatrix[i][j] = false;
                    tilesPlaced++;
                }
            }
        }
    }

    public boolean isBomb(int row, int col){
        return mBombMatrix[row][col];
    }

    public int getBombsAroundTile(int row, int col){
        int bombs = 0;

        boolean validRowNegative = row - 1 >= 0;
        boolean validRowPositive = row + 1 < mRows;

        boolean validColNegative = col - 1 >= 0;
        boolean validColPositive = col + 1 < mCols;

        if(validRowNegative && mBombMatrix[row - 1][col])
            bombs++;
        if (validRowNegative && validColNegative && mBombMatrix[row - 1][col - 1])
            bombs++;
        if(validRowNegative && validColPositive && mBombMatrix[row - 1][col + 1])
            bombs++;

        if(validRowPositive && mBombMatrix[row + 1][col])
            bombs++;
        if(validRowPositive && validColNegative && mBombMatrix[row + 1][col - 1])
            bombs++;
        if(validRowPositive && validColPositive && mBombMatrix[row + 1][col + 1])
            bombs++;

        if(validColPositive && mBombMatrix[row][col + 1])
            bombs++;

        if(validColNegative && mBombMatrix[row][col - 1])
            bombs++;

        return bombs;
    }
}
