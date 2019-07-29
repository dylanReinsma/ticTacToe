package com.dylanreinsma.ticTacToe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = X's & 1 = O's
    int activePlayer = 0;
    boolean isGameActive = true;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int drawCounter = 0;
    //ImageView counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && isGameActive) {

            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.x);
                activePlayer = 1;
                drawCounter++;

            } else {

                counter.setImageResource(R.drawable.o);
                activePlayer = 0;
                drawCounter++;
            }

            //counter.animate().rotation(360).setDuration(500);

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

                } else {

                    if (drawCounter == 9) {

                            isGameActive = false;
                            TextView textView = findViewById(R.id.textView);
                            textView.setText("Draw!");
                            textView.setVisibility(View.VISIBLE);
                            Button button = findViewById(R.id.button);
                            button.setVisibility(View.VISIBLE);
                        }
                    }

                }
            }
        }

    public void playAgain(View view) {

        isGameActive = true;
        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = 2;
        }

        android.support.v7.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }

        TextView textView = findViewById(R.id.textView);
        textView.setText("");
        Button button = findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);
        drawCounter = 0;
    }
}
