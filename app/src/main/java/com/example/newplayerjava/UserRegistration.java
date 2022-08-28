package com.example.newplayerjava;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegistration extends AppCompatActivity {

    private EditText nameText, emailText, passwordText;
    private MaterialButton okButton;
    private FirebaseAuth mAuth;
    private SwitchMaterial switcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        mAuth = FirebaseAuth.getInstance();
        nameText = findViewById(R.id.nameText);
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.pasText);
        okButton = findViewById(R.id.okBtn);
        switcher = findViewById(R.id.isTeacher);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
            }
        });
    }
    private void registerNewUser(){
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();
        String name = nameText.getText().toString().trim();
        Boolean isTeacher = switcher.isChecked();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(name, email, password, isTeacher);
                            FirebaseDatabase.getInstance().getReference("user")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user);
                        }
                    }
                });
    }
}