package com.example.onlineexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuccessActivity extends AppCompatActivity {
    TextView question;
    TextView result;
    TextView queRemain;
    RadioGroup radioGroup;
    RadioButton opt1;
    RadioButton opt2;
    RadioButton opt3;
    Button button;

    int x = 0;
    Integer marks;
    Integer remain;

    Map<Integer, String> answerMap;
    List<String> answerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        question = findViewById(R.id.textQ);
        result = findViewById(R.id.resultMarks);
        queRemain = findViewById(R.id.qRemain);
        opt1 = findViewById(R.id.radio1);
        opt2 = findViewById(R.id.radio2);
        opt3 = findViewById(R.id.radio3);
        button = findViewById(R.id.btnsubmit);
        radioGroup = findViewById(R.id.radioGroup);
        answerMap = new HashMap<>();
        answerList = new ArrayList<>();
        marks = 0;
        remain=10;
        queRemain.setText(""+remain);
        prepareCorrectArray();
        showdata();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String value =
                        ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId()))
                                .getText().toString();
                System.out.println("Your Answer \t" + value);

                answerMap.put(x, value);
                if (value.equals(answerList.get(x).toString())) {
                    marks++;
                }

               // System.out.println("Your Marks Now\t" + marks);
                x++;
                if(x<10){
                    showdata();
                    remain--;
                    queRemain.setText(""+remain);

                }else{
                    Intent intent=new Intent(SuccessActivity.this, Score.class);
                    intent.putExtra("score", ""+marks);
                    startActivity(intent);

                }

            }
        });
    }

    //***************************************
    public void prepareCorrectArray() {
        RequestQueue requestQueueQ = Volley.newRequestQueue(this);
        String url = "http://online555.000webhostapp.com/api/apiquestion.php";
        JsonArrayRequest jsonReq = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                  for (int i = 0; i < response.length(); i++) {
                         System.out.println("All Correct Answer"+ response.get(i));
                        JSONObject jsonObject = response.getJSONObject(i);
                        answerList.add(jsonObject.getString("correct"));
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                question.setText("------ERROR-------------");
            }
        });
        requestQueueQ.add(jsonReq);
    }

    public void showdata() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://online555.000webhostapp.com/api/apiquestion.php";
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    String s = "";
                    JSONObject jsonObject = response.getJSONObject(x);

                    String ques = jsonObject.getString("question");
                    String opta = jsonObject.getString("option_a");
                    String optb = jsonObject.getString("option_b");
                    String optc = jsonObject.getString("option_c");
                    String price = jsonObject.getString("correct");
                    question.setText(ques);
                    opt1.setText(opta);
                    opt2.setText(optb);
                    opt3.setText(optc);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                question.setText("------ERROR-------------");
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

//********************************
}