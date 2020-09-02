package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class DetailQuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_question);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_detail_container);

        if (fragment == null) {
            DetailQuestionFragment detailQuestionFragment = new DetailQuestionFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_detail_container, detailQuestionFragment).commit();
        }
    }
}