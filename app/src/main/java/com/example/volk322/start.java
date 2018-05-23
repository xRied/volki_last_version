
package com.example.volk322;



import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class start extends AppCompatActivity implements View.OnClickListener {

    static public Bitmap bitmap0;
    static final private int change = 0;
    static private boolean izm = false;
    private String PhotoPath ;
    private String last_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);

        Button filter_btn = (Button) findViewById(R.id.button2);
        Button back_btn = (Button) findViewById(R.id.button_back);
        Button back_save = (Button) findViewById(R.id.button_save);
        Button nerezk_btn = (Button) findViewById(R.id.button5);

        filter_btn.setOnClickListener(this);
        back_btn.setOnClickListener(this);
        back_save.setOnClickListener(this);
        nerezk_btn.setOnClickListener(this);
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
                  setPicCam();
            }
        }

    }

    private File createImageFile() throws IOException {

        String imageFileName = "JPEG_" + System.currentTimeMillis() + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        PhotoPath = image.getAbsolutePath();
        Log.d("path=",PhotoPath);
        return image;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
                Intent intent = new Intent(this, filters_activity.class);
                last_button="filters";
                startActivityForResult(intent, change);
                break;

            case R.id.button_back:
                Dialog dialog1= onCreateBackDialog();
                dialog1.show();

                break;

            case R.id.button_save:
                Dialog dialog2= onCreateSaveDialog();
                dialog2.show();
                break;

            case R.id.button5:
                Intent intent2 = new Intent(this, nerezkoe.class);
                last_button="nerezk";
                startActivityForResult(intent2, change);
                break;

            default:
                break;
        }
    }

    public Dialog onCreateSaveDialog() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setMessage(getString(R.string.Save_image_question));
        dialog.setPositiveButton("Да",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which) {
                try {
                    startSave();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        dialog.setNegativeButton("Нет",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which) {
                dialog.dismiss();
            }
        });


        return dialog.create();
    }

    public Dialog onCreateBackDialog() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setMessage(getString(R.string.back_question));
        dialog.setPositiveButton("Да",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which) {
                try {
                    startSave();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                izm = false;
                finish();
            }
        });
        dialog.setNegativeButton("Нет",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which) {
                dialog.dismiss();
                izm = false;
                finish();
            }

        });
        dialog.setNeutralButton("Отмена",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        return dialog.create();
    }

    public void onBackPressed() {

        Dialog dialog3= onCreateBackDialog();
        dialog3.show();

    }

    public void startSave() throws IOException {

        FileOutputStream fileOutputStream =null;
        File new_file =  createImageFile();
        try{
            fileOutputStream = new FileOutputStream(new_file);
            bitmap0.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
            Toast.makeText(this,getString(R.string.save_success),Toast.LENGTH_SHORT).show();
            fileOutputStream.flush();
            fileOutputStream.close();
        }
        catch (FileNotFoundException e){

        }

        refreshGallery(new_file);

    }

    public void refreshGallery(File file){
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        sendBroadcast(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        ImageView mImageView = (ImageView) findViewById(R.id.imageView);


        if (resultCode == RESULT_OK) {

            if (last_button.equals("filters")) {
                String cha1 = data.getStringExtra("Result_filter");

                if (cha1.equals("no")) {
                    mImageView.setImageBitmap(bitmap0);
                }
                if (cha1.equals("ok"))

                {
                    bitmap0 = filters_activity.BitmapGetter();
                    mImageView.setImageBitmap(bitmap0);
                    izm = true;

                }
            }
            if (last_button.equals("nerezk")) {
                String cha2 = data.getStringExtra("Result_nerezk");
                if (cha2.equals("no")) {
                    mImageView.setImageBitmap(bitmap0);
                }
                if (cha2.equals("ok"))
                {
                    bitmap0 = nerezkoe.BitmapGetter();
                    mImageView.setImageBitmap(bitmap0);
                    izm = true;
                }
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