package com.example.autoconsultancy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    final String URL="http://192.168.111.210:5000/predict";
    public EditText year, kms_driven;
    public AutoCompleteTextView name,fuel_type;
    public AutoCompleteTextView company;
    RequestQueue queue;
    public TextView result;
    public Button predict;
    public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=(AutoCompleteTextView) findViewById(R.id.model);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,details.models);
        name.setAdapter(adapter);

        company=(AutoCompleteTextView) findViewById(R.id.brand);
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,details.brands);
        company.setAdapter(adapter1);

        year=(EditText) findViewById(R.id.year);
        kms_driven=(EditText) findViewById(R.id.kms_driven);

        fuel_type=(AutoCompleteTextView) findViewById(R.id.fuel_type);

        fuel_type=(AutoCompleteTextView) findViewById(R.id.fuel_type);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,details.Ftype);
        fuel_type.setAdapter(adapter2);

        result=(TextView) findViewById(R.id.price);
        predict=(Button) findViewById(R.id.predict);
        queue = Volley.newRequestQueue(MainActivity.this);

        //HERE WE CAN FIND PRICE TEXTVIEW
        result.setVisibility(View.INVISIBLE);


        predict.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(validate()){
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject=new JSONObject(response);
                                String data=jsonObject.getString("price");
                                if(data.equals("")){
                                    result.setText("Data Not Found");
                                }
                                else {
                                    result.setText(data);
                                    id=jsonObject.getInt("price");
                                    System.out.println("price is:"+id);
                                    String s=String.valueOf(id);
                                    System.out.println("value here is:"+s);
                                    Intent i=new Intent(MainActivity.this,MainActivity2.class);
                                    i.putExtra("key",s);
                                    startActivity(i);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                    ){
                        @Override
                        protected Map<String,String> getParams(){
                            Map<String,String> params=new HashMap<String,String>();
                            params.put("name",name.getText().toString());
                            params.put("company",company.getText().toString());
                            params.put("year",year.getText().toString());
                            params.put("kms_driven",kms_driven.getText().toString());
                            params.put("fuel_type",fuel_type.getText().toString());

                            return params;
                        }
                    };
                    queue.add(stringRequest);
//                    String s=String.valueOf(1234);
//                    System.out.println("value here is:"+s);
//                    Intent i=new Intent(MainActivity.this,MainActivity2.class);
//                    i.putExtra("key",s);
//                    startActivity(i);
                }
                else
                    Toast.makeText(getApplicationContext(), "All fields should be filled", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean validate() {
        return !name.getText().toString().equals("") && !company.getText().toString().equals("") &&
                !year.getText().toString().equals("") && !kms_driven.getText().toString().equals("") &&
                !fuel_type.getText().toString().equals("") ;
    }
}