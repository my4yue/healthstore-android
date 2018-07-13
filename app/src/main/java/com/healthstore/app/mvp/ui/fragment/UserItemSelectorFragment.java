package com.healthstore.app.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class UserItemSelectorFragment extends AppFragment<UserPresenter> implements UserContract.View {

    @BindView(R.id.top_bar) QMUITopBarLayout topBar;
    @BindView(R.id.item_seletor) QMUIGroupListView itemSelectors;

    @Override int layoutResId() {
        return R.layout.fragment_user_item_selector;
    }

    @Inject ItemCache itemCache;

    @Override void setUpComponent(AppComponent appComponent) {
        DaggerUserComponent.builder()
                .appComponent(appComponent)
                .userModule(new UserModule(this, getActivity()))
                .build()
                .inject(this);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        topBar.setTitle("项目设置");
        topBar.addLeftBackImageButton().setOnClickListener(v->mActivityManager.popupFragment());
        topBar.addRightTextButton("保存", R.id.btn_save_user_item).setOnClickListener(v-> {});

        itemCache.getItemList().forEach(item-> System.out.println(item.getName()));

        QMUIGroupListView.Section section = QMUIGroupListView.newSection(getContext());
        for (Item item : itemCache.getItemList()) {
            QMUICommonListItemView itemView = itemSelectors.createItemView(item.getName());
            itemView.setTag(item);
            section.addItemView(itemView, v -> { });
        }

        section.addTo(itemSelectors);
    }
}
