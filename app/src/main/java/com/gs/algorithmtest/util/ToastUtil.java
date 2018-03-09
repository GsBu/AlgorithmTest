package com.gs.algorithmtest.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by camdora on 17-11-28.
 */

public class ToastUtil {

    public static void showToastShort(Context context, int stringId){
        showToastShort(context, context.getApplicationContext().getString(stringId));
    }

    public static void showToastShort(Context context, String string){
        Toast.makeText(context.getApplicationContext(),string,Toast.LENGTH_SHORT).show();
    }

    public static void showToastLong(Context context, int stringId){
        showToastLong(context, context.getApplicationContext().getString(stringId));
    }

    public static void showToastLong(Context context, String string){
        Toast.makeText(context.getApplicationContext(),string,Toast.LENGTH_LONG).show();
    }
}
