package com.healthstore.app.mvp.ui.fragment;

import android.support.v4.app.Fragment;

import com.healthstore.app.mvp.ui.activity.HealthAppActivity;

public class BaseFragment extends Fragment{

    protected HealthAppActivity getFragmentController() {
        return (HealthAppActivity) getActivity();
    }

}
