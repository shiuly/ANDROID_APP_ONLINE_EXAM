package com.example.onlineexam;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ResultFrag extends Fragment {
    List<String> answerList;
    String[] menuItem;
    ListView listView;
    public ResultFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container,false);
        answerList = new ArrayList<>();
        menuItem = new String[15];
        //----------------------------------------------
            m3();
        //----------------------------------------------

         listView = view.findViewById(R.id.resultmenu);


       // System.out.println("/////////////////---------------------------"+ menuItem[0].toString());
        return  view;
    }
    public void prepareCorrectArray() {
        RequestQueue requestQueueQ = Volley.newRequestQueue(getActivity().getApplicationContext());
        //RequestQueue requestQueueQ = Volley.newRequestQueue(this);
        String url = "http://online555.000webhostapp.com/api/apiresult.php";
        JsonArrayRequest jsonReq = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        answerList.add(jsonObject.getString("name").toUpperCase()+"\t\t\t\t : \t"+jsonObject.getString("marks")+"\t " +
                                "||\t\t Status:\t "+
                                jsonObject.getString("status").toUpperCase() );
                       // menuItem[i]=jsonObject.getString("name");
                    }
                    System.out.println("/////////////////-------------------------------/////////////////////"+ answerList.size());
                    ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
                            getActivity(),
                            android.R.layout.simple_list_item_1, answerList
                    );
                    listView.setAdapter(listAdapter);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error from result fragment");
            }
        });
        requestQueueQ.add(jsonReq);
    }
    public  void m3(){
        prepareCorrectArray();
    }
}
