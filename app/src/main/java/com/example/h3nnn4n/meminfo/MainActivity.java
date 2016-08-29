package com.example.h3nnn4n.meminfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<String> memInfo_array = new ArrayList<>();
    private int available;
    private int cached;
    private int buffers;
    private int free;
    private int total;
    private int used;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        available   = -1;
        cached      = -1;
        buffers     = -1;
        free        = -1;
        total       = -1;
        used        = -1;
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
//                    Log.d(TAG, line);
                    memInfo_array.add(line);
                }
            } catch (IOException eee) {
                Log.e(TAG, "Error while reading " + fpath, eee);
            }
        } catch (Exception masterTreta) {
            Log.e(TAG, "Error while opening file.", masterTreta);
        }

        for (int i = 0; i < memInfo_array.size(); i++) {
            if (Pattern.matches("MemTotal:.*", memInfo_array.get(i))) {
                final TextView MemTotal = (TextView) findViewById(R.id.MemTotal);
                total = filterText(memInfo_array.get(i));
                MemTotal.setText(Integer.toString(total));
            }

            if (Pattern.matches("MemFree:.*", memInfo_array.get(i))) {
                final TextView MemTotal = (TextView) findViewById(R.id.MemFree);
                free = filterText(memInfo_array.get(i));
                MemTotal.setText(Integer.toString(free));
            }

            if (Pattern.matches("Buffers:.*", memInfo_array.get(i))) {
                final TextView MemTotal = (TextView) findViewById(R.id.Buffers);
                buffers = filterText(memInfo_array.get(i));
                MemTotal.setText(Integer.toString(buffers));
            }

            if (Pattern.matches("Cached:.*", memInfo_array.get(i))) {
                final TextView MemTotal = (TextView) findViewById(R.id.Cached);
                cached = filterText(memInfo_array.get(i));
                MemTotal.setText(Integer.toString(cached));
            }
        }

        final TextView avaliableTV = (TextView) findViewById(R.id.Avaliable);
        available = free + cached + buffers;
        avaliableTV.setText(Integer.toString(available));

        final TextView usedTV = (TextView) findViewById(R.id.Used);
        used = total - (free + cached + buffers);
        usedTV.setText(Integer.toString(used));
    }

    public int filterText(String str) {
        String str2 = str.replaceAll("\\s+", " ");
        String str3[] = str2.split(" ");
        return Integer.parseInt(str3[1]);
    }
}
