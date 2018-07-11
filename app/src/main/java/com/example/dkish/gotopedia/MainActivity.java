package com.example.dkish.gotopedia;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;


import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    Button search;
    EditText input;
    Toolbar toolbar;
    Gson gson_element = new Gson();
    Character converted_character = new Character();
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.search);

        input = findViewById(R.id.input);



        requestQueue = Volley.newRequestQueue(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("GOTopedia");

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String  APIurl = "https://api.got.show/api/characters/";
                String s = input.getText().toString();
                JsonObjectRequest objectRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        APIurl+s,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                String converted_response = gson_element.toJson(response);
                                converted_character = gson_element.fromJson(converted_response , Character.class);

                                TextView name = findViewById(R.id.name);
                                TextView gender = findViewById(R.id.gender);
                                TextView dob = findViewById(R.id.dob);
                                TextView spouse = findViewById(R.id.spouse);
                                TextView culture = findViewById(R.id.culture);
                                TextView house = findViewById(R.id.house);
                                TextView titles = findViewById(R.id.titles);

                                name.setText(converted_character.name);
                                gender.setText(converted_character.name);
                                dob.setText(converted_character.name);
                                spouse.setText(converted_character.name);
                                culture.setText(converted_character.name);
                                house.setText(converted_character.house);
                                titles.setText(converted_character.name);

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }


                );
                requestQueue.add(objectRequest);
            }
        });



    }





}
