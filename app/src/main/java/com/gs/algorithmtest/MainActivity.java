package com.gs.algorithmtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gs.algorithmtest.sort.SortActivity;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{
    private final static String TAG = "MainActivity";

    private Button btSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        btSort = (Button)findViewById(R.id.bt_sort);

        btSort.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.bt_sort:
                intent = new Intent(MainActivity.this, SortActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
