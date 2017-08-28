package com.example.unknown.vollyproject;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by UNKNOWN on 7/8/2016.
 */
public class MySingleton {

    private Context context;
    private RequestQueue requestQueue;
    private static MySingleton mySingleton;

    public MySingleton(Context context){
        this.context = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue(){

        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }


    public static synchronized MySingleton getInstance(Context context){

        if(mySingleton == null){
            mySingleton = new MySingleton(context);
        }
        return mySingleton;
    }

    public<T> void addToRequestQueue(Request<T> request){

        requestQueue.add(request);

    }


}
