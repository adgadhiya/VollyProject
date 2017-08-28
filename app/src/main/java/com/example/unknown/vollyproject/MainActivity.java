package com.example.unknown.vollyproject;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button btn;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.tv);
        btn = (Button) findViewById(R.id.btn);
        iv = (ImageView)findViewById(R.id.iv);

        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String url = "http://192.168.246.1/series/dynamic/Checking/login.php";
                        String img_url = "http://192.168.246.1/series/dynamic/Myphp/images/1.jpg";

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        tv.setText(response);
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                tv.setText(error.getMessage().toString());
                            }
                        });

                        ImageRequest imageRequest = new ImageRequest(img_url,
                                new Response.Listener<Bitmap>() {
                                    @Override
                                    public void onResponse(Bitmap response) {
                                        iv.setImageBitmap(response);
                                    }
                                }, 0, 0, ImageView.ScaleType.FIT_XY, null,
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        error.printStackTrace();
                                        Toast.makeText(MainActivity.this,"Some error occured",Toast.LENGTH_SHORT).show();
                                    }
                                });

                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Toast.makeText(MainActivity.this,"JSON Received",Toast.LENGTH_SHORT).show();
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this,"JSON not  Received",Toast.LENGTH_SHORT).show();
                            }
                        });

                        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(imageRequest);
                        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
                        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
                    }
                }
        );
    }
}
