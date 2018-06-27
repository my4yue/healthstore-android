package com.healthstore.app.view;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.healthstore.app.R;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeActivity extends AppCompatActivity {

    @BindView(R.id.top_bar) QMUITopBarLayout topBar;
    @BindView(R.id.icon) QMUIRadiusImageView icon;
    @BindView(R.id.me_list) QMUIGroupListView meListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        ButterKnife.bind(this);

        topBar.setTitle("个人信息");
        topBar.addLeftBackImageButton();

        QMUICommonListItemView iv = meListView.createItemView("昵称");
        iv.setDetailText("风之幽水");
        iv.setOrientation(QMUICommonListItemView.HORIZONTAL);

        QMUIGroupListView.newSection(this)
                .addItemView(iv, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MeActivity.this, "我要改昵称", Toast.LENGTH_SHORT).show();
                    }
                })
                .addItemView(meListView.createItemView("性别"), null)
                .addItemView(meListView.createItemView("地区"), null).addTo(meListView);

        QMUIStatusBarHelper.translucent(this);

    }
}
