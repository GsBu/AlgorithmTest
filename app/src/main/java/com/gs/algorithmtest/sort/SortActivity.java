package com.gs.algorithmtest.sort;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gs.algorithmtest.R;
import com.gs.algorithmtest.util.ToastUtil;

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
        bt3 = (Button)findViewById(R.id.bt_3);
        bt4 = (Button)findViewById(R.id.bt_4);
        bt5 = (Button)findViewById(R.id.bt_5);
        bt6 = (Button)findViewById(R.id.bt_6);
        bt7 = (Button)findViewById(R.id.bt_7);
        bt8 = (Button)findViewById(R.id.bt_8);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
    }

    private void initData(){
        mData = new int[]{56, 34, 56, 23, 78, 1, 37, 6, 3, 2, 9};
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
            case R.id.bt_3:
                insertSort();
                break;
            case R.id.bt_4:
                shellSort();
                break;
            case R.id.bt_5:
                selectSort();
                break;
            case R.id.bt_6:

                break;
            case R.id.bt_7:

                break;
            case R.id.bt_8:

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
     * 快速排序是不稳定的，其时间平均时间复杂度是O(n*log₂n)。
     *
     * 快速排序由于排序效率在同为O(n*log₂n)的几种排序方法中效率较高.
     * 该方法的基本思想是：
     * 1．先从数列中取出一个数作为基准数。
     * 2．分区过程，将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边。
     * 3．再对左右区间重复第二步，直到各区间只有一个数。
     * 对挖坑填数进行总结
     * 1．i =L; j = R; 将基准数挖出形成第一个坑a[i]。
     * 2．j--由后向前找比它小的数，找到后挖出此数填前一个坑a[i]中。
     * 3．i++由前向后找比它大的数，找到后也挖出此数填到前一个坑a[j]中。
     * 4．再重复执行2，3二步，直到i==j，将基准数填入a[i]中。
     *
     * 备注：
     * 快速排序排序效率非常高。 虽然它运行最糟糕时将达到O(n²)的时间复杂度,
     * 但通常平均来看, 它的时间复杂为O(n*log₂n), 比同样为O(n*log₂n)时间复杂度的归并排序还要快.
     * 快速排序似乎更偏爱乱序的数列, 越是乱序的数列, 它相比其他排序而言, 相对效率更高.
     *
     * Tips: 同选择排序相似, 快速排序每次交换的元素都有可能不是相邻的,
     * 因此它有可能打破原来值为相同的元素之间的顺序. 因此, 快速排序并不稳定.
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

    /**
     * 插入排序由于操作不尽相同, 可分为 直接插入排序 ,
     * 折半插入排序(又称二分插入排序), 链表插入排序 , 希尔排序 。
     * 我们先来看下直接插入排序。
     * 直接插入排序的基本思想是：将数组中的所有元素依次跟前面已经排好的元素相比较，
     * 如果选择的元素比已排序的元素小，则交换，直到全部元素都比较过为止。
     *
     * 如果比较操作的代价比交换操作大的话，可以采用二分查找法来减少比较操作的数目。
     * 该算法可以认为是插入排序的一个变种，称为二分查找插入排序。
     *
     * 直接插入排序复杂度如下：
     * 平均时间复杂度	最好情况	最坏情况	空间复杂度
     * O(n²)	    O(n²)	O(n²)	O(1)
     * Tips: 由于直接插入排序每次只移动一个元素的位，
     * 并不会改变值相同的元素之间的排序， 因此它是一种稳定排序。
     */
    private void insertSort(){
        ToastUtil.showToastShort(this, "直接插入排序");
        int i, j, k;
        //++++++++++++++ 方法一 ++++++++++++++
        // 设数组为a[0…n-1]。
        // 1.初始时，a[0]自成1个有序区，无序区为a[1..n-1]。令i=1
        // 2.将a[i]并入当前的有序区a[0…i-1]中形成a[0…i]的有序区间。
        // 3.i++并重复第二步直到i==n-1。排序完成。
        for(i = 1; i < mData.length; i++){
            //为a[i]在前面的a[0...i-1]有序区间中找一个合适的位置
            for(j = i - 1; j >= 0; j--){
                if(mData[j] <= mData[i]){
                    break;
                }
            }
            //如找到了一个合适的位置
            if(j != i - 1){
                //将比a[i]大的数据向后移
                int temp = mData[i];
                for (k = i - 1; k > j; k--){
                    mData[k + 1] = mData [k];
                }
                //将a[i]放到正确位置上
                mData[k + 1] = temp;
            }
        }
        //-------------- 方法一 --------------

        //++++++++++++++ 方法二 ++++++++++++++
        // 这样的代码太长了，不够清晰。现在进行一下改写，
        // 将搜索和数据后移这二个步骤合并。
        // 即每次a[i]先和前面一个数据a[i-1]比较，
        // 如果a[i] > a[i-1]说明a[0…i]也是有序的，无须调整。
        // 否则就令j=i-1,temp=a[i]。
        // 然后一边将数据a[j]向后移动一边向前搜索，
        // 当有数据a[j]<a[i]时停止并将temp放到a[j + 1]处。
        i = 0; j = 0;
        for(i = 1; i < mData.length; i++){
            if(mData[i - 1] > mData[i]) {
                int temp = mData[i];
                for (j = i - 1; j >= 0 && mData[j] > temp; j--) {
                        mData[j + 1] = mData[j];
                }
                mData[j + 1] = temp;
            }
        }
        //-------------- 方法二 --------------
    }

    /**
     * 希尔排序的实质就是分组插入排序，该方法又称缩小增量排序。
     * 该方法的基本思想是：先将整个待排元素序列分割成
     * 若干个子序列（由相隔某个“增量”的元素组成的）分别进行直接插入排序，
     * 然后依次缩减增量再进行排序，待整个序列中的元素基本有序（增量足够小）时，
     * 再对全体元素进行一次直接插入排序。
     * 因为直接插入排序在元素基本有序的情况下（接近最好情况），效率是很高的。
     * 以下是希尔排序复杂度:
     * 平均时间复杂度	最好情况	    最坏情况	    空间复杂度
     * O(n*log₂n)	O(n*log₂n)	O(n*log₂n)	O(1)
     */
    private void shellSort(){
        ToastUtil.showToastShort(this, "Shell排序");
        int i, j, gap;
        //++++++++++++++ 方法一 ++++++++++++++
        for (gap = mData.length / 2; gap > 0; gap /= 2){//步长
            for (i = 0; i < gap; i++){//直接插入排序
                for (j = i + gap; j < mData.length; j += gap){
                    if(mData[j] < mData[j - gap]){
                        int temp = mData[j];
                        int k = j - gap;
                        while (k >= 0 && mData[k] > temp){
                            mData[k + gap] = mData[k];
                            k -= gap;
                        }
                        mData[k + gap] = temp;
                    }
                }
            }
        }
        //-------------- 方法一 --------------

        //++++++++++++++ 方法二 ++++++++++++++
        // 以第二次排序为例，原来是每次从1A到1E，从2A到2E，
        // 可以改成从1B开始，先和1A比较，然后取2B与2A比较，
        // 再取1C与前面自己组内的数据比较…….。
        // 这种每次从数组第gap个元素开始，
        // 每个元素与自己组内的数据进行直接插入排序显然也是正确的。
        for (gap = mData.length / 2; gap > 0; gap /= 2) {
            for (j = gap; j < mData.length; j++) {    //从数组第gap个元素开始
                if (mData[j] < mData[j - gap]) {     //每个元素与自己组内的数据进行直接插入排序
                    int temp = mData[j];
                    int k = j - gap;
                    while (k >= 0 && mData[k] > temp) {
                        mData[k + gap] = mData[k];
                        k -= gap;
                    }
                    mData[k + gap] = temp;
                }
            }
        }
        //-------------- 方法二 --------------
    }

    /**
     * 选择排序的思想其实和冒泡排序有点类似，都是在一次排序后把最小的元素放到最前面。
     * 但是过程不同，冒泡排序是通过相邻的比较和交换。而选择排序是通过对整体的选择。
     * 举个栗子，对5,3,8,6,4这个无序序列进行简单选择排序，首先要选择5以外的最小数来和5交换，
     * 也就是选择3和5交换，一次排序后就变成了3,5,8,6,4.
     * 对剩下的序列一次进行选择和交换，最终就会得到一个有序序列。
     * 其实选择排序可以看成冒泡排序的优化，因为其目的相同，
     * 只是选择排序只有在确定了最小数的前提下才进行交换，
     * 大大减少了交换的次数。选择排序的时间复杂度为O(n²)
     *
     * 设数组为a[0…n-1]。
     * 1.初始时，数组全为无序区为a[0..n-1]。令i=0
     * 2.在无序区a[i…n-1]中选取一个最小的元素，将其与a[i]交换。交换之后a[0…i]就形成了一个有序区。
     * 3.i++并重复第二步直到i==n-1。排序完成。
     *
     * 以下是选择排序复杂度:
     * 平均时间复杂度	最好情况	最坏情况	空间复杂度
     * O(n²)	    O(n²)	O(n²)	O(1)
     * 选择排序的简单和直观名副其实，这也造就了它”出了名的慢性子”，
     * 无论是哪种情况，哪怕原数组已排序完成，它也将花费将近n²/2次遍历来确认一遍。
     * 即便是这样，它的排序结果也还是不稳定的。
     */
    private void selectSort(){
        ToastUtil.showToastShort(this, "简单选择排序");

        int i, j, index;
        for(i = 0; i < mData.length; i++){
            index = i;
            for (j = i + 1; j < mData.length; j++){
                if(mData[j] < mData[index]){
                    index = j;
                }
            }
            if(index != i) {
                int temp = mData[index];
                mData[index] = mData[i];
                mData[i] = temp;
            }
        }
    }
}
