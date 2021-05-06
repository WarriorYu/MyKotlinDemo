package com.example.yuxibing.mykotlindemo.javacode.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.yuxibing.mykotlindemo.R;

import me.kaelaela.verticalviewpager.VerticalViewPager;
import me.kaelaela.verticalviewpager.transforms.StackTransformer;

public class MallCategoryActivity extends AppCompatActivity {

    private RecyclerView recy;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_category);
//        recy = (RecyclerView) findViewById(R.id.recy);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
//        recy.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        RecyAdapter adapter = new RecyAdapter(this);
//        recy.setAdapter(adapter);

        String title = "ContentFragment";
        viewPager.setAdapter(new ContentFragmentAdapter.Holder(getSupportFragmentManager())
                .add(ContentFragment.newInstance("我是1", 1))
                .add(ContentFragment.newInstance("我是2", 2))
                .add(ContentFragment.newInstance("我是3", 3))
                .add(ContentFragment.newInstance("我是4", 4))
                .add(ContentFragment.newInstance("我是5", 5))
                .set());
        viewPager.setPageTransformer(true, new StackTransformer());

        //If you setting other scroll mode, the scrolled fade is shown from either side of display.
        viewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }
}
