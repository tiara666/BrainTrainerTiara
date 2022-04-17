package com.example.braintrainertiara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //inisialisasi variabel
    TextView resultTextView, pointTextView, sumTextView, timerTextView;
    Button startButton, button1,button2,button3,button4, playAgainButton;
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    RelativeLayout gameRelativeLayout;

    ArrayList<Integer> answer = new ArrayList<Integer>();

    //tombol start
    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);

        playAgain(findViewById(R.id.playAgainButton));

    }

    //tombol play again
    public void playAgain(View view){

        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        pointTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();

        //timer pengerjaan soal
        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long l) {

                timerTextView.setText(String.valueOf(l/1000)+"s");

            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score" + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

            }
        }.start();

    }

    //generate pertanyaan random
    public void generateQuestion(){

        Random rand = new Random();


        int a = rand.nextInt(21); //angka 1
        int b = rand.nextInt(21); //angka 2

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answer.clear();

        for (int i=0; i<4; i++) {

            if (i == locationOfCorrectAnswer){

                answer.add(a+b);

            } else {

                int incorrectAnswer = rand.nextInt(41);

                while(incorrectAnswer == a+b){

                    incorrectAnswer = rand.nextInt(41);

                }

                answer.add(incorrectAnswer);
            }
        }
        //tag
        button1.setText(Integer.toString(answer.get(0)));
        button2.setText(Integer.toString(answer.get(1)));
        button3.setText(Integer.toString(answer.get(2)));
        button4.setText(Integer.toString(answer.get(3)));


    }

    //tombol aksi klik jawaban yang dipilih
    public  void chooseAnswer(View view){

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){

            score++;
            resultTextView.setText("Correct!");

        } else {

            resultTextView.setText("Wrong!");
        }

        numberOfQuestions++;
        pointTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointTextView = (TextView) findViewById(R.id.pointTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);

    }
}