
package com.example.volk322;


import android.content.Intent;
import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.ByteBuffer;


public class start extends AppCompatActivity implements View.OnClickListener {

    static public Bitmap bitmap0;
    static final private int change = 0;
    static private boolean izm = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);
        Button filter_btn = (Button) findViewById(R.id.button2);
        filter_btn.setOnClickListener(this);
        bitmap0=start_vibor.BitmapGetter();

    }

    public void onWindowFocusChanged(boolean hasFocus) {
        if(!izm) {
            super.onWindowFocusChanged(hasFocus);
            ImageView mImageView = (ImageView) findViewById(R.id.imageView);

            if (hasFocus) {

                if (start_vibor.CamGetter() == 1)
                    setPicCam();
                else if (start_vibor.CamGetter() == 2)
                //   mImageView.setImageBitmap(bitmap0);
                  setPicCam();
            }
        }

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
                Intent intent = new Intent(this, filters_activity.class);
                startActivityForResult(intent, change);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        ImageView mImageView = (ImageView) findViewById(R.id.imageView);


        if (resultCode == RESULT_OK) {
            String cha = data.getStringExtra("Result");
            if (cha.equals("no")) {
                mImageView.setImageBitmap(bitmap0);
            }

            if (cha.equals("ok"))

            {

                bitmap0 = filters_activity.BitmapGetter();
                mImageView.setImageBitmap(bitmap0);
                izm=true;

            }
        }
        else if (resultCode == RESULT_CANCELED){
            mImageView.setImageBitmap(bitmap0);
        }

    }

    public static Bitmap BitmapGetter(){
        return bitmap0;
    }



    private void setPicCam() {

        ImageView mImageView = (ImageView) findViewById(R.id.imageView);

        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();


        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(start_vibor.PathGetter(), bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        bmOptions.inSampleSize =scaleFactor+3;
        bmOptions.inJustDecodeBounds = false;


        bitmap0 = BitmapFactory.decodeFile(start_vibor.PathGetter(), bmOptions);
        mImageView.setImageBitmap(bitmap0);
    }
}