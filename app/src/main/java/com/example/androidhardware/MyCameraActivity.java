package com.example.androidhardware;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.UUID;

public class MyCameraActivity extends Activity {
    private static final int CAMERA_PHOTO_REQUEST = 996;
    private static final int CAMERA_REQUEST = 998;
    private static final int CAMERA_VIDEO_REQUEST = 997;
    private ImageView imgPresent;
    private Uri outputFileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_camera);

        imgPresent = findViewById(R.id.imgResult);
    }

    public void clickToTakePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    public void clickToPhotoFile(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_PHOTO_REQUEST);
    }

    public void clickToVideo(View view) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, CAMERA_VIDEO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("data: " + data);
        if(requestCode == CAMERA_REQUEST){
            if(data != null){
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imgPresent.setImageBitmap(photo);
            }
        } else if(requestCode == CAMERA_VIDEO_REQUEST){
            Toast.makeText(MyCameraActivity.this,"Saved Video Successfully" ,Toast.LENGTH_SHORT).show();
        } else if(requestCode == CAMERA_PHOTO_REQUEST) {
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");

            saveImageToExternalStorage(UUID.randomUUID().toString(),bitmap);
            Toast.makeText(MyCameraActivity.this,"Saved Image Successfully" ,Toast.LENGTH_SHORT).show();

        }
    }

    private boolean saveImageToExternalStorage(String imgName, Bitmap bmp){
        Uri ImageCollection = null;
        ContentResolver resolver = getContentResolver();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            ImageCollection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
        }else {
            ImageCollection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, imgName + ".jpg");
        contentValues.put(MediaStore.Images.Media.MIME_TYPE,"image/jpeg");
        Uri imageUri = resolver.insert(ImageCollection, contentValues);
        try {
            OutputStream outputStream = resolver.openOutputStream(Objects.requireNonNull(imageUri));
            bmp.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            Objects.requireNonNull(outputStream);
            return true;
        }
        catch (Exception e){
            Toast.makeText(this,"Image not saved: \n" + e,Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return false;
    }
}