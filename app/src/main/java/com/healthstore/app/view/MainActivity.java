package com.healthstore.app.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.healthstore.app.R;
import com.qmuiteam.qmui.widget.QMUITopBar;

public class MainActivity extends AppCompatActivity {

    QMUITopBar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
    }

}
