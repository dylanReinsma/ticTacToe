package com.dylanreinsma.ticTacToe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 0 = X's & 1 = O's
    int activePlayer = 0;

    boolean isGameActive = true;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void onClick(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && isGameActive) {

            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.x);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.o);
                activePlayer = 0;
            }

            counter.animate().rotation(3600).setDuration(300);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    String winner;

                    if (activePlayer == 1) {
                        winner = "Player X Wins!";
                    } else {
                        winner = "Player O Wins!";
                    }
                    isGameActive = false;
                    TextView textView = findViewById(R.id.textView);
                    textView.setText(winner);
                    textView.setVisibility(View.VISIBLE);
                    Button button = findViewById(R.id.button);
                    button.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void restartGame(View view) {
        TextView textView = findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);
        Button button = findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        try {
            for (int i = 0; i < gridLayout.getChildCount(); i++) {

                ImageView counter = (ImageView) gridLayout.getChildAt(i);
                counter.setImageDrawable(null);
            }

            for (int i = 0; i < gameState.length; i++) {
                gameState[i] = 2;
            }

            activePlayer = 0;

            isGameActive = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
