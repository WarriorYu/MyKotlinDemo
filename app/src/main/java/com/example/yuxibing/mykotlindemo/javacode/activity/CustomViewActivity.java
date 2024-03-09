package com.example.yuxibing.mykotlindemo.javacode.activity;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yuxibing.mykotlindemo.R;
import com.example.yuxibing.mykotlindemo.javacode.view.CustomView;
import com.example.yuxibing.mykotlindemo.javacode.model.PieEntity;

import java.util.ArrayList;

public class CustomViewActivity extends AppCompatActivity {
    private CustomView pie;
    private int[] colors = {Color.RED,Color.BLUE,Color.GRAY,Color.GREEN,Color.BLACK};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        pie = (CustomView) findViewById(R.id.pie);
        ArrayList<PieEntity> pieEntities = new ArrayList<>();
        for (int i = 0; i < colors.length; i++) {
            pieEntities.add(new PieEntity(i+1,colors[i]));
        }
        pie.setData(pieEntities);
    }
}
