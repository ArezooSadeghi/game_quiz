package com.example.quizgame.Controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.quizgame.DetailQuestionActivity;
import com.example.quizgame.R;
import com.example.quizgame.model.Question;
import com.example.quizgame.repository.QuestionRepository;

import java.util.List;

public class QuizListFragment extends Fragment {
    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";
    private RecyclerView mRecyclerView;

    public QuizListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_of_question_, container, false);
        findViews(view);
        initViews();
        return view;
    }

    private void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        QuestionRepository questionRepository = QuestionRepository.getInstance();
        List<Question> questions = questionRepository.getQuestions();
        QuestionAdapter questionAdapter = new QuestionAdapter(questions);
        mRecyclerView.setAdapter(questionAdapter);
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerview_list_of_question);
    }

    public class QuestionHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewOfQuestion;
        private CheckBox mCheckBoxAnswer, mCheckBoxCheat;
        private Question mQuestion;

        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewOfQuestion = itemView.findViewById(R.id.row_txtquestion);
            mCheckBoxAnswer = itemView.findViewById(R.id.row_checkboxanswer);
            mCheckBoxCheat = itemView.findViewById(R.id.row_checkboxcheat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), DetailQuestionActivity.class);
                    intent.putExtra(EXTRA_QUESTION_ID, mQuestion.getId());
                    startActivity(intent);
                }
            });
        }


        public void bindQuestion(Question question) {
            mQuestion = question;
            mTextViewOfQuestion.setText(question.getResourceId());
        }
    }

    public class QuestionAdapter extends RecyclerView.Adapter<QuestionHolder> {
        private List<Question> mQuestions;

        public QuestionAdapter(List<Question> questions) {
            mQuestions = questions;
        }

        public List<Question> getQuestions() {
            return mQuestions;
        }

        public void setQuestions(List<Question> questions) {
            mQuestions = questions;
        }

        @NonNull
        @Override
        public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.question_row_list, parent, false);
            QuestionHolder questionHolder = new QuestionHolder(view);
            return questionHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull QuestionHolder holder, int position) {
            Question question = mQuestions.get(position);
            holder.bindQuestion(question);

        }

        @Override
        public int getItemCount() {
            return mQuestions.size();
        }
    }
}