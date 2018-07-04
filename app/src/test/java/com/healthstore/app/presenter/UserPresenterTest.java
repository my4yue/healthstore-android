package com.healthstore.app.presenter;

import com.healthstore.app.presenter.impl.UserPresenterImpl;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserPresenterTest {

    @Test
    public void testGetUser() throws Exception {
        new UserPresenterImpl().getUser();
    }
}