package com.healthstore.app.mvp.ui.fragment;

import android.support.v4.app.Fragment;

import com.healthstore.app.mvp.ui.activity.AppActivity;

public class BaseFragment extends Fragment{

    protected AppActivity getFragmentController() {
        return (AppActivity) getActivity();
    }

}
