package com.mvvmrecycleview.extremedeveloper.beecard.viewModel;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;


import com.mvvmrecycleview.extremedeveloper.beecard.R;
import com.mvvmrecycleview.extremedeveloper.beecard.model.Card;
import com.mvvmrecycleview.extremedeveloper.beecard.presenter.CardClicked;
import com.mvvmrecycleview.extremedeveloper.beecard.presenter.CardUpdated;
import com.mvvmrecycleview.extremedeveloper.beecard.repository.Repository;

import java.util.Vector;

/**
 * Created by מחשב שלי on 05/12/2018.
 */

public class MainViewModel extends ViewModel{
    Repository repository;
    String myId;

    MainViewModel()
    {
        repository = Repository.getInstance();
    }

    public void getId(Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        myId = sharedPref.getString(activity.getString(R.string.my_id), "");
        if(myId.compareTo("")!=0)
            Toast.makeText(activity,"id is "+myId,Toast.LENGTH_SHORT).show();
        else
            repository.saveCard((CardUpdated)activity);
    }

    public MutableLiveData<Vector<Card>> getCards() {
        return repository.getCards();
    }

    public void cardInserted(Activity activity, String Card_id) {
        //save id
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(activity.getString(R.string.my_id), Card_id);
        editor.commit();
        myId = Card_id;
        Toast.makeText(activity,"your id is"+Card_id,Toast.LENGTH_SHORT).show();
        repository.updateAllCards();
    }

    public void addCard(Card card) {
        //right now only show the name of myself
        Log.d("new card",card.getFullName());
    }

    public String getMyId() {
        return myId;
    }

    public void getMySelf(Activity activity) {
        repository.getCard(myId,(CardUpdated) activity);
    }
}
