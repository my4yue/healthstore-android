package com.healthstore.app.presenter;

import com.healthstore.app.mvp.presenter.UserPresenterImpl;

import org.junit.Test;

public class UserPresenterTest {

    @Test
    public void testGetUser() throws Exception {
        new UserPresenterImpl().getUser();
    }
}