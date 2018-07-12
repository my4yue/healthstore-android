package com.healthstore.app.utils;

import android.content.Context;
import android.os.Handler;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

public class TipUtils {

    static Handler handler = new Handler();

    public static QMUITipDialog provideLoadingTip(Context context) {
        return new QMUITipDialog.Builder(context).setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING).setTipWord("请稍等").create();
    }

    public static void showTipDialog(Context context, String message) {
        QMUITipDialog ret = new QMUITipDialog.Builder(context).setIconType(QMUITipDialog.Builder.ICON_TYPE_INFO).setTipWord(message).create();
        ret.show();
        handler.postDelayed(ret::dismiss, 2000);
    }

    public static void showSucceedDialog(Context context, String message) {
        QMUITipDialog ret = new QMUITipDialog.Builder(context).setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS).setTipWord(message).create();
        ret.show();
        handler.postDelayed(ret::dismiss, 2000);
    }

    public static void showFailedDialog(Context context, String message) {
        QMUITipDialog ret = new QMUITipDialog.Builder(context).setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL).setTipWord(message).create();
        ret.show();
        handler.postDelayed(ret::dismiss, 2000);
    }

}
