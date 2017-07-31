package com.example.yuxibing.mykotlindemo.javacode;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.yuxibing.mykotlindemo.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private CustomView pie;
    private int[] colors = {Color.RED,Color.BLUE,Color.GRAY,Color.GREEN,Color.BLACK};
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pie = (CustomView) findViewById(R.id.pie);
        btn = (Button) findViewById(R.id.btn);
        ArrayList<PieEntity> pieEntities = new ArrayList<>();
        for (int i = 0; i < colors.length; i++) {
            pieEntities.add(new PieEntity(i+1,colors[i]));
        }
        pie.setData(pieEntities);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ViewGroupActivity.class));
            }
        });
    }

}
