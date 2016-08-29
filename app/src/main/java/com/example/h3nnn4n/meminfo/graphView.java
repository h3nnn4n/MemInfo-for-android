package com.example.h3nnn4n.meminfo;

/**
 * Created by h3nnn4n on 8/29/16.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class graphView extends View {
    private ArrayList<Integer> used_array = new ArrayList<>();
    private static final String TAG = MainActivity.class.getSimpleName();
    private int total;
    int oldsize = 0;

    public void addUsed(int k) {
        used_array.add(k);
        Log.d(TAG,"Added " + Integer.toString(k));
        postInvalidate();
    }

    public void setTotal(int k){
        total = k;
    }

    public graphView(Context context) {
        super(context);
    }

    public graphView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public graphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(0xff101010);

        if ( oldsize == used_array.size() ) {
            Log.d(TAG, "No update");
        } else {
            oldsize =- used_array.size();
            Log.d(TAG, "Size is " + Integer.toString(used_array.size()));

            if (used_array.size() > 1) {
                for (int i = 0; i < used_array.size() - 1; i++) {
                    float k1 = (used_array.get(i) / (float) total) * getHeight();
                    float k2 = (used_array.get(i + 1) / (float) total) * getHeight();

                    canvas.drawLine(getWidth() / (used_array.size()-1) * i, (int) k1,
                                    getWidth() / (used_array.size()-1) * (i + 1), (int) k2, paint);
                }
            }
            Log.d(TAG, "Update");
            postInvalidate();
        }
    }
}