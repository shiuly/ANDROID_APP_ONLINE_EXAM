package com.example.onlineexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoginPage extends AppCompatActivity {
EditText text1;
EditText text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        text1 = (EditText) findViewById(R.id.email);
        text2 = (EditText) findViewById(R.id.pass);
    }

    public void checkData() {
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://online555.000webhostapp.com/api/apistu.php";
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    String email = text1.getText().toString();
                    String password = text2.getText().toString();
                    System.out.println(email+"\t----------+++++++++++-------------"+password);
                    System.out.println(email+"9999"+password);
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);


                        String emails=jsonObject.getString("email");
                        String passs=jsonObject.getString("password");
                        if(emails.equals(email) && passs.equals(password)){
                            Intent intent=new Intent(LoginPage.this, SuccessActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(),"LOGIN FAILED",Toast.LENGTH_SHORT).show();
                        }

                        System.out.println(emails+"\t----------+++++++++++-------------"+passs);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    public void m1(View view){
        checkData();
    }
}
