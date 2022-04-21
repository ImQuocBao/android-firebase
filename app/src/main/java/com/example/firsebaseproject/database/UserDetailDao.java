package com.example.firsebaseproject.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.firsebaseproject.UserDetail;

import java.util.List;

@Dao
public interface UserDetailDao {

    @Insert
    void insertDetail(UserDetail userDetail);

    @Query("SELECT * FROM userDetail")
    List<UserDetail> getListUserDetail();

    @Query("SELECT * FROM userDetail where email= :email")
    UserDetail getUserDetailFromEmail(String email);

    @Update
    void updateUserDetail(UserDetail userDetail);
}
