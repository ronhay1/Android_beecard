package com.mvvmrecycleview.extremedeveloper.beecard.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.mvvmrecycleview.extremedeveloper.beecard.model.Card;
import com.mvvmrecycleview.extremedeveloper.beecard.presenter.CardClicked;
import com.mvvmrecycleview.extremedeveloper.beecard.R;
import com.mvvmrecycleview.extremedeveloper.beecard.viewModel.MainViewModel;
import com.mvvmrecycleview.extremedeveloper.beecard.adapter.ListViewAdapter;
import com.mvvmrecycleview.extremedeveloper.beecard.databinding.ActivityMainBinding;

import java.util.Vector;

public class MainActivity extends AppCompatActivity implements CardClicked{
    ActivityMainBinding bind;
    MainViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        bind.recycleCards.setLayoutManager(linearLayoutManager);
        final ListViewAdapter listViewAdapter = new ListViewAdapter(MainActivity.this);
        bind.recycleCards.setAdapter(listViewAdapter);

        viewModel.getCards().observe(this, new Observer<Vector<Card>>() {
            @Override
            public void onChanged(@Nullable Vector<Card> cards) {
                listViewAdapter.setCards(cards);
            }
        });
    }

    @Override
    public void onCardClick(Card card) {
        Toast.makeText(this, card.Name + " " + card.Company, Toast.LENGTH_SHORT).show();
    }
}
