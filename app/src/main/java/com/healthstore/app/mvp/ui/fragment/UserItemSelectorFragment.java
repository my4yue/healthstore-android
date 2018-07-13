package com.healthstore.app.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.healthstore.app.R;
import com.healthstore.app.di.component.AppComponent;
import com.healthstore.app.di.component.DaggerUserComponent;
import com.healthstore.app.di.module.UserModule;
import com.healthstore.app.mvp.contract.UserContract;
import com.healthstore.app.mvp.model.cache.ItemCache;
import com.healthstore.app.mvp.model.entity.Item;
import com.healthstore.app.mvp.model.entity.User;
import com.healthstore.app.mvp.presenter.UserPresenter;
import com.healthstore.app.mvp.ui.adapter.UserItemSelectorAdapter;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import butterknife.BindView;

public class UserItemSelectorFragment extends AppFragment<UserPresenter> implements UserContract.View {

    @BindView(R.id.top_bar)
    QMUITopBarLayout topBar;
    @BindView(R.id.item_selector)
    RecyclerView itemSelectors;

    @Override
    int layoutResId() {
        return R.layout.fragment_user_item_selector;
    }

    @Inject
    ItemCache itemCache;
    @Inject
    UserItemSelectorAdapter userItemSelectorAdapter;

    @Override
    void setUpComponent(AppComponent appComponent) {
        DaggerUserComponent.builder()
                .appComponent(appComponent)
                .userModule(new UserModule(this, getActivity()))
                .build()
                .inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        topBar.setTitle("项目设置");
        topBar.addLeftBackImageButton().setOnClickListener(v -> mActivityManager.popupFragment());
        topBar.addRightTextButton("保存", R.id.btn_save_user_item)
                .setOnClickListener(v -> {
                    mPresenter.updateMainUser(new User() {
                        @Override
                        public List<Item> getAttentionItems() {
                            return userItemSelectorAdapter.getSelectedItemId().stream().map(itemId -> itemCache.getItemById(itemId)).collect(Collectors.toList());
                        }
                    });
                });

        itemSelectors.setAdapter(userItemSelectorAdapter);
        itemSelectors.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        itemSelectors.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mAppManager.getMainUser().observe(this, user -> {
            userItemSelectorAdapter.setSelectedItemId(new HashSet<>(user.getAttentionItems().stream().map(item -> item.getItemId()).collect(Collectors.toList())));
            userItemSelectorAdapter.notifyDataSetChanged();
        });
    }
}
