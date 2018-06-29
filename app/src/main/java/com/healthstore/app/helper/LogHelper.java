package com.healthstore.app.helper;

import android.content.Context;
import android.widget.Toast;

public class LogHelper {

    public static void toast(Context ctx, CharSequence text) {
        Toast.makeText(ctx, text, Toast.LENGTH_SHORT).show();
    }

}
