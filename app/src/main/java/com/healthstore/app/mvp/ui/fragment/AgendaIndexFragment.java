package com.healthstore.app.mvp.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.healthstore.app.R;
import com.healthstore.app.di.component.AppComponent;
import com.healthstore.app.di.component.DaggerAgendaComponent;
import com.healthstore.app.di.module.AgendaModule;
import com.healthstore.app.mvp.contract.AgendaContract;
import com.healthstore.app.mvp.presenter.AgendaPresenter;
import com.healthstore.app.utils.DateUtils;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUIProgressBar;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.popup.QMUIBasePopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

import java.util.Date;

import butterknife.BindView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class AgendaIndexFragment extends AppFragment<AgendaPresenter> implements AgendaContract.View {

    @BindView(R.id.agenda_bg)
    ImageView agendaBackground;
    @BindView(R.id.top_bar)
    QMUITopBarLayout topBar;
    @BindView(R.id.progress_bar)
    QMUIProgressBar progressBar;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_weekday)
    TextView tvWeekDay;

    @Override
    int layoutResId() {
        return R.layout.fragment_agenda_index;
    }

    @Override
    void setUpComponent(AppComponent appComponent) {
        DaggerAgendaComponent.builder()
                .appComponent(appComponent)
                .agendaModule(new AgendaModule(this))
                .build().inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        topBar.setTitle("日程");
        topBar.addRightImageButton(android.R.drawable.ic_menu_add, R.id.btn_agenda_index_add).setOnClickListener(v -> {
            View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.layout_agenda_index_popup, null);

            QMUIPopup popup = new QMUIPopup(getActivity(), QMUIPopup.DIRECTION_BOTTOM){
                @Override public void setContentView(View root) {
                    super.setContentView(root);
                    mArrowUp.setColorFilter(ContextCompat.getColor(getContext(), R.color.app_color_primary));
                }
            };
            popup.setPositionOffsetYWhenBottom(QMUIDisplayHelper.dpToPx(15));
            popup.setContentView(inflate);
            popup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
            popup.show(v);
        });

        progressBar.setQMUIProgressBarTextGenerator((progressBar, value, maxValue) -> (value * 100 / maxValue) + "%");
        progressBar.setProgress(90);
        tvDate.setText(DateUtils.formatDate(new Date(), DateUtils.sdfDateCn));
        tvWeekDay.setText(DateUtils.getWeekDay(new Date()).getDescCn());

        mAppManager.getMainUser().observe(this, user -> mImageLoader.loadPicture(getContext(), user.getAgendaBackgroundImageUrl(), agendaBackground));
    }

}
