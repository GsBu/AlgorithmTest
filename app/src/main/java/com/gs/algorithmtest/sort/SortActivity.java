package com.gs.algorithmtest.sort;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gs.algorithmtest.R;

import java.util.ArrayList;
import java.util.List;

public class SortActivity extends AppCompatActivity
        implements View.OnClickListener{
    private final static String TAG = "SortActivity";

    private Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8;
    private TextView tvList,tvListSort;

    private int[] mData ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        initView();
        initData();
    }

    private void initView(){
        tvList = (TextView) findViewById(R.id.tv_list);
        tvListSort = (TextView) findViewById(R.id.tv_list_sort);
        bt1 = (Button)findViewById(R.id.bt_1);

        bt1.setOnClickListener(this);
    }

    private void initData(){
        mData = new int[]{56, 34, 57, 23, 78, 1, 37, 6, 3, 2, 9};
        StringBuffer stringBuffer = new StringBuffer("排序前:");
        for (int i : mData){
            stringBuffer.append(i + ", ");
        }

        tvList.setText(stringBuffer);
    }

    /**
     * 排序之后，显示数据
     */
    private void sortFinish(){
        StringBuffer stringBuffer = new StringBuffer("排序后:");
        for (int i : mData){
            stringBuffer.append(i + ", ");
        }

        tvListSort.setText(stringBuffer);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        initData();//排序前重置数据
        switch (v.getId()){
            case R.id.bt_1:
                bubbleSort();
                break;
            default:
                break;
        }
        sortFinish();//排序后显示数据
    }

    /**
     * 冒泡排序是最简单的排序之一了，其大体思想就是通过与相邻元素的比较和交换来把小的数交换到最前面。
     * 这个过程类似于水泡向上升一样，因此而得名。举个栗子，对5,3,8,6,4这个无序序列进行冒泡排序。
     * 首先从后向前冒泡，4和6比较，把4交换到前面，序列变成5,3,8,4,6。同理4和8交换，
     * 变成5,3,4,8,6,3和4无需交换。5和3交换，变成3,5,4,8,6,3.这样一次冒泡就完了，
     * 把最小的数3排到最前面了。对剩下的序列依次冒泡就会得到一个有序序列。
     * 冒泡排序的时间复杂度为O(n^2)。
     */
    private void bubbleSort(){
        //方式一
        int count = 0;
        for(int i = 0; i < mData.length - 1; i++){//执行length - 1轮
            for (int j = 0; j < mData.length - i - 1; j++){
                count++;
                if(mData[j] > mData[j+1]){
                    int temp = mData[j];
                    mData[j] = mData[j+1];
                    mData[j+1] = temp;
                }
            }
        }
        Log.e(TAG,"执行次数："+count);
        //方式二
        count = 0;
        for(int i = mData.length - 1; i > 0; i--){
            for(int j = 0; j < i; j++){
                count++;
                if(mData[j] > mData[j+1]){
                    int temp = mData[j];
                    mData[j] = mData[j+1];
                    mData[j+1] = temp;
                }
            }
        }
        Log.e(TAG,"执行次数："+count);
    }
}
