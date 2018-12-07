package com.mvvmrecycleview.extremedeveloper.beecard.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mvvmrecycleview.extremedeveloper.beecard.model.Card;

import java.util.Iterator;
import java.util.Vector;

import static android.content.ContentValues.TAG;

/**
 * Created by מחשב שלי on 05/12/2018.
 */

public class Repository {
    static Repository repository;
    MutableLiveData<Vector<Card>> cards;

    public static Repository getInstance()
    {
        if(repository==null)
            repository = new Repository();
        return repository;
    }

    private Repository()
    {
        cards = new MutableLiveData<>();
        final FirebaseDatabase database = FirebaseDatabase.getInstance("https://beecard-138a4.firebaseio.com/");
        DatabaseReference myRef = database.getReference("users");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Vector<Card> newcards = new Vector<>();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Card card = postSnapshot.getValue(Card.class);
                    newcards.add(card);
                }
                if(newcards.size()>0)
                    cards.setValue(newcards);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
/*
        Card card = new Card();
        card.Company = "idf";
        card.Name = "Ron Hay";
        cards.add(card);
        Card card1 = new Card();
        card1.Company = "hve";
        card1.Name = "Viki Fedelman";
        cards.add(card1);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://beecard-138a4.firebaseio.com/");
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");
    */
    }

    public MutableLiveData<Vector<Card>> getCards()
    {
        return cards;
    }
}