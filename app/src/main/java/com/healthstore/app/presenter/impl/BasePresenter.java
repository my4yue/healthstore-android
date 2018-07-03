package com.healthstore.app.presenter.impl;

import com.healthstore.app.injection.components.DaggerApiComponent;

public class BasePresenter {

    public BasePresenter(){
        DaggerApiComponent.create().inject(this);
    }

}
