package com.varrel.samsungb2b;

import android.content.Context;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataAPI {
    String url = "https://reqres.in/api/users";
    RequestQueue que ;

    //Method untuk ngambil data dari API
    public void getUserData (Context ctx){
        Cache cache = new DiskBasedCache(ctx.getCacheDir(), 1024*1024);
        que = new RequestQueue(cache, new BasicNetwork(new HurlStack()));
        Response.Listener<JSONObject> resp = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray  data = response.getJSONArray("data");
                    for (int i = 0; i < data.length();i++){
                        JSONObject obj = data.getJSONObject(i);
                        int uID = obj.getInt("id");
                        String email = obj.getString("email");
                        String first_name = obj.getString("first_name");
                        String last_name = obj.getString("last_name");
                        String avatar = obj.getString("avatar");
                        DataUser dataUser = new DataUser(uID,email,first_name,last_name,avatar);
                        Data.Data_User.add(dataUser);
                        Log.d("test", ""+dataUser);
                    }
                    MainActivity.adapterUser.notifyDataSetChanged();
                } catch (JSONException e){

                };
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, resp,errorListener);
        que.add(jsonObjectRequest);
        que.start();

    }
}
