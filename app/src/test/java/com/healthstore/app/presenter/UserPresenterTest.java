package com.healthstore.app.presenter;

import org.junit.Test;

public class UserPresenterTest {

    @Test
    public void testGetUser() throws Exception {
        new UserPresenterImpl().getUser();
    }
}