package com.mahendra.tvprogram.db.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mahendra.tvprogram.R;
import com.mahendra.tvprogram.models.MLChannelModel;
import com.mahendra.tvprogram.ui.MLProgrammeActivity;
import com.mahendra.tvprogram.utils.MLConstants;

import java.util.ArrayList;

public class MLChannelAdapter extends RecyclerView.Adapter<MLChannelAdapter.ViewHolder> {

    private ArrayList<MLChannelModel> mData;
    private Context mContext;

    public MLChannelAdapter(Context context, ArrayList<MLChannelModel> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_channel, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Glide.with(mContext).load(this.mData.get(position).getIconURL()).into(holder.imgChannelIcon);
        holder.lblChannelName.setText(this.mData.get(position).getDisplayName());

        ((LinearLayout) holder.lblChannelName.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MLProgrammeActivity.class);
                intent.putExtra(MLConstants.EXTRA_CHANNEL_ID, mData.get(position).getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgChannelIcon;
        private TextView lblChannelName;

        public ViewHolder(View v) {
            super(v);
            setupLayout(v);
        }

        private void setupLayout(View v) {
            imgChannelIcon = (ImageView) v.findViewById(R.id.imgChannelIcon);
            lblChannelName = (TextView) v.findViewById(R.id.lblChannelName);
        }
    }

    public void deleteItemAtPosition(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }
}