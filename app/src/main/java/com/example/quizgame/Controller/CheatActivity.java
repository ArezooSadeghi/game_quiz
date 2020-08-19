package com.example.quizgame.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizgame.R;

public class CheatActivity extends AppCompatActivity {

    private Button mButtonYesCheat, mButtonNoCheat;

    private TextView mTextViewAnswerOfQuestion;

    private boolean mAnswer;

    public static final String EXTRA_PLAYER_CHEATED = "Extra_Player_Cheated";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        setTitle("Cheat");

        Intent intent = getIntent();
        mAnswer = intent.getBooleanExtra(GeoQuizActivity.EXTRA_ANSWER_OF_QUESTION, false);

        findViews();

        setListeners();
    }

     public void findViews() {
        mButtonYesCheat = findViewById(R.id.btn_yescheat);
        mButtonNoCheat = findViewById(R.id.btn_nocheat);
        mTextViewAnswerOfQuestion = findViewById(R.id.txt_answerofquestion);
     }

     public void setListeners() {
        mButtonYesCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswer) {
                    mTextViewAnswerOfQuestion.setText("درست");
                }else {
                    mTextViewAnswerOfQuestion.setText("نادرست");
                }

                Intent intent = new Intent(CheatActivity.this, GeoQuizActivity.class);
                intent.putExtra(EXTRA_PLAYER_CHEATED, true);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        mButtonNoCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo
            }
        });
     }
}