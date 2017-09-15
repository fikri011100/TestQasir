package com.santriprogrammer.testqasir;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.santriprogrammer.testqasir.Model.ModelData;

import java.util.List;

import static com.santriprogrammer.testqasir.R.layout.list_data;

/**
 * Created by fikriimaduddin on 9/15/17.
 */

public class HorizontalRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ModelData.DataBean.VariantsBean> mDataList;
    private int mRowIndex;
    Context context;

    public HorizontalRVAdapter() {
    }

    public void setData(Context context, List<ModelData.DataBean.VariantsBean> data) {
        this.context = context;
        this.mDataList = data;
    }

    public void setRowIndex(int index) {
        mRowIndex = index;
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView harga;
        private ImageView imageDalem;

        public ItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.txtMerek);
            harga = (TextView)itemView.findViewById(R.id.txtHarga);
            imageDalem = (ImageView)itemView.findViewById(R.id.imgDalem);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.dalem_list, parent, false);
        ItemViewHolder holder = new ItemViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder rawHolder, int position) {
        ItemViewHolder holder = (ItemViewHolder) rawHolder;
        holder.title.setText(mDataList.get(position).getVariant_name());
        holder.harga.setText(mDataList.get(position).getPrice());
        Glide.with(context)
                .load(mDataList.get(position).getImage())
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imageDalem);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

}
