package com.healthstore.app.mvp.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.healthstore.app.AppManager;
import com.healthstore.app.R;
import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.model.entity.User;
import com.healthstore.app.mvp.ui.fragment.UserFeedbackFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

@FragmentScope
public class UserFuncIndexAdapter extends RecyclerView.Adapter<UserFuncIndexAdapter.ViewHolder> {

    static final int FUNC_COUNT = 6;

    @Inject Context mContext;
    @Inject AppManager mAppManager;

    @Inject
    public UserFuncIndexAdapter(){
    }

    public void setUpUser(User user){
        if (!user.isVip()) {
            mVipInfoHolder.iv.setColorFilter(Color.GRAY);
            mVipInfoHolder.tv.setTextColor(Color.GRAY);
            mVipInfoHolder.itemView.setOnClickListener(v -> {
                mAppManager.showToastUtils("请先成为会员");
            });
        } else {
            mVipInfoHolder.iv.clearColorFilter();
            mVipInfoHolder.tv.setTextColor(Color.BLACK);
            mVipInfoHolder.itemView.setOnClickListener(v -> {
                // todo: goto VipFragment
            });
        }
    }

    private ViewHolder mVipCardHolder;
    private ViewHolder mVipInfoHolder;
    private ViewHolder mMallHolder;
    private ViewHolder mDeviceHolder;
    private ViewHolder mFeedbackHolder;
    private ViewHolder mSettingHolder;

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.me_item_layout, null);
        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (position) {
            case 0:
                mVipCardHolder = holder;
                mVipCardHolder.fragment = new Fragment();
                mVipCardHolder.tv.setText("会员卡");
                mVipCardHolder.iv.setImageResource(R.mipmap.vip_card);
                mVipCardHolder.itemView.setOnClickListener(v -> {
                    // todo: goto VipCardFragment
                });
                break;
            case 1:
                mVipInfoHolder = holder;
                mVipInfoHolder.fragment = new Fragment();
                mVipInfoHolder.tv.setText("会籍资料");
                mVipInfoHolder.iv.setImageResource(R.mipmap.vip);
                break;
            case 2:
                mMallHolder = holder;
                mMallHolder.fragment = new Fragment();
                mMallHolder.tv.setText("商城");
                mMallHolder.iv.setImageResource(R.mipmap.mall);
                mMallHolder.itemView.setOnClickListener(v -> {
                    mAppManager.showToastUtils("敬请期待");
                });
                break;
            case 3:
                mDeviceHolder = holder;
                mDeviceHolder.fragment = new Fragment();
                mDeviceHolder.tv.setText("我的设备");
                mDeviceHolder.iv.setImageResource(R.mipmap.my_device);
                mDeviceHolder.itemView.setOnClickListener(v -> {
                    // todo: goto SettingsFragment
                });
                break;
            case 4:
                mFeedbackHolder = holder;
                mFeedbackHolder.fragment = new UserFeedbackFragment();
                mFeedbackHolder.tv.setText("反馈");
                mFeedbackHolder.iv.setImageResource(R.mipmap.feedback);
                mFeedbackHolder.itemView.setOnClickListener(v -> {
                    // todo: goto FeedbackFragment
                });
                break;
            case 5:
                mSettingHolder = holder;
                mSettingHolder.fragment = new Fragment();
                mSettingHolder.tv.setText("设置");
                mSettingHolder.iv.setImageResource(R.mipmap.settings);
                mSettingHolder.itemView.setOnClickListener(v -> {
                    // todo: goto SettingsFragment
                });
                break;

        }
    }

    @Override public int getItemCount() {
        return FUNC_COUNT;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_text) TextView tv;
        @BindView(R.id.item_icon) ImageView iv;

        Fragment fragment;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void disable(){

        }

        void enable(){

        }
    }

}
