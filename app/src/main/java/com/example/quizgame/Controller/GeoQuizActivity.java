package com.example.quizgame.Controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizgame.R;
import com.example.quizgame.SettingActivity;
import com.example.quizgame.model.Question;

public class GeoQuizActivity extends AppCompatActivity {

    private ImageButton mImageButtonTrue, mImageButtonFalse, mImageButtonNext, mImageButtonPrev,
            mImageButtonFirst, mImageButtonLast, mImageButtonReset, mImageButtonSetting;

    private Button mButtonCheat;

    private TextView mTextViewQuestion, mTextViewScore, mTextViewFinalScore;

    private LinearLayout mLayoutScore, mLayoutMiddle, mLayoutLast;

    private FrameLayout mLayoutParent;

    private int mCurrentIndex, mNumOfAnswered, mScore = 0;

    private boolean mIsCheated;

    private String mSizeOfText, mBackground;

    private static final String M_SIZE_OF_TEXTQUESTION = "M_SIZE_OF_TEXTQUESTION";
    private static final String M_CURRENT_INDEX = "mCurrentIndex";
    private static final String M_SCORE = "mScore";
    private static final String M_NUM_OF_ANSWERED = "mNumOfAnswered";
    private static final String M_QUESTIONS_BANK = "mQuestionsBank";
    public static final String EXTRA_ANSWER_OF_QUESTION = "EXTRA_ANSWER_OF_QUESTION";
    public static final String M_BACKGROUND_COLOR = "M_BACKGROUND_COLOR";
    public static final int REQUIST_CODE_CHEAT_ACTIVITY = 0;
    public static final int REQUIST_CODE_SETTING_ACTIVITY = 1;

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

        setContentView(R.layout.activity_geoquiz);

        setTitle("GeoQuiz");

        findViews();

        setListeners();

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(M_CURRENT_INDEX, 0);
            mNumOfAnswered = savedInstanceState.getInt(M_NUM_OF_ANSWERED, 0);
            mScore = savedInstanceState.getInt(M_SCORE, 0);
            mQuestionsBank = (Question[]) savedInstanceState.getSerializable(M_QUESTIONS_BANK);

            if (mNumOfAnswered == mQuestionsBank.length) {
                finishGame();
            }

            float textSize = savedInstanceState.getFloat(M_SIZE_OF_TEXTQUESTION);
            mTextViewQuestion.setTextSize(textSize);

            int backgroundColor = savedInstanceState.getInt(M_BACKGROUND_COLOR);
            mLayoutParent.setBackgroundColor(backgroundColor);
        }

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
        outState.putFloat(M_SIZE_OF_TEXTQUESTION, mTextViewQuestion.getTextSize());
        ColorDrawable viewColor = (ColorDrawable) mLayoutParent.getBackground();
        int colorId = viewColor.getColor();
        outState.putInt(M_BACKGROUND_COLOR, colorId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUIST_CODE_CHEAT_ACTIVITY) {
                mIsCheated = data.getBooleanExtra(CheatActivity.EXTRA_PLAYER_CHEATED, false);
                mQuestionsBank[mCurrentIndex].setCheatedPlayer(mIsCheated);
            }
        }
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUIST_CODE_SETTING_ACTIVITY) {
                /*mSizeOfText = data.getStringExtra(SettingActivity.EXTRA_SIZE_OF_TEXT);
                if (mSizeOfText.equals("کوچک")) {
                    mTextViewQuestion.setTextSize(14);
                } else if (mSizeOfText.equals("متوسط")) {
                    mTextViewQuestion.setTextSize(18);
                } else {
                    mTextViewQuestion.setTextSize(26);
                }*/
                mBackground = data.getStringExtra(SettingActivity.EXTRA_BACKGROUND);
                if (mBackground.equals("سفید")) {
                    mLayoutParent.setBackgroundColor(Color.parseColor("#ffffff"));
                } else if (mBackground.equals("قرمز روشن")) {
                    mLayoutParent.setBackgroundColor(Color.parseColor("#FF0000"));
                } else if (mBackground.equals("آبی روشن")) {
                    mLayoutParent.setBackgroundColor(Color.parseColor("#add8e6"));
                } else {
                    mLayoutParent.setBackgroundColor(Color.parseColor("#32CD32"));
                }

            }

        }
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
                mButtonCheat.setVisibility(View.VISIBLE);
                mImageButtonSetting.setVisibility(View.VISIBLE);
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

        mButtonCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GeoQuizActivity.this, CheatActivity.class);
                intent.putExtra(EXTRA_ANSWER_OF_QUESTION, mQuestionsBank[mCurrentIndex].isTrueAnswer());
                startActivityForResult(intent, REQUIST_CODE_CHEAT_ACTIVITY);
            }
        });

        mImageButtonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GeoQuizActivity.this, SettingActivity.class);
                startActivityForResult(intent, REQUIST_CODE_SETTING_ACTIVITY);
            }
        });
    }

    private void checkedAnswer(boolean userPressed) {
        if (mQuestionsBank[mCurrentIndex].isDisable()) {
            mImageButtonTrue.setEnabled(false);
            mImageButtonFalse.setEnabled(false);
        } else {
            mNumOfAnswered++;
            mQuestionsBank[mCurrentIndex].setDisable(true);
            mImageButtonTrue.setEnabled(true);
            mImageButtonFalse.setEnabled(true);
            if (mQuestionsBank[mCurrentIndex].isTrueAnswer() == userPressed) {
                mTextViewQuestion.setTextColor(Color.GREEN);
                mScore++;
                mTextViewScore.setText(mScore + " - " + mQuestionsBank.length);
                if (mQuestionsBank[mCurrentIndex].isCheatedPlayer()) {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toast_layout));
                    Toast toast = new Toast(this);
                    toast.setGravity(Gravity.BOTTOM, 0, 75);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    TextView textView = layout.findViewById(R.id.txt_toast);
                    textView.setText("تقلب کار خوبی نیست!!!");
                    toast.setView(layout);
                    toast.show();
                } else {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toast_layout));
                    Toast toast = new Toast(this);
                    toast.setGravity(Gravity.BOTTOM, 0, 75);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    TextView textView = layout.findViewById(R.id.txt_toast);
                    textView.setText(R.string.toast_true);
                    textView.setTextColor(Color.GREEN);
                    ImageView imageView = layout.findViewById(R.id.img_toast);
                    imageView.setImageResource(R.drawable.ic_checked);
                    toast.setView(layout);
                    toast.show();
                }
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
            finishGame();
        }
    }

    private void finishGame() {
        if (mNumOfAnswered == mQuestionsBank.length) {
            mLayoutScore.setVisibility(View.VISIBLE);
            mLayoutMiddle.setVisibility(View.GONE);
            mLayoutLast.setVisibility(View.GONE);
            mTextViewScore.setVisibility(View.GONE);
            mButtonCheat.setVisibility(View.GONE);
            mImageButtonSetting.setVisibility(View.GONE);
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
        mImageButtonSetting = findViewById(R.id.btn_setting);
        mButtonCheat = findViewById(R.id.btn_cheat);
        mTextViewQuestion = findViewById(R.id.txt_question);
        mTextViewScore = findViewById(R.id.txt_score);
        mTextViewFinalScore = findViewById(R.id.txt_finalscore);
        mLayoutParent = findViewById(R.id.layout_parent);
        mLayoutScore = findViewById(R.id.layout_score);
        mLayoutMiddle = findViewById(R.id.layout_middle);
        mLayoutLast = findViewById(R.id.layout_last);
    }
}