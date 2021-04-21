package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    Button start,a,b,c,d;
    TextView timer,question,score;
    CountDownTimer countDownTimer;
    int op1,op2,ans;
    TextView textView;
    int nm=0,dm=0;
    public void startGame(View view)
    {
        a.setEnabled(true);
        b.setEnabled(true);
        c.setEnabled(true);
        d.setEnabled(true);
        Log.i("msg","Start Preseed");
        start = findViewById(R.id.startButton);
        start.setVisibility(View.INVISIBLE);
        setQuestion();
        setOptions();
        countDownTimer = new CountDownTimer(30000 + 100,1000) {
            @Override
            public void onTick(long millisUntilFinished)
            {
                Log.i("info",String.valueOf(millisUntilFinished/1000));
                timer = findViewById(R.id.timerTextView);
                timer.setText(String.valueOf((int)millisUntilFinished/1000));
                //setQuestion();
            }
            @Override
            public void onFinish()
            {
                start.setText("Play Again");
                start.setVisibility(View.VISIBLE);
                a.setEnabled(false);
                b.setEnabled(false);
                c.setEnabled(false);
                d.setEnabled(false);
                timer.setText("30");
                score.setText("0/0");
                question.setText("Get Ready!");
                textView.setText("Total:"+String.valueOf(nm));

            }
        }.start();
    }
    public  void checkAnswer(View view)
    {
        textView = findViewById(R.id.resultText);
        int op = Integer.parseInt(String.valueOf(view.getTag()));
        if(op == ans)
        {
            //textView = findViewById(R.id.resultText);
            Log.i("msg","Correct");
            textView.setText("Correct!");
            updateScore(1);
        }
        else
        {
            Log.i("msg","wriong");
            textView.setText("Wrong!");
            updateScore(0);
        }
        setQuestion();
        setOptions();
    }
    public void updateScore(int res)
    {
        dm+=1;
        String scores="";
        if(res==0)
        {
            scores=scores+String.valueOf(nm)+"/"+String.valueOf(dm);
        }
        else
        {
            nm+=1;
            scores=scores+String.valueOf(nm)+"/"+String.valueOf(dm);
        }
        score = findViewById(R.id.scoreTextView);
        score.setText(scores);
    }
    public void setQuestion()
    {
        Random rand= new Random();
        op1=rand.nextInt(101);
        op2=rand.nextInt(101);
        question = findViewById(R.id.questionTextView);
        question.setText(String.valueOf(op1)+" + "+String.valueOf(op2)+" = ?");
        ans=op1+op2;
    }
    public  void setOptions()
    {
        Random rand = new Random();
        int i = rand.nextInt(4);
        if(i==0)
        {
            a.setText(String.valueOf(ans));
            b.setText(String.valueOf(rand.nextInt(202)));
            c.setText(String.valueOf(rand.nextInt(202)));
            d.setText(String.valueOf(rand.nextInt(202)));
            ans=0;
        }
        else if(i==1)
        {

            b.setText(String.valueOf(ans));
            a.setText(String.valueOf(rand.nextInt(202)));
            c.setText(String.valueOf(rand.nextInt(202)));
            d.setText(String.valueOf(rand.nextInt(202)));
            ans=1;
        }
        else if(i==2)
        {
            c.setText(String.valueOf(ans));
            b.setText(String.valueOf(rand.nextInt(202)));
            a.setText(String.valueOf(rand.nextInt(202)));
            d.setText(String.valueOf(rand.nextInt(202)));
            ans=2;
        }
        else if(i==3)
        {
            d.setText(String.valueOf(ans));
            b.setText(String.valueOf(rand.nextInt(202)));
            c.setText(String.valueOf(rand.nextInt(202)));
            a.setText(String.valueOf(rand.nextInt(202)));
            ans=3;
        }
        //textView.setText("");

    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = findViewById(R.id.button0);
        b = findViewById(R.id.button1);
        c = findViewById(R.id.button2);
        d = findViewById(R.id.button3);
        a.setEnabled(false);
        b.setEnabled(false);
        c.setEnabled(false);
        d.setEnabled(false);
    }
}
