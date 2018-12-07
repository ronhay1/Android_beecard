package com.mvvmrecycleview.extremedeveloper.beecard.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


import com.mvvmrecycleview.extremedeveloper.beecard.model.Card;
import com.mvvmrecycleview.extremedeveloper.beecard.repository.Repository;

import java.util.Vector;

/**
 * Created by מחשב שלי on 05/12/2018.
 */

public class MainViewModel extends ViewModel{
    Repository repository;

    MainViewModel()
    {
        repository = Repository.getInstance();
        // Write a message to the database
       /* FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");*/
    }

    public MutableLiveData<Vector<Card>> getCards() {
        return repository.getCards();
    }
}
