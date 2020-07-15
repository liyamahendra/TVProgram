package com.mahendra.tvprogram.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MLConnection {
    private static final int READ_TIME_OUT = 30000;
    private static final int CONNECTION_TIME_OUT = 30000;
    private static final String REQUEST_TYPE = "GET";

    private URL mURL;
    private InputStream mInputStream;
    HttpURLConnection mConnection;

    public MLConnection(String urlString) throws MalformedURLException {
        mURL = new URL(urlString);
    }

    public InputStream openConnectionAndGetInputStream() throws IOException {

        mConnection = (HttpURLConnection) mURL.openConnection();
        mConnection.setReadTimeout(READ_TIME_OUT);
        mConnection.setConnectTimeout(CONNECTION_TIME_OUT);
        mConnection.setRequestMethod(REQUEST_TYPE);
        //mConnection.setDoInput(false);
        mConnection.connect();
        mInputStream = mConnection.getInputStream();
        return mInputStream;
    }

    public void closeStreamAndConnection() throws IOException{
        if(mInputStream != null)
            mInputStream.close();

        if(mConnection != null)
            mConnection.disconnect();
    }
}
