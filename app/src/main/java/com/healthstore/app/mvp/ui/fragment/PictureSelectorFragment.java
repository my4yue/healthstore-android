package com.healthstore.app.mvp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.healthstore.app.R;
import com.healthstore.app.di.component.AppComponent;
import com.healthstore.app.di.component.DaggerPictureComponent;
import com.healthstore.app.di.module.PictureModule;
import com.healthstore.app.mvp.contract.PictureContract;
import com.healthstore.app.mvp.model.entity.Picture;
import com.healthstore.app.mvp.presenter.PicturePresenter;
import com.healthstore.app.mvp.ui.adapter.PictureSelectorAdapter;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class PictureSelectorFragment extends AppFragment<PicturePresenter> implements PictureContract.View {

    @BindView(R.id.picture_view) RecyclerView recyclerView;
    @BindView(R.id.top_bar) QMUITopBarLayout topBar;

    @Inject PictureSelectorAdapter adapter;
    @Inject Context context;

    @Override
    int layoutResId() {
        return R.layout.fragment_pic_selector;
    }

    @Override
    void setUpComponent(AppComponent appComponent) {
        DaggerPictureComponent.builder()
                .appComponent(appComponent)
                .pictureModule(new PictureModule(this, getActivity()))
                .build()
                .inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        topBar.setTitle("惠尔仕相册");
        topBar.addLeftBackImageButton().setOnClickListener(v -> mActivityManager.popupFragment());

        recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL));

        mPresenter.loadPicture();
    }

    @Override
    public void onLoadDone(List<Picture> pictures) {
        adapter.getPictures().addAll(pictures);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
