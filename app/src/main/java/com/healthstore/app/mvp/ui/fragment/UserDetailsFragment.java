package com.healthstore.app.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.healthstore.app.R;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDetailsFragment extends Fragment {

    @BindView(R.id.top_bar) QMUITopBarLayout topBar;
    @BindView(R.id.icon) QMUIRadiusImageView icon;
    @BindView(R.id.me_list) QMUIGroupListView meListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_details, null);
        ButterKnife.bind(this, view);

        topBar.setTitle("个人信息");
        topBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getFragmentController().popBackStack();
            }
        });
        topBar.addRightTextButton(R.string.save, R.id.btn_save_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "正在保存", Toast.LENGTH_SHORT).show();
            }
        });

        final QMUICommonListItemView ivName = meListView.createItemView("昵称");
        ivName.setDetailText("风之幽水");
        QMUICommonListItemView ivSex = meListView.createItemView("性别");
        ivSex.setDetailText("男");

        QMUICommonListItemView ivDistrict = meListView.createItemView("地区");
        ivDistrict.setDetailText("天津市");

        QMUIGroupListView.newSection(getContext())
                .addItemView(ivName, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(getActivity());
                        builder.setTitle("修改昵称")
                                .setPlaceholder("在此输入您的昵称")
                                .setInputType(InputType.TYPE_CLASS_TEXT)
                                .addAction("取消", new QMUIDialogAction.ActionListener() {
                                    @Override
                                    public void onClick(QMUIDialog dialog, int index) {
                                        dialog.dismiss();
                                    }
                                })
                                .addAction("确定", new QMUIDialogAction.ActionListener() {
                                    @Override
                                    public void onClick(QMUIDialog dialog, int index) {
                                        CharSequence text = builder.getEditText().getText();
                                        if (text != null && text.length() > 0) {
                                            ivName.setDetailText(text);
                                            dialog.dismiss();
                                        } else {
                                            Toast.makeText(getActivity(), "请填入昵称", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                })
                                .create().show();
                    }
                })
                .addItemView(ivSex, null)
                .addItemView(ivDistrict, null)
                .addTo(meListView);

        return view;
    }
}
