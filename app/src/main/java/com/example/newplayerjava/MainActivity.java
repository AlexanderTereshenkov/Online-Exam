package com.example.newplayerjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialButton loginButton;
    private MaterialButton registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.loginBtn);
        registerButton = findViewById(R.id.regBtn);
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.regBtn:
                startActivity(new Intent(this, UserRegistration.class));
                break;
        }
    }
}