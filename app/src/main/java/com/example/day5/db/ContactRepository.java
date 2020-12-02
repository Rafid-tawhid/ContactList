package com.example.day5.db;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.day5.daos.ContactDao;
import com.example.day5.entities.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class ContactRepository {

private LiveData<List<ContactModel>> contactModelList;

private ContactDao contactDao;


    public ContactRepository(ContactDao contactDao) {
        this.contactDao=contactDao;
        contactModelList=new MutableLiveData<>();
        contactModelList=contactDao.getAllContact();



    }

    public void addNewContact(ContactModel contactModel)
    {

//        Thread thread=new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                //background thread
//                contactDao.insertContact(contactModel);
//            }
//        });
//        thread.run();

        ContactDatabase.dbExecutor.execute(() -> contactDao.insertContact(contactModel));


    }
    public LiveData<List<ContactModel>> getAllContact()
    {
        return contactModelList;
    }

    public void delete(ContactModel contactModel)
    {

        ContactDatabase.dbExecutor.execute(() -> contactDao.deleteContact(contactModel));
    }

    public LiveData<ContactModel> getContactById(long id)
    {
        return contactDao.getContactById(id);
    }

    public void updateContact(ContactModel contactModel)
    {

        ContactDatabase.dbExecutor.execute(() -> contactDao.updateContact(contactModel));
    }


}
