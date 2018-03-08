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
        bt2 = (Button)findViewById(R.id.bt_2);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
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
            case R.id.bt_2:
                quickSort(0, mData.length - 1);
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

    /**
     * 快速排序一听名字就觉得很高端，在实际应用当中快速排序确实也是表现最好的排序算法。
     * 冒泡排序虽然高端，但其实其思想是来自冒泡排序，冒泡排序是通过相邻元素的比较和交换
     * 把最小的冒泡到最顶端，而快速排序是比较和交换小数和大数，
     * 这样一来不仅把小数冒泡到上面同时也把大数沉到下面。
     * 举个栗子：对5,3,8,6,4这个无序序列进行快速排序，思路是右指针找比基准数小的，左指针找比基准数大的，交换之。
     * 5,3,8,6,4 用5作为比较的基准，最终会把5小的移动到5的左边，比5大的移动到5的右边。
     * 5,3,8,6,4 首先设置i,j两个指针分别指向两端，j指针先扫描（思考一下为什么？）4比5小停止。
     * 然后i扫描，8比5大停止。交换i,j位置。
     * 5,3,4,6,8 然后j指针再扫描，这时j扫描4时两指针相遇。停止。然后交换4和基准数。
     * 4,3,5,6,8 一次划分后达到了左边比5小，右边比5大的目的。之后对左右子序列递归排序，最终得到有序序列。
     * 上面留下来了一个问题为什么一定要j指针先动呢？首先这也不是绝对的，这取决于基准数的位置，
     * 因为在最后两个指针相遇的时候，要交换基准数到相遇的位置。一般选取第一个数作为基准数，
     * 那么就是在左边，所以最后相遇的数要和基准数交换，那么相遇的数一定要比基准数小。
     * 所以j指针先移动才能先找到比基准数小的数。
     * 快速排序是不稳定的，其时间平均时间复杂度是O(N*logN)。
     *
     * 快速排序由于排序效率在同为O(N*logN)的几种排序方法中效率较高.
     * 该方法的基本思想是：
     * 1．先从数列中取出一个数作为基准数。
     * 2．分区过程，将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边。
     * 3．再对左右区间重复第二步，直到各区间只有一个数。
     * 对挖坑填数进行总结
     * 1．i =L; j = R; 将基准数挖出形成第一个坑a[i]。
     * 2．j--由后向前找比它小的数，找到后挖出此数填前一个坑a[i]中。
     * 3．i++由前向后找比它大的数，找到后也挖出此数填到前一个坑a[j]中。
     * 4．再重复执行2，3二步，直到i==j，将基准数填入a[i]中。
     * @param l
     * @param r
     */
    private void quickSort(int l, int r){
        if(l < r){
            int x = mData[l];//选取左边第一个数作为基准数
            int i = l, j = r;
            while (i < j){
                while (i < j && mData[j] >= x){// 从右向左找第一个小于x的数
                    j--;
                }
                if(i < j){
                    mData[i] = mData[j];
                    i++;
                }

                while (i < j && mData[i] < x){// 从左向右找第一个大于等于x的数
                    i++;
                }
                if(i < j){
                    mData[j] = mData[i];
                    j--;
                }
            }

            mData[i] = x;

            quickSort(l, i - 1);
            quickSort(i + 1, r);
        }

    }
}
