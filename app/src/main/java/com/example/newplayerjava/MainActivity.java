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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailText, passwordText;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MaterialButton loginButton = findViewById(R.id.loginBtn);
        TextView registerButton = findViewById(R.id.regBtn);
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
                            //startActivity(new Intent(MainActivity.this, ChangeTypeActivity.class));
                            loadActivity();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Что-то пошло не так:(", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void loadActivity(){
        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        userID = mAuth.getCurrentUser().getUid();
        databaseReference.child(userID)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User currentUser = snapshot.getValue(User.class);
                        if(currentUser != null){
                            Toast.makeText(MainActivity.this, currentUser.name, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}