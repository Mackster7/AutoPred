package com.example.autoconsultancy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    public TextView price,price_plus;
    public Button ok_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        price=(TextView) findViewById(R.id.price);
        price_plus=(TextView) findViewById(R.id.priceplus);
        ok_button=(Button) findViewById(R.id.close_button);

        String value= getIntent().getStringExtra("key");
        System.out.println("val is "+value);
        price.setText(value);

        int value1=Integer.parseInt(value);
        value1=value1+((value1/100)*5);
        System.out.println("val1 is "+value1);
        String value_plus=String.valueOf(value1);
        price_plus.setText(value_plus);

        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}