package com.healthstore.app.mvp.ui.activity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.healthstore.app.R;
import com.healthstore.app.di.component.AppComponent;
import com.healthstore.app.di.component.DaggerIndexComponent;
import com.healthstore.app.mvp.IPresenter;
import com.healthstore.app.mvp.ui.fragment.UserIndexFragment;
import com.healthstore.app.utils.LogUtils;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class IndexActivity extends AppActivity<IPresenter.Empty> {

    @BindView(R.id.content_view) View contentView;

    @BindView(R.id.tab_agenda) TextView tabAgenda;
    @BindView(R.id.tab_expert) TextView tabExpert;
    @BindView(R.id.tab_health) TextView tabHealth;
    @BindView(R.id.tab_me) TextView tabMe;

    @BindView(R.id.btn_add_record) QMUIAlphaImageButton btnAddRecord;

    List<Fragment> fragments = new ArrayList<>();
    List<TextView> tabs = new ArrayList<>();

    Integer currentSelected = null;

    private static final String TAG = IndexActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");

//        getSupportFragmentManager().beginTransaction().replace(R.id.content_view, new UserIndexFragment()).commit();

        tabs = new ArrayList<>();
        tabs.add(tabAgenda);
        tabs.add(tabExpert);
        tabs.add(tabHealth);
        tabs.add(tabMe);

        fragments = new ArrayList<>();
        fragments.add(new Fragment());
        fragments.add(new Fragment());
        fragments.add(new Fragment());
        fragments.add(new UserIndexFragment());

        onSelected(tabAgenda);

    }

    @OnClick({R.id.tab_agenda, R.id.tab_expert, R.id.tab_health, R.id.tab_me})
    public void onSelected(TextView tv) {
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

        LogUtils.toast(this, tv.getText());

    }

    @OnClick(R.id.btn_add_record)
    public void onAddRecord() {
        LogUtils.toast(this, "添加健康记录");
    }

    @Override
    int contentViewId() {
        return R.id.content_view;
    }

    @Override
    int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    void setupActivityComponent(AppComponent appComponent) {
        DaggerIndexComponent.builder().appComponent(appComponent).build().inject(this);
    }
}
