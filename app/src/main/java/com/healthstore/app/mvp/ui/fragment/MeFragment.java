package com.healthstore.app.mvp.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.healthstore.app.R;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment {

    @BindView(R.id.recycler) RecyclerView recyclerView;
    @BindView(R.id.icon) QMUIRadiusImageView iconView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, null);
        ButterKnife.bind(this, view);

        iconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentController().startFragment(new MeUserFragment());
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(new MeRecyclerViewAdapter());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    private class MeRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final String[] arrayTitle = new String[]{"会员卡", "会籍资料", "商城", "我的设备", "反馈", "设置"};
        private final int[] arrayIcons = new int[]{R.mipmap.vip_card, R.mipmap.vip, R.mipmap.mall,
                R.mipmap.my_device, R.mipmap.feedback, R.mipmap.settings};
        private final Fragment[] arrayFragment = new Fragment[]{null, null, null, null, new FeedbackFragment(), null};

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.me_item_layout, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
            holder.iv.setImageResource(arrayIcons[position]);
            holder.tv.setText(arrayTitle[position]);
            final Fragment targetFragment = arrayFragment[position];
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), holder.tv.getText(), Toast.LENGTH_SHORT).show();
                    if (targetFragment != null)
                        getFragmentController().startFragment(targetFragment);
                }
            });
        }

        @Override
        public int getItemCount() {
            return arrayTitle.length;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_text) TextView tv;
        @BindView(R.id.item_icon) ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
