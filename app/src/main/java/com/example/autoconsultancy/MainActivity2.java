package com.example.autoconsultancy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {
    public TextView price,price_plus;
    public Button ok_button,olx;
    public ImageView image,image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        price=(TextView) findViewById(R.id.price);
        price_plus=(TextView) findViewById(R.id.priceplus);
        ok_button=(Button) findViewById(R.id.close_button);
        image=(ImageView) findViewById(R.id.logo);
        olx=(Button) findViewById(R.id.olx);

        String value= getIntent().getStringExtra("key");
        System.out.println("val is "+value);

        int value1=Integer.parseInt(value);
        int value2=value1+((value1/100)*5);
        System.out.println("val1 is "+value1);


        //converting to indian format...

        NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        String myCurrency = currency.format(value1);

        price.setText(myCurrency);
        NumberFormat currency_plus = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        String myCurrency_plus = currency.format(value2);

        price_plus.setText(myCurrency_plus);


        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainActivity2.this,MainActivity.class);
                startActivity(in);
                finish();
            }
        });

        olx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.olxgroup.panamera.app.users.onboarding.activities.SplashActivity");

                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }
                else
                {
                    Toast.makeText(MainActivity2.this,"there is no such package",Toast.LENGTH_LONG).show();
                }*/

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setClassName("com.olx.southasia", "com.olxgroup.panamera.app.users.onboarding.activities.SplashActivity");
                startActivity(intent);
            }
        });
    }
}