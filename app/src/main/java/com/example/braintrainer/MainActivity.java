package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button goBtn;
    Button timeLeftBtn;
    Button scoreBtn;
    Button playAgainBtn;
    Button[] optionBtns = new Button[4];
    TextView questionTextView;
    TextView statusTextView;

    int correctQues, totalQues, correctOption;
    boolean gameGoingOn;

    public void startGame(View view) {

        goBtn.setVisibility(View.INVISIBLE);
        timeLeftBtn.setVisibility(View.VISIBLE);
        scoreBtn.setVisibility(View.VISIBLE);
        optionBtns[0].setVisibility(View.VISIBLE);
        optionBtns[1].setVisibility(View.VISIBLE);
        optionBtns[2].setVisibility(View.VISIBLE);
        optionBtns[3].setVisibility(View.VISIBLE);
        questionTextView.setVisibility(View.VISIBLE);
        statusTextView.setVisibility(View.VISIBLE);
        newGame();
    }

    public void newGame() {
        correctQues=0;
        totalQues=0;
        updateScore();
        statusTextView.setText("");
        playAgainBtn.setVisibility(View.INVISIBLE);

        updateTimeLeft(30);
        gameGoingOn = true;
        new CountDownTimer(30000 + 100, 1000) {
            @Override
            public void onTick(long milliSecondsLeft) {
                updateTimeLeft((int)milliSecondsLeft/1000);
            }

            @Override
            public void onFinish() {
                statusTextView.setText("Done!");
                playAgainBtn.setVisibility(View.VISIBLE);
                updateTimeLeft(0);
                gameGoingOn = false;
            }
        }.start();
        generateNewQues();
        Log.i("main activity", "newGame: out");
    }

    public void checkCorrectness(View view) {
        if(gameGoingOn) {
            Button optionSelected = (Button) view;
            if (optionSelected.getTag().toString().compareTo(Integer.toString(correctOption)) == 0) {
                correctQues++;
                statusTextView.setText("Correct :)");
            } else {
                statusTextView.setText("Wrong :(");
            }
            totalQues++;
            updateScore();
            generateNewQues();
        }
    }

    public void playAgain (View view) {
        newGame();
    }
    public void updateScore() {
        scoreBtn.setText(Integer.toString(correctQues) + "/" + Integer.toString(totalQues));
    }
    public void updateTimeLeft(int timeLeft) {
        timeLeftBtn.setText(Integer.toString(timeLeft) + "s");
    }
    public void generateNewQues(){
        Log.i("main activity", "generateNewQues: in");
        int minVar = 0, maxVar = 19;
        int a = (int) (minVar + Math.random()*(maxVar - minVar + 1));
        int b = (int) (minVar + Math.random()*(maxVar - minVar + 1));

        correctOption = (int) (Math.random()*4 + 1);

        for (int i=0; i<4; i++) {
            if(i+1 == correctOption) {
                optionBtns[i].setText(Integer.toString(a+b));
            } else {
                int wrongAns = (int) (Math.random()*30 + 1);
                while(wrongAns == a+b) {
                    wrongAns = (int) (Math.random()*30 + 1);
                }
                optionBtns[i].setText(Integer.toString(wrongAns));

            }
        }
        questionTextView.setText(Integer.toString(a) +" + " + Integer.toString(b));
    }
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goBtn = findViewById(R.id.goBtn);
        timeLeftBtn = findViewById(R.id.timeLeftBtn);
        scoreBtn = findViewById(R.id.scoreBtn);
        playAgainBtn = findViewById(R.id.playAgainBtn);
        optionBtns[0] = findViewById(R.id.option1Btn);
        optionBtns[1] = findViewById(R.id.option2Btn);
        optionBtns[2] = findViewById(R.id.option3Btn);
        optionBtns[3] = findViewById(R.id.option4Btn);
        questionTextView = findViewById(R.id.questionTextView);
        statusTextView = findViewById(R.id.statusTextView);

        timeLeftBtn.setVisibility(View.INVISIBLE);
        scoreBtn.setVisibility(View.INVISIBLE);
        playAgainBtn.setVisibility(View.INVISIBLE);
        optionBtns[0].setVisibility(View.INVISIBLE);
        optionBtns[1].setVisibility(View.INVISIBLE);
        optionBtns[2].setVisibility(View.INVISIBLE);
        optionBtns[3].setVisibility(View.INVISIBLE);
        questionTextView.setVisibility(View.INVISIBLE);
        statusTextView.setVisibility(View.INVISIBLE);
    }
}