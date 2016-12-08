package cn.lemon.recyclerview.ui;

import android.content.Context;
import android.view.ViewGroup;

import cn.lemon.recyclerview.ui.bean.Consumption;
import com.ecar.recycler.adapter.BaseViewHolder;
import com.ecar.recycler.adapter.RecyclerAdapter;


class CardRecordAdapter extends RecyclerAdapter<Consumption> {

    public CardRecordAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder<Consumption> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new CardRecordHolder(parent);
    }
}