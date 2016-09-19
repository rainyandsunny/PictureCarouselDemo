package com.arcsoft.mysoft.picturecarouseldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.arcsoft.mysoft.picturecarouseldemo.view.CanvasView;

public class Sample extends AppCompatActivity {

    private CanvasView mView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        mView = (CanvasView) findViewById(R.id.canvasview);
        mView.setCurrentValue(950,mView);
    }
}
