package com.example.onlineexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Score extends AppCompatActivity {
    Bundle extras;
    TextView scoreView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        scoreView = findViewById(R.id.score);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String scoreRes = extras.getString("score");
        System.out.println("asssacs score is"+scoreRes);
        scoreView.setText(""+scoreRes);
    }
    public  void goHome(View view){
        Intent intent = new Intent(Score.this, MainActivity.class);
        startActivity(intent);
    }
    public  void m2(View view){
        goHome(view);
    }
}
