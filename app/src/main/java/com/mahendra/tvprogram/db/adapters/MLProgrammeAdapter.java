package com.mahendra.tvprogram.db.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mahendra.tvprogram.R;
import com.mahendra.tvprogram.models.MLProgrammeModel;

import java.util.ArrayList;

public class MLProgrammeAdapter extends RecyclerView.Adapter<MLProgrammeAdapter.ViewHolder> {

    private ArrayList<MLProgrammeModel> mData;
    private Context mContext;

    public MLProgrammeAdapter(Context context, ArrayList<MLProgrammeModel> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_programme, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        System.out.println("URL: " + this.mData.get(position).getIconURL());

        Glide.with(mContext).load(this.mData.get(position).getIconURL()).into(holder.imgChannelIcon);
        holder.lblProgrammeName.setText(this.mData.get(position).getTitle());
        holder.lblProgrammeSubtitle.setText(this.mData.get(position).getSubTitle());
        holder.lblProgrammeDescription.setText(this.mData.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgChannelIcon;
        private TextView lblProgrammeName, lblProgrammeSubtitle, lblProgrammeDescription;

        public ViewHolder(View v) {
            super(v);
            setupLayout(v);
        }

        private void setupLayout(View v) {
            imgChannelIcon = (ImageView) v.findViewById(R.id.imgChannelIcon);
            lblProgrammeName = (TextView) v.findViewById(R.id.lblProgrammeName);
            lblProgrammeSubtitle = (TextView) v.findViewById(R.id.lblProgrammeSubtitle);
            lblProgrammeDescription = (TextView) v.findViewById(R.id.lblProgrammeDescription);
        }
    }

    public void deleteItemAtPosition(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }
}