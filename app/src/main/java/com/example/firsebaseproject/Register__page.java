package com.example.firsebaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register__page extends AppCompatActivity {

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        fAuth = FirebaseAuth.getInstance();

        Button btn_register = findViewById(R.id.btnRegister2);
        EditText edtName = findViewById(R.id.edt_name2);
        EditText edtEmail = findViewById(R.id.edt_email2);
        EditText edtPass = findViewById(R.id.edt_password2);
        EditText edtRePass = findViewById(R.id.edt_repassword2);

        if(fAuth.getCurrentUser() != null) {

        }

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                String rePass = edtRePass.getText().toString().trim();

                if(pass.equals(rePass)) {
                    Toast.makeText(Register__page.this, "SOS", Toast.LENGTH_SHORT).show();

                    fAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(Register__page.this, "create success" , Toast.LENGTH_SHORT).show();
                                Log.d("task ", "onComplete: " + task);
                                // Đăng Xuất
                                //  FirebaseAuth.getInstance().signOut();
                            } else {
                                Toast.makeText(Register__page.this, "create Fail" , Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(Register__page.this, "Pass không giống nhau", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}