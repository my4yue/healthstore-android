package com.healthstore.app.view;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.healthstore.app.R;
import com.healthstore.app.helper.LogHelper;
import com.healthstore.app.presenter.impl.UserPresenterImpl;
import com.healthstore.app.view.fragment.MeFragment;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends HealthAppActivity {

    @BindView(R.id.content_view) View contentView;

    @BindView(R.id.tab_agenda) TextView tabAgenda;
    @BindView(R.id.tab_expert) TextView tabExpert;
    @BindView(R.id.tab_health) TextView tabHealth;
    @BindView(R.id.tab_me) TextView tabMe;

    @BindView(R.id.btn_add_record) QMUIAlphaImageButton btnAddRecord;

    List<Fragment> fragments = new ArrayList<>();
    List<TextView> tabs = new ArrayList<>();

    Integer currentSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        QMUIStatusBarHelper.translucent(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.content_view, new MeFragment()).commit();

        tabs = new ArrayList<>();
        tabs.add(tabAgenda);
        tabs.add(tabExpert);
        tabs.add(tabHealth);
        tabs.add(tabMe);

        fragments = new ArrayList<>();
        fragments.add(new Fragment());
        fragments.add(new Fragment());
        fragments.add(new Fragment());
        fragments.add(new MeFragment());

        onSelected(tabAgenda);

//        try {
//            new UserPresenterImpl().getUser();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @OnClick({R.id.tab_agenda, R.id.tab_expert, R.id.tab_health, R.id.tab_me})
    public void onSelected(TextView tv){
        Integer index = Integer.valueOf(tv.getTag().toString());

        if (currentSelected == index)
            return;

        int colorSelected = ContextCompat.getColor(this, R.color.app_tab_color_on_selected);
        int colorUnselected = Color.WHITE;

        if (currentSelected != null) {
            TextView tabUnselected = tabs.get(currentSelected);
            Drawable drawableUnselected = tabUnselected.getCompoundDrawables()[1];
            drawableUnselected.clearColorFilter();
            tabUnselected.setTextColor(colorUnselected);
        }

        currentSelected = index;

        Drawable drawableSelected = tv.getCompoundDrawables()[1];       // index为1是top图片
        drawableSelected.setColorFilter(colorSelected, PorterDuff.Mode.SRC_ATOP);
        tv.setTextColor(colorSelected);

        getSupportFragmentManager().beginTransaction().replace(R.id.content_view, fragments.get(index)).commit();

        LogHelper.toast(this, tv.getText());

    }

    @OnClick(R.id.btn_add_record)
    public void onAddRecord() {
        LogHelper.toast(this, "添加健康记录");
    }

    @Override
    int getContentViewId() {
        return R.id.content_view;
    }
}
