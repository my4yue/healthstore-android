package com.healthstore.app.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
//import com.bumptech.glide.request.RequestOptions;
import com.healthstore.app.R;
import com.healthstore.app.di.component.AppComponent;
import com.healthstore.app.di.component.DaggerUserComponent;
import com.healthstore.app.di.module.UserModule;
import com.healthstore.app.mvp.contract.UserContract;
import com.healthstore.app.mvp.model.entity.Gender;
import com.healthstore.app.mvp.model.entity.User;
import com.healthstore.app.mvp.presenter.UserPresenter;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;

public class UserDetailsFragment extends AppFragment<UserPresenter> implements UserContract.View {

    @BindView(R.id.top_bar) QMUITopBarLayout topBar;
    @BindView(R.id.icon) QMUIRadiusImageView iconView;
    @BindView(R.id.me_list) QMUIGroupListView meListView;

    @Override int layoutResId() {
        return R.layout.fragment_user_details;
    }

    @Override void setUpComponent(AppComponent appComponent) {
        DaggerUserComponent.builder()
                .userModule(new UserModule(this, getActivity()))
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        QMUICommonListItemView ivName = meListView.createItemView("昵称");
        QMUICommonListItemView ivSex = meListView.createItemView("性别");
        QMUICommonListItemView ivDistrict = meListView.createItemView("地区");

        topBar.setTitle("个人信息");
        topBar.addLeftBackImageButton().setOnClickListener(v -> {
            mActivityManager.popupFragment();
        });
        topBar.addRightTextButton(R.string.save, R.id.btn_save_user)
                .setOnClickListener(v -> {
                    User user = new User();
                    user.setGender(Gender.from(ivSex.getDetailText().toString()));
                    user.setUserName(ivName.getDetailText().toString());
                    user.setDistrict(ivDistrict.getDetailText().toString());

                    mPresenter.updateMainUser(user);
                });


        QMUIGroupListView.newSection(getContext())
                .addItemView(ivName, v -> {
                    final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(getActivity());
                    builder.setTitle("修改昵称")
                            .setPlaceholder(ivName.getDetailText().toString())
                            .setInputType(InputType.TYPE_CLASS_TEXT)
                            .addAction("取消", (dialog, index) -> dialog.dismiss())
                            .addAction("确定", (dialog, index) -> {
                                CharSequence text = builder.getEditText().getText();
                                if (text != null && text.length() > 0) {
                                    ivName.setDetailText(text);
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(getActivity(), "请填入昵称", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .create().show();
                })
                .addItemView(ivSex, v->{
                    new QMUIBottomSheet.BottomListSheetBuilder(getContext(), true)
                            .setCheckedIndex(Gender.from(ivSex.getText().toString()).order())
                            .addItem(Gender.M.desc())
                            .addItem(Gender.F.desc())
                            .setOnSheetItemClickListener((dialog, itemView, position, tag) -> {
                                ivSex.setDetailText(tag);
                                dialog.dismiss();
                            })
                            .build()
                            .show();
                })
                .addItemView(ivDistrict, null)
                .addTo(meListView);

        mAppManager.getMainUser().observe(this, user->{
            ivName.setDetailText(user.getUserName());
            ivSex.setDetailText(user.getGender().desc());
            ivDistrict.setDetailText(user.getDistrict());
            mImageLoader.load(getContext(), user.getIconUrl(), iconView);
        });



    }

}
