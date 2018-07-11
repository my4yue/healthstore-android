package com.healthstore.app.mvp.ui.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.healthstore.app.ActivityManager;
import com.healthstore.app.AppManager;
import com.healthstore.app.R;
import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.model.entity.User;
import com.healthstore.app.mvp.ui.activity.AppActivity;
import com.healthstore.app.mvp.ui.fragment.AppFragment;
import com.healthstore.app.mvp.ui.fragment.UserFeedbackFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

@FragmentScope
public class UserFuncIndexAdapter extends RecyclerView.Adapter<UserFuncIndexAdapter.ViewHolder> {

    static final int FUNC_COUNT = 6;

    @Inject AppManager mAppManager;
    @Inject ActivityManager mActivityManager;
    @Inject AppActivity mAppActivity;
    @Inject AppFragment mAppFragment;

    @Inject
    public UserFuncIndexAdapter(){
    }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mAppActivity).inflate(R.layout.me_item_layout, null);
        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user  = mAppManager.getMainUser();
        switch (position) {
            case 0:
                holder.fragment = new Fragment();
                holder.tv.setText("会员卡");
                holder.iv.setImageResource(R.mipmap.vip_card);
                holder.itemView.setOnClickListener(v -> {
                    // todo: goto VipCardFragment
                });
                break;
            case 1:
                holder.fragment = new Fragment();
                holder.tv.setText("会籍资料");
                holder.iv.setImageResource(R.mipmap.vip);
                if (!user.isVip()) {
                    holder.iv.setColorFilter(Color.GRAY);
                    holder.tv.setTextColor(Color.GRAY);
                    holder.itemView.setOnClickListener(v -> {
                        mAppManager.showToast("请先成为会员");
                    });
                } else {
                    holder.iv.clearColorFilter();
                    holder.tv.setTextColor(Color.BLACK);
                    holder.itemView.setOnClickListener(v -> {
                        // todo: goto VipFragment
                    });
                }
                break;
            case 2:
                holder.fragment = new Fragment();
                holder.tv.setText("商城");
                holder.iv.setImageResource(R.mipmap.mall);
                holder.itemView.setOnClickListener(v -> {
                    mAppManager.showToast("敬请期待");
                });
                break;
            case 3:
                holder.fragment = new Fragment();
                holder.tv.setText("我的设备");
                holder.iv.setImageResource(R.mipmap.my_device);
                holder.itemView.setOnClickListener(v -> {
                    // todo: goto SettingsFragment
                });
                break;
            case 4:
                holder.fragment = new UserFeedbackFragment();
                holder.tv.setText("反馈");
                holder.iv.setImageResource(R.mipmap.feedback);
                holder.itemView.setOnClickListener(v -> {
                    mActivityManager.replaceFragment(mAppFragment.getContainerId(), new UserFeedbackFragment());
                });
                break;
            case 5:
                holder.fragment = new Fragment();
                holder.tv.setText("设置");
                holder.iv.setImageResource(R.mipmap.settings);
                holder.itemView.setOnClickListener(v -> {
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
