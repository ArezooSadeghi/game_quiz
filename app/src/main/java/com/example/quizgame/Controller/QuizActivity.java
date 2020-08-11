package com.example.quizgame.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizgame.R;
import com.example.quizgame.model.Question;

public class QuizActivity extends AppCompatActivity {

    private ImageButton mImageButtonTrue, mImageButtonFalse, mImageButtonNext, mImageButtonPrev,
            mImageButtonFirst, mImageButtonLast, mImageButtonReset;

    private TextView mTextViewQuestion, mTextViewScore, mTextViewFinalScore;

    private LinearLayout mLayoutScore, mLayoutMiddle, mLayoutLast;

    private int mCurrentIndex, mNumOfAnswered, mScore = 0;

    private static final String M_CURRENT_INDEX = "mCurrentIndex";
    private static final String M_SCORE = "mScore";
    private static final String M_NUM_OF_ANSWERED = "mNumOfAnswered";
    private static final String M_QUESTIONS_BANK = "mQuestionsBank";

    Question[] mQuestionsBank = {new Question(R.string.question_tehran, true),
            new Question(R.string.question_africa, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_americans, false),
            new Question(R.string.question_asia, false),
            new Question(R.string.question_australia, false),
            new Question(R.string.question_middleast, false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz);

        findViews();

        setListeners();

        mTextViewQuestion.setText(mQuestionsBank[mCurrentIndex].getResourceId());
        mTextViewScore.setText(mScore + " - " + mQuestionsBank.length);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(M_CURRENT_INDEX, mCurrentIndex);
        outState.putInt(M_SCORE, mScore);
        outState.putInt(M_NUM_OF_ANSWERED, mNumOfAnswered);
        outState.putSerializable(M_QUESTIONS_BANK, mQuestionsBank);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCurrentIndex = savedInstanceState.getInt(M_CURRENT_INDEX, 0);
        mTextViewQuestion.setText(mQuestionsBank[mCurrentIndex].getResourceId());
        mScore = savedInstanceState.getInt(M_SCORE, 0);
        mTextViewScore.setText(mScore + " - " + mQuestionsBank.length);
        mNumOfAnswered = savedInstanceState.getInt(M_NUM_OF_ANSWERED, 0);
        mQuestionsBank = (Question[]) savedInstanceState.getSerializable(M_QUESTIONS_BANK);
    }

    private void setListeners() {
        mImageButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkedAnswer(true);
            }
        });

        mImageButtonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkedAnswer(false);
            }
        });

        mImageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1 + mQuestionsBank.length) % mQuestionsBank.length;
                mTextViewQuestion.setText(mQuestionsBank[mCurrentIndex].getResourceId());
                mTextViewQuestion.setTextColor(Color.BLACK);
                if (mQuestionsBank[mCurrentIndex].isDisable() == false) {
                    mImageButtonTrue.setEnabled(true);
                    mImageButtonFalse.setEnabled(true);
                } else {
                    mImageButtonTrue.setEnabled(false);
                    mImageButtonFalse.setEnabled(false);
                }
            }
        });

        mImageButtonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex - 1 + mQuestionsBank.length) % mQuestionsBank.length;
                mTextViewQuestion.setText(mQuestionsBank[mCurrentIndex].getResourceId());
                mTextViewQuestion.setTextColor(Color.BLACK);
                if (mQuestionsBank[mCurrentIndex].isDisable() == false) {
                    mImageButtonTrue.setEnabled(true);
                    mImageButtonFalse.setEnabled(true);
                } else {
                    mImageButtonTrue.setEnabled(false);
                    mImageButtonFalse.setEnabled(false);
                }
            }
        });

        mImageButtonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = 0;
                mTextViewQuestion.setText(mQuestionsBank[mCurrentIndex].getResourceId());
                mTextViewQuestion.setTextColor(Color.BLACK);
                if (mQuestionsBank[mCurrentIndex].isDisable() == false) {
                    mImageButtonTrue.setEnabled(true);
                    mImageButtonFalse.setEnabled(true);
                } else {
                    mImageButtonTrue.setEnabled(false);
                    mImageButtonFalse.setEnabled(false);
                }
            }
        });

        mImageButtonLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = mQuestionsBank.length - 1;
                mTextViewQuestion.setText(mQuestionsBank[mCurrentIndex].getResourceId());
                mTextViewQuestion.setTextColor(Color.BLACK);
                if (mQuestionsBank[mCurrentIndex].isDisable() == false) {
                    mImageButtonTrue.setEnabled(true);
                    mImageButtonFalse.setEnabled(true);
                } else {
                    mImageButtonTrue.setEnabled(false);
                    mImageButtonFalse.setEnabled(false);
                }
            }
        });

        mImageButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayoutScore.setVisibility(View.GONE);
                mLayoutMiddle.setVisibility(View.VISIBLE);
                mLayoutLast.setVisibility(View.VISIBLE);
                mTextViewScore.setVisibility(View.VISIBLE);
                mImageButtonTrue.setEnabled(true);
                mImageButtonFalse.setEnabled(true);
                mScore = 0;
                mNumOfAnswered = 0;
                mCurrentIndex = 0;
                mTextViewScore.setText(mScore + " - " + mQuestionsBank.length);
                mTextViewQuestion.setText(mQuestionsBank[mCurrentIndex].getResourceId());
                mTextViewQuestion.setTextColor(Color.BLACK);
                for (int i = 0; i < mQuestionsBank.length; i++) {
                    mQuestionsBank[i].setDisable(false);
                }
            }
        });
    }

    private void checkedAnswer(boolean userPressed) {
        mNumOfAnswered++;
        mQuestionsBank[mCurrentIndex].setDisable(true);
        mImageButtonTrue.setEnabled(false);
        mImageButtonFalse.setEnabled(false);
        if (mQuestionsBank[mCurrentIndex].isTrueAnswer() == userPressed) {
            mTextViewQuestion.setTextColor(Color.GREEN);
            mScore++;
            mTextViewScore.setText(mScore + " - " + mQuestionsBank.length);
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toast_layout));
            Toast toast = new Toast(this);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            TextView textView = layout.findViewById(R.id.txt_toast);
            textView.setText(R.string.toast_true);
            textView.setTextColor(Color.GREEN);
            ImageView imageView = layout.findViewById(R.id.img_toast);
            imageView.setImageResource(R.drawable.ic_checked);
            toast.setView(layout);
            toast.show();
        } else {
            mTextViewQuestion.setTextColor(Color.RED);
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toast_layout));
            Toast toast = new Toast(this);
            toast.setGravity(Gravity.TOP, 0, 75);
            toast.setDuration(Toast.LENGTH_SHORT);
            TextView textView = layout.findViewById(R.id.txt_toast);
            textView.setText(R.string.toast_false);
            textView.setTextColor(Color.RED);
            ImageView imageView = layout.findViewById(R.id.img_toast);
            imageView.setImageResource(R.drawable.ic_unchecked);
            toast.setView(layout);
            toast.show();

        }
        if (mNumOfAnswered == mQuestionsBank.length) {
            mLayoutScore.setVisibility(View.VISIBLE);
            mLayoutMiddle.setVisibility(View.GONE);
            mLayoutLast.setVisibility(View.GONE);
            mTextViewScore.setVisibility(View.GONE);
            mTextViewFinalScore.setText("امتیاز کسب شده شما در این بازی : " + mScore);
        }
    }

    private void findViews() {
        mImageButtonTrue = findViewById(R.id.btn_true);
        mImageButtonFalse = findViewById(R.id.btn_false);
        mImageButtonNext = findViewById(R.id.btn_next);
        mImageButtonPrev = findViewById(R.id.btn_prev);
        mImageButtonFirst = findViewById(R.id.btn_first);
        mImageButtonLast = findViewById(R.id.btn_last);
        mImageButtonReset = findViewById(R.id.btn_reset);
        mTextViewQuestion = findViewById(R.id.txt_question);
        mTextViewScore = findViewById(R.id.txt_score);
        mTextViewFinalScore = findViewById(R.id.txt_finalscore);
        mLayoutScore = findViewById(R.id.layout_score);
        mLayoutMiddle = findViewById(R.id.layout_middle);
        mLayoutLast = findViewById(R.id.layout_last);
    }
}