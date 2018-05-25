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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class filters_activity extends AppCompatActivity {
    static final double[][] cetk = {{-1, -1, -1}, {-1, 9, -1}, {-1, -1, -1}};
    static final double[][] rel = {{-2, -1, 0}, {-1, 1, 1}, {0, 1, 2}};
    static final double[][] kra = {{0, 1, 0}, {1, -4, 1}, {0, 1, 0}};
    static final double[][] tis = {{0, 1, 0}, {-1, 0, 1}, {0, -1, 0}};
    public boolean is_asynk_go= false;
    static public ImageView img;

    static public Bitmap bitmap322;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters_activity);


        Button rel_btn = (Button) findViewById(R.id.rel_btn);
        Button cb_btn = (Button) findViewById(R.id.cb_btn);
        Button cetk_btn = (Button) findViewById(R.id.cetk_btn);
        Button purple_btn = (Button) findViewById(R.id.purple_btn);
        Button sepia_btn = (Button) findViewById(R.id.sepia_btn);
        Button neg_btn = (Button) findViewById(R.id.neg_btn);
        Button tis_btn = (Button) findViewById(R.id.tis_btn);
        Button aqua_btn = (Button) findViewById(R.id.aqua_btn);
        Button back_btn = (Button) findViewById(R.id.button_back);
        Button ok_btn = (Button) findViewById(R.id.button_save);
        img = (ImageView) findViewById(R.id.imageView);

        img.setImageBitmap(start.BitmapGetter());


        back_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if (!is_asynk_go){
                    Intent answerIntent = new Intent();
                    answerIntent.putExtra("Result_filter","no");
                    setResult(RESULT_OK, answerIntent);
                    finish();
                }
                else {
                    Toast.makeText(filters_activity.this,"Процесс ещё идёт",Toast.LENGTH_SHORT).show();
                }
            }
        });
        ok_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if (!is_asynk_go){
                    Intent answerIntent = new Intent();
                    answerIntent.putExtra("Result_filter","ok");
                    setResult(RESULT_OK, answerIntent);
                    finish();
                }
                else {
                    Toast.makeText(filters_activity.this,"Процесс ещё идёт",Toast.LENGTH_SHORT).show();
                }
            }
        });

        cb_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if (!is_asynk_go){
                    CBTask task = new CBTask();
                    task.execute(start.BitmapGetter());
                    bitmap322=((BitmapDrawable)img.getDrawable()).getBitmap();

                }
                else {
                    Toast.makeText(filters_activity.this,"Процесс ещё идёт",Toast.LENGTH_SHORT).show();
                }
            }
        });
        rel_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if (!is_asynk_go){
                    RelTask task = new RelTask();
                    task.execute(start.BitmapGetter());
                    bitmap322=((BitmapDrawable)img.getDrawable()).getBitmap();
                }
                else {
                    Toast.makeText(filters_activity.this,"Процесс ещё идёт",Toast.LENGTH_SHORT).show();
                }
            }
        });
        cetk_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if (!is_asynk_go){
                    CetkTask task = new CetkTask();
                    task.execute(start.BitmapGetter());
                    bitmap322=((BitmapDrawable)img.getDrawable()).getBitmap();
                }
                else {
                    Toast.makeText(filters_activity.this,"Процесс ещё идёт",Toast.LENGTH_SHORT).show();
                }
            }
        });
        purple_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if (!is_asynk_go){
                    PurplTask task = new PurplTask();
                    task.execute(start.BitmapGetter());
                    bitmap322=((BitmapDrawable)img.getDrawable()).getBitmap();
                }
                else {
                    Toast.makeText(filters_activity.this,"Процесс ещё идёт",Toast.LENGTH_SHORT).show();
                }
            }
        });
        sepia_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if (!is_asynk_go){
                    SepiaTask task = new SepiaTask();
                    task.execute(start.BitmapGetter());
                    bitmap322=((BitmapDrawable)img.getDrawable()).getBitmap();
                }
                else {
                    Toast.makeText(filters_activity.this,"Процесс ещё идёт",Toast.LENGTH_SHORT).show();
                }
            }
        });
        neg_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if (!is_asynk_go){
                    NegTask task = new NegTask();
                    task.execute(start.BitmapGetter());
                    bitmap322=((BitmapDrawable)img.getDrawable()).getBitmap();
                }
                else {
                    Toast.makeText(filters_activity.this,"Процесс ещё идёт",Toast.LENGTH_SHORT).show();
                }
            }
        });
        tis_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if (!is_asynk_go){
                    TisTask task = new TisTask();
                    task.execute(start.BitmapGetter());
                    bitmap322=((BitmapDrawable)img.getDrawable()).getBitmap();
                }
                else {
                    Toast.makeText(filters_activity.this,"Процесс ещё идёт",Toast.LENGTH_SHORT).show();
                }

            }
        });
        aqua_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if (!is_asynk_go){
                    AquaTask task = new AquaTask();
                    task.execute(start.BitmapGetter());
                    bitmap322=((BitmapDrawable)img.getDrawable()).getBitmap();
                }
                else {
                    Toast.makeText(filters_activity.this,"Процесс ещё идёт",Toast.LENGTH_SHORT).show();
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


    public static Bitmap BitmapGetter(){

        bitmap322=((BitmapDrawable)img.getDrawable()).getBitmap();
        return bitmap322;
    }


    public class AquaTask extends AsyncTask<Bitmap, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            is_asynk_go = true;
            Toast.makeText(filters_activity.this,"Фильтрация началась",Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onPostExecute(Bitmap bitmapP) {
            img = (ImageView) findViewById(R.id.imageView);
            img.setImageBitmap(bitmapP);

            is_asynk_go = false;
            Toast.makeText(filters_activity.this,"Фильтрация закончилась",Toast.LENGTH_SHORT).show();


        }

        @Override
        protected Bitmap doInBackground(Bitmap... parameter) {
            Bitmap bitmapP=parameter[0];
            bitmapP = SkrutkaMed(start.BitmapGetter(),5);
            bitmapP = SkrutkaNEW(bitmapP,cetk,3);



            return bitmapP;
        }






        private Bitmap SkrutkaMed (Bitmap input,  int kernelsize ){
            int width = input.getWidth();
            int height = input.getHeight();
            int[][] cmap=getColourMap(input,width,height,kernelsize);
            Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++){
                    int [] medianr = new int[25];
                    int [] mediang = new int[25];
                    int [] medianb = new int[25];
                    int c=0;
                    for (int i = 0; i < kernelsize; i++) {
                        for (int j = 0; j < kernelsize; j++) {
                            int pixelPosX = x + i;
                            int pixelPosY = y + j;
                            int r =  Color.red(cmap[pixelPosX][pixelPosY]);
                            int g =  Color.green(cmap[pixelPosX][pixelPosY]);
                            int b =  Color.blue(cmap[pixelPosX][pixelPosY]);
                            medianr[c]=r;
                            mediang[c]=g;
                            medianb[c]=b;
                            c++;
                        }
                    }
                    java.util.Arrays.sort(medianr);
                    java.util.Arrays.sort(mediang);
                    java.util.Arrays.sort(medianb);

                    int alphapixel=Color.alpha(cmap[x+(kernelsize/2)][y+(kernelsize/2)]);
                    int newpixel =Color.argb(alphapixel,medianr[13],mediang[13],medianb[13]);
                    output.setPixel(x,y,newpixel);
                }
            }
            return output;



        }

        private Bitmap SkrutkaNEW (Bitmap input, double[][] kernel, int kernelsize ){
            int width = input.getWidth();
            int height = input.getHeight();
            int[][] cmap=getColourMap(input,width,height,kernelsize);
            Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++){
                    double rSum = 0, gSum = 0, bSum = 0, kSum = 0;
                    for (int i = 0; i < kernelsize; i++) {
                        for (int j = 0; j < kernelsize; j++) {
                            int pixelPosX = x + i;
                            int pixelPosY = y + j;
                            int r =  Color.red(cmap[pixelPosX][pixelPosY]);
                            int g =  Color.green(cmap[pixelPosX][pixelPosY]);
                            int b =  Color.blue(cmap[pixelPosX][pixelPosY]);
                            double kernelVal = kernel[i][j];
                            rSum += r * kernelVal;
                            gSum += g * kernelVal;
                            bSum += b * kernelVal;
                            kSum += kernelVal;
                        }
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

                    int alphapixel=Color.alpha(cmap[x+(kernelsize/2)][y+(kernelsize/2)]);
                    int newpixel =Color.argb(alphapixel,(int)rSum,(int)gSum,(int)bSum);
                    output.setPixel(x,y,newpixel);
                }
            }
            return output;



        }
    }



    public class NegTask extends AsyncTask<Bitmap, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            is_asynk_go = true;
            Toast.makeText(filters_activity.this,"Фильтрация началась",Toast.LENGTH_SHORT).show();

        }
        @Override
        protected void onPostExecute(Bitmap bitmapP) {
            img = (ImageView) findViewById(R.id.imageView);
            img.setImageBitmap(bitmapP);

            is_asynk_go = false;
            Toast.makeText(filters_activity.this,"Фильтрация закончилась",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Bitmap doInBackground(Bitmap... parameter) {
            Bitmap bitmapP=parameter[0];
            bitmapP = RGBNeg(start.BitmapGetter());


            return bitmapP;
        }






        private Bitmap RGBNeg (Bitmap input){
            int width = input.getWidth();
            int height = input.getHeight();
            int [][] cmap = new int [width][height];
            for (int x=0;x<width;x++) {
                for (int y = 0; y < height; y++) {
                    cmap[x ][y ]=input.getPixel(x, y);
                }
            }
            Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++){
                    int r =  Color.red(cmap[x][y]);
                    int g =  Color.green(cmap[x][y]);
                    int b =  Color.blue(cmap[x][y]);
                    r=255-r;
                    g=255-g;
                    b=255-b;
                    int alphapixel=Color.alpha(cmap[x][y]);
                    int newpixel =Color.argb(alphapixel,r,g,b);
                    output.setPixel(x,y,newpixel);
                }
            }
            return output;



        }

    }

    public class SepiaTask extends AsyncTask<Bitmap, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            is_asynk_go = true;
            Toast.makeText(filters_activity.this,"Фильтрация началась",Toast.LENGTH_SHORT).show();

        }
        @Override
        protected void onPostExecute(Bitmap bitmapP) {
            img = (ImageView) findViewById(R.id.imageView);
            img.setImageBitmap(bitmapP);

            is_asynk_go = false;
            Toast.makeText(filters_activity.this,"Фильтрация закончилась",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Bitmap doInBackground(Bitmap... parameter) {
            Bitmap bitmapP=parameter[0];
            bitmapP = RGBSepia(start.BitmapGetter());



            return bitmapP;
        }






        private Bitmap RGBSepia (Bitmap input){
            int width = input.getWidth();
            int height = input.getHeight();
            int [][] cmap = new int [width][height];
            for (int x=0;x<width;x++) {
                for (int y = 0; y < height; y++) {
                    cmap[x ][y ]=input.getPixel(x, y);
                }
            }
            Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++){
                    int r =  Color.red(cmap[x][y]);
                    int g =  Color.green(cmap[x][y]);
                    int b =  Color.blue(cmap[x][y]);
                    double nr= 0.393*r + 0.769*g + 0.189*b;
                    double ng= 0.349*r + 0.686*g + 0.168*b;
                    double nb= 0.272*r + 0.534*g + 0.131*b;
                    if (nr> 255)
                        r=255;
                    else
                        r=(int)nr;
                    if (ng> 255)
                        g=255;
                    else
                        g=(int)ng;
                    if (nb> 255)
                        b=255;
                    else
                        b=(int)nb;

                    int alphapixel=Color.alpha(cmap[x][y]);
                    int newpixel =Color.argb(alphapixel,r,g,b);
                    output.setPixel(x,y,newpixel);

                }
            }
            return output;



        }

    }


    public class PurplTask extends AsyncTask<Bitmap, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            is_asynk_go = true;
            Toast.makeText(filters_activity.this,"Фильтрация началась",Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onPostExecute(Bitmap bitmapP) {
            img = (ImageView) findViewById(R.id.imageView);
            img.setImageBitmap(bitmapP);

            is_asynk_go = false;
            Toast.makeText(filters_activity.this,"Фильтрация закончилась",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Bitmap doInBackground(Bitmap... parameter) {
            Bitmap bitmapP=parameter[0];
            bitmapP = RGBFilt(start.BitmapGetter(),0,256,0);



            return bitmapP;
        }






        private Bitmap RGBFilt (Bitmap input, int minR, int minG  , int minB){
            int width = input.getWidth();
            int height = input.getHeight();
            int [][] cmap = new int [width][height];
            for (int x=0;x<width;x++) {
                for (int y = 0; y < height; y++) {
                    cmap[x ][y ]=input.getPixel(x, y);
                }
            }
            Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++){
                    int r =  Color.red(cmap[x][y]);
                    int g =  Color.green(cmap[x][y]);
                    int b =  Color.blue(cmap[x][y]);
                    if (r<minR)
                        r=0;
                    if (g<minG)
                        g=0;
                    if (b<minB)
                        b=0;

                    int alphapixel=Color.alpha(cmap[x][y]);
                    int newpixel =Color.argb(alphapixel,r,g,b);
                    output.setPixel(x,y,newpixel);
                }
            }
            return output;



        }


    }

    public class CetkTask extends AsyncTask<Bitmap, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            is_asynk_go = true;

            Toast.makeText(filters_activity.this,"Фильтрация началась",Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onPostExecute(Bitmap bitmapP) {
            img = (ImageView) findViewById(R.id.imageView);
            img.setImageBitmap(bitmapP);

            is_asynk_go = false;
            Toast.makeText(filters_activity.this,"Фильтрация закончилась",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Bitmap doInBackground(Bitmap... parameter) {
            Bitmap bitmapP=parameter[0];
            bitmapP = SkrutkaNEW(bitmapP,cetk,3);



            return bitmapP;
        }






        private Bitmap SkrutkaNEW (Bitmap input, double[][] kernel, int kernelsize ){
            int width = input.getWidth();
            int height = input.getHeight();
            int[][] cmap=getColourMap(input,width,height,kernelsize);
            Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++){
                    double rSum = 0, gSum = 0, bSum = 0, kSum = 0;
                    for (int i = 0; i < kernelsize; i++) {
                        for (int j = 0; j < kernelsize; j++) {
                            int pixelPosX = x + i;
                            int pixelPosY = y + j;
                            int r =  Color.red(cmap[pixelPosX][pixelPosY]);
                            int g =  Color.green(cmap[pixelPosX][pixelPosY]);
                            int b =  Color.blue(cmap[pixelPosX][pixelPosY]);
                            double kernelVal = kernel[i][j];
                            rSum += r * kernelVal;
                            gSum += g * kernelVal;
                            bSum += b * kernelVal;
                            kSum += kernelVal;
                        }
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

                    int alphapixel=Color.alpha(cmap[x+(kernelsize/2)][y+(kernelsize/2)]);
                    int newpixel =Color.argb(alphapixel,(int)rSum,(int)gSum,(int)bSum);
                    output.setPixel(x,y,newpixel);
                }
            }
            return output;



        }


    }


    public class RelTask extends AsyncTask<Bitmap, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            is_asynk_go = true;
            Toast.makeText(filters_activity.this,"Фильтрация началась",Toast.LENGTH_SHORT).show();

        }
        @Override
        protected void onPostExecute(Bitmap bitmapP) {
            img = (ImageView) findViewById(R.id.imageView);
            img.setImageBitmap(bitmapP);

            is_asynk_go = false;
            Toast.makeText(filters_activity.this,"Фильтрация закончилась",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Bitmap doInBackground(Bitmap... parameter) {
            Bitmap bitmapP=parameter[0];
            bitmapP = SkrutkaNEW(start.BitmapGetter(),rel,3);



            return bitmapP;
        }






        private Bitmap SkrutkaNEW (Bitmap input, double[][] kernel, int kernelsize ){
            int width = input.getWidth();
            int height = input.getHeight();
            int[][] cmap=getColourMap(input,width,height,kernelsize);
            Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++){
                    double rSum = 0, gSum = 0, bSum = 0, kSum = 0;
                    for (int i = 0; i < kernelsize; i++) {
                        for (int j = 0; j < kernelsize; j++) {
                            int pixelPosX = x + i;
                            int pixelPosY = y + j;
                            int r =  Color.red(cmap[pixelPosX][pixelPosY]);
                            int g =  Color.green(cmap[pixelPosX][pixelPosY]);
                            int b =  Color.blue(cmap[pixelPosX][pixelPosY]);
                            double kernelVal = kernel[i][j];
                            rSum += r * kernelVal;
                            gSum += g * kernelVal;
                            bSum += b * kernelVal;
                            kSum += kernelVal;
                        }
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

                    int alphapixel=Color.alpha(cmap[x+(kernelsize/2)][y+(kernelsize/2)]);
                    int newpixel =Color.argb(alphapixel,(int)rSum,(int)gSum,(int)bSum);
                    output.setPixel(x,y,newpixel);
                }
            }
            return output;



        }



    }



    public class CBTask extends AsyncTask<Bitmap, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            is_asynk_go = true;
            Toast.makeText(filters_activity.this,"Фильтрация началась",Toast.LENGTH_SHORT).show();

        }
        @Override
        protected void onPostExecute(Bitmap bitmapP) {
            img = (ImageView) findViewById(R.id.imageView);
            img.setImageBitmap(bitmapP);

            is_asynk_go = false;
            Toast.makeText(filters_activity.this,"Фильтрация закончилась",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Bitmap doInBackground(Bitmap... parameter) {
            Bitmap bitmapP=parameter[0];
            bitmapP = RGBCB(bitmapP);



            return bitmapP;
        }






        private Bitmap RGBCB (Bitmap input){
            int width = input.getWidth();
            int height = input.getHeight();
            int [][] cmap = new int [width][height];
            for (int x=0;x<width;x++) {
                for (int y = 0; y < height; y++) {
                    cmap[x ][y ]=input.getPixel(x, y);
                }
            }
            Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++){
                    int r =  Color.red(cmap[x][y]);
                    int g =  Color.green(cmap[x][y]);
                    int b =  Color.blue(cmap[x][y]);
                    int cb=(r+g+b)/3;
                    int alphapixel=Color.alpha(cmap[x][y]);
                    int newpixel =Color.argb(alphapixel,cb,cb,cb);
                    output.setPixel(x,y,newpixel);

                }
            }
            return output;



        }



    }



    public class TisTask extends AsyncTask<Bitmap, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            is_asynk_go = true;
            Toast.makeText(filters_activity.this,"Фильтрация началась",Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onPostExecute(Bitmap bitmapP) {
            img = (ImageView) findViewById(R.id.imageView);
            img.setImageBitmap(bitmapP);

            is_asynk_go = false;
            Toast.makeText(filters_activity.this,"Фильтрация закончилась",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Bitmap doInBackground(Bitmap... parameter) {
            Bitmap bitmapP=parameter[0];
            bitmapP = SkrutkaTis(bitmapP,tis,3);



            return bitmapP;
        }






        private Bitmap SkrutkaTis (Bitmap input, double[][] kernel, int kernelsize ){
            int width = input.getWidth();
            int height = input.getHeight();
            int[][] cmap=getColourMap(input,width,height,kernelsize);
            Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++){
                    double rSum = 0, gSum = 0, bSum = 0, kSum = 0;
                    for (int i = 0; i < kernelsize; i++) {
                        for (int j = 0; j < kernelsize; j++) {
                            int pixelPosX = x + i;
                            int pixelPosY = y + j;
                            int r =  Color.red(cmap[pixelPosX][pixelPosY]);
                            int g =  Color.green(cmap[pixelPosX][pixelPosY]);
                            int b =  Color.blue(cmap[pixelPosX][pixelPosY]);
                            double kernelVal = kernel[i][j];
                            rSum += r * kernelVal;
                            gSum += g * kernelVal;
                            bSum += b * kernelVal;
                            kSum += kernelVal;
                        }
                    }

                    if (kSum <= 0) kSum = 1;
                    rSum /= kSum;
                    rSum+=128;
                    if (rSum < 0) rSum = 0;
                    if (rSum > 255) rSum = 255;
                    gSum /= kSum;
                    gSum+=128;
                    if (gSum < 0) gSum = 0;
                    if (gSum > 255) gSum = 255;
                    bSum /= kSum;
                    bSum+=128;
                    if (bSum < 0) bSum = 0;
                    if (bSum > 255) bSum = 255;

                    int alphapixel=Color.alpha(cmap[x+(kernelsize/2)][y+(kernelsize/2)]);
                    int newpixel =Color.argb(alphapixel,(int)rSum,(int)gSum,(int)bSum);
                    output.setPixel(x,y,newpixel);
                }
            }
            return output;



        }



    }


}