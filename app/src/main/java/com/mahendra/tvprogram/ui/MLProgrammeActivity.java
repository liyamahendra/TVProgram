package com.mahendra.tvprogram.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mahendra.tvprogram.R;
import com.mahendra.tvprogram.db.dao.MLDaoChannels;
import com.mahendra.tvprogram.db.adapters.MLProgrammeAdapter;
import com.mahendra.tvprogram.utils.MLConstants;

public class MLProgrammeActivity extends AppCompatActivity {

    private RecyclerView rvProgrammes;
    private MLDaoChannels daoChannels;
    private String channelID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programme);

        daoChannels = new MLDaoChannels(this);
        setupLayout();
    }

    private void setupLayout() {
        channelID = getIntent().getExtras().getString(MLConstants.EXTRA_CHANNEL_ID);

        rvProgrammes = findViewById(R.id.rvProgrammes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvProgrammes.setLayoutManager(layoutManager);

        MLProgrammeAdapter adapter = new MLProgrammeAdapter(this, daoChannels.getProgrammesForChannel(channelID));
        rvProgrammes.setAdapter(adapter);
    }
}