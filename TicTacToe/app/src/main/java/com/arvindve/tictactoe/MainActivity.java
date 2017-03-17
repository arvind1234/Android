package com.arvindve.tictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.EventLog;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private final String TAG = "TICTACTOE";
    private int[][] board;
    private int currentUser = User.X.getValue();

    private enum GameState {
        Incomplete,
        Draw,
        XWins,
        OWins
    }

    private enum User {
        X(0),
        O(1);

        private final int id;

        User(int id) {
            this.id = id;
        }

        public int getValue() {
            return id;
        }
    }

    private void resetBoard() {
        currentUser = 1;
        board = new int[3][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = -1;
            }
        }
    }

    private void resetButtons() {
        Button b;

        b = ((Button)findViewById(R.id.button1));
        b.setBackgroundColor(Color.WHITE);
        b.setTextColor(Color.BLUE);
        b.setText("-");

        b = ((Button)findViewById(R.id.button2));
        b.setBackgroundColor(Color.WHITE);
        b.setTextColor(Color.BLUE);
        b.setText("-");

        b = ((Button)findViewById(R.id.button3));
        b.setBackgroundColor(Color.WHITE);
        b.setTextColor(Color.BLUE);
        b.setText("-");

        b = ((Button)findViewById(R.id.button4));
        b.setBackgroundColor(Color.WHITE);
        b.setTextColor(Color.BLUE);
        b.setText("-");

        b = ((Button)findViewById(R.id.button5));
        b.setBackgroundColor(Color.WHITE);
        b.setTextColor(Color.BLUE);
        b.setText("-");

        b = ((Button)findViewById(R.id.button6));
        b.setBackgroundColor(Color.WHITE);
        b.setTextColor(Color.BLUE);
        b.setText("-");

        b = ((Button)findViewById(R.id.button7));
        b.setBackgroundColor(Color.WHITE);
        b.setTextColor(Color.BLUE);
        b.setText("-");

        b = ((Button)findViewById(R.id.button8));
        b.setBackgroundColor(Color.WHITE);
        b.setTextColor(Color.BLUE);
        b.setText("-");

        b = ((Button)findViewById(R.id.button9));
        b.setBackgroundColor(Color.WHITE);
        b.setTextColor(Color.BLUE);
        b.setText("-");
    }

    private boolean contains(int[] list, int n) {
        for (int item : list) {
            if (item == n) {
                return true;
            }
        }
        return false;
    }

    private GameState getGameState() {
        if (board[0][0] != -1 && (board[0][0] == board[0][1] && board[0][1] == board[0][2])) {
            return board[0][0] == User.X.getValue() ? GameState.XWins : GameState.OWins;

        }
        if (board[1][0] != -1 && (board[1][0] == board[1][1] && board[1][1] == board[1][2])) {
            return board[1][0] == User.X.getValue() ? GameState.XWins : GameState.OWins;
        }
        if (board[2][0] != -1 && (board[2][0] == board[2][1] && board[2][1] == board[2][2])) {
            return board[2][0] == User.X.getValue() ? GameState.XWins : GameState.OWins;
        }

        if (board[0][0] != -1 && (board[0][0] == board[1][0] && board[1][0] == board[2][0])) {
            return board[0][0] == User.X.getValue() ? GameState.XWins : GameState.OWins;

        }
        if (board[0][1] != -1 && (board[0][1] == board[1][1] && board[1][1] == board[2][1])) {
            return board[0][1] == User.X.getValue() ? GameState.XWins : GameState.OWins;
        }
        if (board[0][2] != -1 && (board[0][2] == board[1][2] && board[1][2] == board[2][2])) {
            return board[0][2] == User.X.getValue() ? GameState.XWins : GameState.OWins;
        }

        if (board[0][0] != -1 && (board[0][0] == board[1][1] && board[1][1] == board[2][2])) {
            return board[0][0] == User.X.getValue() ? GameState.XWins : GameState.OWins;
        }

        if (board[0][2] != -1 && (board[0][2] == board[1][1] && board[1][1] == board[2][0])) {
            return board[0][2] == User.X.getValue() ? GameState.XWins : GameState.OWins;
        }

        if (contains(board[0], -1) || contains(board[1], -1) || contains(board[2], -1)) {
            return GameState.Incomplete;
        }

        return GameState.Draw;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resetBoard();
        resetButtons();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void showAlert(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        resetBoard();
                        resetButtons();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

    public void onClick(View v) {
        int x, y;
        Button b = (Button)v;
        String[] buttonNumber = v.getTag().toString().split(",");
        x = Integer.parseInt(buttonNumber[0]);
        y = Integer.parseInt(buttonNumber[1]);

        Log.d(TAG, String.format("Button %d,%d clicked", x, y));

        if (board[x][y] == -1) {
            board[x][y] = currentUser;
            b.setText(currentUser == User.X.getValue() ? "X" : "O");
            b.setTextColor(currentUser == User.X.getValue() ? Color.RED : Color.BLUE);
            GameState gameState = getGameState();
            switch (gameState) {
                case Draw:
                    showAlert("Draw!", "Good game");
                    break;
                case XWins:
                    showAlert("X Wins!", "Good job X!");
                    break;
                case OWins:
                    showAlert("O Wins!", "Good job O!");
                    break;
                default:
                    Log.d(TAG, "Game goes on");
            }
            currentUser ^= 1;
        } else {
            Log.d(TAG, "Square taken, pick another one");
        }



    }
}
