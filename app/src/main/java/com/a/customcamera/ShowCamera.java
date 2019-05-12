package com.a.customcamera;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.VolumeShaper;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.List;

public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback {

    Camera c;
    SurfaceHolder holder;

    public ShowCamera(Context context, Camera c) {
        super(context);
        this.c = c;
        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //param da camera
        Camera.Parameters params = c.getParameters();

        //Configuracao do aspeto da camara em outros dispositivos
        List<Camera.Size> sizes = params.getSupportedPictureSizes();
        Camera.Size mSize=null;

        for( Camera.Size size:sizes){
            mSize=size;
        }
        //trocar a orientaçao da camera
        if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
            params.set("orientation", "portrait");
            c.setDisplayOrientation(90);
            params.setRotation(90);
        } else {
            params.set("orientation", "landscape");
            c.setDisplayOrientation(0);
            params.setRotation(0);
        }

        params.setPictureSize(mSize.width,mSize.height);
        c.setParameters(params);
        try {
            c.setPreviewDisplay(holder);
            c.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }

}


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        c.stopPreview();
        c.release();
    }
}
