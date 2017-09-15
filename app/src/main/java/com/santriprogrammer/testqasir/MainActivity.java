package com.santriprogrammer.testqasir;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.santriprogrammer.testqasir.Model.ModelData;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    Gson gson;
    String BASE_URL = "https://api.myjson.com/bins/1g511h";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);


        requestQueue = Volley.newRequestQueue(this);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        loadData();


    }

    private void initView() {
        recyclerView = (RecyclerView)findViewById(R.id.recyclerHome);
    }

    private void loadData() {
        StringRequest req = new StringRequest(Request.Method.GET, BASE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if (String.valueOf(new JSONObject(response).getString("message")).equals("success")){
                        try {

                            Log.i("Response Data", response);
                            final ModelData model = gson.fromJson(response, ModelData.class);
                            final AdapterKategori adapterKategori = new AdapterKategori(model.getData(), MainActivity.this);
                            recyclerView.setAdapter(adapterKategori);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"Database Is Noll",Toast.LENGTH_LONG).show();
                    }
                }catch (JSONException e){

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Check Your Internet Connection",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(req);
    }
}
