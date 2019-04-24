package com.example.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btns.add(R.id.BT_5);
        btns.add(R.id.BT_1);
        btns.add(R.id.BT_3);
        btns.add(R.id.BT_7);
        btns.add(R.id.BT_9);
        btns.add(R.id.BT_2);
        btns.add(R.id.BT_4);
        btns.add(R.id.BT_6);
        btns.add(R.id.BT_8);

    }

    ArrayList<Integer> btns = new ArrayList<>();
    boolean isEnd = false;
    
    public void onTileClick(View v) {
        if(isEnd) return;

        Button btn = (Button)v;
        if(!btn.getText().equals(".")) return;
        btn.setText("X");
        btn.setTextColor(Color.RED);

        moveComputer();
        checkIfEnd();
    }

    public void onNewGameClick(View v) {
        for (Integer id : btns) {
            Button btn = findViewById(id);
            btn.setText(".");
            btn.setTextColor(Color.BLACK);
        }
        ((EditText)findViewById(R.id.ET_WIN)).setText("");
        isEnd = false;
    }

    public void onEndClick(View v) { finish(); }

    private void checkIfEnd() {
        if(playerWon()) {
            isEnd = true;
            ((EditText)findViewById(R.id.ET_WIN)).setText("YOU WON!");
        }
        else if (computerWon()) {
            isEnd = true;
            ((EditText)findViewById(R.id.ET_WIN)).setText("COMPUTER WON!");
        }
        if(!isEnd){
            for (Integer id : btns) {
                if(((Button)findViewById(id)).getText().equals(".")) {
                    return;
                }
            }
            isEnd = true;
            ((EditText)findViewById(R.id.ET_WIN)).setText("DRAW!");
        }
    }

    private boolean playerWon() {
        boolean playerWon;
        playerWon = playerWon(R.id.BT_1, R.id.BT_2, R.id.BT_3);
        if(playerWon) return playerWon;
        playerWon = playerWon(R.id.BT_4, R.id.BT_5, R.id.BT_6);
        if(playerWon) return playerWon;
        playerWon = playerWon(R.id.BT_7, R.id.BT_8, R.id.BT_9);
        if(playerWon) return playerWon;

        playerWon = playerWon(R.id.BT_1, R.id.BT_4, R.id.BT_7);
        if(playerWon) return playerWon;
        playerWon = playerWon(R.id.BT_2, R.id.BT_5, R.id.BT_8);
        if(playerWon) return playerWon;
        playerWon = playerWon(R.id.BT_3, R.id.BT_6, R.id.BT_9);
        if(playerWon) return playerWon;


        playerWon = playerWon(R.id.BT_1, R.id.BT_5, R.id.BT_9);
        if(playerWon) return playerWon;
        playerWon = playerWon(R.id.BT_3, R.id.BT_5, R.id.BT_7);
        if(playerWon) return playerWon;
        return false;
    }

    private boolean computerWon() {
        boolean computerWon;
        computerWon = computerWon(R.id.BT_1, R.id.BT_2, R.id.BT_3);
        if(computerWon) return computerWon;
        computerWon = computerWon(R.id.BT_4, R.id.BT_5, R.id.BT_6);
        if(computerWon) return computerWon;
        computerWon = computerWon(R.id.BT_7, R.id.BT_8, R.id.BT_9);
        if(computerWon) return computerWon;

        computerWon = computerWon(R.id.BT_1, R.id.BT_4, R.id.BT_7);
        if(computerWon) return computerWon;
        computerWon = computerWon(R.id.BT_2, R.id.BT_5, R.id.BT_8);
        if(computerWon) return computerWon;
        computerWon = computerWon(R.id.BT_3, R.id.BT_6, R.id.BT_9);
        if(computerWon) return computerWon;


        computerWon = computerWon(R.id.BT_1, R.id.BT_5, R.id.BT_9);
        if(computerWon) return computerWon;
        computerWon = computerWon(R.id.BT_3, R.id.BT_5, R.id.BT_7);
        if(computerWon) return computerWon;
        return false;
    }

    private void moveComputer() {
        if(isEnd) return;

        Integer nextMove = findTileToWin();
        if(nextMove != null) {
            markComputerTile(nextMove);
            return;
        }

        nextMove = findTileToDefend();
        if(nextMove != null) {
            markComputerTile(nextMove);
            return;
        }

        for (Integer id : btns) {
            Button btn = findViewById(id);

            if(btn.getText().equals(".")) {
                markComputerTile(id);
                return;
            }
        }
    }

    private Integer findTileToWin() {
        Integer posibleMove;

        posibleMove = checkComputerTriplet(R.id.BT_1, R.id.BT_2, R.id.BT_3);
        if(posibleMove != null) return posibleMove;
        posibleMove = checkComputerTriplet(R.id.BT_4, R.id.BT_5, R.id.BT_6);
        if(posibleMove != null) return posibleMove;
        posibleMove = checkComputerTriplet(R.id.BT_7, R.id.BT_8, R.id.BT_9);
        if(posibleMove != null) return posibleMove;

        posibleMove = checkComputerTriplet(R.id.BT_1, R.id.BT_4, R.id.BT_7);
        if(posibleMove != null) return posibleMove;
        posibleMove = checkComputerTriplet(R.id.BT_2, R.id.BT_5, R.id.BT_8);
        if(posibleMove != null) return posibleMove;
        posibleMove = checkComputerTriplet(R.id.BT_3, R.id.BT_6, R.id.BT_9);
        if(posibleMove != null) return posibleMove;


        posibleMove = checkComputerTriplet(R.id.BT_1, R.id.BT_5, R.id.BT_9);
        if(posibleMove != null) return posibleMove;
        posibleMove = checkComputerTriplet(R.id.BT_3, R.id.BT_5, R.id.BT_7);
        if(posibleMove != null) return posibleMove;

        return null;
    }

    private Integer findTileToDefend() {
        Integer posibleMove;

        posibleMove = checkUserTriplet(R.id.BT_1, R.id.BT_2, R.id.BT_3);
        if(posibleMove != null) return posibleMove;
        posibleMove = checkUserTriplet(R.id.BT_4, R.id.BT_5, R.id.BT_6);
        if(posibleMove != null) return posibleMove;
        posibleMove = checkUserTriplet(R.id.BT_7, R.id.BT_8, R.id.BT_9);
        if(posibleMove != null) return posibleMove;

        posibleMove = checkUserTriplet(R.id.BT_1, R.id.BT_4, R.id.BT_7);
        if(posibleMove != null) return posibleMove;
        posibleMove = checkUserTriplet(R.id.BT_2, R.id.BT_5, R.id.BT_8);
        if(posibleMove != null) return posibleMove;
        posibleMove = checkUserTriplet(R.id.BT_3, R.id.BT_6, R.id.BT_9);
        if(posibleMove != null) return posibleMove;


        posibleMove = checkUserTriplet(R.id.BT_1, R.id.BT_5, R.id.BT_9);
        if(posibleMove != null) return posibleMove;
        posibleMove = checkUserTriplet(R.id.BT_3, R.id.BT_5, R.id.BT_7);
        if(posibleMove != null) return posibleMove;

        return null;
    }

    private Integer checkComputerTriplet(int firstId, int secondId, int thirdId) {
        if(isEmpty(firstId) && isComputer(secondId) && isComputer(thirdId)) { return firstId; }
        if(isEmpty(secondId) && isComputer(firstId) && isComputer(thirdId)) { return secondId; }
        if(isEmpty(thirdId) && isComputer(secondId) && isComputer(firstId)) { return thirdId; }
        return null;
    }

    private boolean computerWon(int firstId, int secondId, int thirdId) {
        return isComputer(firstId) && isComputer(secondId) && isComputer(thirdId);
    }

    private boolean playerWon(int firstId, int secondId, int thirdId) {
        return isPlayer(firstId) && isPlayer(secondId) && isPlayer(thirdId);
    }

    private Integer checkUserTriplet(int firstId, int secondId, int thirdId) {
        if(isEmpty(firstId) && isPlayer(secondId) && isPlayer(thirdId)) { return firstId; }
        if(isEmpty(secondId) && isPlayer(firstId) && isPlayer(thirdId)) { return secondId; }
        if(isEmpty(thirdId) && isPlayer(secondId) && isPlayer(firstId)) { return thirdId; }
        return null;
    }


    private void markComputerTile(int id) {
        Button btn = findViewById(id);
        btn.setText("O");
        btn.setTextColor(Color.BLUE);
    }

    private boolean isEmpty(int id) {
        return isEmpty((Button)findViewById(id));
    }

    private boolean isEmpty(Button btn) {
        return btn.getText().equals(".");
    }

    private boolean isComputer(int id) {
        return isComputer((Button)findViewById(id));
    }

    private boolean isComputer(Button btn) {
        return btn.getText().equals("O");
    }

    private boolean isPlayer(int id) {
        return isPlayer((Button)findViewById(id));
    }

    private boolean isPlayer(Button btn) {
        return btn.getText().equals("X");
    }
}
