package com.healthstore.app.mvp.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
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
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

import java.util.Date;

import butterknife.BindView;

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
        Drawable drawable = ContextCompat.getDrawable(getActivity(), android.R.drawable.ic_input_add);
        topBar.addRightImageButton(android.R.drawable.ic_input_add, R.id.btn_agenda_index_add).setOnClickListener(v -> {
            QMUIPopup popup = new QMUIPopup(getActivity(), QMUIPopup.DIRECTION_BOTTOM);
//            popup.setPopupLeftRightMinMargin(-20);
            popup.setPositionOffsetX(100);
//            popup.setPositionOffsetYWhenBottom(100);
            TextView textView = new TextView(getContext());
            textView.setLayoutParams(popup.generateLayoutParam(WRAP_CONTENT, WRAP_CONTENT));
//            textView.setLineSpacing(QMUIDisplayHelper.dp2px(getContext(), 4), 1.0f);
            int padding = QMUIDisplayHelper.dp2px(getContext(), 20);
            textView.setPadding(padding, padding, padding, padding);
            textView.setText("添加提醒");
            textView.setTextColor(Color.BLACK);
            popup.setContentView(textView);
            popup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_RIGHT);
            popup.show(v);
        });

        progressBar.setQMUIProgressBarTextGenerator((progressBar, value, maxValue) -> (value * 100 / maxValue) + "%");
        progressBar.setProgress(90);
        tvDate.setText(DateUtils.formatDate(new Date(), DateUtils.sdfDateCn));
        tvWeekDay.setText(DateUtils.getWeekDay(new Date()).getDescCn());

        mAppManager.getMainUser().observe(this, user -> mImageLoader.loadPicture(getContext(), user.getAgendaBackgroundImageUrl(), agendaBackground));
    }
}
