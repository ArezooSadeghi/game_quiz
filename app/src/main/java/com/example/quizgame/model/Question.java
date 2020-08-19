package com.example.quizgame.model;

import java.io.Serializable;

public class Question implements Serializable {
    private int mResourceId;
    private boolean mIsTrueAnswer;
    private boolean mIsDisable;
    private boolean mIsCheatedPlayer;

    public Question() {
    }

    public Question(int resourceId, boolean answerOfQuestion) {
        mResourceId = resourceId;
        mIsTrueAnswer = answerOfQuestion;
    }

    public boolean isCheatedPlayer() {
        return mIsCheatedPlayer;
    }

    public void setCheatedPlayer(boolean cheatedPlayer) {
        mIsCheatedPlayer = cheatedPlayer;
    }

    public int getResourceId() {
        return mResourceId;
    }

    public void setResourceId(int resourceId) {
        mResourceId = resourceId;
    }

    public boolean isTrueAnswer() {
        return mIsTrueAnswer;
    }

    public void setTrueAnswer(boolean trueAnswer) {
        mIsTrueAnswer = trueAnswer;
    }

    public boolean isDisable() {
        return mIsDisable;
    }

    public void setDisable(boolean disable) {
        mIsDisable = disable;
    }
}
