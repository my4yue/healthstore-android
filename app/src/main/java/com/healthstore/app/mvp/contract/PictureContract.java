package com.healthstore.app.mvp.contract;

import com.healthstore.app.mvp.IModel;
import com.healthstore.app.mvp.IView;
import com.healthstore.app.mvp.model.entity.Picture;

import java.util.List;

public interface PictureContract {

    interface View extends IView {
        void onLoadDone(List<Picture> pictures);
    }
    interface Model extends IModel {}

}
