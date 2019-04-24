package com.gatech.astroworld.spacetrader.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.gatech.astroworld.spacetrader.R;
import com.gatech.astroworld.spacetrader.model.ticTacToe;

import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class miniGame extends AppCompatActivity {
    private byte choice = 0;
    private ticTacToe TestTicTac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_game);


        final Button[][] buttonMatrix = new Button[3][3];
        TableLayout layout = findViewById(R.id.tictactoeTableLayout);
        AlertDialog.Builder playerChoice = new AlertDialog.Builder(this);
        playerChoice.setCancelable(false);

        playerChoice.setPositiveButton("O", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAlert((byte)2);
            }
        });
        playerChoice.setNegativeButton("X", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAlert((byte)1);
            }
        });
        playerChoice.setMessage("Choose X's or O's.");
        playerChoice.show();
        TableRow.LayoutParams rowParams =
                new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < 3; i++) {
            TableRow tr = new TableRow(this);

            for (int j = 0; j < 3; j++) {
                Button tmpButton = new Button(this);
                buttonMatrix[i][j] = tmpButton;
                final int tempX = i;
                final int tempY = j;
                buttonMatrix[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TestTicTac.addChoice(new Point(tempX, tempY));
                        if (buttonMatrix[tempX][tempY].getText().toString().equals("")) {
                            if (choice == 1) {
                                buttonMatrix[tempX][tempY].setText("X");
                            } else {
                                buttonMatrix[tempX][tempY].setText("O");
                            }
                            randomChoice(buttonMatrix, tempX, tempY);
                        }
                    }
                });
                tr.addView(buttonMatrix[i][j], rowParams);
            }
            layout.addView(tr, rowParams);
        }
    }
    private void showAlert(byte choice){
        this.choice = choice;
        TestTicTac = new ticTacToe(choice);
    }

    private void randomChoice(Button[][] buttons, int prevX, int prevY) {
        String randomChoice;
        String userString;
        if (choice == 1) {
            userString = "x";
            randomChoice = "O";
        } else {
            userString = "o";
            randomChoice = "X";
        }
        boolean picked = false;
        while (!picked) {
            Random rand = new Random();
            int x = rand.nextInt(3);
            int y = rand.nextInt(3);
            if (x != prevX && y != prevY) {
                if (buttons[x][y].getText().toString().toLowerCase().compareTo(userString) != 0) {
                    buttons[x][y].setText(randomChoice);
                    picked = true;
                }
            }
        }
    }
}
