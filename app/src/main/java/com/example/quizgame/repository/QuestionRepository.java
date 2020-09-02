package com.example.quizgame.repository;

import com.example.quizgame.R;
import com.example.quizgame.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionRepository {
    private static QuestionRepository sInstance;
    private List<Question> mQuestions;

    private QuestionRepository() {
        mQuestions = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Question question = new Question();
            question.setResourceId(R.string.question_middleast);
            question.setTrueAnswer(true);
            mQuestions.add(question);
        }
    }

    public static QuestionRepository getInstance() {
        if (sInstance == null) {
            sInstance = new QuestionRepository();
        }
        return sInstance;
    }

    public List<Question> getQuestions() {
        return mQuestions;
    }

    public void setQuestions(List<Question> questions) {
        mQuestions = questions;
    }

    public void createQuestion(Question question) {
        mQuestions.add(question);
    }

    public void deleteQuestion(Question question) {
        mQuestions.remove(question);
    }

    public Question readQuestion(UUID id) {
        for (Question question : mQuestions) {
            if (question.getId().equals(id)) {
                return question;
            }
        }
        return null;
    }

    public void updateQuestion(Question question) {
        //todo
    }
}
