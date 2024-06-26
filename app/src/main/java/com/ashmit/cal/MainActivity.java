package com.ashmit.cal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ashmit.cal.RecyclerView.RecyclerViewStatAct;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    // ArrayLists to store the win counts for each player and the number of draws
    ArrayList<Integer> arrXwin = new ArrayList<>();
    ArrayList<Integer> arrOwin = new ArrayList<>();
    ArrayList<Integer> arrDraws = new ArrayList<>();

    Intent intent, recyclerViewIntent;
    TextView playerMove, setXWins, setOwins, setDraws;

    // Buttons representing the Tic-Tac-Toe grid
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, statsBtn;

    // String variables to store the text on each button
    String strBtn1, strBtn2, strBtn3, strBtn4, strBtn5, strBtn6, strBtn7, strBtn8, strBtn9;

    // Variables for toggling player turns and counting moves
    int toggle = 0, counter = 0;

    // Variables to keep track of total wins and draws
    int totalDraws = 0, totalXWins = 0, totalOWins = 0, totalMatches = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            // Initialize variables and UI components
            init();

            return insets;
        });
    }

    // Method to handle button clicks for the Tic-Tac-Toe grid
    public void check(View view) {
        counter++;
        Button btnCurrent = (Button) view;

        if (duplicateMoveChecker(btnCurrent)) {
            toggle(btnCurrent);
        } else {
            Toast.makeText(this, "Duplicate Move !!", Toast.LENGTH_SHORT).show();
        }

        // Check for a winning condition after at least 5 moves
        if (counter >= 5) {
            winingConditionCheck();
        }
    }

    @SuppressLint("SetTextI18n")
    void toggle(Button btn) {
        if (toggle == 0) {
            playerMove.setText("O's Turn");
            btn.setText("X");
            toggle = 1;
        } else {
            playerMove.setText("X's Turn");
            btn.setText("O");
            toggle = 0;
        }
    }

    @SuppressLint("SetTextI18n")
    void winingConditionCheck() {
        strBtn1 = btn1.getText().toString();
        strBtn2 = btn2.getText().toString();
        strBtn3 = btn3.getText().toString();
        strBtn4 = btn4.getText().toString();
        strBtn5 = btn5.getText().toString();
        strBtn6 = btn6.getText().toString();
        strBtn7 = btn7.getText().toString();
        strBtn8 = btn8.getText().toString();
        strBtn9 = btn9.getText().toString();

        // Check all winning conditions for Tic-Tac-Toe
        if ((strBtn1.equals(strBtn2) && strBtn2.equals(strBtn3)) && !strBtn1.isEmpty()) {
            playerMove.setText(strBtn2 + " has Won the Game !!");
            winsCounter(strBtn2);
            delayedTxtViewWithReset();
        } else if ((strBtn4.equals(strBtn5) && strBtn5.equals(strBtn6)) && !strBtn4.isEmpty()) {
            playerMove.setText(strBtn4 + " has Won the Game !!");
            winsCounter(strBtn5);
            delayedTxtViewWithReset();
        } else if ((strBtn7.equals(strBtn8) && strBtn8.equals(strBtn9)) && !strBtn7.isEmpty()) {
            playerMove.setText(strBtn8 + " has Won the Game !!");
            winsCounter(strBtn8);
            delayedTxtViewWithReset();
        } else if ((strBtn1.equals(strBtn5) && strBtn5.equals(strBtn7)) && !strBtn1.isEmpty()) {
            playerMove.setText(strBtn5 + " has Won the Game !!");
            winsCounter(strBtn5);
            delayedTxtViewWithReset();
        } else if ((strBtn2.equals(strBtn5) && strBtn5.equals(strBtn8)) && !strBtn2.isEmpty()) {
            playerMove.setText(strBtn2 + " has Won the Game !!");
            winsCounter(strBtn2);
            delayedTxtViewWithReset();
        } else if ((strBtn3.equals(strBtn6) && strBtn6.equals(strBtn9)) && !strBtn3.isEmpty()) {
            playerMove.setText(strBtn3 + " has Won the Game !!");
            winsCounter(strBtn3);
            delayedTxtViewWithReset();
        } else if ((strBtn1.equals(strBtn5) && strBtn5.equals(strBtn9)) && !strBtn1.isEmpty()) {
            playerMove.setText(strBtn1 + " has Won the Game !!");
            winsCounter(strBtn1);
            delayedTxtViewWithReset();
        } else if ((strBtn3.equals(strBtn5) && strBtn5.equals(strBtn7)) && !strBtn3.isEmpty()) {
            playerMove.setText(strBtn5 + " has Won the Game !!");
            winsCounter(strBtn5);
            delayedTxtViewWithReset();
        } else if (counter == 9) {
            playerMove.setText("Match Draw !!");
            totalDraws++;
            arrXwin.add(0);
            arrOwin.add(0);
            arrDraws.add(1);
            reset();
        }
    }

    // Initialize UI components and variables
    void init() {
        recyclerViewIntent = new Intent(MainActivity.this, RecyclerViewStatAct.class);
        intent = new Intent(MainActivity.this, StatActivity.class);
        recyclerView = findViewById(R.id.recyclerView);
        setXWins = findViewById(R.id.xWins);
        setOwins = findViewById(R.id.oWins);
        setDraws = findViewById(R.id.totlDraws);
        playerMove = findViewById(R.id.playersMove);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
    }

    // Check for duplicate moves to prevent overwriting
    boolean duplicateMoveChecker(Button btn) {
        return btn.getText().toString().isEmpty();
    }

    // Delay text update and reset the game
    void delayedTxtViewWithReset() {
        delayedTxtVw();
        reset();
    }

    // Reset button click handler
    public void resetBtn(View view) {
        Toast.makeText(this, "Game Has Been Reset", Toast.LENGTH_SHORT).show();
        delayedTxtVw();
        reset();
    }

    // Reset the game state for a new game
    void reset() {
        setXWins.setText("" + totalXWins);
        setOwins.setText("" + totalOWins);
        setDraws.setText("" + totalDraws);
        toggle = 0;
        counter = 0;
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
    }

    // Update win counters based on the winner
    void winsCounter(String str) {
        if (str.equals("X")) {
            arrXwin.add(1);
            arrOwin.add(0);
            arrDraws.add(0);
            totalXWins++;
        } else {
            arrOwin.add(1);
            arrXwin.add(0);
            arrDraws.add(0);
            totalOWins++;
        }
    }

    // Delay text update to show the winner before resetting
    void delayedTxtVw() {
        Handler handler = new Handler();
        handler.postDelayed(() -> playerMove.setText(R.string.welcome_to_tic_tac_toe_game), 2000);
    }

    // Navigate to the statistics activity
    public void Stat(View view) {
        intent.putExtra("xWinsList", arrXwin);
        intent.putExtra("oWinsList", arrOwin);
        intent.putExtra("drawsList", arrDraws);
        startActivity(intent);
    }

    // Navigate to the RecyclerView statistics activity
    public void RecyclerViewStatAct(View view) {
        recyclerViewIntent.putExtra("xWinsList", arrXwin);
        recyclerViewIntent.putExtra("oWinsList", arrOwin);
        recyclerViewIntent.putExtra("drawsList", arrDraws);
        startActivity(recyclerViewIntent);
    }
}
