package com.example.quizgame;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quizgame.Controller.fragment.QuizListFragment;
import com.example.quizgame.model.Question;
import com.example.quizgame.repository.QuestionRepository;

import java.util.UUID;

public class DetailQuestionFragment extends Fragment {
    private TextView mTextViewQuestion;
    private CheckBox mCheckBoxAnswer, mCheckBoxCheat;

    private Question mQuestion;
    private QuestionRepository mRepository;

    public DetailQuestionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = QuestionRepository.getInstance();

        UUID id = (UUID) getActivity().getIntent().getSerializableExtra(QuizListFragment.EXTRA_QUESTION_ID);

        QuestionRepository questionRepository = QuestionRepository.getInstance();
        mQuestion = questionRepository.readQuestion(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_question, container, false);
        findViews(view);
        initViews();
        setListeners();
        return view;
    }

    private void findViews(View view) {
        mTextViewQuestion = view.findViewById(R.id.txt_listener);
        mCheckBoxAnswer = view.findViewById(R.id.checkanswer_listener);
        mCheckBoxCheat = view.findViewById(R.id.checkcheat_listener);
    }

    private void initViews() {
        mTextViewQuestion.setText(mQuestion.getResourceId());
        mCheckBoxAnswer.setChecked(mQuestion.isTrueAnswer());

    }
    private void setListeners() {

        mCheckBoxAnswer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

    }
}