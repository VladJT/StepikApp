package jt.projects.stepikapp.common;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GetDataFromInternet extends AsyncTask<URL, Void, String> {

    private static final String TAG = "GetDataFromInternet";

    public GetDataFromInternet(AsyncResponce delegate) {
        this.delegate = delegate;
    }

    public interface AsyncResponce{
        void processFinish(String response);
    }
    public AsyncResponce delegate;

    // для GUI
    @Override
    protected void onPreExecute() {
        Log.d(TAG, "onPreExecute: ");
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(URL[] urls) {
        Log.d(TAG, "doInBackground: ");
        String result = "";
        try {
            result = getDataFromInternet(urls[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d(TAG, "onPostExecute: " + result);
        delegate.processFinish(result);
    }

    String getDataFromInternet(URL url) throws IOException {
        String result = "";
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = conn.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");// конец строки
            boolean hasData = scanner.hasNext();
            if (hasData) {
                result = scanner.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
            return result;
        }
    }
}
