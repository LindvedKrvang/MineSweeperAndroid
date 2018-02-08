package com.krvang.lindved.minesweeper;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private LinearLayout mGameBoard;

    private int mRow, mCols;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRow = 10;
        mCols = 10;

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tile tile = (Tile)v;
                tile.showPosition();
                tile.switchImage();
            }
        };
        instantiateBoard(ocl);
    }

    private void instantiateBoard(View.OnClickListener ocl){
        mGameBoard = findViewById(R.id.llGameBoard);
        mGameBoard.removeAllViews();
        for(int i = 0; i < mRow; i++){
            LinearLayout layoutRow = new LinearLayout(this);
            mGameBoard.addView(layoutRow);
            layoutRow.setOrientation(LinearLayout.HORIZONTAL);
            for(int j = 0; j < mCols; j++){
                Tile tile = new Tile(this, i, j);
                layoutRow.addView(tile);
                tile.setOnClickListener(ocl);
            }
        }
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
            Toast.makeText(super.getContext(), "(" + mRow + ":" + mCol + ")", Toast.LENGTH_SHORT).show();
        }

        public void switchImage(){
            mImage = mImage == R.drawable.tile ? R.drawable.tile_flag : R.drawable.tile;
            setImageResource(mImage);
        }
    }
}
