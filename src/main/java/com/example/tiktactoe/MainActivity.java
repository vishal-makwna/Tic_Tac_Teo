package com.example.tiktactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    //player
    // 0 = X;
    // 1 = O;
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    //state meaning
    // 0 = X
    // 1 = O
    // 2 = null
    int[][] winPositions ={ {0,1,2},{3,4,5},{6,7,8},
                            {0,3,6},{1,4,7},{2,5,8},
                            {0,4,8},{2,4,6}};

    public  void  PlayerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (!gameActive){
            gameReset(view);
        }

        if (gameState[tappedImage] == 2){
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0){
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView Status = findViewById(R.id.Status);
                Status.setText("O's Turn- Tap to Play");
            }else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView Status = findViewById(R.id.Status);
                Status.setText("X's Turn- Tap to Play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }

        //check who is winner
        for (int [] winPosition: winPositions){
                if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {

                    //who won find out
                    String winnerStr = "No body won";
                    gameActive = false;
                    if (gameState[winPosition[0]] == 0){
                        winnerStr = "X has won!!";
                    }else{
                        winnerStr = "O has won!!";
                    }
                    TextView Status = findViewById(R.id.Status);
                    Status.setText(winnerStr);
                }
        }

    }

    //reset a game
    public  void gameReset (View view)
    {
        gameActive = true;
        activePlayer = 0;
        for (int i =0 ; i<gameState.length;i++){
            gameState[i] = 2;
        }

        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView Status = findViewById(R.id.Status);
        Status.setText("X's Turn - Tap to Play");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}