package com.example.h3nnn4n.meminfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<String> memInfo_array = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void readMemInfo(View v) {
        BufferedReader br = null;
        try {
            String fpath = "/proc/meminfo";
            try {
                br = new BufferedReader(new FileReader(fpath));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            String line;
            try {
                assert br != null;
                while ((line = br.readLine()) != null) {
                    Log.d(TAG, line);
                    memInfo_array.add(line);
                }
            } catch (IOException eee) {
                Log.d(TAG, "Error while reading " + fpath, eee);
            }
        } catch (Exception masterTreta) {
            Log.d(TAG, "Error while opening file.", masterTreta);
        }

        for (int i = 0; i < memInfo_array.size(); i++) {
            if (Pattern.matches("MemTotal:.*", memInfo_array.get(i))) {
                final TextView MemTotal = (TextView) findViewById(R.id.MemTotal);
                MemTotal.setText(memInfo_array.get(i));
            }

            if (Pattern.matches("MemFree:.*", memInfo_array.get(i))) {
                final TextView MemTotal = (TextView) findViewById(R.id.MemFree);
                MemTotal.setText(memInfo_array.get(i));
            }

            if (Pattern.matches("Buffers:.*", memInfo_array.get(i))) {
                final TextView MemTotal = (TextView) findViewById(R.id.Buffers);
                MemTotal.setText(memInfo_array.get(i));
            }

            if (Pattern.matches("Cached:.*", memInfo_array.get(i))) {
                final TextView MemTotal = (TextView) findViewById(R.id.Cached);
                MemTotal.setText(memInfo_array.get(i));
            }
        }
    }
}
