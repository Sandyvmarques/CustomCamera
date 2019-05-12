package com.a.customcamera;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    //um hardware camera
    Camera camera;
    FrameLayout fl;
    ShowCamera showCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //chamada dos objetos do design
        fl = (FrameLayout) findViewById(R.id.framLayout);

        //abrir a camera
        camera = Camera.open();
        showCamera = new  ShowCamera(this,camera);
        fl.addView(showCamera);
    }
}
