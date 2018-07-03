package com.healthstore.app.view.fragment;

import android.support.v4.app.Fragment;

import com.healthstore.app.view.activity.HealthAppActivity;

public class BaseFragment extends Fragment{

    protected HealthAppActivity getFragmentController() {
        return (HealthAppActivity) getActivity();
    }

}
