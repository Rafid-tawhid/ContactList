package com.example.day5.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.day5.db.ContactDatabase;
import com.example.day5.db.ContactRepository;
import com.example.day5.entities.ContactModel;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    private ContactDatabase db;
    private ContactRepository repository;
    private LiveData<List<ContactModel>> mutableLiveData;




    public ContactViewModel(@NonNull Application application) {
        super(application);

        db=ContactDatabase.getInstance(application);
        repository=new ContactRepository(db.getContactDao());
        mutableLiveData=new MutableLiveData<>();
        mutableLiveData=repository.getAllContact();


    }

    public LiveData<List<ContactModel>> getContacts()
    {
        return mutableLiveData;
    }


    public LiveData<ContactModel> getContactsById(long id)
    {
        return repository.getContactById(id);
    }

    public void setContact(ContactModel contact)
    {
        repository.addNewContact(contact);
    }
    public void updateContact(ContactModel contactModel)
    {

        repository.updateContact(contactModel);
    }

    public void deleteContact(ContactModel contactModel)
    {
        repository.delete(contactModel);
    }
}

