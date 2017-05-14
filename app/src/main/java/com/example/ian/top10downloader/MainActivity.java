package com.example.ian.top10downloader;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: starting AsyncTask");
        DownloadData downloadData = new DownloadData();
        downloadData.execute("URL goes here");
        Log.d(TAG, "onCreate: done");
    }

    /**
     * AsyncTask is an interface for creating background threads in Android
     * this ensures that the download of data is done outside the UI thread.
     * AsyncTask takes 3 arguments.
     * String - the URL
     * Void - what will be displayed during progress
     * String - the expected result
     */
    private class DownloadData extends AsyncTask<String, Void, String> {
        private static final String TAG = "DownloadData";

        /**
         * This method runs on the main UI thread after doInBackground() is completed
         * @param s The result of the operation
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "onPostExecute: parameter is " + s);
        }

        /**
         * Code that the AsyncTask executes
         * @param strings The URL/s that the AsyncTask receives as input
         * @return
         */
        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: starts with " + strings[0]);
            return "doInBackground completed.";
        }
    }
}
