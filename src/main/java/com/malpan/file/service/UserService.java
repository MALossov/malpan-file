package com.malpan.file.service;

import com.malpan.file.common.RestResult;
import com.malpan.file.model.User;

public interface UserService {
    RestResult<String> registerUser(User user);
    RestResult<User> login(User user);
    User getUserByToken(String token);
}