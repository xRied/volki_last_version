package com.example.volk322;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class povorot extends AppCompatActivity {

    public boolean is_asynk_go = false;
    static public Bitmap bitmap322;
    static public ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_povorot);

        Button back_btn = (Button) findViewById(R.id.button_back);
        Button ok_btn = (Button) findViewById(R.id.button_save);
        Button btn_90_l = (Button) findViewById(R.id.btn_90_l);
        Button btn_90_r = (Button) findViewById(R.id.btn_90_r);
        Button btn_180 = (Button) findViewById(R.id.btn_180);

        img = (ImageView) findViewById(R.id.imageView);
        img.setImageBitmap(start.BitmapGetter());
        bitmap322 = start.BitmapGetter();

        back_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (!is_asynk_go) {
                    Intent answerIntent = new Intent();
                    answerIntent.putExtra("Result_povorot", "no");
                    setResult(RESULT_OK, answerIntent);
                    finish();
                } else {
                    Toast.makeText(povorot.this, "Процесс ещё идёт", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ok_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!is_asynk_go) {
                    Intent answerIntent = new Intent();
                    answerIntent.putExtra("Result_povorot", "ok");
                    setResult(RESULT_OK, answerIntent);
                    finish();
                } else {
                    Toast.makeText(povorot.this, "Процесс ещё идёт", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btn_90_l.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!is_asynk_go) {

                        povorot.l_90_Task task = new povorot.l_90_Task();
                        task.execute(bitmap322);

                        bitmap322=((BitmapDrawable)img.getDrawable()).getBitmap();
                        img.setImageBitmap(bitmap322);

                } else {
                    Toast.makeText(povorot.this, "Процесс ещё идёт", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_90_r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!is_asynk_go) {

                        povorot.r_90_Task task = new povorot.r_90_Task();
                        task.execute(bitmap322);

                        bitmap322=((BitmapDrawable)img.getDrawable()).getBitmap();

                        img.setImageBitmap(bitmap322);

                } else {
                    Toast.makeText(povorot.this, "Процесс ещё идёт", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_180.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!is_asynk_go) {
                    povorot.p_180_Task task = new povorot.p_180_Task();
                    task.execute(bitmap322);

                    bitmap322=((BitmapDrawable)img.getDrawable()).getBitmap();

                    img.setImageBitmap(bitmap322);

                } else {
                    Toast.makeText(povorot.this, "Процесс ещё идёт", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static Bitmap BitmapGetter() {

        bitmap322 = ((BitmapDrawable) img.getDrawable()).getBitmap();
        return bitmap322;
    }

    public int[][] getColourMap(Bitmap bitmap, int width, int height) {
        int[][] cm = new int[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cm[x][y] = bitmap.getPixel(x, y);
            }
        }
        return cm;
    }

    public Bitmap povorot_90_l(Bitmap input) {

        int width1 = input.getWidth();
        int height1 = input.getHeight();
        int[][] cmap;
        cmap = getColourMap(input, width1, height1);

        Bitmap output = Bitmap.createBitmap(height1, width1, Bitmap.Config.ARGB_8888);
        for (int x1 = 0; x1 < width1; x1++) {
            for (int y1 = 0; y1 < height1; y1++) {
                output.setPixel(y1, x1, cmap[width1-1-x1][y1]);
            }
        }
        return output;

    }

    public Bitmap povorot_90_r(Bitmap input) {

        int width1 = input.getWidth();
        int height1 = input.getHeight();
        int[][] cmap;
        cmap = getColourMap(input, width1, height1);

        Bitmap output = Bitmap.createBitmap(height1, width1, Bitmap.Config.ARGB_8888);

        for (int x1 = 0; x1 < width1; x1++) {
            for (int y1 = 0; y1 < height1; y1++) {

                output.setPixel(y1, x1, cmap[x1][height1 - 1 - y1]);
            }
        }
        return output;

    }

    public Bitmap povorot_180(Bitmap input) {

        int width1 = input.getWidth();
        int height1 = input.getHeight();
        int[][] cmap;
        cmap = getColourMap(input, width1, height1);

        Bitmap output = Bitmap.createBitmap(width1, height1, Bitmap.Config.ARGB_8888);

        for (int x1 = 0; x1 < width1; x1++) {
            for (int y1 = 0; y1 < height1; y1++) {

                output.setPixel(width1 - 1 - x1, height1 - 1 - y1, cmap[x1][y1]);
            }
        }
        return output;

    }


    public class l_90_Task extends AsyncTask<Bitmap, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            is_asynk_go = true;
            Toast.makeText(povorot.this, "Изменение началось", Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onPostExecute(Bitmap bitmapP) {
            img = (ImageView) findViewById(R.id.imageView);
            img.setImageBitmap(bitmapP);

            is_asynk_go = false;
            Toast.makeText(povorot.this, "Изменение закончилось", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Bitmap doInBackground(Bitmap... parameter) {
            bitmap322 = povorot_90_l(bitmap322);

            return bitmap322;
        }
    }

    public class r_90_Task extends AsyncTask<Bitmap, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            is_asynk_go = true;
            Toast.makeText(povorot.this, "Изменение началось", Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onPostExecute(Bitmap bitmapP) {
            img = (ImageView) findViewById(R.id.imageView);
            img.setImageBitmap(bitmapP);

            is_asynk_go = false;
            Toast.makeText(povorot.this, "Изменение закончилось", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Bitmap doInBackground(Bitmap... parameter) {
            bitmap322 = povorot_90_r(bitmap322);

            return bitmap322;
        }
    }

    public class p_180_Task extends AsyncTask<Bitmap, Void, Bitmap> {

            @Override
            protected void onPreExecute() {
                is_asynk_go = true;
                Toast.makeText(povorot.this, "Изменение началось", Toast.LENGTH_SHORT).show();

            }

            @Override
            protected void onPostExecute(Bitmap bitmapP) {
                img = (ImageView) findViewById(R.id.imageView);
                img.setImageBitmap(bitmapP);

                is_asynk_go = false;
                Toast.makeText(povorot.this, "Изменение закончилосьь", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected Bitmap doInBackground(Bitmap... parameter) {
                bitmap322 = povorot_180(bitmap322);

                return bitmap322;
            }
    }




    }
