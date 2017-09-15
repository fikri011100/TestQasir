package com.santriprogrammer.testqasir;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.santriprogrammer.testqasir.Model.ModelData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fikriimaduddin on 9/15/17.
 */

public class AdapterKategori extends RecyclerView.Adapter<AdapterKategori.SimpleViewHolder> {

    private final Context mContext;
    List<ModelData.DataBean> mData;

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView Imagebtn,plus;
        HorizontalRVAdapter horizontalAdapter;
        RecyclerView horizontalList;

        public SimpleViewHolder(View view) {
            super(view);
            Context context = itemView.getContext();
            title = (TextView) view.findViewById(R.id.txtNama);
            Imagebtn = (ImageView) view.findViewById(R.id.btnHidden);
            plus = (ImageView) view.findViewById(R.id.btnPlus);
            horizontalList = (RecyclerView) itemView.findViewById(R.id.recDalem);
            horizontalList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            horizontalAdapter = new HorizontalRVAdapter();
            horizontalList.setAdapter(horizontalAdapter);
        }
    }

    public AdapterKategori(List<ModelData.DataBean> data, MainActivity context) {
        this.mContext = context;
        this.mData = data;

    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.list_data, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder holder, final int position) {
        holder.title.setText(mData.get(position).getProduct_name());
        holder.horizontalAdapter.setData(mContext,mData.get(position).getVariants()); // List of Strings
        holder.horizontalAdapter.setRowIndex(position);
        holder.horizontalList.setVisibility(View.GONE);
        holder.Imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.horizontalList.setVisibility(View.GONE);
                holder.Imagebtn.setVisibility(View.GONE);
                holder.plus.setVisibility(View.VISIBLE);
            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.horizontalList.setVisibility(view.VISIBLE);
                holder.plus.setVisibility(View.GONE);
                holder.Imagebtn.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
