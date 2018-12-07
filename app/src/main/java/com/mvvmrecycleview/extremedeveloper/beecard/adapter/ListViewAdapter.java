package com.mvvmrecycleview.extremedeveloper.beecard.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mvvmrecycleview.extremedeveloper.beecard.databinding.SingleCardBinding;
import com.mvvmrecycleview.extremedeveloper.beecard.model.Card;
import com.mvvmrecycleview.extremedeveloper.beecard.presenter.CardClicked;



import java.util.List;

/**
 * Created by מחשב שלי on 05/12/2018.
 */

public class ListViewAdapter extends RecyclerView.Adapter<contectViewHolder>
{
    List<Card> cards;
    CardClicked presenter;
    public ListViewAdapter(CardClicked presenter)
    {
        this.presenter = presenter;
    }

    @Override
    public contectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        SingleCardBinding bind = SingleCardBinding.inflate(inflater,parent,false);
        return new contectViewHolder(bind);
    }

    @Override
    public void onBindViewHolder(contectViewHolder holder, int position) {
        holder.bind(cards.get(position),presenter);
    }

    public void setCards(List<Card> cards)
    {
        this.cards = cards;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(cards ==null)
            return 0;
        return cards.size();
    }

}
class contectViewHolder extends RecyclerView.ViewHolder
{
    private final SingleCardBinding bind;

    public contectViewHolder(SingleCardBinding bind) {
        super(bind.getRoot());
        this.bind = bind;
    }
    public void bind(Card card,CardClicked presenter)
    {
        bind.setCard(card);
        bind.executePendingBindings();
        bind.setPresenter(presenter);
    }
}
