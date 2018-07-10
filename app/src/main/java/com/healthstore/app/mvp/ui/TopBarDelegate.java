package com.healthstore.app.mvp.ui;

import android.view.View;

public interface TopBarDelegate {

    String getTitle();
    default String getSubTitle() {
        return null;
    }

    default View.OnClickListener onBackButtonClick() {
        return null;
    }

}
