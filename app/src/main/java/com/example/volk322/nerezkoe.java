
package com.example.volk322;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static java.lang.Math.abs;
import static java.lang.Math.exp;


public class nerezkoe extends AppCompatActivity {

    static public ImageView img;
    static public Bitmap bitmap1;
    public boolean is_asynk_go= false;
    public int rad;
    public double am;
    public int th;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nerezkoe);
        Button back_btn = (Button) findViewById(R.id.button_back);
        Button ok_btn = (Button) findViewById(R.id.button_save);
        Button btn = (Button) findViewById(R.id.prosmotr_btn);
        final EditText radius = (EditText) findViewById(R.id.radius);
        final EditText amount = (EditText) findViewById(R.id.effect);
        final EditText threshold = (EditText) findViewById(R.id.porog);
        img = (ImageView) findViewById(R.id.imageView);

        img.setImageBitmap(start.BitmapGetter());


        back_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
             //   if (!is_asynk_go) {
                    Intent answerIntent = new Intent();
                    answerIntent.putExtra("Result_nerezk", "no");
                    setResult(RESULT_OK, answerIntent);
                    finish();
              //  } else {
             //       Toast.makeText(filters_activity.this, "Процесс ещё идёт", Toast.LENGTH_SHORT).show();
              //  }
            }
        });
        ok_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            //    if (!is_asynk_go) {
                    Intent answerIntent = new Intent();
                    answerIntent.putExtra("Result_nerezk", "ok");
                    setResult(RESULT_OK, answerIntent);
                    finish();
             //   } else {
              //      Toast.makeText(filters_activity.this, "Процесс ещё идёт", Toast.LENGTH_SHORT).show();
            //    }
            }
        });

        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {



                int k=3;
                if (radius.getText().toString().equals(""))
                {
                    k--;
                }

                if (amount.getText().toString().equals(""))
                {
                    k--;
                }
                if (threshold.getText().toString().equals(""))
                {
                    k--;
                }
                if (k==3) {

                    rad=Integer.parseInt(radius.getText().toString());
                    am=Integer.parseInt(amount.getText().toString())/100;
                    th=Integer.parseInt(threshold.getText().toString());
                    int am_prov=Integer.parseInt(amount.getText().toString());

                    if (!is_asynk_go) {
                        if ((rad < 21 && rad > 0) && (am_prov > -1 && am_prov < 501) && (th > -1 && th < 256)) {

                            NerezTask task = new NerezTask();
                            task.execute(start.BitmapGetter());
                        } else {
                            Toast.makeText(nerezkoe.this,"Недопустимое значение",Toast.LENGTH_SHORT).show();

                        }
                    }
                    else{
                        Toast.makeText(nerezkoe.this, "Процесс ещё идёт", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(nerezkoe.this,"Не все данные введены",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    public int[][] getColourMap (Bitmap bitmap,int width,int height,int kernelsize)
    {
        int [][] cmap = new int [width+(kernelsize/2)*2][height+(kernelsize/2)*2];
        for (int x=0;x<width;x++) {
            for (int y = 0; y < height; y++) {
                cmap[x + (kernelsize / 2)][y + (kernelsize / 2)]=bitmap.getPixel(x, y);
            }
        }
        int bx=(kernelsize / 2);
        int by=(kernelsize / 2);
        int ex=(kernelsize / 2)+width-1;
        int ey=(kernelsize / 2)+height-1;

        for (int i=0;i<(kernelsize / 2);i++)
        {
            cmap[bx-1][by-1]=cmap[bx][by];
            cmap[bx-1][ey+1]=cmap[bx][ex];
            cmap[ex+1][by-1]=cmap[ex][by];
            cmap[ex+1][ey+1]=cmap[ex][ey];
            for (int j=bx;j<=ex;j++){
                cmap[j][by-1]=cmap[j][by];
            }
            for (int j=bx;j<=ex;j++){
                cmap[j][ey+1]=cmap[j][ey];
            }
            for (int j=by;j<=ey;j++){
                cmap[bx-1][j]=cmap[bx][j];
            }
            for (int j=by;j<=ey;j++){
                cmap[ex+1][j]=cmap[ex][j];
            }

        }
        return cmap;
    }





    public class NerezTask extends AsyncTask<Bitmap, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            is_asynk_go = true;
            Toast.makeText(nerezkoe.this,"Фильтрация началась",Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onPostExecute(Bitmap bitmapP) {
            img = (ImageView) findViewById(R.id.imageView);
            img.setImageBitmap(bitmapP);
            is_asynk_go = false;
            Toast.makeText(nerezkoe.this,"Фильтрация закончилась",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Bitmap doInBackground(Bitmap... parameter) {
            int width = parameter[0].getWidth();
            int height = parameter[0].getHeight();
            Bitmap bitmapP = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

            int[][] cmap=getColourMap(parameter[0],width,height,(rad*2)+1);
            double[] coef =GetGausCoef();
            int[][] gcmap=SkrutkaGausHor(cmap,width,height,coef);
            gcmap=SkrutkaGausVer(gcmap,width,height,coef);
            cmap=CutOff(cmap,width,height);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int sum=0;
                    int r=Color.red(cmap[x][y]);
                    int g=Color.green(cmap[x][y]);
                    int b=Color.blue(cmap[x][y]);
                    int rnew=Color.red(gcmap[x][y]);
                    int gnew=Color.green(gcmap[x][y]);
                    int bnew=Color.blue(gcmap[x][y]);
                    sum=abs((r-rnew)+(g-gnew)+(b-bnew))*2;
                    if (sum>th){
                        r+=(r-rnew)*am;
                        g+=(g-gnew)*am;
                        b+=(b-bnew)*am;

                        if (r < 0) r = 0;
                        if (r > 255) r = 255;

                        if (g < 0) g = 0;
                        if (g > 255) g = 255;

                        if (b < 0) b = 0;
                        if (b > 255) b = 255;
                    }
                    int newpixel =Color.argb(255,(int)r,(int)g,(int)b);
                    bitmapP.setPixel(x,y,newpixel);

                }
            }





            return bitmapP;
        }


        private int[][] SkrutkaGausVer (int[][] input,int width,int height ,double[] kernel ){

            int[][] output=new int [width][height];
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++){
                    double rSum = 0, gSum = 0, bSum = 0, kSum = 0;
                    for (int i = 0; i < (rad*2)+1; i++) {

                        int pixelPosX = x ;
                        int pixelPosY = y+i;
                        int r =  Color.red(input[pixelPosX][pixelPosY]);
                        int g =  Color.green(input[pixelPosX][pixelPosY]);
                        int b =  Color.blue(input[pixelPosX][pixelPosY]);
                        double kernelVal = kernel[i];
                        rSum += r * kernelVal;
                        gSum += g * kernelVal;
                        bSum += b * kernelVal;
                        kSum += kernelVal;

                    }

                    if (kSum <= 0) kSum = 1;
                    rSum /= kSum;

                    if (rSum < 0) rSum = 0;
                    if (rSum > 255) rSum = 255;
                    gSum /= kSum;

                    if (gSum < 0) gSum = 0;
                    if (gSum > 255) gSum = 255;
                    bSum /= kSum;

                    if (bSum < 0) bSum = 0;
                    if (bSum > 255) bSum = 255;

                    int alphapixel=Color.alpha(input[x+rad][y+rad]);
                    int newpixel =Color.argb(alphapixel,(int)rSum,(int)gSum,(int)bSum);
                    output[x][y]=newpixel;

                }
            }

            return output;

        }
        private int[][] SkrutkaGausHor (int[][] input,int width,int height ,double[] kernel ){

            int[][] output=new int [width+rad*2][height+rad*2];
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++){
                    double rSum = 0, gSum = 0, bSum = 0, kSum = 0;
                    for (int i = 0; i < (rad*2)+1; i++) {

                        int pixelPosX = x + i;
                        int pixelPosY = y;
                        int r =  Color.red(input[pixelPosX][pixelPosY]);
                        int g =  Color.green(input[pixelPosX][pixelPosY]);
                        int b =  Color.blue(input[pixelPosX][pixelPosY]);
                        double kernelVal = kernel[i];
                        rSum += r * kernelVal;
                        gSum += g * kernelVal;
                        bSum += b * kernelVal;
                        kSum += kernelVal;

                    }

                    if (kSum <= 0) kSum = 1;
                    rSum /= kSum;

                    if (rSum < 0) rSum = 0;
                    if (rSum > 255) rSum = 255;
                    gSum /= kSum;

                    if (gSum < 0) gSum = 0;
                    if (gSum > 255) gSum = 255;
                    bSum /= kSum;

                    if (bSum < 0) bSum = 0;
                    if (bSum > 255) bSum = 255;

                    int alphapixel=Color.alpha(input[x+rad][y+rad]);
                    int newpixel =Color.argb(alphapixel,(int)rSum,(int)gSum,(int)bSum);
                    output[x+rad][y+rad]=newpixel;
                }
            }


            int bx=(rad);
            int by=(rad);
            int ex=(rad)+width-1;
            int ey=(rad)+height-1;

            for (int i=0;i<rad;i++)
            {
                output[bx-1][by-1]=output[bx][by];
                output[bx-1][ey+1]=output[bx][ex];
                output[ex+1][by-1]=output[ex][by];
                output[ex+1][ey+1]=output[ex][ey];
                for (int j=bx;j<=ex;j++){
                    output[j][by-1]=output[j][by];
                }
                for (int j=bx;j<=ex;j++){
                    output[j][ey+1]=output[j][ey];
                }
                for (int j=by;j<=ey;j++){
                    output[bx-1][j]=output[bx][j];
                }
                for (int j=by;j<=ey;j++){
                    output[ex+1][j]=output[ex][j];
                }

            }

            return output;

        }

        private double[] GetGausCoef (){
            double sigma=rad/3;
            double[] coef= new double[(rad*2)+1];
            coef[rad]=1;
            for (int i=1;i<=rad;i++) {
                coef[rad+1]=exp(-1*(i*i)*(2*sigma*sigma));
                coef[rad-i]=coef[rad+i];
            }
            return coef;

        }


        private int[][] CutOff (int[][] input,int width,int height)
        {
            int [][] output = new int [width][height];
            for (int x=0;x<width;x++) {
                for (int y = 0; y < height; y++) {
                    output[x][y]=input[x + rad][y + rad];
                }
            }

            return output;
        }

    }




    public static Bitmap BitmapGetter(){

        bitmap1=((BitmapDrawable)img.getDrawable()).getBitmap();
        return bitmap1;
    }
}
