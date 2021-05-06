package com.example.yuxibing.mykotlindemo.javacode.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuxibing.mykotlindemo.R;


/**
 * Created by yuxibing on 2018/5/15.
 * 描述：
 */

public class RecyAdapter extends RecyclerView.Adapter {
    private Context context;

    public RecyAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recy_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tx;

        public ViewHolder(View itemView) {
            super(itemView);
            tx = (TextView) itemView.findViewById(R.id.tx);
        }
        public void setData(int position){
            tx.setText("分类"+position);

        }
    }
}
