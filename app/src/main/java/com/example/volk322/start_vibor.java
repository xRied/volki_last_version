package com.example.volk322;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class start_vibor extends AppCompatActivity implements View.OnClickListener {

    static final int GALLERY_REQUEST = 1;
    static final int REQUEST_TAKE_PHOTO = 1;

    static public Bitmap bitmap1;
    static public int cam;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static public String mCurrentPhotoPath;
    static public Uri photoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_vibor);
        Button gal_btn = (Button) findViewById(R.id.gal_btn);
        Button cam_btn = (Button) findViewById(R.id.cam_btn);
        gal_btn.setOnClickListener(this);
        cam_btn.setOnClickListener(this);


    }

    private File createImageFile() throws IOException {

        String imageFileName = "JPEG_" + System.currentTimeMillis() + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();

        return image;
    }

public static Bitmap BitmapGetter(){
        return bitmap1;
}
public static String PathGetter(){
        return mCurrentPhotoPath;
    }
public static int CamGetter(){
        return cam;
    }

    public void onClick(View view) {



        switch (view.getId()) {

            case R.id.cam_btn:
                cam=1;

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {

                    }

                    if (photoFile != null) {
                        photoUri = FileProvider.getUriForFile(this,
                                "com.example.android.fileprovider",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                    }
                }

                break;

            case R.id.gal_btn:
                cam=2;

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);


                break;

            default:
                break;
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (cam == 1) {

           // if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
          //      Bundle extras = data.getExtras();
          //      bitmap1 = (Bitmap) extras.get("data");
         //   }
            Uri selectedImage = photoUri;
            try {

                bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }

                Intent intent2 = new Intent(this, start.class);
                startActivity(intent2);

        }
        else if (cam == 2) {

            super.onActivityResult(requestCode, resultCode, data);

            switch (requestCode) {
                case GALLERY_REQUEST:
                    if (resultCode == RESULT_OK) {
                        Uri selectedImage = data.getData();
                        try {

                            bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
            }
            Intent intent2 = new Intent(this, start.class);
            startActivity(intent2);
        }
    }


}