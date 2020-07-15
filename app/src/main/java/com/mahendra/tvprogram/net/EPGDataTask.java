package com.mahendra.tvprogram.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.mahendra.tvprogram.db.dao.MLDaoChannels;
import com.mahendra.tvprogram.db.dao.MLDaoProgrammes;
import com.mahendra.tvprogram.parser.MLEPGPullParser;
import com.mahendra.tvprogram.utils.MLConstants;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

public class EPGDataTask extends AsyncTask<String, Void, HashMap<String, Object>> {
    private static final String LOG_TAG = EPGDataTask.class.getSimpleName();

    private ProgressDialog mDialog;
    private Context mContext;
    private IMLAsyncTaskCompletionListener<HashMap<String, Object>> callback;
    private HashMap<String, Object> response = null;

    private MLEPGPullParser parser = null;

    public EPGDataTask(Context context, IMLAsyncTaskCompletionListener<HashMap<String, Object>> callback) {
        this.mContext = context;
        this.callback = callback;
        mDialog = new ProgressDialog(mContext);
        mDialog.setCancelable(false);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mDialog.show();
    }

    @Override
    protected HashMap<String, Object> doInBackground(String... params) {
        try {
            parser = new MLEPGPullParser(MLConstants.URL_EPG);
            response = parser.parseXmlStream();
        } catch (ParseException ex) {
            Log.e(LOG_TAG, "Exception: " + ex.getMessage());
            response = new HashMap<String, Object>();
            response.put(MLConstants.KEY_PARSER_IS_ERROR, Boolean.valueOf(true));
            response.put(MLConstants.KEY_PARSER_ERROR_MESSAGE, ex.getMessage());
            ex.printStackTrace();
        } catch (XmlPullParserException ex) {
            Log.e(LOG_TAG, "Exception: " + ex.getMessage());
            response = new HashMap<String, Object>();
            response.put(MLConstants.KEY_PARSER_IS_ERROR, Boolean.valueOf(true));
            response.put(MLConstants.KEY_PARSER_ERROR_MESSAGE, ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            Log.e(LOG_TAG, "Exception: " + ex.getMessage());
            response = new HashMap<String, Object>();
            response.put(MLConstants.KEY_PARSER_IS_ERROR, Boolean.valueOf(true));
            response.put(MLConstants.KEY_PARSER_ERROR_MESSAGE, ex.getMessage());
            ex.printStackTrace();
        }

        return response;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        if (parser != null)
            parser.stopParsing();
    }

    @Override
    protected void onPostExecute(HashMap<String, Object> result) {
        super.onPostExecute(result);

        if(mDialog != null) {
            mDialog.dismiss();
        }

        MLDaoChannels daoChannels = new MLDaoChannels(mContext);
        MLDaoProgrammes daoProgrammes = new MLDaoProgrammes(mContext);

        daoChannels.saveChannels(parser.getmChannels());
        daoProgrammes.saveProgrammes(parser.getmProgrammes());

        if (this.callback != null)
            this.callback.onAsyncTaskComplete(true);
    }
}
