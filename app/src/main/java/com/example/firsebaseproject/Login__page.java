package com.example.firsebaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login__page extends AppCompatActivity {

    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        EditText Email = findViewById(R.id.edt_email);
        EditText password = findViewById(R.id.edtPassword1);

        fAuth = FirebaseAuth.getInstance();

        Button btnLogin = findViewById(R.id.btnSignIn1);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = Email.getText().toString().trim();
                String passwordFeild = password.getText().toString().trim();

                fAuth.signInWithEmailAndPassword(email, passwordFeild).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Intent intent = new Intent(Login__page.this, ResultPageActivity.class);
                            Bundle b = new Bundle();
                            b.putString("emailUser", email);
                            intent.putExtras(b);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Login__page.this, "Error !!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}