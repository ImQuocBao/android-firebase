package com.example.firsebaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.firsebaseproject.database.UserDetailDataBase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collection;
import java.util.HashSet;

public class ResultPageActivity extends AppCompatActivity {

    private FirebaseAuth fAuth;
    private FirebaseDatabase fData;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_page);

        // FireBaseDatabase
        fData = FirebaseDatabase.getInstance("https://android-studio-first-project-default-rtdb.asia-southeast1.firebasedatabase.app");
        reference = fData.getReference("UserDetail");

        UserDetail userDetail;
        String email = getIntent().getExtras().getString("emailUser");

        boolean flag = false;

        if(readUserFromDB(email) != null ) {
            // có trong database thì get ra khum cần khởi tạo
            userDetail = readUserFromDB(email);
            flag = true;
        } else {
            userDetail = new UserDetail(email, 0, 0, 0);
        }

        ImageButton btnHappy = findViewById(R.id.btnHappy);
        ImageButton btnSimple = findViewById(R.id.btnSimple);
        ImageButton btnSad = findViewById(R.id.btnSad);
        Button btnFinish = findViewById(R.id.buttonFinish);
        fAuth = FirebaseAuth.getInstance();

        btnHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDetail.setCountOfHappy(userDetail.getCountOfHappy() + 1);
                Log.d("Happy Click : ", "" + userDetail.getCountOfHappy());
            }
        });

        btnSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDetail.setCountOfSimple(userDetail.getCountOfSimple() + 1);
            }
        });

        btnSad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDetail.setCountOfSad(userDetail.getCountOfSad() + 1);
            }
        });

        boolean finalFlag = flag;
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (finalFlag == true) {
                    updateUserDetail(userDetail);
                } else {
                    addUserDetail(userDetail);
                }

//                int id = readUserFromDB(userDetail.getEmail()).getId();

//                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(ResultPageActivity.this, Login__page.class);
//                startActivity(intent);
            }
        });
    }

    private void updateUserDetail(UserDetail userDetail) {
        Log.d("DA CO USE", "THUC HIEN LENH UPDATE: ");
        UserDetailDataBase.getInstance(this).userDetailDao().updateUserDetail(userDetail);
    }

    private void addUserDetail(UserDetail userDetail) {
        Log.d("==============", "================");
        Log.d("ADD TO DB", "---");
        Log.d("Id :  ", "" + userDetail.getId());
        Log.d("Email :  ", "" + userDetail.getEmail());
        Log.d("Happy Click : ", "" + userDetail.getCountOfHappy());
        Log.d("Simple Click : ", "" + userDetail.getCountOfSimple());
        Log.d("Sad Click : ", "" + userDetail.getCountOfSad());
        Log.d("==============", "================");
        UserDetailDataBase.getInstance(this).userDetailDao().insertDetail(userDetail);

    }

    private UserDetail readUserFromDB (String email) {
        UserDetail userDetail = UserDetailDataBase.getInstance(this).userDetailDao().getUserDetailFromEmail(email);
        if(userDetail != null ) {
            Log.d("==============", "================");
            Log.d("Đã Có User Trong DB local ", "Load Data");
            Log.d("Id :  ", "" + userDetail.getId());
            Log.d("Email :  ", "" + userDetail.getEmail());
            Log.d("Happy Click : ", "" + userDetail.getCountOfHappy());
            Log.d("Simple Click : ", "" + userDetail.getCountOfSimple());
            Log.d("Sad Click : ", "" + userDetail.getCountOfSad());
            Log.d("==============", "================");
        }
        return userDetail;
    }

}