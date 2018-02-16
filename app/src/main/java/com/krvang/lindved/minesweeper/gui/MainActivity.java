package com.krvang.lindved.minesweeper.gui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.krvang.lindved.minesweeper.R;
import com.krvang.lindved.minesweeper.bll.GameBoard;
import com.krvang.lindved.minesweeper.bll.IGameBoard;


public class MainActivity extends AppCompatActivity {


    private LinearLayout mGameBoardLayout;

    private IGameBoard mGameBoard;

    private int mRow, mCols;
    private Tile[][] mTiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRow = 10;
        mCols = 10;
        mTiles = new Tile[mRow][mCols];

        mGameBoard = new GameBoard(mRow, mCols);

        View.OnClickListener ocl = createOnClickListener();

        View.OnLongClickListener olcl = createOnLongClickListener();

        instantiateBoard(ocl, olcl);
    }

    private void instantiateBoard(View.OnClickListener ocl, View.OnLongClickListener olcl){
        mGameBoardLayout = findViewById(R.id.llGameBoard);
        mGameBoardLayout.removeAllViews();
        for(int i = 0; i < mRow; i++){
            LinearLayout layoutRow = new LinearLayout(this);
            mGameBoardLayout.addView(layoutRow);
            layoutRow.setOrientation(LinearLayout.HORIZONTAL);
            for(int j = 0; j < mCols; j++){
                Tile tile = new Tile(this, i, j);
                layoutRow.addView(tile);
                tile.setOnClickListener(ocl);
                tile.setOnLongClickListener(olcl);
                mTiles[i][j] = tile;
            }
        }
    }

    private View.OnClickListener createOnClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tile tile = (Tile)v;
//                tile.showPosition();
                tile.clicked();
            }
        };
    }

    private View.OnLongClickListener createOnLongClickListener(){
        return new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {
                Tile tile = (Tile)v;
                tile.switchFlag();
                return true;
            }
        };
    }

    public void resetGame(View view){
        View.OnClickListener ocl = createOnClickListener();
        View.OnLongClickListener olcl = createOnLongClickListener();
        mGameBoard = new GameBoard(mRow, mCols);
        instantiateBoard(ocl, olcl);
    }

    class Tile extends AppCompatImageView {

        private int mRow, mCol, mImage;

        public Tile(Context context, int row, int col){
            super(context);
            mRow = row;
            mCol = col;
            mImage = R.drawable.tile;
            setImageResource(mImage);
        }

        public void showPosition(){
            Toast.makeText(super.getContext(),
                    "(" + mRow + ":" + mCol + ") is a bomb: " + mGameBoard.isBomb(mRow, mCol),
                    Toast.LENGTH_SHORT).show();
        }

        public void switchFlag(){
            mImage = mImage == R.drawable.tile ? R.drawable.tile_flag : R.drawable.tile;
            setImageResource(mImage);
        }

        public void clicked(){
            if(mGameBoard.isBomb(mRow, mCol))
                mImage = R.drawable.tile_bomb;
            else
                setImage();
            setImageResource(mImage);
        }

        private void setImage(){
            switch (mGameBoard.getBombsAroundTile(mRow, mCol)){
                case 0:
                    mImage = R.drawable.tile_blank;
                    break;
                case 1:
                    mImage = R.drawable.tile_one;
                    break;
                case 2:
                    mImage = R.drawable.tile_two;
                    break;
                case 3:
                    mImage = R.drawable.tile_three;
                    break;
                case 4:
                    mImage = R.drawable.tile_four;
                    break;
                case 5:
                    mImage = R.drawable.tile_five;
                    break;
                case 6:
                    mImage = R.drawable.tile_six;
                    break;
                case 7:
                    mImage = R.drawable.tile_seven;
                    break;
                case 8:
                    mImage = R.drawable.tile_eight;
                    break;
                default:
                    mImage = R.drawable.tile;
            }
        }
    }
}
