package com.healthstore.app.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.healthstore.app.R;
import com.healthstore.app.mvp.ui.fragment.BaseFragment;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackFragment extends BaseFragment {

    @BindView(R.id.top_bar) QMUITopBarLayout topBar;
    @BindView(R.id.edt_feedback) EditText editFeedback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, null);
        ButterKnife.bind(this, view);

        topBar.setTitle(R.string.feedback_title);
        topBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getFragmentController().popBackStack();
            }
        });

        return view;
    }

    @OnClick(R.id.btn_submit)
    void onClick(View btn) {
        Toast.makeText(getContext(), editFeedback.getText(), Toast.LENGTH_SHORT).show();
    }
}
