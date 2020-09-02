package com.example.quizgame.Controller.activity;

import androidx.fragment.app.Fragment;

import com.example.quizgame.Controller.fragment.QuizListFragment;

public class QuizListActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return new QuizListFragment();
    }
}