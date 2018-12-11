package com.mvvmrecycleview.extremedeveloper.beecard.presenter;

import com.mvvmrecycleview.extremedeveloper.beecard.model.Card;

/**
 * Created by מחשב שלי on 11/12/2018.
 */

public interface CardUpdated {
    void cardInserted(String Card_id);
    void addCard(Card card);
}
