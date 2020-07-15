package com.mahendra.tvprogram.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mahendra.tvprogram.R;
import com.mahendra.tvprogram.db.adapters.MLChannelAdapter;
import com.mahendra.tvprogram.db.dao.MLDaoChannels;
import com.mahendra.tvprogram.db.dao.MLDaoProgrammes;
import com.mahendra.tvprogram.net.EPGDataTask;
import com.mahendra.tvprogram.net.IMLAsyncTaskCompletionListener;

import java.util.HashMap;

public class MLChannelsActivity extends AppCompatActivity {

    private RecyclerView rvChannels;
    private MLDaoChannels daoChannels;
    private MLDaoProgrammes daoProgrammes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daoChannels = new MLDaoChannels(this);
        daoProgrammes = new MLDaoProgrammes(this);


        daoChannels.removeAll();
        daoProgrammes.removeAll();

        setupLayout();
        fetchData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_help:
                Intent intent = new Intent(this, HelpFaqActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupLayout() {
        rvChannels = findViewById(R.id.rvChannels);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvChannels.setLayoutManager(layoutManager);
    }

    private void fetchData() {
        EPGDataTask epgDataTask = new EPGDataTask(this, new IMLAsyncTaskCompletionListener<HashMap<String, Object>>() {
            @Override
            public void onAsyncTaskComplete(boolean isSuccess) {
                System.out.println("isSuccess: " + isSuccess);
                if(isSuccess) {
                    MLChannelAdapter adpater = new MLChannelAdapter(MLChannelsActivity.this, daoChannels.getAllChannels());
                    rvChannels.setAdapter(adpater);
                }
            }
        });

        epgDataTask.execute();
    }
}