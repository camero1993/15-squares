package com.example.a15squares;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

/*
 * MainActivity is in charge of executing each method defined in GridController
 * In the onCreate method it initializes the button array, and the onClick method
 * checks for an empty space, moves the button, and checks if the player has won.
 * There are helper methods updateSquares and randomize which aid in these processes.
 *
 * @author
 * Magnus Graham
 *Feb 2023
 */

public class MainActivity extends AppCompatActivity {

    private GridController gc;
    private Button[][] buttons;
    private Button randomizeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the board and buttons
        gc = new GridController();
        buttons = new Button[4][4];
        buttons[0][0] = findViewById(R.id.button_00);
        buttons[0][1] = findViewById(R.id.button_01);
        buttons[0][2] = findViewById(R.id.button_02);
        buttons[0][3] = findViewById(R.id.button_03);
        buttons[1][0] = findViewById(R.id.button_10);
        buttons[1][1] = findViewById(R.id.button_11);
        buttons[1][2] = findViewById(R.id.button_12);
        buttons[1][3] = findViewById(R.id.button_13);
        buttons[2][0] = findViewById(R.id.button_20);
        buttons[2][1] = findViewById(R.id.button_21);
        buttons[2][2] = findViewById(R.id.button_22);
        buttons[2][3] = findViewById(R.id.button_23);
        buttons[3][0] = findViewById(R.id.button_30);
        buttons[3][1] = findViewById(R.id.button_31);
        buttons[3][2] = findViewById(R.id.button_32);
        buttons[3][3] = findViewById(R.id.button_33);
        randomizeButton = findViewById(R.id.randomizeButton);

        // Randomize placement of buttons
        randomize();

        // Update the button text with the initial state of the board
        updateSquares();

        // Set OnClickListeners
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                final int row = i;
                final int col = j;
                buttons[i][j].setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //check if the squares adjacent are empty
                        if (gc.checkEmptySpace(row, col)) {
                            gc.shift(row, col, gc.getEmptyRow(), gc.getEmptyCol());
                            updateSquares();
                            if (gc.solved()) {
                                // Turns background color red and displays a congratulary message
                                Toast toast = Toast.makeText(getApplicationContext(), "YAHOO!! YOU WIN!!", Toast.LENGTH_SHORT);

                                View view = toast.getView();
                                view.setBackgroundColor(Color.GREEN);
                                TextView text = view.findViewById(android.R.id.message);
                                text.setTextColor(Color.WHITE);
                                text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 45); // Set the text size to 24sp
                                getWindow().getDecorView().setBackgroundColor(Color.RED);
                                toast.show();

                                /*
                                Shows how to customize Toast in Android
                                https://developer.android.com/guide/topics/ui/notifiers/toasts
                                 */

                            }
                        }
                    }
                });
            }
        }
    }
    public void onClick2(View view) {
        randomize();
    }


    private void updateSquares() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                int value = gc.getSquare(row, col);
                String label;
                if (value == 0) {
                    label = "";
                } else {
                    label = Integer.toString(value);
                }
                buttons[row][col].setText(label);
            }
        }
    }
    // updates all of the buttons on the board


    public void randomize() {

            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            int emptyRow, emptyCol, buttonRow, buttonCol;
            //for 200 iterations, make a random move to shuffle board
            for (int i = 0; i < 200; i++) {
                emptyRow = gc.getEmptyRow();
                emptyCol = gc.getEmptyCol();
                Random rnd = new Random();
                int dir = rnd.nextInt(4);
                buttonRow = emptyRow + directions[dir][0];
                buttonCol = emptyCol + directions[dir][1];

                if (buttonRow >= 0 && buttonRow < 4 && buttonCol >= 0 && buttonCol < 4) {
                    gc.shift(emptyRow, emptyCol, buttonRow, buttonCol);
                }
            }
        updateSquares();
    }
    //shuffles all of the buttons on the board and empty space
}