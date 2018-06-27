package com.healthstore.app.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.healthstore.app.R;
import com.healthstore.app.view.fragment.FeedbackFragment;
import com.healthstore.app.view.fragment.MeFragment;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.content_view) View contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        QMUIStatusBarHelper.translucent(this);

//        getFragmentManager().beginTransaction().replace(R.id.content_view, new FeedbackFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_view, new MeFragment()).commit();
    }


}
