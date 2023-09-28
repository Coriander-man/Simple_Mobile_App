package com.feva.myapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Page> mData;

    public ProductAdapter(Context context, ArrayList<Page> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_product_cell, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.ivPageThumbnail = (ImageView) view.findViewById(R.id.ivPageThumbnail);
        holder.tvPageTitle = (TextView) view.findViewById(R.id.tvPageTitle);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Page prod = mData.get(position);

        holder.tvPageTitle.setText(prod.getTitle());
        Glide.with(mContext)
                .load(prod.pageThumbnailUrl)
                .into(holder.ivPageThumbnail);

        holder.tvPageTitle.setVisibility(prod.getTitle() == null ? View.GONE : View.VISIBLE);
        holder.ivPageThumbnail.setVisibility(prod.getThumbnail() == null ? View.GONE : View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder  {
        public ImageView ivPageThumbnail;
        public TextView tvPageTitle;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}