package com.healthstore.app.utils;

import android.content.Context;
import android.widget.Toast;

public class LogUtils {

    public static void toast(Context ctx, CharSequence text) {
        Toast.makeText(ctx, text, Toast.LENGTH_SHORT).show();
    }

}
