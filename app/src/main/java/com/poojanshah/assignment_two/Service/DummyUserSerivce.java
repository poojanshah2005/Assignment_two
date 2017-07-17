package com.poojanshah.assignment_two.Service;

/**
 * Created by Poojan on 16/07/2017.
 */

/**
 * Dummy User Serivces methods
 */
public class DummyUserSerivce implements IUserService {
    @Override
    public boolean logIn(String email, String password) {
        return true;
    }

    @Override
    public boolean register(String email, String password, String confirmPassword) {
        return true;
    }
}
