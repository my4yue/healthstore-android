package com.healthstore.app.mvp.ui.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.healthstore.app.R;
import com.healthstore.app.di.component.AppComponent;
import com.healthstore.app.di.component.DaggerAgendaComponent;
import com.healthstore.app.di.module.AgendaModule;
import com.healthstore.app.mvp.contract.AgendaContract;
import com.healthstore.app.mvp.model.entity.User;
import com.healthstore.app.mvp.presenter.AgendaPresenter;
import com.qmuiteam.qmui.widget.QMUIProgressBar;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import javax.inject.Inject;

import butterknife.BindView;

public class AgendaIndexFragment extends AppFragment<AgendaPresenter> implements AgendaContract.View {

    @BindView(R.id.agenda_bg) ImageView agendaBackground;
    @BindView(R.id.top_bar) QMUITopBarLayout topBar;
    @BindView(R.id.progress_bar) QMUIProgressBar progressBar;

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
        topBar.addRightTextButton("+", 0);

        progressBar.setQMUIProgressBarTextGenerator(new QMUIProgressBar.QMUIProgressBarTextGenerator() {
            @Override public String generateText(QMUIProgressBar progressBar, int value, int maxValue) {
                return (value * 100 / maxValue) + "%";
            }
        });
        progressBar.setProgress(90);

        mAppManager.getMainUser().observe(this, user -> mImageLoader.loadPicture(getContext(), user.getAgendaBackgroundImageUrl(), agendaBackground));
    }
}
