package com.example.newplayerjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialButton loginButton;
    private TextView registerButton;
    private EditText emailText, passwordText;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.loginBtn);
        registerButton = findViewById(R.id.regBtn);
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        emailText = findViewById(R.id.emailTxt);
        passwordText = findViewById(R.id.passwordTxt);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.regBtn:
                startActivity(new Intent(this, UserRegistration.class));
                break;
            case R.id.loginBtn:
                loginUser();
                break;
        }
    }
    private void loginUser(){
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(MainActivity.this, ChangeTypeActivity.class));
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Что-то пошло не так:(", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}