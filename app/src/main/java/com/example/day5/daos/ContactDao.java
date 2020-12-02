package com.example.day5.daos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.day5.entities.ContactModel;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertContact(ContactModel contactModel);
    @Update
    int updateContact(ContactModel contactModel);

    @Delete
    int deleteContact(ContactModel contactModel);

    @Query("select * from tbl_contact")
    LiveData<List<ContactModel>> getAllContact();


    @Query("select * from tbl_contact where id=:id")
    LiveData<ContactModel> getContactById(long id);



}
