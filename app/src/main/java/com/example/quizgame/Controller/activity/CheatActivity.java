package com.example.quizgame.Controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.quizgame.Controller.fragment.CheatFragment;
import com.example.quizgame.R;

public class CheatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        FragmentManager fragmentManager = getSupportFragmentManager();
        CheatFragment cheatFragment = new CheatFragment();
        fragmentManager.beginTransaction().add(R.id.cheatfragment_container, cheatFragment).commit();
    }
}