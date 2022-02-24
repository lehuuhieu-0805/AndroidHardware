package com.example.btnUtils;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.io.IOException;

public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder holdMe;
    private Camera camera;

    public ShowCamera(Context context, Camera camera){
        super(context);
        this.camera = camera;
        this.holdMe = getHolder();
        this.holdMe.addCallback(this);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int format, int width, int height) { }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) { }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        try {
            this.camera.setPreviewDisplay(surfaceHolder);
            this.camera.startPreview();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
