package com.healthstore.app.mvp.contract;

import com.healthstore.app.mvp.IModel;
import com.healthstore.app.mvp.IView;

public interface FeedbackContract {

    interface View extends IView {
        void onSubmitSuccess();

        void onSubmitFailed();
    }

    interface Model extends IModel {
        void submitFeedback(String text);
    }

}
