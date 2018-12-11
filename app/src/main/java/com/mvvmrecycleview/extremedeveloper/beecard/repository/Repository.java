package com.mvvmrecycleview.extremedeveloper.beecard.repository;

import android.arch.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.mvvmrecycleview.extremedeveloper.beecard.model.Card;
import com.mvvmrecycleview.extremedeveloper.beecard.presenter.CardUpdated;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

/**
 * Created by מחשב שלי on 05/12/2018.
 */

public class Repository {
    private static String Tag = "Repository";
    public static final String LASTNAME= "Last_name";
    public static final String NAME = "Name";
    public static final String PHONE= "Phone";

    FirebaseFirestore db;
    CollectionReference card_ref;
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
        db = FirebaseFirestore.getInstance();
        card_ref = db.collection("users");
        updateAllCards();
        //saveCard();
        /*final FirebaseDatabase database = FirebaseDatabase.getInstance("https://beecard-138a4.firebaseio.com/");
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
        });*/
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

    public void saveCard(final CardUpdated presenter)
    {
        Map<String,Object> card = new HashMap<>();
        card.put(NAME,"bla");
        card.put(LASTNAME,"blala");
        card.put(PHONE,"052222222");
        db.collection("users").add(card).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                presenter.cardInserted(documentReference.getId());
            }
        });
    }

    public void getCard(String id, final CardUpdated cardUpdated)
    {
        db.collection("users").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                try {
                    Card card = new Card();
                    card.Phone = documentSnapshot.getString(PHONE);
                    card.Last_Name = documentSnapshot.getString(LASTNAME);
                    card.Name = documentSnapshot.getString(NAME);
                    cardUpdated.addCard(card);
                }
                catch (Exception e){}
            }
        });
    }



    public void updateAllCards()
    {
        final Vector<Card> newcards = new Vector<>();
        card_ref.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty())
                {
                    Iterator<DocumentSnapshot> I= queryDocumentSnapshots.getDocuments().iterator();
                    while (I.hasNext())
                    {
                        try {
                            DocumentSnapshot cur = I.next();
                            Card card = new Card();
                            card.Phone = cur.getString(PHONE);
                            card.Last_Name = cur.getString(LASTNAME);
                            card.Name = cur.getString(NAME);
                            if(card.Phone==null || card.Last_Name==null || card.Name == null) throw new Exception("null");
                            newcards.add(card);
                        }catch (Exception e){}
                    }
                    cards.setValue(newcards);
                }
            }
        });
     }

    public MutableLiveData<Vector<Card>> getCards()
    {
        return cards;
    }
}