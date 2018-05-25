package com.example.volk322;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class mashtab extends AppCompatActivity {


    static public ImageView img;
    static public Bitmap bitmap1;
    public boolean is_asynk_go= false;
    public int mas_koef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mashtab);


        Button back_btn = (Button) findViewById(R.id.button_back);
        Button ok_btn = (Button) findViewById(R.id.button_save);
        Button btn = (Button) findViewById(R.id.prosmotr_btn);

        final EditText amount = (EditText) findViewById(R.id.koef);

        img = (ImageView) findViewById(R.id.imageView);
        img.setImageBitmap(start.BitmapGetter());


        back_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!is_asynk_go) {
                    Intent answerIntent = new Intent();
                    answerIntent.putExtra("Result_mash", "no");
                    setResult(RESULT_OK, answerIntent);
                    finish();
                } else {
                    Toast.makeText(mashtab.this, "Процесс ещё идёт", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ok_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!is_asynk_go) {
                    Intent answerIntent = new Intent();
                    answerIntent.putExtra("Result_mash", "ok");
                    setResult(RESULT_OK, answerIntent);
                    finish();
                } else {     Toast.makeText(mashtab.this, "Процесс ещё идёт", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {


                int k=1;
                if (amount.getText().toString().equals(""))
                {
                    k--;
                }

                if (k==1) {


                    mas_koef =Integer.parseInt(amount.getText().toString());

                    if (!is_asynk_go) {
                        if (mas_koef < 301 && mas_koef > 0)  {

                            MasTask task = new MasTask();
                            task.execute(start.BitmapGetter());
                        } else {
                            Toast.makeText(mashtab.this, "Недопустимое значение", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else{
                        Toast.makeText(mashtab.this, "Процесс ещё идёт", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(mashtab.this,"Данные не введены",Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    public class MasTask extends AsyncTask<Bitmap, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            is_asynk_go = true;
            Toast.makeText(mashtab.this, "Фильтрация началась", Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onPostExecute(Bitmap bitmapP) {
            img = (ImageView) findViewById(R.id.imageView);
            img.setImageBitmap(bitmapP);
            is_asynk_go = false;
            Toast.makeText(mashtab.this, "Фильтрация закончилась", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Bitmap doInBackground(Bitmap... parameter) {
            int width = parameter[0].getWidth();
            int height = parameter[0].getHeight();
            int [][] cmap = new int [width][height];
            for (int x=0;x<width;x++) {
                for (int y = 0; y < height; y++) {
                    cmap[x ][y ]=parameter[0].getPixel(x, y);
                }
            }
            int new_width = width*mas_koef/100;
            int new_height = height*mas_koef/100;
            Bitmap bitmapP = Bitmap.createBitmap(new_width, new_height, Bitmap.Config.ARGB_8888);
            int newmap[] = bilinear(cmap , width , height , new_width , new_height);
            bitmapP.setPixels(newmap,0,new_width,0,0,new_width,new_height);
            return bitmapP;
        }
        private int[] bilinear(int[][] input, int w, int h, int w2, int h2){
            int[] output = new int[w2*h2];
            double x_ratio = ((double)(w-1))/w2;
            double y_ratio = ((double)(h-1))/h2 ;
            int offset=0;
            for (int i=0;i<h2;i++) {
                for (int j=0;j<w2;j++) {
                    int x = (int)(x_ratio * j) ;
                    int y = (int)(y_ratio * i) ;
                    double x_diff = (x_ratio * j) - x ;
                    double y_diff = (y_ratio * i) - y ;
                    int a = input[x][y];
                    int b = input[x+1][y];
                    int c = input[x][y+1];
                    int d = input[x+1][y+1];
                    double blue = (a&0xff)*(1-x_diff)*(1-y_diff) + (b&0xff)*(x_diff)*(1-y_diff) +
                            (c&0xff)*(y_diff)*(1-x_diff)   + (d&0xff)*(x_diff*y_diff);

                    // green element
                    // Yg = Ag(1-w)(1-h) + Bg(w)(1-h) + Cg(h)(1-w) + Dg(wh)
                    double green = ((a>>8)&0xff)*(1-x_diff)*(1-y_diff) + ((b>>8)&0xff)*(x_diff)*(1-y_diff) +
                            ((c>>8)&0xff)*(y_diff)*(1-x_diff)   + ((d>>8)&0xff)*(x_diff*y_diff);

                    // red element
                    // Yr = Ar(1-w)(1-h) + Br(w)(1-h) + Cr(h)(1-w) + Dr(wh)
                    double red = ((a>>16)&0xff)*(1-x_diff)*(1-y_diff) + ((b>>16)&0xff)*(x_diff)*(1-y_diff) +
                            ((c>>16)&0xff)*(y_diff)*(1-x_diff)   + ((d>>16)&0xff)*(x_diff*y_diff);

                    output[offset++] =
                            0xff000000 | // alpha
                                    ((((int)red)<<16)&0xff0000) |
                                    ((((int)green)<<8)&0xff00) |
                                    ((int)blue) ;
                }
            }
            return output ;
        }
    }

    public static Bitmap BitmapGetter(){

        bitmap1=((BitmapDrawable)img.getDrawable()).getBitmap();
        return bitmap1;
    }
}
