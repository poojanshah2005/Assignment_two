package com.poojanshah.assignment_two.Service;

/**
 * Created by Poojan on 16/07/2017.
 */

public interface IUserService {
    public boolean logIn(String email,String password);
    public boolean register(String email,String password, String confirmPassword);
}
