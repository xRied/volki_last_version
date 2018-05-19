package com.example.volk322;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start_btn = (Button) findViewById(R.id.start_btn);
        start_btn.setOnClickListener(this);
    }



    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_btn:
                Intent intent = new Intent(this, start_vibor.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}

