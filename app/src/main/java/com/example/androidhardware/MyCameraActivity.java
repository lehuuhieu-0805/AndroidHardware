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
    private static final String outputFileName = "takePhoto.jpg";
    private static final String outputVideoName = "takeVideo.mp4";
    private VideoView videoPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_camera);

        imgPresent = findViewById(R.id.imgResult);
        videoPresent = findViewById(R.id.videoViewResult);

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, CAMERA_REQUEST);
    }

    public void clickToTakePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    public void clickToPhotoFile(View view) {
//        File sdCard = Environment.getExternalStorageDirectory();
//        String realPath = sdCard.getAbsolutePath();
//        File directory = new File(realPath + "/MyDBs");
//        File file = new File(directory, outputFileName);
////
//        outputFileUri = Uri.fromFile(file);
////
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
//        startActivityForResult(intent, CAMERA_REQUEST);


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_PHOTO_REQUEST);


//        BitmapDrawable bitmapDrawable = (BitmapDrawable) imgPresent.getDrawable();
//        Bitmap bitmap = bitmapDrawable.getBitmap();
//
//        FileOutputStream outputStream = null;
//        File file = Environment.getExternalStorageDirectory();
//        File dir = new File(file.getAbsolutePath() + "/MyPics");
//        dir.mkdirs();
//
//        String filename = String.format("%d.png",System.currentTimeMillis());
//        File outFile = new File(dir,filename);
//        try{
//            outputStream = new FileOutputStream(outFile);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
//        try{
//            outputStream.flush();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        try{
//            outputStream.close();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }

//        mGetImage.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));

//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, CAMERA_PHOTO_REQUEST);
    }

    public void clickToVideo(View view) {
//        File sdCard = Environment.getExternalStorageDirectory();
//        String realPath = sdCard.getAbsolutePath();
//        File directory = new File(realPath + "/MyDBs");
//        File file = new File(directory, outputVideoName);
//
//        outputFileUri = Uri.fromFile(file);
//
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
//        startActivityForResult(intent, CAMERA_VIDEO_REQUEST);

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
            } else {
                int width = imgPresent.getWidth();
                int height = imgPresent.getHeight();
                BitmapFactory.Options factoryOptions = new BitmapFactory.Options();
                factoryOptions.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(outputFileUri.getPath(), factoryOptions);
                int imageWidth = factoryOptions.outWidth;
                int imageHeight = factoryOptions.outHeight;

                // Determine how much to scale down the image
                int scaleFactor = Math.min(imageWidth / width, imageHeight / height);
                // Decode the image file into a Bitmap sized to fill the View
                factoryOptions.inJustDecodeBounds = false;
                factoryOptions.inSampleSize = scaleFactor;
                factoryOptions.inPurgeable= true;
                Bitmap bitmap = BitmapFactory.decodeFile(outputFileUri.getPath(), factoryOptions);
                Toast.makeText(this, "File has already started", Toast.LENGTH_LONG).show();
                imgPresent.setImageBitmap(bitmap);
            }
        } else if(requestCode == CAMERA_VIDEO_REQUEST){
//            Log.d("ddd", "abc");
//            videoPresent.setVideoPath(outputFileUri.getPath());
//            videoPresent.start();
//            videoPresent.setVideoPath(data.getData().getPath());
//            videoPresent.start();
            outputFileUri = data.getData();
            Log.i("VIDEO_RECORD_TAG", "Video is recorded and available at path: "+ outputFileUri);
        } else if(requestCode == CAMERA_PHOTO_REQUEST) {
//            int width = imgPresent.getWidth();
//            int height = imgPresent.getHeight();
//            BitmapFactory.Options factoryOptions = new BitmapFactory.Options();
//            factoryOptions.inJustDecodeBounds = true;
//            BitmapFactory.decodeFile(outputFileUri.getPath(), factoryOptions);
//            int imageWidth = factoryOptions.outWidth;
//            int imageHeight = factoryOptions.outHeight;
//
//            // Determine how much to scale down the image
//            int scaleFactor = Math.min(imageWidth / width, imageHeight / height);
//            // Decode the image file into a Bitmap sized to fill the View
//            factoryOptions.inJustDecodeBounds = false;
//            factoryOptions.inSampleSize = scaleFactor;
//            factoryOptions.inPurgeable= true;
//            Bitmap bitmap = BitmapFactory.decodeFile(outputFileUri.getPath(), factoryOptions);
//            Toast.makeText(this, "File has already started", Toast.LENGTH_LONG).show();
//            imgPresent.setImageBitmap(bitmap);

            System.out.println("CAMERA_PHOTO_REQUEST");

            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");

            saveImageToExternalStorage(UUID.randomUUID().toString(),bitmap);
            Toast.makeText(MyCameraActivity.this,"saved Image Successfully" ,Toast.LENGTH_SHORT).show();

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