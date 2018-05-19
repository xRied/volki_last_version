
package com.example.volk322;

        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.drawable.Drawable;
        import android.net.Uri;
        import android.provider.MediaStore;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.ImageView;

        import java.io.IOException;

        import static com.example.volk322.start_vibor.GALLERY_REQUEST;


public class start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


            ImageView img = (ImageView) findViewById(R.id.imageView);
            img.setImageBitmap(start_vibor.BitmapGetter());


    }
}